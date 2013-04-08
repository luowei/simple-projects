<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
  String error=(String)request.getAttribute("error");

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
  <div align="center">
    <p><font color="red" size="5"><%=error %></font></p>
  </div>
  <div align="center">
    <a href="<%=basePath %>/login.jsp">返回重新登录</a>
  </div>

</body>
</html>
