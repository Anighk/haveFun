package com.shfb.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shfb.common.rs.info.IndexInfo;
import com.shfb.common.util.BaseUtil;
import com.shfb.common.util.Constant;

@Controller
public class TestAct {
	private transient Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value ="/toTest.do")
	public String toExpertSearch(ModelMap modelMap) {
		modelMap.addAttribute("navIndex", "#nav_exs");
		return Constant.VIEW_PATH + "sys/test.html";
	}
	
	@RequestMapping(value="/testGarbled.do", produces="text/html;charset=UTF-8" )
	@ResponseBody
	public String testGarbled(ModelMap modelMap) {
		return "中文乱码";
	}
	
	@RequestMapping(value="/async.do")
	public String testAsync(ModelMap modelMap,HttpServletResponse response) {
		response.setCharacterEncoding("UTF_8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write("[{id:20,pId:0,name:'中海油服'},{id:21,pId:20,name:'中国海洋石油总公司2'},{id:22,pId:20,name:'中海石油研究中心2'}]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/asyncc.do" , produces="text/html;charset=UTF-8")
	@ResponseBody
	public String testAsyncc(ModelMap modelMap) {
		return "[{id:20,pId:0,name:'中海油服'},{id:21,pId:20,name:'中国海洋石油总公司2'},{id:22,pId:20,name:'中海石油研究中心2'}]";
	}
	
	@RequestMapping(value="/testobj.do")
	public String testJsObject(HttpServletRequest request,ModelMap modelMap) {
		String json=request.getParameter("json");
		System.out.println(json);
//		IndexInfo ii=BaseUtil.getObjectFromJson(json, IndexInfo.class);
//		System.out.println(ii.getKey());
//		System.out.println(ii.getValue());
		modelMap.addAttribute("json", json);
		return Constant.VIEW_PATH + "sys/test.html";
	}
	

		/**
		 * 异常页面控制
		 * 
		 * @param runtimeException
		 * @return
		 */
		@ExceptionHandler(RuntimeException.class)
		public String runtimeExceptionHandler(RuntimeException runtimeException,
				ModelMap modelMap) {
			logger.error(runtimeException.getLocalizedMessage());
			modelMap.put("status", false);
			return "exception";
		}


}
