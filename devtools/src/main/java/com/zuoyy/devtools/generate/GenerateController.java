package com.zuoyy.devtools.generate;

import com.zuoyy.common.utils.ToolUtil;
import com.zuoyy.common.vo.JsonResponse;
import com.zuoyy.devtools.generate.domain.Generate;
import com.zuoyy.devtools.generate.enums.FieldType;
import com.zuoyy.devtools.generate.template.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zuo
 */
@Controller
public class GenerateController {

    @GetMapping("/dev/code")
    public String index(Model model) {
        try {
            model.addAttribute("basic", DefaultValue.getBasic());
            model.addAttribute("fieldList", DefaultValue.fieldList());
            model.addAttribute("fieldType", ToolUtil.enumToMap(FieldType.class));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return "/devtools/generate/index";
    }

    @PostMapping("/dev/code/save")
    @ResponseBody
    public JsonResponse save(@RequestBody Generate generate){

        Map<String, String> fieldMap = new LinkedHashMap<>();
        if(generate.getTemplate().isEntity()){
            fieldMap.put("实体类", EntityTemplate.generate(generate));
        }
        if(generate.getTemplate().isRepository()){
            fieldMap.put("数据访问层", RepositoryTemplate.generate(generate));
        }
        if(generate.getTemplate().isQuery()){
            fieldMap.put("条件查询类", QueryTemplate.generate(generate));
        }
        if(generate.getTemplate().isService()){
            fieldMap.put("服务层", ServiceTemplate.generate(generate));
            fieldMap.put("服务实现层", ServiceImplTemplate.generate(generate));
        }
        if(generate.getTemplate().isController()){
            fieldMap.put("控制器", ControllerTemplate.generate(generate));
        }
        if(generate.getTemplate().isIndex()){
            fieldMap.put("列表页面", IndexHtmlTemplate.generate(generate));
        }
        // 当模块结构为独立模块时生成业务模块
//        if(generate.getBasic().getModuleType().equals(ModuleType.ALONE.getCode())){
//            GenerateUtil.genMavenModule(generate);
//        }

        return JsonResponse.success(fieldMap);
    }



}
