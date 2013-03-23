<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../commons/head.jsp" %>
<%@ include file="../../commons/js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>审核信息</title>
	<style type="text/css">
	.ui-tabs { padding: .2em; zoom: 1; width:100%; height:98%;}
	</style>
	<link rel="stylesheet" type="text/css" href="../../css/a.css">
    <script src="../../js/jquery-1.6.2.js" type="text/javascript"></script> 
    <link href="../../themes/ui-lightness/jquery-ui-1.8.16.custom.css"../../js/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" /> 
    <script src="../../ui/jquery-ui-1.8.16.custom.js" type="text/javascript"></script> 
	<script type="text/javascript" >
		$(function()
		{
			$("#mydialog").dialog(); 
            $("#select").tabs(); 
			$("#select").click(function() { $("#info").css("display", ""); });
		});
	</script>
	<script type="text/javascript">
	function loadPhonebrands(type)
	{
	 	alert(type);
		
	}
	function loadPhones(type)
	{
	 	alert(type);
		
	}
	</script>
</head>

<body style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(${ctx }/manage/images/bg.gif); BACKGROUND-REPEAT: repeat-x">
<!--
<h1>审核信息</h1>
-->

<div id="select">
<ul> 
			<li><a href="#phonebrands">品牌信息</a></li>
			<li><a href="#phones">手机信息</a></li>
</ul> 
<div id="phonebrands">
<!-- 
<div id="phonebrandtype">
<a href="#" onclick="loadPhonebrands('1')">未审核数据|</a>
<a href="#" onclick="loadPhonebrands('2')">已审核的数据|</a>
<a href="#" onclick="loadPhonebrands('3')" >审核未通过|</a>
<a href="#" onclick="loadPhonebrands('4')">审核已通过|</a>
</div>
 -->
<iframe frameborder=0 height=88% width=100% src="${ctx }/manage/verifyPhonebrand/list.do" id="ifphonebrands"></iframe>	
</div>
		
<div id="phones">
<iframe frameborder=0 height=88% width=100% src="${ctx }/manage/verifyPhone/list.do" id="ifphones"></iframe>
</div>
</body>
</html>
