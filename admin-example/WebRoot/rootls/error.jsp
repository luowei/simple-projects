<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>后台管理系统-隆众石化商务网</title>
    <script>
        parent.reloadcode();
    </script>
</head>
<body>
<div align="center">
    <p><font size="5">出错信息</font></p>
</div>
<hr/>
<%Exception ex = (Exception) request.getAttribute("exception");%>
<H2>Exception:</H2>
<%ex.printStackTrace(new PrintWriter(out));%>

</body>
</html>
