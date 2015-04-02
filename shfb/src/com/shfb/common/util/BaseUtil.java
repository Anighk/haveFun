package com.shfb.common.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;


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
	
	/**生成图片存储相对路径*/
	public static String getYearMouthPath(){
		String timeStr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/");// 设置日期格式
		timeStr = sdf.format(new Date());
		return timeStr;
	}
	
	/**存储图片并返回图片存储路径*/
	public static String saveImgAndReturnPath(MultipartFile myfile){
		String myfilename=myfile.getOriginalFilename();
		if(myfilename.isEmpty()){
			return "";
		}
		String rootpath=ConfigManager.getKeyValue("img.rootpath");
		String filename=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System 
				.currentTimeMillis())) +getFileNameExt(myfilename);
		String path= getYearMouthPath();
        File f = new File(rootpath+"/"+path);
        // 创建文件夹
        if (!f.exists()) {
            f.mkdirs();
        }
		File file=new File(rootpath+"/"+path,filename);
		try {
			FileUtils.copyInputStreamToFile(myfile.getInputStream(), file);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return path+filename;
	}
	
	/**得到文件的扩展名*/
	public static String getFileNameExt(String filename){
		return filename.substring(filename.lastIndexOf("."));
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
//		System.out.println(getYearMouthPath());
		String filename="abc.txt";
		System.out.println(getFileNameExt(filename));
	}
}
