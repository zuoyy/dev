package com.zuoyy.admin.tools;

import com.zuoyy.admin.common.tools.EmailTool;
import com.zuoyy.admin.common.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/tools/email")
public class EmailController extends BaseController {

    @Autowired
    private EmailTool emailTool;

    @GetMapping("/index.shtml")
    @RequiresPermissions("tools:email:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }

//        Map<String, Object> valueMap = new HashMap<>();
//        valueMap.put("to", new String[]{"peng@bonday.cn"});
//        valueMap.put("title", "[高盛] 邀请函 | 7月27-31日 | 高盛全球新生代夏季系列活动");
//        valueMap.put("content", "<p>邮件内容</p>");
//        emailTool.sendSimpleMail(valueMap);
        return "/tools/email/index";
    }


}
