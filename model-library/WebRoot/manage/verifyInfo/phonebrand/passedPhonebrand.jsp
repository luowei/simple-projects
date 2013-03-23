<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<script type="text/javascript" charset="utf-8">
function changeCode()
{
	var brandName=document.getElementById("brandName").value;
	brandName=encodeURI(brandName);
	brandName=encodeURI(brandName);
	document.getElementById("brandName").value=brandName;
	
}
/*提交查询表单*/

function submitForm()
{
	document.getElementById("filterPhoneBrandForm").submit();
}
			jQuery(function($){
			$('#startDate').datepicker({
					yearRange: '1900:2099', //取值范围.
					showOn: 'both', //输入框和图片按钮都可以使用日历控件。
					buttonImage: '${ctx}/js/date/calendar.gif', //日历控件的按钮
					buttonImageOnly: true,
					showButtonPanel: true
				});	
			$('#endDate').datepicker({
					yearRange: '1900:2099', //取值范围.
					showOn: 'both', //输入框和图片按钮都可以使用日历控件。
					buttonImage: '${ctx}/js/date/calendar.gif', //日历控件的按钮
					buttonImageOnly: true,
					showButtonPanel: true
				});	

			});
</script>
<body>
<!--根据条件查询手机平台信息  -->
<form id="filterPhoneBrandForm" action="${ctx }/manage/verifyPhonebrand/list.do" method="post" onsubmit="changeCode()">
<table>
	<tr>
		<td>
			<select name="key" onchange="submitForm()">
				<option value="0" <c:if test="${brandStatus==0}">selected</c:if>>未审核</option>
				<option value="1" <c:if test="${brandStatus==1}">selected</c:if>>审核通过</option>
				<option value="2" <c:if test="${brandStatus==2}">selected</c:if>>审核不通过</option>
				<option value="3" <c:if test="${brandStatus==3}">selected</c:if>>已审核</option>
			</select>
		</td>
		<td>
			品牌名称:<input id="autocomplete" name="brandName" />
		</td>
		<td>开始时间</td>
		<td><input type="text" name="startDate" id="startDate" value="${startDate }"></td>
		<td>结束时间</td>
		<td><input type="text" name="endDate" id="endDate" value="${endDate }"></td>
		<td><input type="submit" value="搜索"/>
		<a href="${ctx }/manage/verifyPhonebrand/clearAll/1.do">清空</a>
	</tr>
	<tr>
	<td height="22" colspan="7">
		 <img alt="搜索" src="${ctx }/manage/images/searchbg1.gif">		
		 <c:forEach items="${filterList}" var="condition" varStatus="s">
			<c:if test="${s.count!=1}">
				+
			</c:if>
			${condition.displayName }<a href="${ctx }/manage/verifyPhonebrand/list.do">删除</a>
		 </c:forEach>
	</td>
	</tr>
</table>
</form> 
<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 
      style="WIDTH: 100%; BORDER-COLLAPSE: collapse" cellSpacing=0 rules=all 
      border=0>
  <tr>
  	<th >
  	序号
  	</th>
    <th >
   		 品牌ID
   		<a href="${ctx }/manage/verifyPhonebrand/list.do${order}id:asc">升序</a>
    	<a href="${ctx }/manage/verifyPhonebrand/list.do${order}id:desc">降序</a>
   	</th>
    <th >
    	品牌名称
    </th>
    <th >品牌图片</th>
	<th >品牌状态</th>
	<th >添加时间
	<a href="${ctx }/manage/verifyPhonebrand/list.do${order }brandAddDate:asc">升序</a>
    <a href="${ctx }/manage/verifyPhonebrand/list.do${order }brandAddDate:desc">降序</a>		
	</th>
  </tr>
<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="s">
  <tr>
  	<td>${index+s.index }</td>
    <td>${phonebrand.id}</td>
    <td>${phonebrand.phonebrandName}</td>
    <td><img src='${phonebrand.phonebrandImage}' /></td>
	<td><font color="green">审核通过</font></td>
	<td>${phonebrand.brandAddDate}</td>
  </tr>
  </c:forEach>
</table>
 ${tag}
