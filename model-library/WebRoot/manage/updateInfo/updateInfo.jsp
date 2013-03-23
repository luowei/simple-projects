<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../commons/head.jsp" %>
<%@ include file="../../commons/js.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息传输</title>
<script src="../../js/jquery-1.6.2.js" type="text/javascript"></script> 
<link href="../../themes/ui-lightness/jquery-ui-1.8.16.custom.css"../../js/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" /> 
<script src="../../ui/jquery-ui-1.8.16.custom.js" type="text/javascript"></script> 
<script type="text/javascript" >
	$(function(){
		$("#inputPattern").tabs()
		//$("#select").click(function() { $("#info").css("display", ""); });
	});
</script>


</head>

<body>
BASEURL :${ctx}/public/getInfor/json.do?<br />
基本参数：inforType=[platform,phonebrand,phone&DataType]&DataType=JSON&PageSize=int<br/>
机型参数：platformName=[utf-8双编码] phonebrandName=[utf-8双编码]
品牌参数：phonebrandName=[utf-8双编码]<br />
平台参数：platformName=[utf-8双编码]<br />
机型：<a href="${ctx}/public/getInfor/json.do?inforType=platform&DataType=JSON&PageSize=20">机型</a>
平台：<a href="${ctx}/public/getInfor/json.do?inforType=phonebrand&DataType=JSON&PageSize=20">平台</a>
品牌：<a href="${ctx}/public/getInfor/json.do?inforType=phone&DataType=JSON&PageSize=20">品牌</a>
${ctx}
</body>
</html>
