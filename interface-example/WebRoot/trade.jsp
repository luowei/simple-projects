<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>进出口产品代码</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css"/>
</head>
<body>
<h1>进出口产品代码</h1>
<b style="color: red">${message}</b><br/>
<b>
    <c:choose>
        <c:when test="${user ne null && (user.username ne null || user.username ne '')}">
            当前用户:${user.username}
        </c:when>
        <c:otherwise>
            <form name="loginForm" action="${pageContext.request.contextPath}/">
                用户名:<input name="username" value="">
                密码:<input type="password" name="password" value="">
                &nbsp;<input type="submit" value="登录">
            </form>
        </c:otherwise>
    </c:choose>
</b> <br/><br/>
<label>添加产品代码:
    <input id="productCode" name="productCode" value="">
    <input type="button" value="添加" onclick="function addProduct() {
            if(document.getElementById('productCode').value.trim()!='' && confirm('您确定要添加此项吗?'))
            location.href='${pageContext.request.contextPath}/trade/addpcode.do?productCode='
            +document.getElementById('productCode').value
            }
            addProduct()">
</label> <br/>

<table id="gradient-style">
    <tr>
        <c:forEach var="item" items="${list}" varStatus="vs">
        <td>
            <b style="width:60px;overflow: hidden;text-overflow:ellipsis">
                <a style="text-decoration: none;color: black;" href="javascript:void(0)" title="${item.productName}">
                        ${fn:substring(item.productName, 0, 5)}</a></b>
            <input id="${item.id}" value="${item.productCode}">
            <input type="button" value="修改" onclick="function updateProduct() {
                    if (confirm('您确定要修改此项吗?'))location.href = '${pageContext.request.contextPath}/trade/updatepcode.do?productCode='
                    + document.getElementById('${item.id}').value + '&id=${item.id}'
            }
            updateProduct()">
            <input type="button" value="删除" onclick="function delProduct() {
                    if (confirm('您确定要删除此项吗?'))location.href = '${pageContext.request.contextPath}/trade/delpcode.do?id=${item.id}'
            }
            delProduct()">
        </td>

        <c:if test="${(vs.index+1)%4==0}">
    </tr>
    <tr></c:if>
        </c:forEach>

    </tr>

</table>

</body>
</html>