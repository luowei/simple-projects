<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${phones}" var="phone">
  <tr>
    <td><a href='${phone.phonePic}'><img src="${phone.phonePic}" /></a></td>
    <td>${phone.phoneName}</td>
    <td>${phone.phonePrice}</td>
	<td>${phone.phoneSize}</td>
	<td>${phone.phoneTouchType}</td>
	<td>${phone.phoneCamera}</td>
	<td>${phone.phoneOs}</td>
	<td>${phone.phoneHot}</td>
  </tr>
</c:forEach>