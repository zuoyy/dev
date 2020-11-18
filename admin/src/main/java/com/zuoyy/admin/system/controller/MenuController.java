package com.zuoyy.admin.system.controller;

import com.zuoyy.admin.common.BaseController;
import com.zuoyy.common.enums.ResultEnum;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.exception.ResultException;
import com.zuoyy.common.utils.EntityBeanUtil;
import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.common.utils.TreeUtil;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.actionLog.action.SaveAction;
import com.zuoyy.component.actionLog.action.StatusAction;
import com.zuoyy.component.actionLog.annotation.ActionLog;
import com.zuoyy.component.actionLog.annotation.EntityParam;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.common.enums.MenuTypeEnum;
import com.zuoyy.modules.system.query.MenuQuery;
import com.zuoyy.modules.system.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {


    @Autowired
    private MenuService menuService;


    @GetMapping("/index.shtml")
    @RequiresPermissions("system:menu:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "/system/menu/index";
    }

    @GetMapping("/listPage")
    @RequiresPermissions("system:menu:index")
    public String listPage(Model model) {
        model.addAttribute("button", getPageButton("system:menu:index"));
        return "/system/menu/list";
    }

    @ResponseBody
    @GetMapping("/treeList")
    @RequiresPermissions("system:menu:index")
    public JsonResponse tree(){
        MenuQuery query = new MenuQuery();
        Sort sort = new Sort(Sort.Direction.ASC,"sort");
        List<Menu> menus = menuService.getByQuery(query,sort);
        try {
            menus = TreeUtil.getTree(menus,"id");
            return JsonResponse.success(menus);
        } catch (Exception e) {
            throw new ResultException(ResultEnum.ERROR);
        }

    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:menu:index")
    public JsonResponse list(){
        MenuQuery query = new MenuQuery();
        Sort sort = new Sort(Sort.Direction.ASC,"sort");
        List<Menu> menus = menuService.getByQuery(query,sort);
        return JsonResponse.success(menus);
    }

    /**
     * 获取排序菜单列表
     */
    @GetMapping("/sortList/{pid}/{notId}")
    @RequiresPermissions({"system:menu:add", "system:menu:edit"})
    @ResponseBody
    public Map<Integer, String> sortList(
            @PathVariable(value = "pid", required = false) String pid,
            @PathVariable(value = "notId", required = false) String notId){
        // 本级排序菜单列表
        notId = notId != null ? notId : "0";
        List<Menu> levelMenu = menuService.getListByPid(pid, notId);
        Map<Integer, String> sortMap = new TreeMap<>();
        for (int i = 1; i <= levelMenu.size(); i++) {
            sortMap.put(i, levelMenu.get(i - 1).getTitle());
        }
        return sortMap;
    }


    @GetMapping("/addPage")
    @RequiresPermissions("system:menu:add")
    public String addPage(Model model,String id) {
        String pid = id;
        model.addAttribute("pid",pid);
        MenuQuery query = new MenuQuery();
        query.setType(MenuTypeEnum.type1.code);
        model.addAttribute("pids",menuService.getByQuery(query));
        List<Menu> sorts = menuService.getListByPid(pid, pid);
        model.addAttribute("sorts",sorts);
        return "/system/menu/add";
    }


    @GetMapping("/editPage")
    @RequiresPermissions("system:menu:edit")
    public String editPage(Model model,String id) {
        Menu menu = menuService.findById(id);
        model.addAttribute("pid",menu.getPid());
        MenuQuery query = new MenuQuery();
        query.setType(MenuTypeEnum.type1.code);
        model.addAttribute("pids",menuService.getByQuery(query));
        model.addAttribute("menu", menu);
        List<Menu> sorts = menuService.getListByPid(menu.getPid(), id);
        model.addAttribute("sorts",sorts);
        return "/system/menu/add";
    }


    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions({"system:menu:add", "system:menu:edit"})
    @ActionLog(name = "菜单管理", message = "菜单：${title}", action = SaveAction.class)
    public JsonResponse save(@EntityParam Menu menu) {
        // 判断权限是否重复
        if (!menu.getPerms().equals("#")&&menuService.repeatByPerms(menu)) {
            throw new ResultException(ResultEnum.MENU_EXIST);
        }

        if(menu.getPid().equals("0")){
            menu.setLevel(1);
            menu.setType(MenuTypeEnum.type1.code);
            menu.setPids("[0]");
        }else{
            // 添加全部上级序号
            Menu pMenu = menuService.findById(menu.getPid());
            menu.setPids(pMenu.getPids() + ",[" + menu.getPid() + "]");
            menu.setLevel(menuService.findById(menu.getPid()).getLevel()+1);
        }
        String notId="0";
        // 复制保留无需修改的数据
        if (!StringUtils.isEmpty(menu.getId())) {
            Menu beMenu = menuService.findById(menu.getId());
            EntityBeanUtil.copyProperties(beMenu, menu, "pids");
            notId = menu.getId();
        }

        // 排序功能
        Integer sort = menu.getSort();
        List<Menu> levelMenu = menuService.getListByPid(menu.getPid(), notId);
        levelMenu.add(sort, menu);
        for (int i = 1; i <= levelMenu.size(); i++) {
            levelMenu.get(i - 1).setSort(i);
        }
        // 保存数据
        menuService.save(levelMenu);
        return JsonResponse.success();
    }

    /**
     * 设置一条或者多条数据的状态
     */
    @ResponseBody
    @RequestMapping("/status/{param}")
    @RequiresPermissions({"system:menu:status","system:menu:delete"})
    @ActionLog(name = "菜单状态", action = StatusAction.class)
    public JsonResponse updateStatus(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<String> ids) {
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (menuService.updateStatus(statusEnum, ids)) {
            return JsonResponse.success(statusEnum.message + "成功");
        } else {
            return JsonResponse.error(statusEnum.message + "失败，请重新操作");
        }
    }


}
