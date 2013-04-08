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
    <title>成交价查询</title>
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

<h2>成交价查询</h2>

<form id="queryForm" name="queryForm" action="${ctx}/spring/pTrade/listPage.sp"
      method="post">
    <label>起始日期:<input id="startDate" name="startDate" style="width: 100"
                       value="<fmt:formatDate value='${startDate}' type='date'/>" onclick="WdatePicker()"></label>
    <label>结束日期:<input id="endDate" name="endDate" style="width: 100"
                       value="<fmt:formatDate value='${endDate}' type='date'/>" onclick="WdatePicker()"></label>
    <label>产品名称:<input id="productName" name="productName" hint="产品名称" value="${productName}" style="width: 100"></label>
    <label>规格型号:<input id="modelName" name="modelName" style="width: 100" value="${modelName}"></label><br/>
    <label>交易市场:<input id="marketName" name="marketName" style="width: 100" value="${marketName}"></label>
    <label>贸易商:<input id="traderName" name="traderName" style="width: 100" value="${traderName}"></label>
    <label>地区:<input id="areaName" name="areaName" style="width: 100" value="${areaName}"></label> &nbsp;&nbsp;&nbsp;&nbsp;
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
</div>  <br/>
<table id="gradient-style" border="1">
    <tr>
        <th>日期</th>
        <th>产品名称</th>
        <th>规格型号</th>
        <th>地区</th>
        <th>交易市场</th>
        <th>贸易商</th>
        <th>成交价/单位</th>
        <th>成交数量</th>
        <th>前台显示</th>
        <th>最后修改</th>
        <th>修改人</th>
    </tr>
    <c:forEach items="${list}" var="trade" varStatus="st">
        <tr>
            <td><fmt:formatDate value="${trade.priceDate}" type="date"/></td>
            <td>${trade.productName}</td>
            <td>${trade.modelName}</td>
            <td>${trade.areaName}</td>
            <td>${trade.marketName}</td>
            <td>${trade.traderName}</td>
            <td>${trade.tradePrice}(${trade.unit})</td>
            <td>${trade.tradeNum}</td>
            <td><c:if test="${trade.display ne 1}">否</c:if> <c:if test="${trade.display eq 1}">是</c:if></td>
            <td><fmt:formatDate value='${trade.modifyDate}' type='both'/></td>
            <td>${trade.userName}</td>
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