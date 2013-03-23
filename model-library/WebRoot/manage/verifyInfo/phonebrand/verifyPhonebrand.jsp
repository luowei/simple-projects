<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<head>

<script type="text/javascript">

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
/*将url加上选择的brandid type为url内型*/
function getSelectId(type)
{
	var temp="";
	var url;
	$('input[@name="brandId"][@checked]').each(function(){ 
									temp=temp+"brandId="+$(this).val()+"&"; 
										}
	); 
	if(temp!="")
	{
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
		return true;
	}
	else
	{
		alert("请选择要处理的数据！");
		return false;
	}
	

}
function allSelected()
{
	$('input[@name="brandId"]').attr("checked", "checked");
	
}
function reverseSelected()
{
	var arr = $('input[@name="brandId"]');
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
<!--根据条件查询手机平台信息  -->
<form id="filterPhoneBrandForm"  action="${ctx }/manage/verifyPhonebrand/list.do" method="post" onsubmit="changeCode()">
<table >
	<tr>
		<td>
			<select name="key" onchange="submitForm()" style="max-width:100px">
				<option  value="0" <c:if test="${brandStatus==0}">selected</c:if>>未审核</option>
				<option value="1" <c:if test="${brandStatus==1}">selected</c:if>>审核通过</option>
				<option value="2" <c:if test="${brandStatus==2}">selected</c:if>>审核不通过</option>
				<option value="3" <c:if test="${brandStatus==3}">selected</c:if>>已审核</option>
			</select>
		</td>
		<td>
			品牌名称:<input id="autocomplete" name="brandName" style="max-width:100px" />
		</td>
		<td>开始时间:<input type="text" name="startDate" style="max-width:100px" id="startDate" value="${startDate }"></td>
		<td>结束时间:<input type="text" name="endDate" style="max-width:100px" id="endDate" value="${endDate }"></td>
		<td>
		<input type="submit" value="搜索" style="max-width:100px"/>
		<a href="${ctx }/manage/verifyPhonebrand/clearAll/0.do">清空</a>
				<a href="${ctx }/manage/verifyPhonebrand/mutiCheckPassed.do" onclick="return getSelectId('1')" id="tg">通过</a>
		<a href="${ctx }/manage/verifyPhonebrand/mutiCheckUnpass.do" onclick="return getSelectId('2')" id="btg">不通过</a>
		<td>
	
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
  		<a href="#" onclick="allSelected()">全选</a>
		<a href="#" onclick="reverseSelected()">反选</a>
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
	<th>关于</th>
	<th >操作选项</th>
  </tr>
<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="s">
  <tr>
  	<td><input type="checkbox" name="brandId" value="${phonebrand.id}"/>${index+s.index }</td>
    <td>${phonebrand.id}</td>
    <td>${phonebrand.phonebrandName}</td>
    <td><img src='${phonebrand.phonebrandImage}' /></td>
	<td><font color="blue">未审核</font></td>
	<td>${phonebrand.brandAddDate}</td>
	<td><a href="${phonebrand.relateURL}" target="_blank">更多</a></td>
	<td>
		<a class=cmdField href="${ctx }/manage/verifyPhonebrand/checkPassed/${phonebrand.id}.do">通过</a>|
		<a class=cmdField href="${ctx }/manage/verifyPhonebrand/checkUnPass/${phonebrand.id}.do">不通过</a>
    </td>
    
  </tr>
  </c:forEach>
</table>
 ${tag}
