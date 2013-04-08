<%@ page language="java" pageEncoding="utf-8" import="com.rootls.common.tool.common.EncodeUtil"%>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>品牌管理</title>
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
			//$('#userName').attr("value","aa");//填充内容	
			
		});	
		
		
</script>
	<script type="text/javascript">
	function changeURLCode(name)
	{
	    var url="${ctx }/manage/manageInfo/phone/list.do?phonebrandName="+encodeURI(encodeURI(name));
	    window.open(url,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, copyhistory=yes, width=1280, height=1024"); 
	
	}
	function changeCode()
	{
		var inputBrandName=document.getElementById("inputBrandName1").value;
		inputBrandName=encodeURI(inputBrandName);
		inputBrandName=encodeURI(inputBrandName);
		document.getElementById("inputBrandName2").value=inputBrandName;	
	}
	</script>

</head>

<body>
<!--根据条件查询手机平台信息  -->
<form action="${ctx }/manage/manageInfo/phonebrand/list.do" method="post" onsubmit="changeCode()" >
<table>
	<tr>
	    
		<td>品牌名：
		<input type="text" name="tempkey" id="inputBrandName1" width="150px" />
		<input type="hidden" name="key" id="inputBrandName2" width="150px" />
		</td>
		<td>开始时间</td>
		<td><input type="text" name="startDate" id="startDate" value="${startDate }"></td>
		<td>结束时间</td>
		<td><input type="text" name="endDate" id="endDate" value="${endDate }"></td>
		<td><input type="submit" value="搜索"/>
		<a href="${ctx }/manage/manageInfo/phonebrand/addphonebrand.jsp">增加</a>
	</tr>
	<tr>
	<td height="22" colspan="7">
		 <img alt="搜索" src="${ctx }/manage/images/searchbg1.gif">		
		 <c:forEach items="${filterList}" var="condition" varStatus="s">
			<c:if test="${s.count!=1}">
				+
			</c:if>
			${condition.displayName }<a href="${ctx }/manage/manageInfo/phonebrand/list.do${condition.url}">删除</a>
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
    <th >
   		 品牌ID
   	<a href="${ctx }/manage/manageInfo/phonebrand/list.do${order}id:asc">升序</a>
    <a href="${ctx }/manage/manageInfo/phonebrand/list.do${order}id:desc">降序</a>
   	</th>
    <th >品牌名称
   
    </th>
    <th >品牌图片</th>
	<th >品牌状态</th>
	<th >品牌排序</th>
	<th >添加时间
	<a href="${ctx }/manage/manageInfo/phonebrand/list.do${order }brandAddDate:asc">升序</a>
    <a href="${ctx }/manage/manageInfo/phonebrand/list.do${order }brandAddDate:desc">降序</a>		
	</th>
	<th >操作选项</th>
  </tr>
<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="s"  >
  <tr>
    <td>${index+s.index}</td>
    <td>${phonebrand.id}</td>
    <td><a href="#" onclick="changeURLCode('${phonebrand.brandName}')">${phonebrand.brandName}</a></td>
    <td><img src='${phonebrand.brandImage}' /></td>
	<td>${phonebrand.brandStatus}</td>
	<td>${phonebrand.brandDesc}</td>
	<td>${phonebrand.brandAddDate}</td>
	<td><a class=cmdField href="${ctx }/manage/manageInfo/phonebrand/edit/${phonebrand.id}.do">编辑</a>|
	<a class=cmdField href="javascript:if(confirm('确实要删除吗?'))location.href='${ctx }/manage/manageInfo/phonebrand/delete/${phonebrand.id}.do';">删除</A>
    </td>
  </tr>
  </c:forEach>
</table>
 ${tag}
</body>
</html>