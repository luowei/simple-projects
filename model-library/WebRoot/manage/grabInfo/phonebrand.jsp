<html>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../commons/head.jsp" %>
<%@ include file="../../commons/js.jsp" %>
<form name="form1" action="" menthod="post">
<script src="../../js/jquery-1.6.2.js"></script>
<script type="text/javascript">
    $(function() {
        $("#selAll").click(function() {
            $("#brand :selbrand").attr("checked", true); 
        });
        $("#unselAll").click(function() {
            $("#brand :selbrand").attr("checked", false);
        });
        $("#reverse").click(function() {
            $("#brand :selbrand").each(function() {
                $(this).attr("checked",!$(this).attr("checked"));
            });
        });
    });
</script>
<div id="brand">
<a href="${ctx }/manage/grapInfor/savePhonebrands.do">保存</a>
<a href="${ctx }/manage/grapInfor/removePhonebrands.do">取消</a>
<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 
      style="WIDTH: 100%; BORDER-COLLAPSE: collapse" cellSpacing=0 rules=all 
      border=0>
  <tr>
  	<th	>厂商名称</th>
	<th	>厂商Log</th>
	<th	>相关URL</th>
  </tr>

<c:forEach items="${phonebrands}" var="phonebrand">
  <tr>
    <td>${phonebrand.phonebrandName}</td>
    <td><a href='${phonebrand.phonebrandImage}'><img src='${phonebrand.phonebrandImage}'/></a></td>
    <td><a href='${phonebrand.relateURL}' target="_brand">${phonebrand.relateURL}</a></td>
  </tr>
</c:forEach>
</table></div>
</form>

