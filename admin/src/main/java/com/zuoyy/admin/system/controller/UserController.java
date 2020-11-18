package com.zuoyy.admin.system.controller;

import com.zuoyy.admin.common.BaseController;
import com.zuoyy.admin.system.dto.UserDTO;
import com.zuoyy.admin.system.mapper.UserMapper;
import com.zuoyy.common.constant.AdminConst;
import com.zuoyy.common.enums.LocaleTypeEnum;
import com.zuoyy.common.enums.ResultEnum;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.exception.ResultException;
import com.zuoyy.common.utils.EntityBeanUtil;
import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.actionLog.action.StatusAction;
import com.zuoyy.component.actionLog.action.UserAction;
import com.zuoyy.component.actionLog.annotation.ActionLog;
import com.zuoyy.component.actionLog.annotation.EntityParam;
import com.zuoyy.component.shiro.ShiroUtil;
import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.system.query.RoleQuery;
import com.zuoyy.modules.system.query.UserQuery;
import com.zuoyy.modules.system.service.RoleService;
import com.zuoyy.modules.system.service.UserService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/index.shtml")
    @RequiresPermissions("system:user:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "/system/user/index";
    }


    @GetMapping("/listPage")
    @RequiresPermissions("system:user:index")
    public String listPage(Model model) {
        model.addAttribute("button", getPageButton("system:user:index"));
        return "/system/user/list";
    }

    @ResponseBody
    @RequestMapping(value = "/getListPage", method = RequestMethod.POST)
    public JsonResponse getListPage(UserQuery query){
        List<UserDTO> list = new ArrayList<UserDTO>();
        Page<User> page = userService.getPageList(query);
        List<User> users = page.getContent();
        if(users!=null&&users.size()>0){
            for(User user:users){
                UserDTO dto = UserMapper.INSTANCE.domainToDto(user);
                Set<Role> roles = user.getRoles();
                String roleName="";
                if(roles!=null&&roles.size()>0){
                    for(Role role : roles){
                        roleName +=role.getTitle()+",";
                    }
                    roleName = roleName.substring(0,roleName.length()-1);
                }
                dto.setRoleName(roleName);
                list.add(dto);
            }
        }
        return JsonResponse.success(setQueryResult(query.getDraw(), (int) page.getTotalElements(),list));
    }

    @GetMapping("/add")
    @RequiresPermissions("system:user:add")
    public String toAdd(Model model) {
        model.addAttribute("roleList",roleService.findByQuery(new RoleQuery()));
        return "/system/user/add";
    }


    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:user:edit")
    public String toEdit(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roleList",roleService.findByQuery(new RoleQuery()));
        return "/system/user/add";
    }

    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions({"system:user:add", "system:user:edit"})
    @ActionLog(key = UserAction.USER_SAVE, action = UserAction.class)
    public JsonResponse save(@EntityParam @RequestBody User user) {
        if (StringUtils.isEmpty(user.getId())) {
            String password="1990320";
            // 对密码进行加密
            String salt = ShiroUtil.getRandomSalt();
            String encrypt = ShiroUtil.encrypt(password, salt);
            user.setPassword(encrypt);
            user.setSalt(salt);
            user.setLanguage(LocaleTypeEnum.zh_CN.code);
        }
        // 判断用户名是否重复
        if (userService.repeatByUsername(user)) {
            throw new ResultException(ResultEnum.USER_EXIST);
        }
        // 复制保留无需修改的数据
        if (!StringUtils.isEmpty(user.getId())) {
            // 不允许操作超级管理员数据
            if (user.getId().equals(AdminConst.ADMIN_ID) &&
                    !ShiroUtil.getSubject().getId().equals(AdminConst.ADMIN_ID)) {
                throw new ResultException(ResultEnum.NO_ADMIN_AUTH);
            }

            User beUser = userService.findById(user.getId());
            String[] fields = {"password", "salt", "picture", "roles"};
            EntityBeanUtil.copyProperties(beUser, user, fields);
        }
        // 保存数据
        userService.save(user);
        return JsonResponse.success();
    }



    @GetMapping("/detail/{id}")
    @RequiresPermissions("system:user:detail")
    public String toDetail(@PathVariable("id") User user, Model model){
        model.addAttribute("user",user);
        return "/system/user/detail";
    }


    @ResponseBody
    @RequestMapping("/status/{param}")
    @RequiresPermissions({"system:user:status","system:user:delete"})
    @ActionLog(name = "用户状态", action = StatusAction.class)
    public JsonResponse updateStatus(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<String> ids) {
        // 不能修改超级管理员状态
        if (ids.contains(AdminConst.ADMIN_ID)) {
            throw new ResultException(ResultEnum.NO_ADMIN_STATUS);
        }
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (userService.updateStatus(statusEnum, ids)) {
            return JsonResponse.success(statusEnum.message + "成功");
        } else {
            return JsonResponse.error(statusEnum.message + "失败，请重新操作");
        }
    }



}
