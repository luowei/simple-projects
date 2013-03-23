<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<hr/>
<table class=gridView id=ctl00_ContentPlaceHolder2_GridView1 
      style="WIDTH: 100%; BORDER-COLLAPSE: collapse" cellSpacing=0 rules=all 
      border=0>
  <tr>
  	<th	>机型图片</th>
	<th	>手机名称</th>
	<th	>手机价格</th>
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
  </tr>
  <c:forEach items="${phones}" var="phone">
  <tr>
    <td><a href='${phone.phonePic}'><img src="${phone.phonePic}" /></a></td>
    <td>${phone.phoneName}</td>
    <td>${phone.phonePrice}</td>
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
  </tr>
  </c:forEach>