<#macro Copyright>
  <a href="#">版权声明</a>|<a href="#">联系我们</a>| 外网链接：<a href="https://www.thomsoninnovation.com/login" target="_blank">Thomson Innovation</a><br> 版权所有 All rights reserved. Copyright 2014
</#macro>
<#macro Pagination>
   <#if (pageCount > 1)>
    <ul class="pagination pull-right">
   	   <li><span class="pagination_total">共<span class="span_number">${total}</span>条记录</span></li>
      <#if (pageNow > 1)><li><a href="javascript:table_fy(${pageNow - 1});">上一页</a></li></#if>
      <#if (pageStart > 1)><li><a href="javascript:table_fy(1);">1</a></li>
        <#if (pageStart == 3)><li><a href="javascript:table_fy(2);">2</a></li></#if>
        <#if (pageStart > 3)><li class="disabled"><a href="#">...</a></li></#if>
      </#if>
      <#list pageStart..pageEnd as i>
        <li  <#if (i == pageNow)>class="active"</#if>><a href="javascript:table_fy(${i});">${i}</a></li>
      </#list>
      <#if (pageEnd < pageCount)>
        <#if (pageEnd < pageCount - 2)><li class="disabled"><a href="#">...</a></li></#if>
        <#if (pageEnd == (pageCount - 2))><li><a href="javascript:table_fy(${pageCount - 1});">${pageCount - 1}</a></li></#if>
        <li><a href="javascript:table_fy(${pageCount});">${pageCount}</a></li>
      </#if>
      <#if (pageNow < pageCount)><li><a href="javascript:table_fy(${pageNow + 1});">下一页</a></li></#if>
    </ul>
  </#if>
</#macro>
<#macro AdminTi name="">
<div class="right">
  <div class="back">
    <span>${name}</span>
    <span class="tll"><a href="javascript:esc();">退出</a></span>
    <span class="tll"><a href="javascript:history.back(-1);">返回</a></span>
    <span class="tll"><a href="toSignPat.do">回到前台</a></span>
  </div>
</#macro>
