<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>机型管理</title>
<script type="text/javascript" charset="utf-8">
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
	<script type="text/javascript">
	function changeCode()
	{
		var inputPhoneName=document.getElementById("inputPhoneName1").value;
		inputPhoneName=encodeURI(inputPhoneName);
		inputPhoneName=encodeURI(inputPhoneName);
		document.getElementById("inputPhoneName2").value=inputPhoneName;
		var inputPlatformName=document.getElementById("inputPlatformName1").value;
		inputPlatformName=encodeURI(inputPlatformName);
		inputPlatformName=encodeURI(inputPlatformName);
		document.getElementById("inputPlatformName2").value=inputPlatformName;
		var inputPhonebrandName=document.getElementById("inputPhonebrandName1").value;
		inputPhonebrandName=encodeURI(inputPhonebrandName);
		inputPhonebrandName=encodeURI(inputPhonebrandName);
		document.getElementById("inputPhonebrandName2").value=inputPhonebrandName;
		
			
	}
	</script>
</head>
<body>
<!--根据条件查询手机机型信息  -->
<form action="${ctx }/manage/manageInfo/phone/list.do" method="get" onsubmit="changeCode()">
<table>
	<tr height="22">
		<td>机型名称:<input type="text" name="tempkey" id="inputPhoneName1" /><input type="hidden" name="key" id="inputPhoneName2" />	</td>
		<td>平台名称:<input type="text" name="tempplatformName" id="inputPlatformName1" /><input type="hidden" name="platformName" id="inputPlatformName2" /></td>
		<td>品牌名称:<input type="text" name="tempphonebrandName"  id="inputPhonebrandName1"/><input type="hidden" name="phonebrandName" id="inputPhonebrandName2" /></td>
		<td><input type="submit" value="搜索"/><a href="${ctx }/manage/manageInfo/phone/initPhonePage.do">增加</a></td>
	</tr>
	<tr height="22">
		<td>开始时间:<input type="text" name="startDate" id="startDate" value="${startDate }"></td>
		<td>结束时间:<input type="text" name="endDate" id="endDate" value="${endDate }"></td>
		<td colspan="2">
		</td>
	</tr>
	<tr>
	<td height="22" colspan="4">
		 <img alt="搜索" src="${ctx }/manage/images/searchbg1.gif">		
		 <c:forEach items="${filterList}" var="condition" varStatus="s">
			<c:if test="${s.count!=1}">
				+
			</c:if>
			${condition.displayName }<a href="${ctx }/manage/manageInfo/phone/list.do${condition.url}">删除</a>
		 </c:forEach>
	</td>
	</tr>
</table>
</form>   
  
<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 
      style="WIDTH: 100%; BORDER-COLLAPSE: collapse" cellSpacing=0 rules=all 
      border=0>
  <tr>
    <th>序号</th>
    <th >机型ID
	<a href="${ctx }/manage/manageInfo/phone/list.do${order}id:asc">升序</a>
	<a href="${ctx }/manage/manageInfo/phone/list.do${order}id:desc">降序</a>
	</th>
    <th>机型名称</th>
    <th>平台名称</th>
    <th>品牌品牌</th>
    <th >机型简称</th>
	<th >机型状态</th>
	<th >机型图片</th>
	<th>机型分辨率</th>
	<th>机型排序</th>
	<th >添加时间
	<a href="${ctx }/manage/manageInfo/phone/list.do${order }phoneAddDate:asc">升序</a>
    <a href="${ctx }/manage/manageInfo/phone/list.do${order }phoneAddDate:desc">降序</a>
	</th>
	<th >操作选项</th>
  </tr>
  <c:forEach items="${phoneList}" var="phone" varStatus="s">
  <tr>
    <td>${index+s.index}</td>
    <td>${phone.id}</td>
    <td>${phone.phoneName }</td>
    <td>${phone.platform.platformName}</td>
    <td>${phone.phonebrand.brandName }</td>
	<td>${phone.phoneHeadName }</td>
	<td>${phone.hotPhone }</td>
	<td><img src="${phone.phoneImage }"/></td>
	<td>${phone.phoneResolution }</td>
	<td>${phone.phoneDesc }</td>
	<td>${phone.phoneAddDate }</td>
	<td><a class=cmdField href="${ctx }/manage/manageInfo/phone/edit/${phone.id}.do">编辑</a>|
	<a class=cmdField href="javascript:if(confirm('确实要删除吗?'))location.href='${ctx }/manage/manageInfo/phone/delete/${phone.id}.do';">删除</A>
	</td>
  </tr>
  </c:forEach>
</table>
 ${tag}