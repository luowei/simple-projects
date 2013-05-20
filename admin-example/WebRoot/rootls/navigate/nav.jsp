<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script type="text/javascript">
        var ctx = '${ctx}'
        var LzCharset = "UTF-8";
    </script>

    <script type="text/javascript" src="http://exp.oilchem.net/admin/ext3/ext-base-GB2312.js" charset="gb2312"></script>
    <script type="text/javascript" src="http://exp.oilchem.net/admin/ext3/ext-all-GB2312.js" charset="gb2312"></script>
    <script type="text/javascript" src="http://exp.oilchem.net/admin/ext3/ext-lang-zh_CN.js" charset="gb2312"></script>
    <script type="text/javascript" src="${ctx}/js/utils.js"></script>
    <script type="text/javascript" src="${ctx}/js/public.js"></script>
    <script type="text/javascript" src="${ctx}/pcdb/navigate/nav.js"></script>
    <link rel="stylesheet" type="text/css" href="http://exp.oilchem.com/admin/ext3/resources/css/ext-all.css" charset="gb2312"/>
    <style type="text/css">
        * {font-size: 13px;}
    </style>



</head>

<body>
<h1>hello world !</h1>

<%--<div id=PLMWindow></div>--%>
</body>
</html>
