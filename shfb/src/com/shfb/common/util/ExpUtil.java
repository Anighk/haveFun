package com.shfb.common.util;

/**
 * 表达式转换工具类
 * @author Anighk
 *
 */
public class ExpUtil {
	
	public static String formParser(String text) {
		System.out.println("exp = = "+text);
		text=symbolBanner(text);
		text=text.replaceAll("not an:\\((.*?)\\)(?:\\s|$)", "applycode not like '%$1%' ")
				.replaceAll("not ad:\\((.*?)\\)(?:\\s|$)", "applydate not like '%$1%' ")
				.replaceAll("not pnm:\\((.*?)\\)(?:\\s|$)", "opencode not like '%$1%' ")
				.replaceAll("not pd:\\((.*?)\\)(?:\\s|$)", "opendate not like '%$1%' ")
				.replaceAll("not kw:\\((.*?)\\)(?:\\s|$)", "contains(titletermsdwpi,'$1')<=0 ")
				.replaceAll("not ti:\\((.*?)\\)(?:\\s|$)", "contains(name,'$1')<=0 ")
				.replaceAll("not ab:\\((.*?)\\)(?:\\s|$)", "contains(abstraction,'$1')<=0 ")
				.replaceAll("not pic:\\((.*?)\\)(?:\\s|$)", "mainclass not like '%$1%' ")
				.replaceAll("not sic:\\((.*?)\\)(?:\\s|$)", "allclass not like '%$1%' ")
				.replaceAll("not pa:\\((.*?)\\)(?:\\s|$)", "contains(applyer,'$1')<=0 ")
				.replaceAll("not in:\\((.*?)\\)(?:\\s|$)", "contains(inventer,'$1')<=0 ")
				.replaceAll("not ar:\\((.*?)\\)(?:\\s|$)", "contains(address,'$1')<=0 ")
				.replaceAll("not agc:\\((.*?)\\)(?:\\s|$)", "contains(agent,'$1')<=0 ")
				.replaceAll("not agt:\\((.*?)\\)(?:\\s|$)", "contains(agenter,'$1')<=0 ")
				.replaceAll("not dm:\\((.*?)\\)(?:\\s|$)", "contains(rightt,'$1')<=0 ")
				;
		text=text.replaceAll("an:\\((.*?)\\)(?:\\s|$)","applycode like '%$1%' ")
				.replaceAll("ad:\\(\\[(.*?) to (.*?)\\]\\)(?:\\s|$)"," applydate > '$1' and applydate < '$2'")
				.replaceAll("ad:\\((.*?)\\)(?:\\s|$)", "applydate like '%$1%' ")
				.replaceAll("pnm:\\((.*?)\\)(?:\\s|$)", "opencode like '%$1%' ")
				.replaceAll("pd:\\(\\[(.*?) to (.*?)\\]\\)(?:\\s|$)"," opendate > '$1' and opendate < '$2'")
				.replaceAll("pd:\\((.*?)\\)(?:\\s|$)", "opendate like '%$1%' ")
				.replaceAll("kw:\\((.*?)\\)(?:\\s|$)", "contains(titletermsdwpi, '$1')>0 ")
				.replaceAll("ti:\\((.*?)\\)(?:\\s|$)", "contains(name,'$1')>0 ")
				.replaceAll("ab:\\((.*?)\\)", "contains(abstraction,'$1')>0 ")
				.replaceAll("pic:\\((.*?)\\)(?:\\s|$)", "mainclass like '%$1%' ")
				.replaceAll("sic:\\((.*?)\\)(?:\\s|$)", "allclass like '%$1%' ")
				.replaceAll("pa:\\((.*?)\\)(?:\\s|$)", "contains(applyer,'$1')>0 ")
				.replaceAll("in:\\((.*?)\\)(?:\\s|$)", "contains(inventer,'$1')>0 ")
				.replaceAll("ar:\\((.*?)\\)(?:\\s|$)", "contains(address,'$1')>0 ")
				.replaceAll("agc:\\((.*?)\\)(?:\\s|$)", "contains(agent,'$1')>0 ")
				.replaceAll("agt:\\((.*?)\\)(?:\\s|$)", "contains(agenter,'$1')>0 ")
				.replaceAll("dm:\\((.*?)\\)(?:\\s|$)", "contains(rightt,'$1')>0 ")
				;
		text=" ( "+text+" ) ";
		return text;
	}

	public static String qkformParser(String text) {
		text=symbolBanner(text);
		return text
				.replaceAll("(issn|country|language|year):\\((.*?)\\)", "$1 like '%$2%'")
				.replaceAll("(title|title2|author|org|keywords|abstract_cn|abstract_en|indexby):\\((.*?)\\)", "contains($1,'$2')>0");
	}

	public static String strSourcesParser(String strSources){
		StringBuffer sb=new StringBuffer();
		for(String s:strSources.split(";")){
			s=" ptype = '"+s+"' or";
			sb.append(s);
		}
		strSources=sb.toString();
		strSources=strSources.substring(0, (strSources.lastIndexOf("or")));
		return strSources;
	}
	
	public static String symbolBanner(String str){
		str=str.replace("%","")
				.replace("?","")
				.replace("*","")
				.replace("#","")
				.replace("<","")
				.replace(">","")
				.replace(";","")
				.replace("~","")
				.replace("'","")
				.replace("\"","")
				.replace("&","")
//				.replace("+","")
//				.replace("/","")
				;
		return str;
	}
	
	public static String getLawStatusWhere(String lst,String t){
		String lsWhere=null;
		if("有效".equals(lst)){
			lsWhere=" and ("+t+"status = '授权' or "+t+"status = '有效' or "+t+"status like '%变更%' or "+t+"status like '%转移%' or "
							+t+"status like '%专利公报更正%')";
		}else if("在审".equals(lst)){
			lsWhere=" and ("+t+"status = '公开' or "+t+"status like '%实质审查%' or "+t+"status = '审定' or "+t+"status = '在审')";
		}else if("无效".equals(lst)){
			lsWhere=" and ("+t+"status like '%撤回%' or "+t+"status like '%驳回%' or "+t+"status like '%终止%' or "
							+t+"status like '%放弃%' or "+t+"status like '%无效%' or "+t+"status = '失效')";
		}else{
			lsWhere="";
		}
		return lsWhere;
	}
	public static  void  main(String[]  args){
//		String text1="an:(计算机) and not an:(通信)";
//		System.out.println(formParser(text1));
//		String text2="00;02";
//		System.out.println(strSourcesParser(text2));
//		String a="[sdfsad]asdf[asdfe]";
		
//		a = a.replaceAll("(?<=\\[)(.*?)(?=\\])", "aa");
//		a = a.replaceAll("\\[(.*?)\\]", "[aa]"); 
//		System.out.println(a);
		String s="(author):(你好)";
		s=s.replaceAll("(title|title2|author|org|keywords|abstract_cn|abstract_en|indexby):\\((.*?)\\)", "contains($1,'$2')>0")
				.replaceAll("(first_author|class_code|issn|country|language|year):\\((.*?)\\)", "$1 like '%$2%'");
		String test1="pnm:(242) and an:(42) and pa:(45) and ar:(12) and sic:(423) and ti:(3563) and ab:(354) and agc:(4563) and agt:(453) and pd:(124) and ad:(12)";
		String exp="exp: sic:(H01H35/38(2006.01)I)";
		String zhyStr="pa:(中海油 OR 中国海洋)";
		System.out.println(formParser(zhyStr));
	}
}
