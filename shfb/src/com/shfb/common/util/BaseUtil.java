package com.shfb.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.ModelMap;

import com.shfb.common.entity.Users;

public class BaseUtil {
	private static String driver = ConfigManager.getKeyValue("jdbc.driverClassName");
	private static String url = ConfigManager.getKeyValue("jdbc.url");
	private static String username = ConfigManager.getKeyValue("jdbc.username");
	private static String password = ConfigManager.getKeyValue("jdbc.password");
	
	//获得数据库连接对象
	public static Connection getConnection(){
		Connection con=null;
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//关闭访问数据库相关对象
	public static void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs){
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * 获得当前时间字符串
	 * 
	 * @return
	 */
	public static String getNowTimeStr() {
		String timeStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		timeStr = sdf.format(new Date());
		return timeStr;
	}
	
	/**
	 * 获得当前日期字符串
	 * @return
	 */
	public static String getNowDateStr() {
		String timeStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");// 设置日期格式
		timeStr = sdf.format(new Date());
		return timeStr;
	}
	
	/**
	 * 获得当前日期字符串
	 * @return
	 */
	public static String DateToString(Date date,String formatStr) {
		String timeStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);// 设置日期格式
		timeStr = sdf.format(date);
		return timeStr;
	}
	
	/**
	 * 获得y年前 1月1日的日期
	 * @param y
	 * @return
	 */
	public static String getBeforeDateStr(Integer y){
		String timeStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");// 设置日期格式
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.YEAR, -y);
		cal.set(Calendar.MONTH,0);
		cal.set(Calendar.DATE,1);
		Date date=cal.getTime();
		timeStr = sdf.format(date);
		return timeStr;
	}

	/**
	 * 字符串转换到时间格式
	 * @param dateStr 需要转换的字符串
	 * @param formatStr 需要格式的目标字符串  举例 yyyy-MM-dd
	 * @return Date 返回转换后的时间
	 * @throws ParseException 转换异常
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date StringToDate(String dateStr){
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("".equals(dateStr)){
			return null;
		}
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 把对象转换成json
	 * 
	 * @param object
	 * @return
	 */
	public static String getJsonFromObject(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 把json转换成对象
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T getObjectFromJson(String json,Class<T> c){
		T o=null;
		try {
			o = new ObjectMapper().readValue(json, c);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return o;
	}
	
	/**
	 * 转赋值
	 * @param request
	 * @param modelMap
	 * @param propertyArray
	 * @return
	 */
	public static ModelMap transAssign(HttpServletRequest request,ModelMap modelMap,String[] propertyArray){
		for(String p:propertyArray){
			String value=request.getParameter(p);
			modelMap.addAttribute(p, value);
		}
		return modelMap;
	}
	
	/**传输文件*/
	public static String tranFileName(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
		String userAgent = request.getHeader("user-agent");
		if (userAgent != null) {
			// IE浏览器，只能使用URLEncoder编码
			if (userAgent.contains("MSIE")) {
				return "filename=\"" + URLEncoder.encode(filename, "UTF8") + "\"";
			}
			// Opera浏览器，只能使用filename*
			if (userAgent.contains("Opera")) {
				return "filename*=UTF-8''" + URLEncoder.encode(filename, "UTF8");
			}
			// Safari浏览器，只能使用ISO编码的中文输出
			if (userAgent.contains("Safari")) {
				return "filename=\"" + new String(filename.getBytes("UTF-8"), "ISO8859-1") + "\"";
			}
			// Chrome浏览器，可以使用MimeUtility编码或ISO编码的中文输出
			if (userAgent.contains("Chrome")) {
				return "filename=\"" + MimeUtility.encodeText(filename, "UTF8", "B") + "\"";
			}
			// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
			if (userAgent.contains("Mozilla")) {
				return "filename*=UTF-8''" + URLEncoder.encode(filename, "UTF8");
			}
		}
		// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
		return "filename=\"" + URLEncoder.encode(filename, "UTF8") + "\"";
	}
	
	/**BaseUtil.stringValueOf的重写*/
    public static String stringValueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
    
	/**分页设置*/
	public static  ModelMap paginate(Integer total,Integer pageSize,Integer pageNow,ModelMap modelMap){
		Integer pageCount = (total - 1) / pageSize + 1;
		Integer pageStart = pageNow - 2;
		Integer pageEnd = pageNow + 2;
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
    /**国别编号的翻译*/
	public static String getType(String typeCode){
		Map<String,String> map=new HashMap<String,String>();
		map.put(Constant.T00,Constant.T00_FMGB);
		map.put(Constant.T01,Constant.T01_SYXX);
		map.put(Constant.T02,Constant.T02_WGSJ);
		map.put(Constant.T03,Constant.T03_FMSQ);
		map.put(Constant.T04,Constant.T04_US);
		map.put(Constant.T05,Constant.T05_JP);
		map.put(Constant.T06,Constant.T06_KR);
		map.put(Constant.T07,Constant.T07_GB);
		map.put(Constant.T08,Constant.T08_FR);
		map.put(Constant.T09,Constant.T09_CH);
		map.put(Constant.T10,Constant.T10_DE);
		map.put(Constant.T11,Constant.T11_RU);
		map.put(Constant.T12,Constant.T12_EPO);
		map.put(Constant.T13,Constant.T13_WIPO);
		map.put(Constant.T14,Constant.T14_AU);
		map.put(Constant.T15,Constant.T15_CA);
		map.put("","全部");
		if(map.get(typeCode)==null){
			return typeCode;	
		}else{
		 return map.get(typeCode);
		}
	}
	
	public static String getAnlsName(String anlsType){
		Map<String,String> map=new HashMap<String,String>();
		map.put(Constant.AD,Constant.AD_);
		map.put(Constant.PD,Constant.PD_);
		map.put(Constant.IPC1,Constant.IPC1_);
		map.put(Constant.IPC3,Constant.IPC3_);
		map.put(Constant.IPC4,Constant.IPC4_);
		map.put(Constant.CITING,Constant.CITING_);
		map.put(Constant.CITED,Constant.CITED_);
		map.put(Constant.PA,Constant.PA_);
		map.put(Constant.INN,Constant.INN_);
		map.put(Constant.AGC,Constant.AGC_);
		map.put(Constant.AGT,Constant.AGT_);
		map.put(Constant.NP,Constant.NP_);
		map.put(Constant.LAW,Constant.LAW_);
		return map.get(anlsType);
	}
	
	/**法律状态的判定*/
	public static String lawStatus(String lvl){
		String ls=null;
		if(lvl.indexOf("终止")>-1||lvl.indexOf("放弃")>-1||lvl.indexOf("驳回")>-1||lvl.indexOf("撤回")>-1||lvl.indexOf("无效")>-1||"失效".equals(lvl)){
			ls="<img src='images/wx.png' width='43' height='19' />";
		}else if(lvl.indexOf("实质审查")>-1||"公开".equals(lvl)||"审定".equals(lvl)||"在审".equals(lvl)){
			ls="<img src='images/zs.png' width='43' height='19' />";
		}else if("授权".equals(lvl)|"有效".equals(lvl)||lvl.indexOf("变更")>-1||lvl.indexOf("转移")>-1||lvl.indexOf("专利公报更正")>-1){
			ls="<img src='images/yx.png' width='43' height='19' />";
		}else{
			ls="";
		}
		return ls;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		Users user=new Users();
		user.setId(2);
		user.setUsername("abc");
		user.setTruename("用户真名");
		user.setAttr("0");
		String userStr=getJsonFromObject(user);
		String userEncode=URLEncoder.encode(userStr, "utf-8");
		String userDecode=URLDecoder.decode(userEncode, "utf-8");
		System.out.println(userStr);
		System.out.println(userEncode);
		System.out.println(userDecode);
//		System.out.println(getType("00"));
	}
}
