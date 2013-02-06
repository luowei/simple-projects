<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>石化产品现货估价平台 - 隆众石化网</title>
    <script type="text/javascript" src="${ctx}/style/jscharts.js"></script>
</head>
<body>
<p>产品名称:${productName}&nbsp;&nbsp;规格型号:${modelName}&nbsp;&nbsp;地区:${areaName}</p>
<div id="graph" align="right">Loading...</div>
<script type="text/javascript">
//    var parame = window.dialogArguments;
    var jsonUrl = "${ctx}/price/jschart/"+encodeURI('${priceJudgeId}')+".do";
    <%--var jsonUrl = "${ctx}/price/jschart2.json";--%>
    var myChart = new JSChart('graph', 'line');
    myChart.setDataJSON(jsonUrl);
    myChart.setTitle('价格曲线图');
    myChart.setTitleColor('#8E8E8E');
    myChart.setTitleFontSize(11);
    myChart.setAxisNameX('日期');
    myChart.setAxisNameY('价格');
    myChart.setAxisPaddingLeft(100);
    myChart.setAxisPaddingRight(30);
    myChart.setAxisPaddingTop(50);
    myChart.setAxisPaddingBottom(40);
//    myChart.setAxisValuesDecimals(2);
    myChart.setAxisValuesNumberX(10);
    myChart.setShowXValues(false);
    myChart.setSize(616, 321);
    myChart.setBackgroundImage('${ctx}/style/chart_bg.jpg');
    myChart.setLineSpeed(97);
    myChart.draw();
</script>
</body>
</html>