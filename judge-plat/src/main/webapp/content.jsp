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
<link href="${ctx}/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/style/jquery.lz.1.2.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/style/judge-plat.js" charset="utf-8"></script>
</head>
<body style="background:url(${ctx}/images/topbg2.jpg) no-repeat center top;">
<div class="wrap" style="margin-top:190px">
<div class="main">
<div class="m_left">
<div class="product_list">
<h2>石化产品</h2>
<dl>
<dt>油品</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'汽油','null')">汽油</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'柴油','null')">柴油</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'液化气','null')">液化气</a></dd>&nbsp;&nbsp;
</dl>
<dl>
<dt>化工</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'甲醇','null')">甲醇</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'苯乙烯','null')">苯乙烯</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'纯苯','null')">纯苯</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'DOP','null')">DOP</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'MTBE','null')">MTBE</dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'C5','null')">C5</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'C9','null')">C9</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'溶剂油','null')">溶剂油</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'醋酸','null')">醋酸</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'二甲苯','null')">二甲苯</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'苯酚','null')">苯酚</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丙酮','null')">丙酮</a></dd>&nbsp;&nbsp;
</dl>
<dl>
<dt>橡胶</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'天然橡胶','null')">天然橡胶</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁苯橡胶','null')">丁苯橡胶</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'顺丁橡胶','null')">顺丁橡胶</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'SBS','null')">SBS</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁腈橡胶','null')">丁腈橡胶</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁基橡胶','null')">丁基橡胶</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'乙丙橡胶','null')">乙丙橡胶</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丁二烯','null')">丁二烯</a></dd>&nbsp;&nbsp;
</dl>
<dl>
<dt>塑料</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PE','null')">PE</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PP粒','null')">PP粒</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PP粉','null')">PP粉</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PVC','null')">PVC</a></dd>&nbsp;&nbsp;
</dl>
<dl>
<dt>化纤</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'丙烯腈','null')">丙烯腈</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'PTA','null')">PTA</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'聚酯切片','null')">聚酯切片</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'涤纶短纤','null')">涤纶短纤</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'涤纶长丝','null')">涤纶长丝</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'己内酰胺','null')">己内酰胺</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'腈纶短纤','null')">腈纶短纤</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'腈纶长丝','null')">腈纶长丝</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'环己酮','null')">环己酮</a></dd>&nbsp;&nbsp;
</dl>
<dl>
<dt>聚氨酯</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'MDI','null')">MDI</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'TDI','null')">TDI</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'THF','null')">THF</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'DMF','null')">DMF</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'环氧丙烷','null')">环氧丙烷</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'聚醚软泡','null')">聚醚软泡</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'BDO','null')">BDO</a></dd>&nbsp;&nbsp;
</dl>
<dl>
<dt>无机化工</dt>
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'硫磺','null')">硫磺</a></dd>&nbsp;&nbsp;
<dd><a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'烧碱','null')">烧碱</a></dd>&nbsp;&nbsp;
</dl>
</div>
</div>

<div class="page_right">


<div class="valuation2">
<h2>石化产品现货估价</h2>
<div class="area">
    <c:forEach var="area" items="${areaList}"><c:if test="${!fn:contains(area,'($)')}">
	    <a href1="${ctx}/l/p.do" href="javascript:void(0)" onclick="subform(this,1,'${productName}','${area}')"
           <c:if test="${area eq jmpArea}">class="facing"</c:if> >${area}</a>
        </c:if></c:forEach></div>

<table class="spot-list">
<tr>
<th>商品名称</th>
<th>地区</th>
<c:if test="${noshowModel ne null && noshowModel eq 'false'}"><th>规格型号</th></c:if>
<th>涨跌<span style="color:#f00;">↑</span><span style="color:#390;">↓</span></th>
<th>价格</th>
<th>单位</th>
<th>日期</th>
<th>曲线图</th>
</tr>

<c:forEach var="price" items="${list}">
    <tr>
        <td>${price.productName}</td>
        <td>${price.areaName}</td>
        <c:if test="${noshowModel ne null && noshowModel eq 'false'}"><td>${price.modelName}</td></c:if>
        <td>
         <c:if test="${price.rateFlag lt 0}"><span style="color:#390;">${price.changeRate}</span></c:if>
         <c:if test="${price.rateFlag gt 0}"><span style="color:#f00;">${price.changeRate}</span></c:if>
         <c:if test="${price.rateFlag eq 0}">${price.changeRate}</c:if>
        </td>
        <td>${price.price}</td>
        <td>${price.unit}</td>
        <td><fmt:formatDate value="${price.priceDate}" type="date"/></td>
        <td><a href="#" onclick="window
                .showModalDialog('${ctx}/price/toJsChart.do?priceJudgeId=${price.priceJugdeId}&productName='
                +encodeURI('${price.productName}')
                + '&areaName='+encodeURI('${price.areaName}')
                +'&modelName='+encodeURI('${price.modelName}')+'',
                null,'dialogWidth=640px;dialogHeight=480px')">查看</a></td>
    </tr>
</c:forEach>
</table>

</div>

<div class="pagebar">
    <div align="center">
        <c:choose>
            <c:when test="${currentIndex == 1 || currentIndex == null}"><span>首页</span>&nbsp;<span>上一页</span></c:when>
            <c:otherwise>
                <a href1="${ctx}${contextUrl}" href="javascript:void(0)" onclick="subform(this,1)">首页</a>
                <a href1="${ctx}${contextUrl}" href="javascript:void(0)" onclick="subform(this,${currentIndex - 1})" >上一页</a>
            </c:otherwise>
        </c:choose>

        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
            <c:choose>
                <c:when test="${i == currentIndex || i == 0}"><span><strong>${i}</strong></span></c:when>
                <c:otherwise><a href1="${ctx}${contextUrl}" href="javascript:void(0)" onclick="subform(this,${i})" >${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${currentIndex == totalPages}"><span>下一页</span>&nbsp;<span>尾页</span></c:when>
            <c:otherwise>
                <a href1="${ctx}${contextUrl}" href="javascript:void(0)" onclick="subform(this,${currentIndex + 1})" >下一页</a>
                <a href1="${ctx}${contextUrl}" href="javascript:void(0)" onclick="subform(this,${totalPages})" >尾页</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</div>
</div>


<form id="pageForm" action="" method="post">
    <input type="hidden" id="pageNumber" name="pageNumber" value="${currentIndex}">
    <input type="hidden" id="productName" name="productName" value="${productName}">
    <input type="hidden" id="area" name="area" value="${jmpArea}">
</form>


<div class="footer">
  <p>
		<a target="_blank" href="http://www.oilchem.net/about/">关于我们</a>
		<a target="_blank" href="http://www.oilchem.net/about/chanpin.htm">服务项目</a>
		<a target="_blank" href="http://news.oilchem.net/2012/cooperation/">广告服务</a>
		<a target="_blank" href="http://www.oilchem.net/about2/%E5%85%A5%E7%BD%91%E9%A1%BB%E7%9F%A5.html">入网须知</a>
		<a target="_blank" href="http://www.oilchem.net/about2/%E6%8A%80%E6%9C%AF%E6%94%AF%E6%8C%81.html">技术支持</a>
		<a target="_blank" href="http://www.oilchem.net/about2/%E6%B3%95%E5%BE%8B%E5%A3%B0%E6%98%8E.html">法律声明</a>
		<a target="_blank" href="http://www.oilchem.net/about/contact.htm">联系我们</a>
		<a target="_blank" href="http://www.oilchem.net/about2/汇款方式2.html">汇款方式</a>
		<a target="_blank" href="http://www.oilchem.net/about/link.htm">友情链接</a>
		<a target="_blank" href="http://www.oilchem.net/s/隆众网络诊断工具.rar">网络诊断工具</a>
		<a target="_blank" href="http://www.oilchem.net/about/anli/">建网案例</a>
		<a target="_blank" href="http://news.oilchem.net/2012/hr/">人才招聘</a>
		<a target="_blank" href="http://news.oilchem.net/2012/sitemap/">网站地图</a>
	</p>
	<p>版权所有 隆众石化网 ICP 经营许可证编号为: 鲁B2-20080117 CopyRight ◎ 2011 oilchem.net All rights reserved.</p>
</div>





</div>


</body>
</html>