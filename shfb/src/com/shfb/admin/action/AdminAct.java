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
import org.springframework.web.multipart.MultipartFile;

import com.shfb.admin.rs.MemberDTO;
import com.shfb.admin.rs.NewsDTO;
import com.shfb.admin.rs.RenewDTO;
import com.shfb.admin.service.AdminService;
import com.shfb.common.entity.Members;
import com.shfb.common.entity.News;
import com.shfb.common.entity.Renew;
import com.shfb.common.util.BaseUtil;
import com.shfb.common.util.Constant;
import com.shfb.common.util.ImgUtil;

@Controller
public class AdminAct {
	private transient Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private AdminService adminService;
	private Integer pageCount;
	private Integer pageStart;
	private Integer pageEnd;
	
	
	@RequestMapping("/listMembers.do")
	public String listMembers(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		MemberDTO dto=adminService.findUsers("1=1 order by nvl(to_char(reg_date,'yyyy-MM-dd'),'1000-01-01') desc", pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getmList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option","");
		modelMap.addAttribute("keyWord","");
		modelMap.addAttribute("sortWord","nvl(to_char(reg_date,'yyyy-MM-dd'),'1000-01-01') desc");
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
	
	@RequestMapping("/listNews.do")
	public String listNews(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		NewsDTO dto=adminService.findNews("1=1 order by edit_date desc", pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option","");
		modelMap.addAttribute("keyWord","");
		modelMap.addAttribute("sortWord","edit_date desc");
		return Constant.VIEW_PATH+"news/index.html";
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
	
	@RequestMapping("/findNews.do")
	public String findNews(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String option=request.getParameter("option");
		String keyWord=request.getParameter("keyWord");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String sortWord=request.getParameter("sortWord");
		String strWhere=getNewsCondition(option,keyWord,startdt,enddt,sortWord);
		NewsDTO dto=adminService.findNews(strWhere, pageNow, pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option",option);
		modelMap.addAttribute("keyWord",keyWord);
		modelMap.addAttribute("startdt",startdt);
		modelMap.addAttribute("enddt",enddt);
		modelMap.addAttribute("sortWord",sortWord);
		return Constant.VIEW_PATH+"news/index.html";
	}
	
	@RequestMapping("/toEmember.do")
	public String toEditMember(HttpServletRequest request,ModelMap modelMap){
		String userid=request.getParameter("user_id");
		String strWhere=" user_id='"+userid+"'";
		MemberDTO dto=adminService.findUsers(strWhere, 1, 1);
		modelMap.addAttribute("info",dto.getmList().get(0));
		return Constant.VIEW_PATH+"admin/emember.html";
	}
	
	@RequestMapping("/toMember_view.do")
	public String toＶiewMember(HttpServletRequest request,ModelMap modelMap){
		String userid=request.getParameter("user_id");
		String strWhere=" user_id='"+userid+"'";
		MemberDTO dto=adminService.findUsers(strWhere, 1, 1);
		modelMap.addAttribute("info",dto.getmList().get(0));
		return Constant.VIEW_PATH+"admin/member_view.html";
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
	
	
	@RequestMapping("/deleteNews.do")
	public String deleteNews(HttpServletRequest request,ModelMap modelMap){
		Integer pageNow=Integer.parseInt(request.getParameter("pageNow")==null||request.getParameter("pageNow")==""?"1":request.getParameter("pageNow"));
		Integer pageSize=Integer.parseInt(request.getParameter("pageSize")==null||request.getParameter("pageSize")==""?"10":request.getParameter("pageSize"));
		String option=request.getParameter("option");
		String keyWord=request.getParameter("keyWord");
		String startdt=request.getParameter("startdt");
		String enddt=request.getParameter("enddt");
		String sortWord=request.getParameter("sortWord");
		String strWhere=getNewsCondition(option,keyWord,startdt,enddt,sortWord);
		String keyStr=request.getParameter("keyStr");
		List<News> list=new ArrayList<News>();
		for (String e : keyStr.split(";")) {
			News nw=new News();
			String[] array=e.split(",");
			nw.setId(Integer.parseInt(array[0]));
			if(array.length>1){
				nw.setImgpath(array[1]);
			}
			list.add(nw);
		}
		System.out.println("strWhere --> "+strWhere);
		NewsDTO dto=adminService.deleteNews(list,strWhere,pageNow,pageSize);
		modelMap=paginate(dto.getTotal(),pageSize,pageNow,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option",option);
		modelMap.addAttribute("keyWord",keyWord);
		modelMap.addAttribute("startdt",startdt);
		modelMap.addAttribute("enddt",enddt);
		modelMap.addAttribute("sortWord",sortWord);
		return Constant.VIEW_PATH+"news/index.html";
	}
	
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
	
	
	@RequestMapping("/addNews.do")
	public String addNews(HttpServletRequest request,ModelMap modelMap,MultipartFile myfile){
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String source=request.getParameter("source");
		String reldt=request.getParameter("reldt");
		String content=request.getParameter("content");
		
		News nw=new News();
		nw.setTitle(title);
		nw.setAuthor(author);
		nw.setSource(source);
		nw.setContent(content);
		nw.setEdit_date(new Date());
		nw.setCreate_date(new Date());
		if(reldt!=null){
			reldt=reldt.replace("T"," ");
			nw.setRelease_date(BaseUtil.StringToDate(reldt, "yyyy-MM-dd HH:mm"));
		}else{
			nw.setRelease_date(new Date());
		}
		nw.setImgpath(BaseUtil.saveImgAndReturnPath(myfile));
		nw.setSimgpath(ImgUtil.imageResize(nw.getImgpath(), 80, 80));
		NewsDTO dto=adminService.editNews(nw, " 1=1 order by edit_date desc",1,10);
		modelMap=paginate(dto.getTotal(),10,1,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option","");
		modelMap.addAttribute("keyWord","");
		modelMap.addAttribute("sortWord","edit_date desc");
		return Constant.VIEW_PATH+"news/index.html";
	}
	
	@RequestMapping("/editNews.do")
	public String editNews(HttpServletRequest request,ModelMap modelMap){
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String author=request.getParameter("author");
		String source=request.getParameter("source");
		String reldt=request.getParameter("reldt");
		String imgpath=request.getParameter("imgpath");
		String content=request.getParameter("content");
		
		News nw=new News();
		nw.setId(Integer.parseInt(id));
		nw.setTitle(title);
		nw.setAuthor(author);
		nw.setSource(source);
		nw.setContent(content);
		nw.setImgpath(imgpath);
		nw.setEdit_date(new Date());
		if(reldt!=null){
			reldt=reldt.replace("T"," ");
			nw.setRelease_date(BaseUtil.StringToDate(reldt, "yyyy-MM-dd HH:mm"));
		}else{
			nw.setRelease_date(new Date());
		}
		NewsDTO dto=adminService.editNews(nw, " 1=1 order by edit_date desc",1,10);
		modelMap=paginate(dto.getTotal(),10,1,modelMap);
		modelMap.addAttribute("List",dto.getList());
		modelMap.addAttribute("total",dto.getTotal());
		modelMap.addAttribute("option","");
		modelMap.addAttribute("keyWord","");
		modelMap.addAttribute("sortWord"," 1=1 order by edit_date desc");
		return Constant.VIEW_PATH+"news/index.html";
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
	
	@RequestMapping("/toAddUser.do")
	public String toAddUser(ModelMap modelMap){
		modelMap.addAttribute("navIndex", "#admnav_user");
		return Constant.VIEW_PATH+"admin/enuser.html";
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
	
	private String getNewsCondition(String option,String keyWord,String startdt,String enddt,String sortWord){
		String condition=" 1=1 ";
		if(option!=null&&!"".equals(option)){
			condition+=" and "+option +" like '%"+keyWord+"%' ";
		}
		if(startdt!=null&&!"".equals(startdt)){
			condition+=" and release_date>=to_date('"+startdt+"','yyyy-MM-dd') ";
		}
		if(enddt!=null&&!"".equals(enddt)){
			condition+=" and release_date<=to_date('"+enddt+"','yyyy-MM-dd') ";
		}
		condition+=" order by "+sortWord;
		return condition;
	}
}
