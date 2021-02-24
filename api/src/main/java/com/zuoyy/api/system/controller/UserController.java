package com.zuoyy.api.system.controller;

import com.zuoyy.api.system.mapper.UserMapper;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.jwt.annotation.JwtPermissions;
import com.zuoyy.modules.system.query.UserQuery;
import com.zuoyy.modules.system.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@ApiModel(value="演示类",description="请求参数类" )
public class UserController {


    @Autowired
    private UserService userService;


    @ResponseBody
    @GetMapping(value = "/list")
    @ApiOperation(value = "获取用户信息", notes = "invokePost说明", httpMethod = "GET")
    public JsonResponse list(String id){
        UserQuery query = new UserQuery();
        return JsonResponse.success(UserMapper.INSTANCE.domainToDto(userService.findByQuery(query)));
    }


    @ResponseBody
    @JwtPermissions
    @PostMapping(value = "/list2")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    public JsonResponse list2(HttpServletRequest request, String id){
        UserQuery query = new UserQuery();
        return JsonResponse.success(userService.findByQuery(query));
    }




}
