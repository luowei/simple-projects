<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 13-3-7
  Time: 上午10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>估价查询</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ext2/adapter/jquery/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/comm/datepicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/spring/common/style.css"/>
    <script type="text/javascript">
        function subQueryForm(pageNumber){
            if (undefined != pageNumber && (pageNumber != null || pageNumber != "")) {
                $("#pageNumber").val(pageNumber);
            }
            $("#queryForm").submit();
        }
    </script>
</head>
<body>
<h2>估价查询</h2>
<form id="queryForm" name="queryForm" action="${ctx}/spring/pJudge/listPage.sp"
      method="post">
    <%--<p><b style="color: green">过滤条件<fmt:formatDate value='${priceDate}' type='date'/></b></p>--%>
    <label>起始日期:<input id="startDate" name="startDate" style="width: 100"
                     value="<fmt:formatDate value='${startDate}' type='date'/>" onclick="WdatePicker()"></label>
    <label>结束日期:<input id="endDate" name="endDate" style="width: 100"
                       value="<fmt:formatDate value='${endDate}' type='date'/>" onclick="WdatePicker()"></label>
    <label>产品名称:<input id="productName" name="productName" hint="产品名称" value="${productName}"
                       style="width: 100"></label>
        <br/>
    <label>规格型号:<input id="modelName" name="modelName" style="width: 100" value="${modelName}"></label>
    <label>地区:<input id="areaName" name="areaName" style="width: 100" value="${areaName}"></label>
        <input id="pageNumber" name="pageNumber" type="hidden" value="1">
    <input type="button" value="查询" onclick="document.getElementById('queryForm').submit();">
</form>
<br/>
<div class="pagebar">
    <div >
        <c:choose>
            <c:when test="${currentIndex == 1 || currentIndex == null}"><span>首页</span>&nbsp;<span>上一页</span></c:when>
            <c:otherwise>
                <a href="javascript:void(0);" onclick="subQueryForm(1)">首页</a>
                <a href="javascript:void(0);" onclick="subQueryForm(${currentIndex - 1})" >上一页</a>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:choose>
                <c:when test="${i == currentIndex || i == 0}"><span><strong>${i}</strong></span></c:when>
                <c:otherwise><a href="javascript:void(0);" onclick="subQueryForm(${i})" >${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentIndex == totalPages}"><span>下一页</span>&nbsp;<span>尾页</span></c:when>
            <c:otherwise>
                <a href="javascript:void(0);" onclick="subQueryForm(${currentIndex + 1})">下一页</a>
                <a href="javascript:void(0);" onclick="subQueryForm(${totalPages})">尾页</a>
            </c:otherwise>
        </c:choose>
    </div>
</div> <br/>
<table id="gradient-style" border="1">
    <tr>
        <th>日期</th>
        <th>产品名称</th>
        <th>规格型号</th>
        <th>地区</th>
        <th>估价(单位)</th>
        <th>涨跌幅</th>
        <th>前台显示</th>
        <th>最后修改</th>
        <th>修改人</th>
    </tr>
    <c:forEach items="${list}" var="judge" varStatus="st">
    <tr>
        <td><fmt:formatDate value="${judge.priceDate}" type="date"/></td>
        <td>${judge.productName}</td>
        <td>${judge.modelName}</td>
        <td>${judge.areaName}</td>
        <td>${judge.judgePrice}(${judge.unit})</td>
        <td>${judge.changeRate}</td>
        <td><c:if test="${judge.display ne 1}">否</c:if> <c:if test="${judge.display eq 1}">是</c:if></td>
        <td><fmt:formatDate value='${judge.modifyDate}' type='both'/></td>
        <td>${judge.userName}</td>
    </tr>
    </c:forEach>
</table>
<br/>
<div class="pagebar">
    <div >
        <c:choose>
            <c:when test="${currentIndex == 1 || currentIndex == null}"><span>首页</span>&nbsp;<span>上一页</span></c:when>
            <c:otherwise>
                <a href="javascript:void(0);" onclick="subQueryForm(1)">首页</a>
                <a href="javascript:void(0);" onclick="subQueryForm(${currentIndex - 1})" >上一页</a>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:choose>
                <c:when test="${i == currentIndex || i == 0}"><span><strong>${i}</strong></span></c:when>
                <c:otherwise><a href="javascript:void(0);" onclick="subQueryForm(${i})" >${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentIndex == totalPages}"><span>下一页</span>&nbsp;<span>尾页</span></c:when>
            <c:otherwise>
                <a href="javascript:void(0);" onclick="subQueryForm(${currentIndex + 1})">下一页</a>
                <a href="javascript:void(0);" onclick="subQueryForm(${totalPages})">尾页</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<br/><br/>
</body>
</html>