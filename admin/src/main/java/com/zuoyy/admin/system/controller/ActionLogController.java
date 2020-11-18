package com.zuoyy.admin.system.controller;

import com.zuoyy.admin.common.BaseController;
import com.zuoyy.admin.system.dto.ActionLogDTO;
import com.zuoyy.admin.system.mapper.ActionLogMapper;
import com.zuoyy.common.constant.DictConst;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.thymeleaf.utility.DictUtil;
import com.zuoyy.modules.system.domain.ActionLog;
import com.zuoyy.modules.system.query.ActionLogQuery;
import com.zuoyy.modules.system.service.ActionLogService;
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

/**
 * @author zuo
 */
@Controller
@RequestMapping("/system/actionLog")
public class ActionLogController extends BaseController {

    @Autowired
    private ActionLogService actionLogService;

    @GetMapping("/index.shtml")
    @RequiresPermissions("system:actionLog:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "/system/actionLog/index";
    }


    @GetMapping("/listPage")
    @RequiresPermissions("system:actionLog:index")
    public String listPage(Model model) {
        model.addAttribute("button", getPageButton("system:actionLog:index"));
        return "/system/actionLog/list";
    }


    @ResponseBody
    @PostMapping("/getListPage")
    public JsonResponse getListPage(ActionLogQuery query) {
        List<ActionLogDTO> list = new ArrayList<>();
        Page<ActionLog> page = actionLogService.getPageList(query);
        List<ActionLog> actionLogs = page.getContent();
        if(!actionLogs.isEmpty()){
            for(ActionLog log : actionLogs){
                ActionLogDTO dto = ActionLogMapper.INSTANCE.domainToDto(log);
                dto.setLogType(DictUtil.keyValue(DictConst.LOG_TYPE,log.getType().toString()));
                list.add(dto);
            }
        }
        return JsonResponse.success(setQueryResult(query.getDraw(), (int) page.getTotalElements(),list));
    }

    @ResponseBody
    @RequestMapping("/status/delete")
    @RequiresPermissions("system:actionLog:delete")
    public JsonResponse delete(
            @RequestParam(value = "ids", required = false) String id){
        if (!StringUtils.isEmpty(id)){
            actionLogService.deleteById(id);
        }else {
            actionLogService.deleteAll();

        }
        return JsonResponse.success();
    }

    @GetMapping("/detail/{id}")
    @RequiresPermissions("system:actionLog:detail")
    public String toDetail(@PathVariable("id") ActionLog actionLog, Model model){
        model.addAttribute("actionLog",actionLog);
        return "/system/actionLog/detail";
    }


}