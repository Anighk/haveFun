package com.shfb.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.rs.RenewDTO;
import com.shfb.admin.service.AdminService;
import com.shfb.common.entity.Members;
import com.shfb.common.entity.Renew;
import com.shfb.common.util.BaseUtil;
import com.shfb.common.util.ConfigManager;
import com.shfb.common.util.Constant;

@Controller
public class AdminAct {
	private transient Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private AdminService adminService;
	private Integer pageCount;
	private Integer pageStart;
	private Integer pageEnd;
	
	private static final String PWD_MAX_ERROR_NUM=ConfigManager.getKeyValue("pmen");
	
	@RequestMapping("/listMembers.do")
	public String listMembers(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		MemberDTO dto=adminService.findUsers("1=1 order by nvl(to_char(overdue_date,'yyyy-MM-dd'),'1000-01-01') desc", pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getmList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option","");
		modelMap.addAttribute("keyWord","");
		modelMap.addAttribute("sortWord","nvl(to_char(overdue_date,'yyyy-MM-dd'),'1000-01-01') desc");
		return Constant.VIEW_PATH+"admin/amember.html";
	}
	
	@RequestMapping("/listRenews.do")
	public String listRenews(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		RenewDTO dto=adminService.findRenew("1=1 order by nvl(to_char(renew_date,'yyyy-MM-dd'),'1000-01-01') desc", pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option","");
		modelMap.addAttribute("keyWord","");
		modelMap.addAttribute("sortWord","nvl(to_char(renew_date,'yyyy-MM-dd'),'1000-01-01') desc");
		return Constant.VIEW_PATH+"admin/renews.html";
	}
	
	@RequestMapping("/findMember.do")
	public String findMember(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String option=request.getParameter("option");
		String keyWord=request.getParameter("keyWord");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String manager=request.getParameter("manager");
		String vip=request.getParameter("vip");
		String sortWord=request.getParameter("sortWord");
		String strWhere=getCommonCondition(option,keyWord,startdt,enddt,manager,vip,sortWord,"member");
		MemberDTO dto=adminService.findUsers(strWhere, pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getmList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option",option);
		modelMap.addAttribute("keyWord",keyWord);
		modelMap.addAttribute("startdt",startdt);
		modelMap.addAttribute("enddt",enddt);
		modelMap.addAttribute("manager",manager);
		modelMap.addAttribute("vip",vip);
		modelMap.addAttribute("sortWord",sortWord);
		return Constant.VIEW_PATH+"admin/amember.html";
	}
	
	@RequestMapping("/findRenew.do")
	public String findRenew(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String option=request.getParameter("option");
		String keyWord=request.getParameter("keyWord");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String manager=request.getParameter("manager");
		String vip=request.getParameter("vip");
		String sortWord=request.getParameter("sortWord");
		String strWhere=getCommonCondition(option,keyWord,startdt,enddt,manager,vip,sortWord,"renew");
		RenewDTO dto=adminService.findRenew_combine(strWhere, pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option",option);
		modelMap.addAttribute("keyWord",keyWord);
		modelMap.addAttribute("startdt",startdt);
		modelMap.addAttribute("enddt",enddt);
		modelMap.addAttribute("manager",manager);
		modelMap.addAttribute("vip",vip);
		modelMap.addAttribute("sortWord",sortWord);
		return Constant.VIEW_PATH+"admin/renews.html";
	}
	
	@RequestMapping("/toEmember.do")
	public String toEditMember(HttpServletRequest request,ModelMap modelMap){
		String userid=request.getParameter("user_id");
		String strWhere=" user_id='"+userid+"'";
		MemberDTO dto=adminService.findUsers(strWhere, 1, 1);
		modelMap.addAttribute("info",dto.getmList().get(0));
		return Constant.VIEW_PATH+"admin/emember.html";
	}
	
	@RequestMapping("/toRenew.do")
	public String toRenew(HttpServletRequest request,ModelMap modelMap){
		String userid=request.getParameter("user_id");
		String strWhere=" user_id='"+userid+"'";
		MemberDTO dto=adminService.findUsers(strWhere, 1, 1);
		modelMap.addAttribute("info",dto.getmList().get(0));
		return Constant.VIEW_PATH+"admin/renew.html";
	}
	
	@RequestMapping("/toRenew_history.do")
	public String toRenew_history(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String userid=request.getParameter("user_id");
		String strWhere=" user_id='"+userid+"' order by id";
		RenewDTO dto=adminService.findRenew(strWhere, pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getList());
		return Constant.VIEW_PATH+"admin/renew_history.html";
	}
	
	
//	@RequestMapping("/deleteMember.do")
//	public String deleteUser(HttpServletRequest request,ModelMap modelMap){
//		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
//		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
//		String keyStr=request.getParameter("keyStr");
//		List<Members> list=new ArrayList<Members>();
//		for (String e : keyStr.split(";")) {
//			Members m=new Members();
//			m.setUser_id(e);
//			list.add(m);
//		}
//		MemberDTO dto=adminService.deleteUser(list);
//		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
//		modelMap.addAttribute("List",dto.getmList());
//		modelMap.addAttribute("total",dto.getTotal());
//		modelMap.addAttribute("option","");
//		modelMap.addAttribute("keyWord","");
//		modelMap.addAttribute("pmen",Integer.parseInt(PWD_MAX_ERROR_NUM));
//		return Constant.VIEW_PATH+"admin/amember.html";
//	}
	
	@RequestMapping("/editMember.do")
	public String editMembers(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String option=request.getParameter("option");
		String keyWord=request.getParameter("keyWord");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String manager=request.getParameter("manager");
		String vip=request.getParameter("vip");
		String sortWord=request.getParameter("sortWord");
		String strWhere=getCommonCondition(option,keyWord,startdt,enddt,manager,vip,sortWord,"member");
		
    	String userid=request.getParameter("userid");
    	String nickname=request.getParameter("nickname");
    	String regdate=request.getParameter("regdate");
    	String overduedate=request.getParameter("overduedate");
    	String isvip=request.getParameter("isvip");
    	String salesmanager=request.getParameter("salesmanager");
    	String province=request.getParameter("province");
    	String city=request.getParameter("city");
    	String company=request.getParameter("company");
    	String jobtitle=request.getParameter("jobtitle");
    	String contacts=request.getParameter("contacts");
    	String tel=request.getParameter("tel");
    	String qq=request.getParameter("qq");
    	String equipment=request.getParameter("equipment");
    	String remark=request.getParameter("remark");
    	
		Members member=new Members();
		member.setUser_id(userid);
		member.setNick_name(nickname);
		member.setReg_date(BaseUtil.StringToDate(regdate,"yyyy-MM-dd"));
		member.setOverdue_date(BaseUtil.StringToDate(overduedate,"yyyy-MM-dd"));
		member.setIsvip(isvip);
		member.setSales_manager(salesmanager);
		member.setProvince(province);
		member.setCity(city);
		member.setCompany(company);
		member.setContacts(contacts);
		member.setJob_title(jobtitle);
		member.setTel(tel);
		member.setQq(qq);
		member.setEquipment(equipment);
		member.setRemark(remark);
		MemberDTO dto=adminService.editUser(member, strWhere,pageNow,pageSize);
		if("error".equals(dto.getMessage())){
			modelMap.addAttribute("info",Constant.ERROR_INFO_EDITUSER);
			return Constant.ERROR_PATH;
		}
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getmList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option",option);
		modelMap.addAttribute("keyWord",keyWord);
		modelMap.addAttribute("startdt",startdt);
		modelMap.addAttribute("enddt",enddt);
		modelMap.addAttribute("manager",manager);
		modelMap.addAttribute("vip",vip);
		modelMap.addAttribute("sortWord",sortWord);
		return Constant.VIEW_PATH+"admin/amember.html";
	}
	
	@RequestMapping("/renew.do")
	public String renew(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String option=request.getParameter("option");
		String keyWord=request.getParameter("keyWord");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String manager=request.getParameter("manager");
		String vip=request.getParameter("vip");
		String sortWord=request.getParameter("sortWord");
		String strWhere=getCommonCondition(option,keyWord,startdt,enddt,manager,vip,sortWord,"member");
		
		String userid=request.getParameter("userid");
		String renewyear=request.getParameter("renewyear");
		String renewto=request.getParameter("renewto");
		String remark=request.getParameter("remark");
		Renew rn=new Renew();
		rn.setUser_id(userid);
		rn.setRenew_year(renewyear);
		rn.setRenew_to(BaseUtil.StringToDate(renewto,"yyyy-MM-dd"));
		rn.setRemark(remark);
		rn.setRenew_date(new Date());
		MemberDTO dto=adminService.renew(rn,strWhere,pageNow,pageSize);
		if("error".equals(dto.getMessage())){
			modelMap.addAttribute("info",Constant.ERROR_INFO_EDITUSER);
			return Constant.ERROR_PATH;
		}
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getmList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option",option);
		modelMap.addAttribute("keyWord",keyWord);
		modelMap.addAttribute("startdt",startdt);
		modelMap.addAttribute("enddt",enddt);
		modelMap.addAttribute("manager",manager);
		modelMap.addAttribute("vip",vip);
		modelMap.addAttribute("sortWord",sortWord);
		return Constant.VIEW_PATH+"admin/amember.html";
	}
	
	
//	@RequestMapping("/addUser.do")
//	public String addUser(HttpServletRequest request,ModelMap modelMap){
//		String username=request.getParameter("username");
//		String passwd=request.getParameter("passwd");
//		String truename=request.getParameter("truename");
//		String dept=request.getParameter("dept");
//		String attr=request.getParameter("attr");
//		Members member=new Members();
//		MemberDTO dto=adminService.editUser(member, 1,10);
//		if("error".equals(dto.getMessage())){
//			modelMap.addAttribute("info",Constant.ERROR_INFO_EDITUSER);
//			return Constant.ERROR_PATH;
//		}
//		modelMap=paginate(dto.getTotal(),10,1,modelMap);
//		modelMap.addAttribute("List",dto.getmList());
//		modelMap.addAttribute("total",dto.getTotal());
//		modelMap.addAttribute("option","");
//		modelMap.addAttribute("keyWord","");
//		modelMap.addAttribute("pmen",Integer.parseInt(PWD_MAX_ERROR_NUM));
//		modelMap.addAttribute("navIndex", "#admnav_user");
//		return Constant.VIEW_PATH+"admin/amember.html";
//	}
	
	
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
	
	@RequestMapping("/toAddUser.do")
	public String toAddUser(ModelMap modelMap){
		modelMap.addAttribute("navIndex", "#admnav_user");
		return Constant.VIEW_PATH+"admin/enuser.html";
	}
	
	
	@RequestMapping("/toEditUser.do")
	public String toEditUser(HttpServletRequest request,ModelMap modelMap){
		String[] properties={"id","username","truename","dept","passwd","attr","isfirst"};
		String errors=request.getParameter("errors");
		modelMap.addAttribute("errors",Integer.parseInt(errors));
		modelMap=BaseUtil.transAssign(request, modelMap, properties);
		modelMap.addAttribute("pmen",Integer.parseInt(PWD_MAX_ERROR_NUM));
		return Constant.VIEW_PATH+"admin/euser.html";
	}
	
	private String getCommonCondition(String option,String keyWord,String startdt,String enddt,String manager,String vip,String sortWord,String func){
		String condition=" 1=1 ";
		if(option!=null&&!"".equals(option)){
			condition+=" and "+option +" like '%"+keyWord+"%' ";
		}
		if(manager!=null&&!"".equals(manager)){
			condition+=" and sales_manager='"+manager+"' ";
		}
		if(vip!=null&&!"".equals(vip)){
			condition+=" and isvip='"+vip+"' ";
		}
		if("member".equals(func)){
			if(startdt!=null&&!"".equals(startdt)){
				condition+=" and overdue_date>=to_date('"+startdt+"','yyyy-MM-dd') ";
			}
			if(enddt!=null&&!"".equals(enddt)){
				condition+=" and overdue_date<=to_date('"+enddt+"','yyyy-MM-dd') ";
			}
		}else if("renew".equals(func)){
			if(startdt!=null&&!"".equals(startdt)){
				condition+=" and renew_date>=to_date('"+startdt+"','yyyy-MM-dd') ";
			}
			if(enddt!=null&&!"".equals(enddt)){
				condition+=" and renew_date<=to_date('"+enddt+"','yyyy-MM-dd') ";
			}
		}
		condition+=" order by "+sortWord;
		return condition;
	}
}
