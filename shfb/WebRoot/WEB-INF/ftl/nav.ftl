<#-- 专利系统导航栏 -->
<script language="javascript">
	$(function(){
    $('.cl').css({'position':'absolute','left':($(window).width()-1080)/2});
	$(window).resize(function(){  
    $('.cl').css({'position':'absolute','left':($(window).width()-1080)/2});
    })  
});  
</script> 
<div id="adm_user"></div><div id="adm_topic"></div><div id="adm_reply"></div><div id="adm_log"></div><div id="adm_nav"></div>
<div class="login_top">
  <div class="head_J"><img src="images/logo.png" width="353" height="83"" />
    <div class="top_right">
      <div class="top_li" id="toBack" style="display:none"><a href="listProjs.do">后台管理</a></div>
      <div class="top_li"><a href="bbsIndex.do">经验共享</a></div>
  	  <div class="personal"><a href="#">个人中心</a>
  	  <div class="top_li_ul">
   <div class="top_li_li"><a href="showCllc.do">我的收藏</a></div><div class="top_li_li"><a href="toPwdm.do">修改密码</a></div>
   </div>
  	  </div> 
      <div class="top_li"><a href="toHelp.do">帮助中心</a></div>
      <div class="top_li"><a href="javascript:esc();">退出</a></div>       
    </div>
  </div>
</div>
<div class="menu">
<div class="menuh">
		<script type="text/javascript">
	   $(function () {
	   		<#if navIndex??>
		   $('${navIndex}').attr("class","menu1h");
		   </#if>
	   });		
		</script>
	<div class="menu1_datamaneger"><a href="#">数据操作</a>
	<ul>
	<li style="display:none" id="daoru1"><a href="toChkin.do">专利导入</a></li>
	<li><a href="toChkout.do">专利导出</a></li>
	<li style="display:none" id="shanchu"><a href="toDelPat.do">专利删除</a></li>
	<li style="display:none" id="daoru2"><a href="toChss.do">导入已标引库</a></li>
	<li style="display:none" id="daoru3"><a href="toChkinPool.do">导入总库</a></li>
	</ul>
	</div>
	<div class="menu1" id="nav_sgd"><a href="toSignPat.do">标引</a></div>	
	<div class="menu1" id="nav_scheck" style="display:none" ><a href="toCheckSignPat.do">质检</a></div>	
	<div class="menu1" id="nav_qks"><a href="toQks.do">快速检索</a></div>
	<div class="menu1" id="nav_exs"><a href="toExs.do">高级检索</a></div>
	<div class="menu1" id="nav_cls"><a href="toCls.do">分类检索</a></div>
	<div class="menu1" id="nav_ipcs"><a href="toIpcs.do">IPC检索</a></div>
	<div class="menu1" id="nav_signs"><a href="toSsi.do">标引检索</a></div>
	<div class="menu1" id="nav_projview"><a href="projanls.do">项目概览</a></div>
</div></div>


