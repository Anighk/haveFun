<#-- 后台管理导航栏 -->
<div class="top">  <div class="cl"><img src="images/logo.png" width="353" height="83"" /></div>
<div id="toBack"></div>
<div id="daoru1"></div>
<div id="daoru2"></div>
<div id="daoru3"></div>
<div id="shanchu"></div>
<div id="nav_scheck"></div>
</div>
	<script type="text/javascript">
	   $(function () {
	   		<#if navIndex??>
		   var c=$('${navIndex}').attr("class");
		   $('${navIndex}').attr("class",c+"h");
		   </#if>
		   
	   });		
	</script>
<div class="body">
<div class="left">
<div class="menu">管理后台</div>

<a href="listProjs.do"><div class="menu6" id="admnav_proj" >项目管理</div></a>
<a href="listSignds.do"><div class="menu5" id="admnav_signdict" >标引管理</div></a>
<a href="listNavs.do" id="adm_nav" style="display:none"><div class="menu7" id="admnav_nav" >分类管理</div></a>
<a href="listUsers.do" id="adm_user" style="display:none"><div class="menu1" id="admnav_user">用户管理</div></a>
<a href="listTopics.do"  id="adm_topic" style="display:none"><div class="menu3" id="admnav_topic">主题管理</div></a>
<a href="listReplys.do"  id="adm_reply" style="display:none"><div class="menu4" id="admnav_reply">回复管理</div></a>
<a href="listLogs.do"  id="adm_log" style="display:none"><div class="menu2" id="admnav_log">日志管理</div></a>
</div>
