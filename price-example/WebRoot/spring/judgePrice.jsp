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
    <title>每日估价</title>
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
                    el[i].value = price + '@' + priceDate + '@' + document.getElementById(price).value
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

        function boxcheck(boxObj) {
            var price = boxObj.getAttribute("oldvalue")
            var priceDate = document.getElementById('priceDate').value;
            boxObj.value = price + '@' + priceDate + '@' + document.getElementById(price).value
            boxObj.checked = true;
        }

        function boxclear(boxObj) {
            boxObj.value = ""
            boxObj.checked = false;
        }

        function submitAll(checkAllBoxId) {
            if (document.getElementById(checkAllBoxId).checked == false) {
                alert("保存前请勾选全选框")
                return;
            }
            var priceInputs = document.getElementsByName("judgePrice");
            for (var i = 0; i < priceInputs.length; i++) {
                if(!isNum(priceInputs[i].value)){
                    alert("价格必须是数字")
                    clearAll("codes");
                    document.getElementById('all').checked = false;
                    priceInputs[i].focus();
                    return;
                }
            }
            document.getElementById("judgeForm").submit();
        }

        function saveOne(checkBoxId,priceInputId) {
            var theboxObj = document.getElementById(checkBoxId);
            if (theboxObj.checked == false) {
                alert("请勾选要保存的记录")
                return;
            }

            var priceInput = document.getElementById(priceInputId);
            if(!isNum(priceInput.value)){
                alert("价格必须是数字")
                boxclear(theboxObj);
                priceInput.focus();
                return;
            }
            document.getElementById("judgeForm").submit();
        }

        function isNum(s) {
            //var pattern = /^d+(.d+)?$/;
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
<h2>每日估价(当前日期:<fmt:formatDate value='${priceDate}' type='date'/>)</h2>

<form id="queryForm" name="queryForm" action="${pageContext.request.contextPath}/${contextUrl}spring/pJudge/list.sp"
      method="post">
    <%--<p><b style="color: green">过滤条件<fmt:formatDate value='${priceDate}' type='date'/></b></p>--%>
    <label>日期:<input id="priceDate" name="priceDate" style="width: 100"
                     value="<fmt:formatDate value='${priceDate}' type='date'/>" onclick="WdatePicker()"></label>
    <label>产品名称:<input id="productName" name="productName" hint="产品名称" value="${productName}"
                       style="width: 100"></label>
    <label>规格型号:<input id="modelName" name="modelName" style="width: 100" value="${modelName}"></label>
    <label>地区:<input id="areaName" name="areaName" style="width: 100" value="${areaName}"></label>
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


<form id="judgeForm" name="judgeForm" action="${pageContext.request.contextPath}/${contextUrl}spring/pJudge/update.sp"
      method="post">
    <p>
        <span name="describe"><b>估价颜色说明:</b></span>
        <span name="describe">当前日期还未估价<input disabled="disabled" style="width: 30px;background-color: red"></span>
        <span name="describe">当前日期已估价,但与最近估价相同<input disabled="disabled" style="width: 30px;background-color: yellow"></span>
        <span name="describe">当前日期已估价,但与最近估价不相同<input disabled="disabled" style="width: 30px;background-color: green"></span>
    </p>


    <table id="gradient-style" border="1">
        <tr>
            <th>日期</th>
            <th>产品名称</th>
            <th>规格型号</th>
            <th>地区</th>
            <th>成交价参考</th>
            <th>最近估价</th>
            <th>估价/单位</th>
            <th>涨跌幅</th>
            <th>前台显示</th>
            <th>最后修改</th>
            <th>修改人</th>
            <th><label><input type="checkbox" id="all"
                              onclick="if(this.checked==true) { checkAll('codes'); } else { clearAll('codes'); }"/>全选</label>
                |<input type="button" value="保存选中" onclick="submitAll('all')"/></th>
        </tr>
        <c:forEach items="${list}" var="judge" varStatus="st">
            <tr>
                <td><fmt:formatDate value="${judge.currentDate}" type="date"/></td>
                <td>${judge.productName}</td>
                <td>${judge.modelName}</td>
                <td>${judge.areaName}</td>
                <td><a href="#"
                       onclick="window .showModalDialog('${pageContext.request.contextPath}/JdProduct/findDetail.do?priceJudgeId=${judge.priceJudgeId}',null,'dialogWidth=720px;dialogHeight=480px')">
                    成交价参考</a></td>
                <td><fmt:formatDate value='${judge.lastDate}' type='date'/>|${judge.lastprice}</td>
                <td><input id="${judge.id}@${judge.priceJudgeId}@${judge.lastprice}" name="judgePrice"
                <c:if test="${judge.currentDate.date ne judge.priceDate.date && judge.judgePrice ne null && judge.judgePrice ne ''}">
                           style="width: 100px;background-color: red;" value="${judge.lastprice}"
                </c:if>
                <c:if test="${judge.currentDate.date eq judge.priceDate.date &&
                    (judge.judgePrice ne null && judge.judgePrice eq judge.lastprice) && judge.judgePrice ne null && judge.judgePrice ne ''}">
                           style="width: 100px;background-color: yellow;" value="${judge.judgePrice}"
                </c:if>
                <c:if test="${judge.currentDate.date eq judge.priceDate.date && (judge.judgePrice ne null && judge.judgePrice ne ''
                            && judge.judgePrice ne judge.lastprice) && judge.judgePrice ne null && judge.judgePrice ne ''}">
                           style="width: 100px;background-color: green;" value="${judge.judgePrice}"
                </c:if>
                <c:if test="${judge.judgePrice eq null || judge.judgePrice eq ''}">
                           style="width: 100px;background-color: red;" value="0.00"
                </c:if>
                        >${judge.unit}</td>
                <td>
                    <c:if test="${judge.currentDate.date ne judge.priceDate.date}">
                        0.0
                    </c:if>
                    <c:if test="${judge.currentDate.date eq judge.priceDate.date}">
                        ${judge.changeRate}
                    </c:if>
                </td>
                <td><c:if test="${judge.display ne 1}">否</c:if> <c:if test="${judge.display eq 1}">是</c:if></td>
                <td><fmt:formatDate value='${judge.modifyDate}' type='both'/></td>
                <td>${judge.userName}</td>
                <td><input type="checkbox" id="${judge.priceJudgeId}" name="codes"
                           oldvalue="${judge.id}@${judge.priceJudgeId}@${judge.lastprice}"
                           onclick="if(this.checked==true) { boxcheck(this); } else { boxclear(this); }"/> |
                    <input type="button" value="保存"
                           onclick="saveOne('${judge.priceJudgeId}','${judge.id}@${judge.priceJudgeId}@${judge.lastprice}')"/></td>
            </tr>
        </c:forEach>
    </table>

    <input type="hidden" id="hproductName" name="productName" value="${productName}">
    <input type="hidden" id="hmodelName" name="modelName" value="${modelName}">
    <input type="hidden" id="hareaName" name="areaName" value="${areaName}">

</form>

<br/><br/>

</body>
</html>