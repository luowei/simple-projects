<%--
  Created by IntelliJ IDEA.
  User: luowei
  Date: 13-4-25
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head id="Head1">
    <title>读取接口通话记录</title>
</head>
<body>

<h1 style="text-align: center">读取接口通话记录</h1>

<hr/>
<form method="post" action="${pageContext.request.contextPath}/call/getLog.do">

    <div style="text-align: center">
        <div>ID<input name="id" type="text" id="id"/></div>
        <div>SessionID<input name="sessionID" type="text" id="sessionID"/></div>
        <div>CallIndex<input name="callIndex" type="text" id="callIndex"/></div>
        <div>分机号码<input name="extCode" type="text" id="extCode"/></div>
        <div>对方号码<input name="otherCode" type="text" id="otherCode"/></div>
        <div>开始时间<input name="startTime" type="text" id="startTime"/></div>
        <div>结束时间<input name="endTime" type="text" id="endTime"/></div>
        <div> 呼入方向
            <select name="callDirect" id="callDirect" style="width:130px;">
                <option value="0">全部</option>
                <option value="1">呼入</option>
                <option value="2">呼出</option>
            </select>
        </div>
        <div>最小呼叫时长<input name="minCallTimeLen" type="text" id="minCallTimeLen" style="width:130px;"/></div>
        <div>最大呼叫时长<input name="maxCallTimeLen" type="text" id="maxCallTimeLen" style="width:130px;"/></div>
        <div>最小通话时长<input name="minConnectTimeLen" type="text" id="minConnectTimeLen" style="width:130px;"/></div>
        <div>最大通话时长<input name="maxConnectTimeLen" type="text" id="maxConnectTimeLen" style="width:130px;"/></div>
        <br/><div><input type="submit" value="读取"/></div>
    </div>
</form>

</body>
</body>
</html>