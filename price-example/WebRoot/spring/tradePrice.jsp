<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 13-1-15
  Time: 下午2:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>每日市场成交价</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ext2/adapter/jquery/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/comm/datepicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/spring/common/style.css"/>
    <script type="text/javascript">
        function checkAll(name) {
            var el = document.getElementsByTagName('input');
            var len = el.length;
            for (var i = 0; i < len; i++) {
                if ((el[i].type == "checkbox") && (el[i].name == name)) {
                    var price = el[i].getAttribute("oldvalue")
                    var priceDate = document.getElementById('priceDate').value;
                    el[i].value = price + '@' + priceDate + '@' + document.getElementById(price+"_price").value + '@' + document.getElementById(price+"_num").value
                    el[i].checked = true;
                }
            }
        }
        function clearAll(name) {
            var el = document.getElementsByTagName('input');
            var len = el.length;
            for (var i = 0; i < len; i++) {
                if ((el[i].type == "checkbox") && (el[i].name == name)) {
                    el[i].value = ""
                    el[i].checked = false;
                }
            }
        }

        function submitAll(checkAllBoxId) {
            if (document.getElementById(checkAllBoxId).checked == false) {
                alert("保存前请勾选全选框")
                return;
            }
            var priceInputs = document.getElementsByName("tradePrice");
            for (var i = 0; i < priceInputs.length; i++) {
                if(!isNum(priceInputs[i].value)){
                    alert("价格必须是数字")
                    clearAll("codes");
                    document.getElementById('all').checked = false;
                    priceInputs[i].focus();
                    return;
                }
            }

            var numInputs = document.getElementsByName("tradeNum");
            for (var i = 0; i < numInputs.length; i++) {
                if(!isNum(numInputs[i].value)){
                    alert("数量必须是数字")
                    clearAll("codes");
                    document.getElementById('all').checked = false;
                    numInputs[i].focus();
                    return;
                }
            }

            document.getElementById("tradeForm").submit();
        }


        function boxcheck(boxObj) {
            var price = boxObj.getAttribute("oldvalue")
            var priceDate = document.getElementById('priceDate').value;
            boxObj.value = price + '@' + priceDate + '@' + document.getElementById(price+"_price").value + '@' + document.getElementById(price+"_num").value
            boxObj.checked = true;
        }

        function boxclear(boxObj) {
            boxObj.value = ""
            boxObj.checked = false;
        }

        function saveOne(priceId,boxId) {
            var theboxObj = document.getElementById(boxId);
            if (theboxObj.checked == false) {
                alert("请勾选要保存的记录")
                return;
            }

            var priceInput = document.getElementById(priceId+"_price");
            var numInput = document.getElementById(priceId+"_num");
            if(!isNum(priceInput.value)){
                alert("价格必须是数字")
                boxclear(theboxObj);
                priceInput.focus();
                return;
            }
            if(!isNum(numInput.value)){
                alert("数量必须是数字")
                boxclear(theboxObj);
                numInput.focus();
                return;
            }

            document.getElementById("tradeForm").submit();
        }

        function isNum(s) {
//            var pattern = /^d+(.d+)?$/;
            var pattern = /^[0-9]+.?[0-9]*$/;
            if (pattern.test(s)) {
                return true;
            }
            return false;
        }

    </script>
</head>
<body>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<h2>每日成交价(当前日期:<fmt:formatDate value='${priceDate}' type='date'/>)</h2>

<form id="queryForm" name="queryForm" action="${pageContext.request.contextPath}/${contextUrl}spring/pTrade/list.sp"
      method="post">
    <label>日期:<input id="priceDate" name="priceDate" style="width: 100"
                     value="<fmt:formatDate value='${priceDate}' type='date'/>" onclick="WdatePicker()"></label>
    <label>产品名称:<input id="productName" name="productName" hint="产品名称" value="${productName}" style="width: 100"></label>
    <label>规格型号:<input id="modelName" name="modelName" style="width: 100" value="${modelName}"></label><br/>
    <label>交易市场:<input id="marketName" name="marketName" style="width: 100" value="${marketName}"></label>
    <label>贸易商:<input id="traderName" name="traderName" style="width: 100" value="${traderName}"></label>
    <label>地区:<input id="areaName" name="areaName" style="width: 100" value="${areaName}"></label> &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" value="确定" onclick="function submitQueryForm(priceDateId, productNameId) {
        if(document.getElementById(priceDateId).value == ''){
            alert('日期输入框不能为空')
            return;
        }
        if(document.getElementById(productNameId).value == ''){
            alert('产品名称输入框不能为空')
            return;
        }
         document.getElementById('queryForm').submit();
    }
    submitQueryForm('priceDate','productName')">
</form>
<br/>


<form id="tradeForm" name="tradeForm" action="${pageContext.request.contextPath}/${contextUrl}spring/pTrade/update.sp"
      method="post">

    <table id="gradient-style" border="1" >
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
            <th> <label><input type="checkbox" id="all"
                       onclick="if(this.checked==true) { checkAll('codes'); } else { clearAll('codes'); }"/>全选</label>
                |<input type="button" value="保存选中" onclick="submitAll('all')"/>
            </th>
        </tr>
        <c:forEach items="${list}" var="trade" varStatus="st">
            <tr>
                <td><fmt:formatDate value="${trade.currentDate}" type="date"/></td>
                <td>${trade.productName}</td>
                <td>${trade.modelName}</td>
                <td>${trade.areaName}</td>
                <td>${trade.marketName}</td>
                <td>${trade.traderName}</td>
                <td><input id="${trade.id}@${trade.priceTraderId}_price" name="tradePrice"
                    <c:if test="${trade.tradePrice eq null || trade.tradePrice eq ''}">value="0.00"</c:if>
                    <c:if test="${trade.tradePrice ne null && trade.tradePrice ne ''}">value="${trade.tradePrice}"</c:if>
                        >${trade.unit}</td>
                <td><input id="${trade.id}@${trade.priceTraderId}_num" name="tradeNum"
                   <c:if test="${trade.tradeNum eq null || trade.tradeNum eq ''}">value="0"</c:if>
                   <c:if test="${trade.tradeNum ne null && trade.tradeNum ne ''}">value="${trade.tradeNum}"</c:if>
                        ></td>
                <td><c:if test="${trade.display ne 1}">否</c:if> <c:if test="${trade.display eq 1}">是</c:if></td>
                <td><fmt:formatDate value='${trade.modifyDate}' type='both'/></td>
                <td>${trade.userName}</td>
                <td><input type="checkbox" id="${trade.priceTraderId}" name="codes" oldvalue="${trade.id}@${trade.priceTraderId}"
                           onclick="if(this.checked==true) { boxcheck(this); } else { boxclear(this); }"/> |
                    <input type="button" value="保存"
                           onclick="saveOne('${trade.id}@${trade.priceTraderId}','${trade.priceTraderId}')"/></td>
            </tr>
        </c:forEach>
    </table>

    <%--<input type="hidden" id="hpriceDate" name="priceDate" value="${priceDate}">--%>
    <input type="hidden" id="hproductName" name="productName" value="${productName}">
    <input type="hidden" id="hmodelName" name="modelName" value="${modelName}">
    <input type="hidden" id="hareaName" name="areaName" value="${areaName}">
    <input type="hidden" id="hmarketName" name="marketName" value="${marketName}">
    <input type="hidden" id="htraderName" name="traderName" value="${traderName}">

</form>

<br/><br/>

</body>
</html>