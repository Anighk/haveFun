package com.shfb.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.shfb.common.util.Constant;

@Controller
public class JumpAct {

	@RequestMapping("/toAddNews.do")
	public String toAddNews(ModelMap modelMap) {
		return Constant.VIEW_PATH + "news/add.html";
	}

}
