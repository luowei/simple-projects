<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<head>
	<title>未审核的机型信息</title>

<script type="text/javascript">
/*提交查询表单*/
function submitForm()
{
	document.getElementById("filterPhoneForm").submit();
}
/*将url加上选择的phoneId type为url类型*/
function getSelectId(type)
{
	var temp="";
	var url;
	$('input[@name="phoneId"][@checked]').each(function(){ 
									temp=temp+"phoneId="+$(this).val()+"&"; 
										}
	); 
	
	if(type==1)
	{
		url=$("#tg").attr("href")+"?"+temp+"time="+new Date();
		$("#tg").attr("href",url); //改变通过的url
	}
	else if(type==2)
	{
		url=$("#btg").attr("href")+"?"+temp+"time="+new Date();
		$("#btg").attr("href",url); //改变不通过的url
	}
	

}
function allSelected()
{
	$('input[@name="phoneId"]').attr("checked", "checked");
	
}
function reverseSelected()
{
	var arr = $('input[@name="phoneId"]');
    for (var i = 0; i < arr.length; i++) 
    {
                arr[i].checked = !arr[i].checked;
    }

}


</script>
</head>
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
<body>
<script type="text/javascript" >
var msg=""+${message};
if(msg!="")
{
	alert(msg);
}
</script>
<form id="filterPhoneForm" action="${ctx}/manage/verifyPhone/list.do" method="post">
<table>
	<tr>
		<td>状&nbsp;&nbsp;态:
			<select name="phoneStatus" onchange="submitForm()">
				<option value="0" <c:if test="${phoneStatus==0}">selected</c:if>>未审核</option>
				<option value="1" <c:if test="${phoneStatus==1}">selected</c:if>>审核通过</option>
				<option value="2" <c:if test="${phoneStatus==2}">selected</c:if>>审核不通过</option>
				<option value="3" <c:if test="${phoneStatus==3}">selected</c:if>>已审核</option>
			</select><br/>
			品牌名:<input type="text" id="brandName" style="max-width:100px" name="brandName" />
		</td>
		<td>
			机型名:<input type="text" id="phoneName" style="max-width:100px" name="key" value="${phoneName }"/><br/>
			平台名:<input type="text" id="phoneOs" style="max-width:100px" name="phoneOs" value="${phoneOs }"/>
		</td>
		<td>开始时间</td>
		<td><input type="text" name="startDate" id="startDate" style="max-width:100px" value="${startDate }"></td>
		<td>结束时间</td>
		<td><input type="text" name="endDate" id="endDate" style="max-width:100px" value="${endDate }"></td>
		<td><input type="submit" value="搜索"/>
		<a href="${ctx }/manage/verifyPhone/mutiCheckPassed.do" onclick="return getSelectId('1')" id="tg">审核通过</a>
		<a href="${ctx }/manage/verifyPhone/mutiCheckUnpass.do" onclick="return getSelectId('2')" id="btg">审核不通过</a>
		<a href="${ctx }/manage/verifyPhone/passAllPhones.do" onclick="submitForm()">一键审核</a>
		<a href="${ctx }/manage/verifyPhone/clearAll.do?phoneStatus=${phoneStatus}&brandName=&phoneOs=" onclick="">清空</a>
	</tr>
	<tr>
	<td height="22" colspan="7">
		 <img alt="搜索" src="${ctx }/manage/images/searchbg1.gif">		
		 <c:forEach items="${filterList}" var="condition" varStatus="s">
			<c:if test="${s.count!=1}">
				+
			</c:if>
			${condition.displayName }<a href="${ctx }/manage/verifyPhone/list.do">删除</a>
		 </c:forEach>
	</td>
	</tr>
</table>
</form>

<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 
      style="WIDTH: 100%; BORDER-COLLAPSE: collapse" cellSpacing=0 rules=all 
      border=0>
  <tr>
  	<th>
  		序号
  		<a href="#" onclick="allSelected()">全选</a>
		<a href="#" onclick="reverseSelected()">反选</a>
  	</th>
  	<th >
   		 ID
   		<a href="${ctx }/manage/verifyPhone/list.do${order}id:asc">升序</a>
    	<a href="${ctx }/manage/verifyPhone/list.do${order}id:desc">降序</a>
   	</th>
  	<th	>机型图片</th>
	<th	>手机名称</th>
	<th>品牌名称</th>
	<!-- 
  	<th >手机制式</th>
    <th >手机类型</th>
    -->
	<th >屏幕大小</th>
    <th >触摸屏</th>
	<th >摄像头</th>
	<th >操作系统</th>
	<!--  
	<th >内存大小</th>
	<th >电池容量</th>
	-->
	<th >手机热度</th>
	<th>审核</th>
  </tr>
  <c:forEach items="${phoneList}" var="phone" varStatus="s">
  <tr>
  	<td><input type="checkbox" name="phoneId" value="${phone.id}"/>${index+s.index }</td>
    <td>${phone.id}</td>
    <td><a href='${phone.phonePic}'><img src="${phone.phonePic}" /></a></td>
    <td>${phone.phoneName}</td>
    <td>${phone.brandName }</td>
    <!-- 
	<td>${phone.phoneModel}</td>
	<td>${phone.phoneView}</td>
	-->
	<td>${phone.phoneSize}</td>
	<td>${phone.phoneTouchType}</td>
	<td>${phone.phoneCamera}</td>
	<td>${phone.phoneOs}</td>
	 <!-- 
	<td>${phone.phoneMemery}</td>
	<td>${phone.phoneBatter}</td>
	-->
	<td>${phone.phoneHot}</td>
	<td>
	<a href="${ctx}/manage/verifyPhone/passPhones${phone.id}.do">通过</a>
	<a href="${ctx}/manage/verifyPhone/unpassPhones${phone.id}.do">不通过</a>
	</td>
  </tr>
  </c:forEach>
</table>
 ${tag}

</body>
</html>
