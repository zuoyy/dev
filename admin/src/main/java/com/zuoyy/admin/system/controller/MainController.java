package com.zuoyy.admin.system.controller;

import com.zuoyy.admin.common.BaseController;
import com.zuoyy.common.constant.AdminConst;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.shiro.ShiroUtil;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.common.enums.MenuTypeEnum;
import com.zuoyy.modules.system.service.MenuService;
import com.zuoyy.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping("/system")
public class MainController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;


    @GetMapping("/index.shtml")
    public String index(Model model,HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "welcome";
    }

    @GetMapping("/druid/index.shtml")
    public String druid(Model model,HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "/system/druid/index";
    }

    @ResponseBody
    @PostMapping("setLanguage")
    public JsonResponse setLanguage(String language) {
        User user = ShiroUtil.getSubject();
        user.setLanguage(language);
        userService.save(user);
        ShiroUtil.resetCookieRememberMe();
        return JsonResponse.success();
    }


    @GetMapping("/my-profile")
    public String myProfile(Model model,String id) {
        model.addAttribute("id",id);
        return "/system/user/myProfile";
    }

    @ResponseBody
    @PostMapping("/tableButton/{menuId}")
    public JsonResponse getTableButton(@PathVariable("menuId") String menuId) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = ShiroUtil.getSubject();
        // 管理员实时更新菜单
        List<Menu> menus;
        if (user.getId()!=null&&user.getId().equals(AdminConst.ADMIN_ID)) {
            menus = menuService.getListBySortOk();
        } else {
            // 其他用户需从相应的角色中获取菜单资源
            menus = new ArrayList<Menu>();
            for(Role role : user.getRoles()){
                for(Menu menu:role.getMenus()){
                    if (menu.getStatus().equals(StatusEnum.OK.code)) {
                        menus.add(menu);
                    }
                }
            }
        }
        List<Menu> tlist = menuService.getListByPidAndType(menuId,MenuTypeEnum.type3.code);
        List<Menu> tableButtons = menus.stream().filter(item -> tlist.contains(item)).collect(toList());

        map.put("tableButtons", tableButtons);
        return JsonResponse.success(tableButtons);
    }





}
