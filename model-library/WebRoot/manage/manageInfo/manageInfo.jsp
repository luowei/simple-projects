<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../commons/head.jsp" %>
<%@ include file="../../commons/js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理</title>
<style type="text/css">
<!--
body {
	background-color: ;
}
-->
.ui-tabs { padding: .2em; zoom: 1; width:100%; height:98%;}

</style>
<link rel="stylesheet" href="../../themes/ui-lightness/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="../../css/tom.css">
<script src="../../js/jquery-1.6.2.js"></script>
<script src="../../ui/jquery.ui.core.js"></script>
<script src="../../ui/jquery.ui.widget.js"></script>
<script src="../../ui/jquery.ui.tabs.js"></script>
<script src="../../ui/jquery.ui.progressbar.js"></script>

<script>
	$(function() {$( "#progressbar1" ).progressbar({value: 59});});
	$(function() {$( "#progressbar2" ).progressbar({value: 19});});
	$(function() {$( "#progressbar3" ).progressbar({value: 79});});
</script>

</head>

<body style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(${ctx }/manage/images/bg.gif); BACKGROUND-REPEAT: repeat-x">
<script>
	$(function() 
			{
				$("#tabs").tabs();
				$("#tabs").tabs({selected:<%=request.getParameter("type")%>});
			}
	 );
</script>
<div id="tabs">
	<ul>
		<li><a href="#tabs-1">平台信息</a></li>
		<li><a href="#tabs-2">品牌信息</a></li>
		<li><a href="#tabs-3">机型信息</a></li>
	</ul>
	<div id="tabs-1">
		<iframe frameborder=0 height=85% width=100% src="${ctx}/manage/manageInfo/platform/list.do"></iframe>
	</div>
	<div id="tabs-2">
	<iframe frameborder=0 height=85% width=100% src="${ctx}/manage/manageInfo/phonebrand/list.do"></iframe>
	</div>
	<div id="tabs-3">
	<iframe frameborder=0 height=85% width=100% src="${ctx}/manage/manageInfo/phone/list.do"></iframe>

	</div>
</div>
</body>
</html>
