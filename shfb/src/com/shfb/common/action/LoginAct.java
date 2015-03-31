package com.shfb.common.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.service.AdminService;
import com.shfb.common.entity.Users;
import com.shfb.common.service.UsersService;
import com.shfb.common.util.BaseUtil;
import com.shfb.common.util.ConfigManager;
import com.shfb.common.util.Constant;


@Controller
public class LoginAct {

	private transient Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private UsersService usersService;
	@Autowired
	private AdminService adminService;
	public LoginAct(){
		
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,ModelMap modelMap,HttpServletResponse response
			,HttpSession session){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Users user=usersService.findUser(username);
		if(user!=null){
				if(!password.equals(user.getPasswd())){
					modelMap.addAttribute("info","密码错误!");
					return "login.html";
				}else{
					String userStr=BaseUtil.getJsonFromObject(user);
					Cookie c=new Cookie("user",userStr);
					c.setPath("/");
					response.addCookie(c);
					MemberDTO dto=adminService.findUsers("1=1 order by nvl(to_char(reg_date,'yyyy-MM-dd'),'1000-01-01') desc", 1, 10);
					modelMap=BaseUtil.paginate(dto.getTotal(), 10,1, modelMap);
					modelMap.addAttribute("List",dto.getmList());
					modelMap.addAttribute("total",dto.getTotal());
					modelMap.addAttribute("option","");
					modelMap.addAttribute("keyWord","");
					modelMap.addAttribute("sortWord","nvl(to_char(reg_date,'yyyy-MM-dd'),'1000-01-01') desc");
					return Constant.VIEW_PATH + "/admin/amember.html";
				}
		}else{
			modelMap.addAttribute("info","用户名不存在!");
			return "login.html";
		}
	}
	
	@RequestMapping("/logout.do")
	public String logout(@CookieValue(value = "user") String userStr,
			HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		//清除cookies
		Cookie[] cookies= request.getCookies();
		for(Cookie c:cookies){
			if(c.getName().equals("user")){
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
//				Cookie cookie = new Cookie("user", null); 
//				response.addCookie(cookie);
			}else if(c.getName().equals("right")){
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
			}
		}
		Users user=BaseUtil.getObjectFromJson(userStr,Users.class);
		MDC.put("userId",BaseUtil.stringValueOf(user.getId()));
		logger.info("用户："+user.getUsername()+" 登出");
		modelMap.addAttribute("info","");
		return "login.html";
	}


	@RequestMapping("/changepwd.do")
	public String changePwd(@CookieValue(value = "user") String userStr,
			HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
		String username = request.getParameter("username");
		String pwd0=request.getParameter("pwd0");
		String pwd1=request.getParameter("pwd1");
		String to=request.getParameter("to");
		String info=null;
		Users user=usersService.findUser(username);
		if(pwd0.equals(user.getPasswd())){
			user.setPasswd(pwd1);
			usersService.update(user);
			info="密码已修改！";
			MDC.put("userId",BaseUtil.stringValueOf(user.getId()));
			logger.info("用户："+user.getUsername()+" 修改新密码为："+pwd1);
		}else{
			info="现在的密码错误！";
		}
		modelMap.addAttribute("info",info);
		modelMap.addAttribute("username",user.getUsername());
		if(to.equals("1")){
			return Constant.VIEW_PATH+"sys/pwdm.html";
		}else{
			if(info.equals("密码已修改！")){
				return Constant.VIEW_PATH+"sys/signindex.html";
			}else{
				return Constant.VIEW_PATH+"sys/pwdf.html";
			}
		}
	}
}
