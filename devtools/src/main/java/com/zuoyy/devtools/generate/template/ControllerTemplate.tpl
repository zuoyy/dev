## 控制器模板

import com.zuoyy.admin.common.tools.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author zuo
 */
@Controller
@RequestMapping("#{requestMapping}")
public class #{entity}Controller extends BaseController {

    @Autowired
    private #{entity}Service #{name}Service;


    @GetMapping("/index.shtml")
    @RequiresPermissions("#{permissions}:index")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response){
        try {
            model.addAttribute("res", getMyResources(request,response));
        } catch (Exception e) {
            model.addAttribute("msg", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            return "/system/main/error";
        }
        return "#{requestMapping}/index";
    }




}
