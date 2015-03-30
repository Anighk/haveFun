<#macro Copyright>
  <a href="#">版权声明</a>|<a href="#">联系我们</a><br> 版权所有 All rights reserved. Copyright 2014
</#macro>
<#macro Pagination id="pn" func="zl_tz">
  <#if (pageCount > 1)>
    <div class="fenye">
      <#if (pageNow > 1)><a href="javascript:zl_fy(${pageNow - 1});">&lt;</a></#if>
      <#if (pageStart > 1)><a href="javascript:zl_fy(1);">1</a>
        <#if (pageStart == 3)><a href="javascript:zl_fy(2);">2</a></#if>
        <#if (pageStart > 3)>...</#if>
      </#if>
      <#list pageStart..pageEnd as i>
        <a href="javascript:zl_fy(${i});" <#if (i == pageNow)>class="fenye_off"</#if>>${i}</a>
      </#list>
      <#if (pageEnd < pageCount)>
        <#if (pageEnd < pageCount - 2)>...</#if>
        <#if (pageEnd == (pageCount - 2))><a href="javascript:zl_fy(${pageCount - 1});">${pageCount - 1}</a></#if>
        <a href="javascript:zl_fy(${pageCount});">${pageCount}</a>
      </#if>
      <#if (pageNow < pageCount)><a href="javascript:zl_fy(${pageNow + 1});">&gt;</a></#if>
      <#if ((pageEnd - pageStart) < pageCount)>
      <span>转到       <input style ="width:20px;height:30px; " type="text" id="${id}" value="" onkeypress="javascript:if(event.keyCode == 13) ${func}(${pageCount})" /> 页 </span></#if>
    </div>
  </#if>
</#macro>

