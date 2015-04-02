package com.shfb.common.util;

public class Constant {
	
	public static final String VIEW_PATH="/WEB-INF/view/"; 
	
	public static final String ERROR_PATH="/WEB-INF/view/error.html";
	
	public static final String YES="yes";
	
	public static final String NO="no";
	
	public static final String SQL_COG_BEFORE="select p from Patentsth p , Cognations c where c.COGCODE in (select cogcode from cognations where cogpatent in (";
	/**applycode*/
	public static final String APPLYCODE="applycode";
	/**opencode*/
	public static final String OPENCODE="opencode";
	/**双引号*/
	public static final String QUOT = "\"";
	/**逗号*/
	public static final String COMMA = ",";
	/**回车*/
	public static final String ENTER = "\r\n";
	/**单引号*/
	public static final String APOS="'";
	/**单引号+逗号*/
	public static final String APOS_COMMA="',";
	/**括号*/
	public static final String BRACKET=")";
	/**双引号+逗号*/
	public static final String QUOT_COMMA="\",";
	/*UTF-8编码的前几个字符 */
	public static final byte[] BOM = new byte[] { (byte)0xEF, (byte)0xBB, (byte)0xBF };
	/*unicode编码的前几个字符*/
	public static final byte[] BOM_UNICODE=new byte[]{};
	/**著录项字符串(下载用)*/
	public static final String ZLX_INIT="公开（公告）号,公开（公告）日,申请号,申请日,名称,主分类号,分类号,申请人,发明人,代理人,代理机构,地址,国省代码,优先权,摘要\r\n";
	/**日志字符串(下载用)*/
	public static final String LOG_INIT="日志ID,用户ID,内容,时间\r\n";
	/**bbs导出帖*/
	public static final String BBS_INIT="层数,用户名,时间,内容\r\n";
	/**BBS导出_楼主*/
	public static final String BBS_LZ="\"楼主\",";
	/**专利错误信息*/
	public static final String ERROR_INFO_PATENT="抱歉，未检索到您查找的专利";
	/**法律状态错误信息*/
	public static final String ERROR_INFO_LAW="抱歉，未检索到您查找的法律状态";
	/**用户编辑错误信息*/
	public static final String ERROR_INFO_EDITUSER="抱歉，用户名冲突";
	/**同义词错误信息*/
	public static final String ERROR_INFO_SYN="抱歉，未找到相关的同义词";
	/**同义词错误信息*/
	public static final String ERROR_INFO_FILENULL="抱歉，您选择的专利暂无全文数据";
	
	//数据库表名
	public static final String TABLE_MEMBERS="zlt_user";
	public static final String TABLE_NEWS="zlt_news";
	public static final String TABLE_RENEW="renew_info";
	public static final String TABLE_USERS="admin_users";
	

}
