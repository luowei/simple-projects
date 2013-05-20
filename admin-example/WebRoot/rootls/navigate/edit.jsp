<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 13-5-14
  Time: 下午2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <link rel="stylesheet" type="text/css" href="http://exp.oilchem.com/shandong/styl.css">

    <script type="text/javascript">
        var ctx = '${ctx}'
        var LzCharset = "UTF-8";
        var webClassId = '${id}'
    </script>
    <script type="text/javascript" src="http://exp.oilchem.net/js/jquery-1.4.2.min.gb2312.js"></script>
    <script type="text/javascript" src="http://exp.oilchem.net/js/jquery.form.2.94.js"></script>
    <script type="text/javascript" src="http://exp.oilchem.net/js/jquery.autocomplete.js"></script>

    <script type="text/javascript" src="${ctx}/pcdb/navigate/jquery.dragsort-0.5.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/pcdb/navigate/edit.js"></script>


</head>
<body>
<h2> 编辑<span style="color: red">${sitName}</span>栏目与产品 </h2>

<div  style="padding-left: 30px;">

<form id="form1" name="form1" action="">
    <input name="id" type="hidden" value="${id}">

    <h3>栏目</h3>
    <b>栏目：</b>
    <input size="35" type="text" name="selLanMu" id="selLanMu" value="" ondblclick="this.value='';">
    <a href="javascript:showLanMuRelation(1,document.form1,0,'信息','','')" style="color:blue;">选择新闻栏目</a>
    <br/><b>拖拽排序:</b>
    <ul id='sLzLanMuList'></ul>
    <%--<input type="button" value="保存排序" onclick="saveLanmuOrder()">--%>
    <b>id序列:</b>
    <input type="text" id="lanmuIds" name="lanmuIds" value="" style="width: 300px">
    <br/><br/><hr/><br/>

    <h3>产品</h3>
    <b>产品：</b>
    <input size="35" type="text" name="selProducts" id="selProducts" value="" ondblclick="this.value='';">
    <a href="javascript:showProductRelation(11,document.form1,0,'信息','')" style="color:blue;">选择相关产品</a>
    <br/><b>拖拽排序:</b>
    <ul id='sLzProductList'></ul>
    <b>id序列:</b>
    <input type="text" id="prodcutIds" name="productIds" value="" style="width: 300px">
    <br/><br/><hr/><br/>

    <h3>页面</h3>
    <b>包含页面：</b>
    <input size="35" type="text" name="selWebClass" id="selWebClass" value="" ondblclick="this.value='';">
    <a href="javascript:showWebClassRelation(11,document.form1,0,'信息','')" style="color:blue;">选择相关页面</a>
    <br/><b>拖拽排序:</b>
    <ul id='sLzWebClassList'></ul>
    <b>id序列:</b>
    <input type="text" id="webClassIds" name="webClassIds" value="" style="width: 300px">
    <br/><br/><hr/><br/>

    <h3>负责人</h3>
    <b>添加负责人:</b>
    <input size="35" type="text" name="selAdmin" id="selAdmin" value="" >
    <br/><b>拖拽排序:</b>
    <ul id='sLzAdminList'></ul>
    <b>id序列:</b>
    <input type="text" id="adminIds" name="adminIds" value="" style="width: 300px">

    <script type="text/javascript">
        $("#sLzLanMuList").dragsort({dragEnd: saveLanmuOrder});
        $("#sLzProductList").dragsort({dragEnd: saveProductOrder});
        $("#sLzWebClassList").dragsort({dragEnd: saveWebClassOrder});
        $("#sLzAdminList").dragsort({dragEnd:saveAdminOrder})
    </script>

    <br/><br/>
    <input type="button" value="提交" onclick="updateWebClass('${ctx}/webClass/update.lz')">

</form>
</div>

</body>
</html>