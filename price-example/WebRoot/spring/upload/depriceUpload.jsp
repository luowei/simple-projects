<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <style type="text/css">
   	form input{
   		border:0px solid black;
   	}
   </style>
   <script language="javascript">
   		function checkForm(){
   			var returnFlag=true;
   			var fileName = document.getElementById("file1").value;
   			var exName = fileName.substr(fileName.lastIndexOf(".") + 1).toUpperCase();
   			if(fileName==""){
   				alert("请传入EXCEL文件");
   				return false;
   			}
   			if(exName=='XLS'||exName=='XLSX'){
   				document.form1.submit();
   			}else{
   				returnFlag=false;
   				alert("请输入正确的EXCEL文件");
   			}
   			return returnFlag;
   		}
   		function checkForm2(){
   			var returnFlag=true;
   			var fileName = document.getElementById("file2").value;
   			var exName = fileName.substr(fileName.lastIndexOf(".") + 1).toUpperCase();
   			if(fileName==""){
   				alert("请传入EXCEL文件");
   				return false;
   			}
   			if(exName=='XLS'||exName=='XLSX'){
   				document.form2.submit();
   			}else{
   				returnFlag=false;
   				alert("请输入正确的EXCEL文件");
   			}
   			return returnFlag;
   		}
   		function checkForm3(){
   			var returnFlag=true;
   			var fileName = document.getElementById("file3").value;
   			var exName = fileName.substr(fileName.lastIndexOf(".") + 1).toUpperCase();
   			if(fileName==""){
   				alert("请传入EXCEL文件");
   				return false;
   			}
   			if(exName=='XLS'||exName=='XLSX'){
   				document.form3.submit();
   			}else{
   				returnFlag=false;
   				alert("请输入正确的EXCEL文件");
   			}
   			return returnFlag;
   		}
   		function checkForm4(){
   			var returnFlag=true;
   			var fileName = document.getElementById("file4").value;
   			var exName = fileName.substr(fileName.lastIndexOf(".") + 1).toUpperCase();
   			if(fileName==""){
   				alert("请传入EXCEL文件");
   				return false;
   			}
   			if(exName=='XLS'||exName=='XLSX'){
   				document.form4.submit();
   			}else{
   				returnFlag=false;
   				alert("请输入正确的EXCEL文件");
   			}
   			return returnFlag;
   		}
   		
   </script>
    <base href="<%=basePath%>">
    
    <title>My JSP 'depriceUpload.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
      <h1>
   国内市场
  </h1>   
  <form id="form1" name="form1" method="post" action="<%=path %>/spring/price/upload.sp" enctype="multipart/form-data">
   <input type="hidden" name="type" value="dmprice"/>
   <input id="file1" type="file" name="file" />
   <input type="submit" value="提交" onclick="return checkForm();" />
   </form>
     <h1>
   国内成品油
  </h1>   
  <form id="form2" name="form2"  method="post" action="<%=path %>/spring/price/upload.sp" enctype="multipart/form-data">
   <input type="hidden" name="type" value="dmoilprice"/>
   <input id="file2" type="file" name="file"/>
   <input type="submit" value="提交"  onclick="return checkForm2();" />
   </form>
     <h1>
   国际市场
  </h1>   
  <form id="form3" name="form3"  method="post" action="<%=path %>/spring/price/upload.sp" enctype="multipart/form-data">
   <input type="hidden" name="type" value="interalprice"/>
   <input id="file3" type="file" name="file" />
   <input type="submit" value="提交"  onclick="return checkForm3();" />
   </form>
     <h1>
   国内出厂价
  </h1>   
  <form id="form4" name="form4"  method="post" action="<%=path %>/spring/price/upload.sp" enctype="multipart/form-data">
   <input type="hidden" name="type" value="extfactoryprice"/>
   <input id="file4" type="file" name="file" />
   <input type="submit"  value="提交"  onclick="return checkForm4();" />
   </form>
  </body>
</html>
