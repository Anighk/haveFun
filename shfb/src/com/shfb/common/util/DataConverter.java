package com.shfb.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shfb.common.rs.info.AnlsInfo;
import com.shfb.common.rs.info.IndexInfo;
import com.shfb.common.rs.info.Znode;
import com.shfb.test.TestEntity;

public class DataConverter {

	/**
	 * 饼图数据转换
	 * 
	 * @param json
	 * @return 二位数组
	 */
	public static String getPieData(String json) {
		String data = null;
		data = json.replace("{", "[").replace("}", "]")
				.replaceAll("\\\"xAxis\\\":\\\"(.*?)\\\"", "'$1'")
				.replaceAll("\\\"yAxis\\\":\\\"(\\d+)\\\"", "$1")
				.replaceAll(",\\\"zAxis\\\":null", "");
		return data;
	}
	/**
	 * 趋势线图
	 * @param json
	 * @return
	 */
	public static String getSLineData(String json) {
		String data = null;
		data = json.replace("{", "[").replace("}", "]")
				.replaceAll("\\\"xAxis\\\":\\\"(.*?)\\\"", "'$1'")
				.replaceAll("\\\"yAxis\\\":\\\"(\\d+)\\\"", "$1")
				.replaceAll(",\\\"zAxis\\\":null", "");
		data = "["+data+"]";
		return data;
	}
	

	/**
	 * 线图/柱图转换__z轴数据
	 * 得到zArray
	 * @param list
	 * @return {label:'$z'}
	 */
	public static String getLineZArray(List<AnlsInfo> list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if(list!=null&&list.size()>0){
			for (AnlsInfo ai : list) {
				sb.append("{label: '" + getFirstAuthor(ai.getzAxis()) + "'},");
			}
			sb.replace(sb.lastIndexOf(","), sb.lastIndexOf(",") + 1, "]");
			return sb.toString();
		}else{
			return "[{label:''}]";
		}

	}

	public static void main(String[] args) throws Exception {
		List<AnlsInfo> list = new ArrayList<AnlsInfo>();
		AnlsInfo ai1 = new AnlsInfo();
		AnlsInfo ai2 = new AnlsInfo();
		AnlsInfo ai3 = new AnlsInfo();
		AnlsInfo ai4 = new AnlsInfo();
		ai1.setxAxis("x1");
		ai2.setxAxis("x2");
		ai3.setxAxis("x3");
		ai4.setxAxis("x4");
		ai1.setyAxis("1");
		ai2.setyAxis("2");
		ai3.setyAxis("3");
		ai4.setyAxis("4");
		ai1.setzAxis("z1,a");
		ai2.setzAxis("z1,b");
		ai3.setzAxis("z2,a");
		ai4.setzAxis("z2,b");
		list.add(ai1);
		list.add(ai2);
		list.add(ai3);
		list.add(ai4);
//		String json=BaseUtil.getJsonFromObject(list);
//		System.out.println(getLineData1(json));
		List<String> zArray=new ArrayList<String>();
		for(AnlsInfo ai:list){
			if(!zArray.contains(ai.getzAxis())){
				zArray.add(ai.getzAxis());
			}
		}
//		System.out.println(getLineData(list));
//		System.out.println(getLineZArray(list));
//		System.out.println(zArray);
//		System.out.println(getBarZArray(list));
		System.out.println(getLineZArray(list));
	}
	
	private static String getLineDataJson(List<AnlsInfo> list){
		List<String> zArray=new ArrayList<String>();
		for(AnlsInfo ai:list){
			if(!zArray.contains(ai.getzAxis())){
				zArray.add(ai.getzAxis());
			}
		}
		List<List<IndexInfo>> zlist=new ArrayList<List<IndexInfo>>();
		for(String z:zArray){
			List<IndexInfo> iList=new ArrayList<IndexInfo>();
			for(AnlsInfo ai:list){
				if(z.equals(ai.getzAxis())){
					IndexInfo ii=new IndexInfo();
					ii.setKey(ai.getxAxis());
					ii.setValue(ai.getyAxis());
					iList.add(ii);
				}
			}
			zlist.add(iList);
		}
		return BaseUtil.getJsonFromObject(zlist);
	}
	
	/**
	 * 线图转换__表格数据
	 * @param list
	 * @return 返回二维表格数据
	 */
	public static  String getLineData(List<AnlsInfo> list){
		String json=getLineDataJson(list);
		String data = null;
		data = json.replace("{", "[").replace("}", "]")
				.replaceAll("\\\"key\\\":\\\"(.*?)\\\"", "'$1'")
				.replaceAll("\\\"value\\\":\\\"(\\d+)\\\"", "$1")
				.replaceAll(",\\\"zAxis\\\":null", "");
		return data;
	}
	

	
	public static String getBarData(List<AnlsInfo> list){
		List<String> zArray=new ArrayList<String>();
		for(AnlsInfo ai:list){
			if(!zArray.contains(ai.getzAxis())){
				zArray.add(ai.getzAxis());
			}
		}
		List<List<String>> yList=new ArrayList<List<String>>();
		for(String z:zArray){
			List<String> yArray=new ArrayList<String>();
			for(AnlsInfo ai:list){
				if(z.equals(ai.getzAxis())){
					yArray.add(ai.getyAxis());
				}
			}
			yList.add(yArray);
		}
		String json=BaseUtil.getJsonFromObject(yList);
		json=json.replaceAll("\\\"","");
		return json;
	}
	/**
	 * 柱图xArray
	 * @param list
	 * @return
	 */
	public static String getBarXArray(List<AnlsInfo> list) {
		List<String> xArray=new ArrayList<String>();
		for(AnlsInfo ai:list){
			if(!xArray.contains(ai.getxAxis())){
				xArray.add(ai.getxAxis());
			}
		}
		for(int i=0;i<xArray.size();i++){
			xArray.set(i, "'"+xArray.get(i)+"'");
		}
		return xArray.toString();
	}
	

	
	/**
	 * 根据作者得到第一作者（提取字符串;前的数据）
	 * @param author
	 * @return
	 */
	public static String getFirstAuthor(String author){
		if(author==null){
			return "";
		}
		String regex = "^(.+?)[,;]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(author);
		if (matcher.find()) {
			String group = matcher.group(1);
			return group;
		}else {
			return author;
		}
	}
	
	/**
	 * 判断是否符合带.的日期格式
	 * @param str
	 * @return
	 */
	public static boolean checkDate(String str){
		String regex = "\\d{4}|\\d{4}\\.\\d{2}|\\d{4}\\.\\d{2}\\.\\d{2}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	/**
	 * 判断申请号或公开号
	 * @param str
	 * @return
	 */
	public static boolean checkPnm(String str){
		String regex ="^[A-Za-z0-9.()]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	/**
	 * 判断分类号
	 * @param str
	 * @return
	 */
	public static boolean checkSic(String str){
		String regex ="^[A-Za-z0-9.()/]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	/**
	 * 判断英文关键字
	 * @param str
	 * @return
	 */
	public static boolean checkEn(String str){
		String regex ="^[A-Za-z]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 得到分类导航树
	 * @return
	 */
	public static String getZnode(){
		List<Znode> list=new ArrayList<Znode>();
		Znode n1=new Znode("1","0","地面系统","地面系统","");
		Znode n2=new Znode("2","0","电法","电法","");
		Znode n3 = new Znode("3","0","声波","声波","");
		Znode n4 = new Znode("4","0","放射性","放射性","");
		Znode n5 = new Znode("5","0","核磁","核磁","");
		Znode n6 = new Znode("6","0","取样","取样","");
		Znode n7 = new Znode("7","0","地质导向","地质导向","");
		Znode n8 = new Znode("8","0","随钻测井","随钻测井","");
		Znode n9 = new Znode("9","0","生产井","生产井","");
		Znode n10 = new Znode("10","0","其他","其它","");
//		Znode n1=new Znode("1","0","地面系统","javascript:cls('地面系统','');");
//		Znode n2=new Znode("2","0","电法","javascript:cls('电法','');");
//		Znode n3 = new Znode("3","0","声波","javascript:cls('声波','');");
//		Znode n4 = new Znode("4","0","放射性","javascript:cls('放射性','');");
//		Znode n5 = new Znode("5","0","核磁","javascript:cls('核磁','');");
//		Znode n6 = new Znode("6","0","取样","javascript:cls('取样','');");
//		Znode n7 = new Znode("7","0","地质导向","javascript:cls('地质导向','');");
//		Znode n8 = new Znode("8","0","随钻测井","javascript:cls('随钻测井','');");
//		Znode n9 = new Znode("9","0","生产井","javascript:cls('生产井','');");
//		Znode n10 = new Znode("10","0","其他","javascript:cls('其它','');");
		list.add(n1);
		list.add(n2);
		list.add(n3);
		list.add(n4);
		list.add(n5);
		list.add(n6);
		list.add(n7);
		list.add(n8);
		list.add(n9);
		list.add(n10);
		for(int i=1;i<11;i++){
			Znode n11= new Znode(i+"1",i+"","国内",getCl(i),"中石油,中石化,中海油");
			Znode n12= new Znode(i+"2",i+"","国外",getCl(i),"斯伦贝谢,哈里伯顿,贝克休斯,威德福");
			Znode n111 = new Znode(i+"11",i+"1","中石油",getCl(i),"中石油");
			Znode n112 = new Znode(i+"12",i+"1","中石化",getCl(i),"中石化");
			Znode n113 = new Znode(i+"13",i+"1","中海油",getCl(i),"中海油");
			Znode n121 = new Znode(i+"21",i+"2","斯伦贝谢",getCl(i),"斯伦贝谢");
			Znode n122 = new Znode(i+"22",i+"2","哈里伯顿",getCl(i),"哈里伯顿");
			Znode n123 = new Znode(i+"23",i+"2","贝克休斯",getCl(i),"贝克休斯");
			Znode n124 = new Znode(i+"24",i+"2","威德福",getCl(i),"威德福");
			list.add(n11);
			list.add(n12);
			list.add(n111);
			list.add(n112);
			list.add(n113);
			list.add(n121);
			list.add(n122);
			list.add(n123);
			list.add(n124);
		}
		return BaseUtil.getJsonFromObject(list);
	}
	
	private static String getClsUrl(int i,String conm){
		String[] nacls={"地面系统","电法","声波","放射性","核磁","取样","地质导向","随钻测井","生产井","其它"};
		String conms="";
		if("国内".equals(conm)){
			conms="中石油,中石化,中海油";
		}else if("国外".equals(conm)){
			conms="斯伦贝谢,哈里伯顿,贝克休斯,威德福";
		}else{
			conms=conm;
		}
		StringBuilder sb=new StringBuilder();
		sb.append("cls('").append(nacls[i-1]).append("','").append(conms).append("')");
//		sb.append("javascript:cls('").append(nacls[i-1]).append("','").append(conms).append("');");
		return sb.toString();
	}
	
	private static String getCl(int i){
		String[] nacls={"地面系统","电法","声波","放射性","核磁","取样","地质导向","随钻测井","生产井","其它"};
		return nacls[i-1];
	}
	
	//根据属性名，得到get方法
	public static  String getAttributeValue(Class<?> c,String key){
		//属性开头字母大写，前面加上get得到方法名
		String method="get"+key.replaceFirst(key.substring(0, 1), key.substring(0, 1).toUpperCase());
		String value=null;
		try {
			//通过方法名，调用方法
			value= (String) c.getMethod(method, new Class[]{}).invoke(c, new Object[]{});
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//根据属性名，动态set属性
	public static  TestEntity setTestAttributeValue(TestEntity p,String key,String value){
		try {
			Field f = p.getClass().getDeclaredField(key);
			f.setAccessible(true);  
			if(f.getType()==Integer.class){
				f.set(p, Integer.parseInt(value));
			}else{
				System.out.println("字段类型：非数字");
				f.set(p, value); 
			};
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}  
		return p;
	}
	
	
	/**通过公开号得到专利类型(国内)*/
	public static String getPtype(String pn){
		String pt="";
		if(pn.startsWith("CN")){
			String ctype="";
			if(pn.startsWith("CN1")){
				ctype="CN1";
			}else if(pn.startsWith("CN2")){
				ctype="CN2";
			}else if(pn.startsWith("CN3")){
				ctype="CN3";
			}else{
				char char5=pn.charAt(4);
				if(char5=='1'){
					ctype="CN1";
				}else if(char5=='2'){
					ctype="CN2";
				}else if(char5=='3'){
					ctype="CN3";
				}
			}
			if("CN1".equals(ctype)){
				String pnm=pn.substring(2);
				if(pnm.contains("B")||pnm.contains("C")){
					pt="CN1B";
				}else{
					pt=ctype;
				}
			}else{
				pt=ctype;
			}
		}else{
			pt=pn.substring(0,2);
		}
		return pt;
	}
}
