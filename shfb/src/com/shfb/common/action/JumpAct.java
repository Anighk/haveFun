package com.shfb.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shfb.common.entity.Users;
import com.shfb.common.rs.info.Znode;
import com.shfb.common.util.BaseUtil;
import com.shfb.common.util.Constant;
import com.shfb.common.util.DataConverter;

@Controller
public class JumpAct {
	
	private Integer pageCount;
	private Integer pageStart;
	private Integer pageEnd;

	
	@RequestMapping("/toTbs.do")
	public String toTableSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_tbs");
		return Constant.VIEW_PATH + "/sys/tbs.html";
	}
	

	@RequestMapping("/toLls.do")
	public String toLawLevelSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_lls");
		return Constant.VIEW_PATH + "/sys/lls.html";
	}

	@RequestMapping("/toCogs.do")
	public String toCogSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_cogs");
		return Constant.VIEW_PATH + "/sys/cogs.html";
	}

	@RequestMapping("/toCits.do")
	public String toCitSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_cits");
		return Constant.VIEW_PATH + "/sys/cits.html";
	}

	@RequestMapping("/toIpcs.do")
	public String toIpcSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_ipcs");
		return Constant.VIEW_PATH + "/sys/ipcs.html";
	}
	
	@RequestMapping("/toHelp.do")
	public String toHelp(ModelMap modelMap) {
		return Constant.VIEW_PATH + "/sys/help.html";
	}

	@RequestMapping("/toConms.do")
	public String toConmSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_conms");
		return Constant.VIEW_PATH + "/sys/conms.html";
	}
	
	@RequestMapping("/toConmi.do")
	public String toConmInner(ModelMap modelMap) {
		modelMap.addAttribute("zNodes", "null");
		return Constant.VIEW_PATH + "/sys/conmi.html";
	}
	
	@RequestMapping("/toSyn.do")
	public String toSynInner(ModelMap modelMap) {
		modelMap.addAttribute("zNodes", "null");
		return Constant.VIEW_PATH + "/sys/syn.html";
	}
	
	@RequestMapping("/toPwdm.do")
	public String toPwdm(@CookieValue(value = "user") String userStr,
			ModelMap modelMap) {
		Users user=BaseUtil.getObjectFromJson(userStr,Users.class);
		modelMap.addAttribute("info","");
		modelMap.addAttribute("username", user.getUsername());
		return Constant.VIEW_PATH + "/sys/pwdm.html";
	}

	/**分页设置*/
private ModelMap paginate(Integer total,Integer pageSize,Integer pageNow,ModelMap modelMap){
	pageCount = (total - 1) / pageSize + 1;
	pageStart = pageNow - 2;
	pageEnd = pageNow + 2;
	if (pageStart < 1) {
		pageEnd += 1 - pageStart;
		pageStart = 1;
		if (pageEnd > pageCount)
			pageEnd = pageCount;
	} else if (pageEnd > pageCount) {
		pageStart += pageCount - pageEnd;
		pageEnd = pageCount;
		if (pageStart < 1)
			pageStart = 1;
	}
	modelMap.addAttribute("pageCount",pageCount);
	modelMap.addAttribute("pageStart",pageStart);
	modelMap.addAttribute("pageEnd",pageEnd);
	modelMap.addAttribute("pageNow",pageNow);
	modelMap.addAttribute("pageSize",pageSize);
	return modelMap;
}
}
