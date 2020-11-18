package com.zuoyy.admin.system.controller;

import com.zuoyy.admin.common.BaseController;
import com.zuoyy.common.constant.AdminConst;
import com.zuoyy.common.enums.ResultEnum;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.exception.ResultException;
import com.zuoyy.common.utils.EntityBeanUtil;
import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.actionLog.action.RoleAction;
import com.zuoyy.component.actionLog.action.StatusAction;
import com.zuoyy.component.actionLog.annotation.ActionLog;
import com.zuoyy.component.actionLog.annotation.EntityParam;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.system.query.RoleQuery;
import com.zuoyy.modules.system.service.MenuService;
import com.zuoyy.modules.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/index.shtml")
    @RequiresPermissions("system:role:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "/system/role/index";
    }

    @GetMapping("/listPage")
    @RequiresPermissions("system:role:index")
    public String listPage(Model model) {
        model.addAttribute("button", getPageButton("system:role:index"));
        return "/system/role/list";
    }

    @ResponseBody
    @PostMapping("/getListPage")
    public JsonResponse getListPage(RoleQuery query,HttpServletResponse response){
        Page<Role> page = roleService.getPageList(query);
        List<Role> roles = page.getContent();
        response.setHeader("Access-Control-Allow-Origin", "*");
        return JsonResponse.success(setQueryResult(query.getDraw(), (int) page.getTotalElements(),roles));
    }

    @GetMapping("/add")
    @RequiresPermissions("system:role:add")
    public String toAdd(){
        return "/system/role/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:role:edit")
    public String toEdit(@PathVariable("id") Role role, Model model){
        model.addAttribute("role", role);
        return "/system/role/add";
    }

    @PostMapping("/save")
    @RequiresPermissions({"system:role:add","system:role:edit"})
    @ResponseBody
    @ActionLog(key = RoleAction.ROLE_SAVE, action = RoleAction.class)
    public JsonResponse save(@EntityParam @RequestBody Role role){
        // 不允许操作超级管理员角色数据
        if (role.getId() !=null && role.getName().equals(AdminConst.ADMIN_ROLE_NAME)){
            throw new ResultException(ResultEnum.NO_ADMINROLE_AUTH);
        }
        // 判断角色编号是否重复
        if (roleService.repeatByName(role)) {
            throw new ResultException(ResultEnum.ROLE_EXIST);
        }
        // 复制保留无需修改的数据
        if(!StringUtils.isEmpty(role.getId())){
            Role beRole = roleService.findById(role.getId());
            String[] fields = {"users", "menus"};
            EntityBeanUtil.copyProperties(beRole, role, fields);
        }
        // 保存数据
        roleService.save(role);
        return JsonResponse.success();
    }

    @GetMapping("/detail/{id}")
    @RequiresPermissions("system:role:detail")
    public String toDetail(@PathVariable("id") Role role, Model model){
        model.addAttribute("role", role);
        return "/system/role/detail";
    }

    @ResponseBody
    @RequestMapping("/status/{param}")
    @RequiresPermissions({"system:role:status","system:role:delete"})
    @ActionLog(name = "角色状态", action = StatusAction.class)
    public JsonResponse status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<String> ids){

        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (roleService.updateStatus(statusEnum, ids)) {
            return JsonResponse.success(statusEnum.message + "成功");
        } else {
            return JsonResponse.error(statusEnum.message + "失败，请重新操作");
        }
    }


    @GetMapping("/auth/{id}")
    @RequiresPermissions("system:role:auth")
    public String toAuth(@PathVariable("id") String id, Model model){
        model.addAttribute("id", id);
        return "/system/role/auth";
    }


    @ResponseBody
    @GetMapping("/authList")
    @RequiresPermissions("system:role:auth")
    public JsonResponse authList(@RequestParam(value = "ids") Role role){
        // 获取指定角色权限资源
        Set<Menu> authMenus = role.getMenus();
        // 获取全部菜单列表
        List<Menu> list = menuService.getListBySortOk();
        // 融合两项数据
        list.forEach(menu -> {
            if(authMenus.contains(menu)){
                menu.setRemark("auth:true");
            }else {
                menu.setRemark("");
            }
        });
        return JsonResponse.success(list);
    }

    @ResponseBody
    @PostMapping("/auth")
    @RequiresPermissions("system:role:auth")
    @ActionLog(key = RoleAction.ROLE_AUTH, action = RoleAction.class)
    public JsonResponse auth(
            @RequestParam(value = "id", required = true) Role role,
            @RequestParam(value = "authId", required = false) HashSet<Menu> menus){
        // 不允许操作超级管理员角色数据
        if (role.getName().equals(AdminConst.ADMIN_ROLE_NAME)){
            throw new ResultException(ResultEnum.NO_ADMINROLE_AUTH);
        }
        // 更新角色菜单
        role.setMenus(menus);
        // 保存数据
        roleService.save(role);
        return JsonResponse.success();
    }


}
