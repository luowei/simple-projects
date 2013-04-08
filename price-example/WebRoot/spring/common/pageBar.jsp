<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 13-1-16
  Time: 上午10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="pagebar">
    <div align="center">
        <c:choose>
            <c:when test="${currentIndex == null || currentIndex == 1}"><span>首页</span>&nbsp;<span>上一页</span></c:when>
            <c:otherwise>
                <a href="javascript:void(0)" onclick="subform(this,1)">首页</a>
                <a href="javascript:void(0)" onclick="subform(this,${currentIndex - 1})" >上一页</a>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:choose>
                <c:when test="${i == currentIndex || i == 0}"><span><strong>${i}</strong></span></c:when>
                <c:otherwise><a href="javascript:void(0)" onclick="subform(this,${i})" >${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentIndex == totalPages}"><span>下一页</span>&nbsp;<span>尾页</span></c:when>
            <c:otherwise>
                <a href="javascript:void(0)" onclick="subform(this,${currentIndex + 1})" >下一页</a>
                <a href="javascript:void(0)" onclick="subform(this,${totalPages})" >尾页</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>