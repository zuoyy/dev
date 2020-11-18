package com.zuoyy.admin.system.controller;

import com.zuoyy.admin.common.BaseController;
import com.zuoyy.admin.system.dto.DictDTO;
import com.zuoyy.admin.system.mapper.DictMapper;
import com.zuoyy.common.constant.DictConst;
import com.zuoyy.common.enums.ResultEnum;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.exception.ResultException;
import com.zuoyy.common.utils.EntityBeanUtil;
import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.component.actionLog.action.SaveAction;
import com.zuoyy.component.actionLog.action.StatusAction;
import com.zuoyy.component.actionLog.annotation.ActionLog;
import com.zuoyy.component.actionLog.annotation.EntityParam;
import com.zuoyy.component.thymeleaf.utility.DictUtil;
import com.zuoyy.modules.system.domain.Dict;
import com.zuoyy.modules.system.query.DictQuery;
import com.zuoyy.modules.system.service.DictService;
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
@RequestMapping("/system/dict")
public class DictController extends BaseController {

    @Autowired
    private DictService dictService;

    @GetMapping("/index.shtml")
    @RequiresPermissions("system:dict:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "/system/dict/index";
    }

    @GetMapping("/listPage")
    @RequiresPermissions("system:dict:index")
    public String listPage(Model model) {
        model.addAttribute("button", getPageButton("system:dict:index"));
        return "/system/dict/list";
    }

    @ResponseBody
    @PostMapping("/getListPage")
    public JsonResponse getListPage(DictQuery query) {
        Page<Dict> page = dictService.getPageList(query);
        List<DictDTO> list = new ArrayList<>();
        List<Dict> dicts = page.getContent();
        if(!dicts.isEmpty()){
            for(Dict dict : dicts){
                DictDTO dto = DictMapper.INSTANCE.domainToDto(dict);
                dto.setDictType(DictUtil.keyValue(DictConst.DICT_TYPE,dict.getType().toString()));
                list.add(dto);
            }
        }
        return JsonResponse.success(setQueryResult(query.getDraw(), (int) page.getTotalElements(),list));
    }

    @GetMapping("/add")
    @RequiresPermissions("system:dict:add")
    public String toAdd() {
        return "/system/dict/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:user:edit")
    public String toEdit(@PathVariable("id") Dict dict, Model model) {
        model.addAttribute("dict", dict);
        return "/system/dict/add";
    }


    @ResponseBody
    @PostMapping({"/save"})
    @RequiresPermissions({"system:dict:add","system:dict:edit"})
    @ActionLog(name = "字典管理", message = "字典：${title}", action = SaveAction.class)
    public JsonResponse save(@EntityParam Dict dict){
        // 判断角色标识是否重复
        if (dictService.repeatByName(dict)) {
            throw new ResultException(ResultEnum.DICT_EXIST);
        }
        // 清除字典值两边空格
        dict.setValue(dict.getValue().trim());
        // 复制保留无需修改的数据
        if(!StringUtils.isEmpty(dict.getId())){
            Dict beDict = dictService.findById(dict.getId());
            EntityBeanUtil.copyProperties(beDict, dict);
        }
        // 保存数据
        dictService.save(dict);
        if(dict.getId() != null){
            DictUtil.clearCache(dict.getName());
        }
        return JsonResponse.success();
    }

    @GetMapping("/detail/{id}")
    @RequiresPermissions("system:dict:detail")
    public String toDetail(@PathVariable("id") Dict dict, Model model){
        model.addAttribute("dict",dict);
        return "/system/dict/detail";
    }

    @ResponseBody
    @RequestMapping("/status/{param}")
    @RequiresPermissions({"system:dict:status","system:dict:delete"})
    @ActionLog(name = "字典状态", action = StatusAction.class)
    public JsonResponse status(
            @PathVariable("param") String param,
            @RequestParam(value = "ids", required = false) List<String> ids){
        // 更新状态
        StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
        if (dictService.updateStatus(statusEnum, ids)) {
            return JsonResponse.success(statusEnum.message + "成功");
        } else {
            return JsonResponse.error(statusEnum.message + "失败，请重新操作");
        }
    }



}