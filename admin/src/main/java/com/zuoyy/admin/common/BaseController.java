package com.zuoyy.admin.common;

import com.zuoyy.common.constant.AdminConst;
import com.zuoyy.common.enums.LocaleTypeEnum;
import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.common.utils.TreeUtil;
import com.zuoyy.component.shiro.ShiroUtil;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.common.enums.MenuTypeEnum;
import com.zuoyy.modules.system.service.MenuService;
import com.zuoyy.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class BaseController {

	@Autowired
	private LocaleResolver localeResolver;
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;


	protected Map<String,Object> getPageButton(String key){
		Map<String, Object> map = new HashMap<>();
		List<Menu> pageButtons = new ArrayList<Menu>();
		List<Menu> tableButtons = new ArrayList<Menu>();

		User user = getUserDetail();
		List<Menu> menus;
		if (user.getId()!=null&&user.getId().equals(AdminConst.ADMIN_ID)) {
			menus = menuService.getListBySortOk();
		} else {
			// 其他用户需从相应的角色中获取菜单资源
			menus = new ArrayList<>();
			for(Role role : user.getRoles()){
				for(Menu menu:role.getMenus()){
					if (menu.getStatus().equals(StatusEnum.OK.code)) {
						if(menu.getType()!=MenuTypeEnum.type1.code){
							menus.add(menu);
						}
					}
				}
			}
		}
		Menu menu = menuService.findByPerms(key);
		List<Menu> mlist = menuService.getListByPid(menu.getId(),menu.getId());
		if(mlist!=null&&mlist.size()>0){
			for(Menu m1 : mlist){
				for(Menu m2 : menus){
					if(m1.getId().equals(m2.getId())){
						m1.setTitle(user.getLanguage().equals(LocaleTypeEnum.zh_CN.code)?m1.getTitle():m1.getUsTitle());
						if(m1.getType()==MenuTypeEnum.type2.code){
							pageButtons.add(m1);
						}else if(m1.getType()==MenuTypeEnum.type3.code){
							tableButtons.add(m1);
						}
					}
				}
			}
		}
		map.put("pageButtons", pageButtons);
		map.put("tableButtons", tableButtons);
		return map;
	}

	protected Map<String,Object> getMyResources(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String sid = request.getParameter("sid");
		// 获取当前登录的用户
		User user = getUserDetail();
		// 管理员实时更新菜单
		List<Menu> menus;
		if (user.getId()!=null&&user.getId().equals(AdminConst.ADMIN_ID)) {
			menus = menuService.getListByType(MenuTypeEnum.type1.code);
		} else {
			// 其他用户需从相应的角色中获取菜单资源
			menus = new ArrayList<>();
			for(Role role : user.getRoles()){
				for(Menu menu:role.getMenus()){
					if (menu.getType()==MenuTypeEnum.type1.code &&menu.getStatus().equals(StatusEnum.OK.code)) {
						menus.add(menu);
					}
				}
			}
			menus.sort((Menu m1, Menu m2) -> m1.getSort().compareTo(m2.getSort()));
		}
		menus = TreeUtil.getTree(menus,"id");
		map.put("user", user);
		map.put("menus", menus);

		//页面title
		String title = "首页";
		List<String> titles = new ArrayList<>();
		if(!StringUtils.isEmpty(sid)){
			Menu menu = menuService.findById(sid);
			Menu parentM = menuService.findById(menu.getPid());
			titles.add(parentM.getTitle());
			titles.add(menu.getTitle());
			title = menu.getTitle();
		}
		map.put("pageTitle", titles);
		map.put("title", title);
		//设置系统语言
		localeResolver.setLocale(request,response, Locale.forLanguageTag(user.getLanguage()));
		return map;
	}


	protected User getUserDetail(){
		return userService.getUserDetail(ShiroUtil.getSubject().getId());
	}


	protected Map<String, Object> setQueryResult(int draw, int rowCount, List<?> records) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap.put("draw", draw);
		resmap.put("recordsTotal", rowCount);
		resmap.put("recordsFiltered", rowCount);
		resmap.put("data", records);
		return resmap;
	}


	protected HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		return ((ServletRequestAttributes) ra).getRequest();
	}


}
