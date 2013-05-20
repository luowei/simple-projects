<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <NOSCRIPT>
        <IFRAME SRC="*.html;*.htm;*.mht;*.txt"></IFRAME>
    </NOSCRIPT>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name=keywords content="隆众商务,石化,专家">
    <meta name="description" content="Design By www.oilchem.com">
    <title>隆众商务专家系统-管理页面</title>
    <link href="${ctx}/pcdb/common/css/admin.css" rel="stylesheet" type="text/css">
    <link type="text/css" rel="StyleSheet" href="${ctx}/pcdb/common/css/sortabletable.css"/>

    <script type="text/javascript" src="${ctx}/pcdb/common/js/datepicker/WdatePicker.js"></script>
    <%--<script type="text/javascript" src="${ctx}/rootls/common/js/jquery-1.7.2.js"></script>--%>
           <script type="text/javascript">
               var LzCharset="UTF-8";
           </script>
    <script type="text/javascript" src="http://exp.oilchem.net/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="http://exp.oilchem.net/js/jquery.form.2.94.js"></script>
    <script type="text/javascript" src="http://exp.oilchem.net/js/jquery.autocomplete.js"></script>

    <%--<script type="text/javascript" src="${ctx}/rootls/common/js/json2.min.js"></script>--%>

    <%--<link type="text/css" rel="StyleSheet" href="${ctx}/rootls/common/js/autocomplete2/jquery.autocomplete.css"/>--%>
    <%--<script type="text/javascript" src="${ctx}/rootls/common/js/autocomplete2/jquery.autocomplete.js"></script>--%>

    <script src="${ctx}/pcdb/common/ajaxfileupload/ajaxfileupload.js"></script>
    <%--<script src="${ctx}/rootls/common/ajaxfileupload/jquery.fileUploader.js"></script>--%>
    <%--<script src="http://malsup.github.com/jquery.form.js"></script>--%>

    <link rel="stylesheet" type="text/css" href="${ctx}/pcdb/common/window/styles/_Window.css"/>
    <script src="${ctx}/pcdb/common/window/styles/_Window.js"></script>
    <script type="text/javascript" src="${ctx}/pcdb/bill/bill.js"></script>


    <script type="text/javascript">
        var url = '${ctx}/bill/listjson.lz'

        $(function () {
            getpage('${ctx}', url);

            $("#trackStr").autocomplete("http://exp.oilchem.net/admin/admin_showadminname-utf-8.asp?sn=1", {
                        minChars: 0,
                        width: 260,
                        max: 300,
                        selectFirst: true,
                        scroll: true,
                        scrollHeight: 280,
                        multiple: false,
                        autoFill: false,
                matchContains: true,
                dataType: "json",
                parse: function(data) {
                    return $.map(data, function(row) {
                        return {
                            data: row,
                            value: row.name,
                            result: row.id + '.' +row.name.replace(/<\/?font[^>]*>/gi,"")
                        }
                    });
                },
                formatItem: function(item) {
                    return format(item);
                }
            });

        });

        function format(user) {
            var colo = "";
            if(user.id<=0){
                colo = "red";
            }else if(user.ds=="离职"){
                colo = "gray";
            }
            return ((colo!="")?"<font color="+colo+">":"" )+ user.name + " (" + user.py + ")" + " (" + user.ds + ")" +( (colo!="")?"</font>":"");
        }

    </script>
</head>
<body>

<table width="95%" border="0" cellspacing="0" cellpadding="0" align=center class="tableBorder">
    <tr>
        <th width="100%" class="tableHeaderText" colspan=2 height=25>发票管理</th>
    </tr>
    <tr>
        <td class="webRowHighlight" colspan=2 align="center">
            <form id='searchForm' method="get" action="" name=form1 onSubmit="return check(this);">
                <div align="left">
                    <strong>开票时间从</strong>：
                    <input type="text" name="startReceiptdate" size="10" VALUE="" onclick="WdatePicker()">
                    <strong>至</strong>
                    <input type="text" name="endReceiptdate" size="10" VALUE="" onclick="WdatePicker()">

                    <strong>款项类型:</strong>
                    <label><input type="checkbox" name="receipttype" value="1">应收</label>
                    <strong>会员类型:</strong>
                    <select name="guestType" size="1" id="guestType">
                        <option value="">----</option>
                        <option value="1">试用</option>
                        <option value="2">试用过期</option>
                        <option value="3">正式</option>
                        <option value="4">正式过期</option>
                    </select>
                    <strong>跟踪人:</strong>
                    <%--<input type='text' id='trackId' name='trackId' size='20' onfocus="inputAutocomplete('${ctx}',this)">--%>
                    <input type='text' id='trackStr' name='trackStr' size='20' >

                    <select name="sel" class="smallselsect" id="sel" onchange="changbind(this)">
                        <option value="qymc" selected>企业名称</option>
                        <option value="name">用户名</option>
                        <option value="htbianhao">合同编号</option>
                        <option value="receipt">发票号</option>
                        <option value="huikuandi">开票地</option>
                        <option value="sendAddr">邮寄地址</option>
                        <option value="sendLxr">联系人</option>
                        <option value="sendPhone">电话</option>
                        <option value="sendMobile">手机</option>
                        <option value="sendTime">邮寄时间</option>
                        <option value="sendContent">邮寄内容</option>
                    </select>
                    <select id='like' name="like" class="smallselsect">
                        <option value="0" selected>等于</option>
                        <option value="1">包含</option>
                    </select>
                    <input type='text' id='keyword' name='qymc' size='20' onfocus="">
                    <input type="button" value="查询" name="submit" style="width: 40px;"
                           onclick="submitSearchForm(1,'${ctx}')">

                    <span style="float: right;padding-right: 50px;padding-top: 3px">
                        <input type="button" value="导入" id="import" name="import" style="width: 40px;" onclick="showImport()">
                        <input type="button" value="导出" id="export" name="export" style="width: 40px;"  onclick="submitExport('${ctx}')">
                    </span>
                </div>
            </form>
        </td>
    </tr>
</table>

<br/>

<table id="table-1" width="95%" border="0" cellspacing="1" cellpadding="0" align=center class="sort-table">
    <form onSubmit="return checkdel(this)" name=del action='' method=post>
        <thead>
        <tr>
            <td nowrap>发票号</td>
            <td nowrap>用户名</td>
            <td nowrap>合同编号</td>
            <td nowrap>会员类型</td>
            <td nowrap>企业名称</td>
            <td nowrap>开票时间</td>
            <td nowrap>收入项目</td>
            <td nowrap>发票金额</td>
            <td nowrap> 款项类型</td>
            <td nowrap>开票地</td>
            <td nowrap>跟踪人</td>
            <td nowrap>操作</td>
        </tr>
        </thead>
        <tbody></tbody>
    </form>
</table>

<table id='tpage' border='0' cellpadding='0' cellspacing='3' width='95%' align='center'>
    <tr></tr>
</table>

<table align=center>
    <tr align=center>
        <td width="100%" class=copyright>隆众商务 , Copyright (c) 2005 <a href="http://www.oilchem.net" target="_blank">
            <font color=#708796><b>oilchem<font color=#CC0000>.Net</font></b></font></a>. All Rights Reserved .
        </td>
    </tr>
</table>

<div id="Data" style="display:none;">
    <div id="billEdit"><form id='billEditForm' action=''></form></div>
    <div id="billShow"></div>
    <div id="billImport">
        <b>选择Excel文件:<input id="importExcel" name="file" type='file' style="width: 200px"/></b>&nbsp;&nbsp;
        <input type='button' value="提 交" style="width: 50px;height: 24px;" onclick="return billImport('${ctx}')">
    </div>
    <div id="showlog"><p></p></div>
</div>

</body>
</html>