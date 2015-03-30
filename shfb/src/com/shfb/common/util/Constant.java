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
	
	/**专利类型与编号对应表*/
	
	public static final String T00="CN1";
	public static final String T000="CN";
	public static final String T01="CN2";
	public static final String T02="CN3";
	public static final String T03="CN1B";
	public static final String T04="US";
	public static final String T05="JP";
	public static final String T06="KR";
	public static final String T07="GB";
	public static final String T08="FR";
	public static final String T09="CH";
	public static final String T10="DE";
	public static final String T11="RU";
	public static final String T12="EP";
	public static final String T13="WO";
	public static final String T14="AU";
	public static final String T15="CA";
	
	public static final String T00_FMGB="中国发明公布";
	public static final String T01_SYXX="中国实用新型";
	public static final String T02_WGSJ="中国外观设计";
	public static final String T03_FMSQ="中国发明授权";
	public static final String T000_CN="中国";
	public static final String T04_US="美国";
	public static final String T05_JP="日本";
	public static final String T06_KR="韩国";
	public static final String T07_GB="英国";
	public static final String T08_FR="法国";
	public static final String T09_CH="瑞士";
	public static final String T10_DE="德国";
	public static final String T11_RU="俄罗斯";
	public static final String T12_EPO="EPO";
	public static final String T13_WIPO="WIPO";
	public static final String T14_AU="澳大利亚";
	public static final String T15_CA="加拿大";
	
	/**分析标题对应表*/
	
	public static final String AD="ad";
	public static final String PD="pd";
	public static final String IPC1="ipc1";
	public static final String IPC3="ipc3";
	public static final String IPC4="ipc4";
	public static final String CITING="citing";
	public static final String CITED="cited";
	public static final String PA="pa";
	public static final String INN="inn";
	public static final String AGC="agc";
	public static final String AGT="agt";
	public static final String NP="np";
	public static final String LAW="law";
	
	public static final String AD_="申请年分析";
	public static final String PD_="公开年分析";
	public static final String IPC1_="主分类号（按部）分析";
	public static final String IPC3_="主分类号（按大类）分析";
	public static final String IPC4_="主分类号（按小类）分析";
	public static final String CITING_="引证分析";
	public static final String CITED_="被引证分析";
	public static final String PA_="申请人分析";
	public static final String INN_="发明人分析";
	public static final String AGC_="代理机构分析";
	public static final String AGT_="代理人分析";
	public static final String NP_="国省分析";
	public static final String LAW_="法律状态分析";
	//数据库表名
	public static final String TABLE_PATENTSTH="patentsth";
	public static final String TABLE_CITINGS="citings";
	public static final String TABLE_PATENTSLAW="patentslaw";
	public static final String TABLE_COGNATIONS="cognations";
	public static final String TABLE_PROJPATENTS="projpatents";
	public static final String TABLE_PROJS="projs";
	public static final String TABLE_SIGNDICT="signdict";
	public static final String TABLE_USERSIGN="usersign";
	public static final String TABLE_NAVIGATION="navigation";
	public static final String TABLE_ASSIGNTASK="assigntask";
	public static final String TABLE_QCRECORD="qcrecord";
	//Patentsth表名含义
	public static final String SQH = "申请号";
	public static final String GKH = "公开号";
	public static final String SQRQ = "申请日";
	public static final String GKRQ = "公开日";
	public static final String SQR = "申请人";
	public static final String FMR = "发明人";
	
	 public static final String[] ZLX_1ROW_NAMES = {"公开号",
	                                          "标题 (原文)",
	                                          "标题 - DWPI",
	                                          "标题词 - DWPI",
	                                          "摘要 - DWPI 新颖性",
	                                          "摘要 - DWPI 详细说明",
	                                          "摘要 - DWPI 用途",
	                                          "摘要 - DWPI 优势",
	                                          "摘要 - DWPI 技术要点",
	                                          "专利权人/申请人",
	                                          "专利权人 - DWPI",
	                                          "公开日期",
	                                          "公开月",
	                                          "申请号",
	                                          "申请日期",
	                                          "优先权号",
	                                          "优先权国家/地区",
	                                          "优先权日",
	                                          "优先权日 - 最早",
	                                          "相关公开号",
	                                          "PCT 申请号",
	                                          "PCT 申请日期",
	                                          "PCT 公开号",
	                                          "PCT 公开日期",
	                                          "IPC - 现版",
	                                          "IPC 大类",
	                                          "IPC 大类组",
	                                          "IPC 部",
	                                          "IPC 小类",
	                                          "IPC 子组",
	                                          "IPC 现版完整",
	                                          "IPC - 现版 - DWPI",
	                                          "IPC 大类- DWPI",
	                                          "IPC 大类组 - DWPI",
	                                          "IPC 部 - DWPI",
	                                          "IPC 小类- DWPI",
	                                          "IPC 子组 - DWPI",
	                                          "CPC - 现版",
	                                          "CPC - 现版 - DWPI",
	                                          "CPC 分类",
	                                          "CPC 大类 - DWPI",
	                                          "美国分类",
	                                          "美国分类 (已划分)",
	                                          "美国分类 - 主类",
	                                          "美国分类 - 原始",
	                                          "DWPI 分类",
	                                          "DWPI 手工代码",
	                                          "施引专利",
	                                          "施引专利计数",
	                                          "施引专利第一专利权人",
	                                          "INPADOC 法律状态",
	                                          "INPADOC 法律状态代码",
	                                          "INPADOC 法律状态日期",
	                                          "INPADOC 法律状态影响",
	                                          "INPADOC 法律状态文本",
	                                          "INPADOC 法律状态专利权人",
	                                          "INPADOC 法律状态评论",
	                                          "美国专利颁发后状态",
	                                          "美国专利维持状态",
	                                          "美国专利转让",
	                                          "美国专利转让 - 最新",
	                                          "美国专利转让 - 受让者 - 最新",
	                                          "美国专利转让 - 转出者 - 最新",
	                                          "美国专利转让 - 日期 - 最新",
	                                          "美国诉讼",
	                                          "EPO 审查程序状态",
	                                          "INPADOC 同族专利成员",
	                                          "DWPI 同族专利成员",
	                                          "DWPI 同族专利成员计数",
	                                          "摘要",
	                                          "摘要 - DWPI",
	                                          "权利要求",
	                                          "首页附图",
//	                                          "PDF 副本",
	                                          "标题",
	                                          "标题 (英语)",
	                                          "标题 (法语)",
	                                          "标题 (德语)",
	                                          "标题 (西班牙语)",
	                                          "摘要 (英语)",
	                                          "摘要 (法语)",
	                                          "摘要 (德语)",
	                                          "摘要 (原文)",
	                                          "摘要 (西班牙语)",
	                                          "摘要 - DWPI 生物活性",
	                                          "摘要 - DWPI 生物学机制",
	                                          "摘要 - DWPI 附图说明",
	                                          "权利要求计数",
	                                          "权利要求 (英语)",
	                                          "权利要求 (法语)",
	                                          "权利要求 (德语)",
	                                          "权利要求 (西班牙语)",
	                                          "权利要求第一项",
	                                          "权利要求第一项 - DWPI",
	                                          "独立权利要求",
	                                          "说明书",
	                                          "专利权人/申请人 (首位)",
	                                          "专利权人 - 标准化",
	                                          "专利权人 - 原始",
	                                          "专利权人 - 原始 (带有地址)",
	                                          "专利权人代码 - DWPI",
	                                          "专利权人计数",
	                                          "发明人",
	                                          "第一发明人",
	                                          "发明人 - 原始",
	                                          "发明人 - 带有地址",
	                                          "发明人 - DWPI",
	                                          "发明人计数",
	                                          "代理人",
	                                          "代理人 - 含地址",
	                                          "代理机构",
	                                          "代理机构 - 带有地址",
	                                          "审查员",
	                                          "DWPI 入藏号",
	                                          "公开国家/地区代码",
	                                          "公开专利文献类型识别代码",
	                                          "公开年",
	                                          "申请国家/地区",
	                                          "申请月",
	                                          "申请年",
	                                          "美国临时专利申请",
	                                          "优先权月",
	                                          "优先权年",
	                                          "最早优先权年",
	                                          "相关专利申请",
	                                          "相关专利申请号",
	                                          "相关专利申请日期",
	                                          "相关公开日期",
	                                          "IPC 现版完整 (4 个字符)",
	                                          "CPC - 现版主要",
	                                          "CPC - 现版主要 - DWPI",
	                                          "CPC - 原始主要",
	                                          "CPC - 原始主要 - DWPI",
	                                          "CPC - 现版 - 组合代码",
	                                          "ECLA",
	                                          "洛迦诺分类",
	                                          "日本 F Term",
	                                          "日本 FI 分类号",
	                                          "引用的参考文献 - 专利",
	                                          "引用的参考文献详细信息 - 专利",
	                                          "引用的参考文献数 - 专利",
	                                          "引用的专利第一专利权人",
	                                          "引用的参考文献 - 非专利",
	                                          "引用的参考文献计数 - 非专利",
	                                          "施引参考文献详细信息 - 专利",
	                                          "美国专利转让 - 受让者",
	                                          "美国专利转让 - 转出者",
	                                          "美国专利转让 - 日期",
	                                          "指定国/地区",
	                                          "异议 (EP)",
	                                          "异议 (EP) - 异议人",
	                                          "异议 (EP) - 异议申请日期",
	                                          "许可 (EP)",
	                                          "异议 (EP) - 律师",
	                                          "许可 (EP) - 被许可者",
	                                          "许可 (EP) - 许可日期",
	                                          "美国政府投资研发",
	                                          "公开语言",
	                                          "DWPI 更新",
	                                          "INPADOC 同族专利 ID",
	                                          "DWPI 同族专利国家/地区计数",
	                                          "首页图像",
	                                          "记录来源"
	 };

}
