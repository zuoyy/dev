package com.zuoyy.devtools.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zuo
 */
@Controller
public class SwaggerController {

    @GetMapping("/dev/doc")
    public String index(){
        return "redirect:/swagger-ui.html";
    }
}
