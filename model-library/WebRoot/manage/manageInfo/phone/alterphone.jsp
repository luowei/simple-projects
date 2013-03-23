<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>修改机型</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css> 
{
	FONT-SIZE: 12px
}
#menuTree A {
	COLOR: #566984; TEXT-DECORATION: none
}
.STYLE2 {font-size: x-large}
</STYLE>

<style type="text/css" media="all"> 
body,div{font-size:12px;}
</style> 

<META content="MSHTML 6.00.2900.5848" name=GENERATOR>

	

</HEAD>
<BODY >
  <TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
    <TR>
      <TD>
    <!-- -------------------------------------------------- -->
      <form name="form1" id="form1" enctype="multipart/form-data" method="post" action="${ctx }/manage/manageInfo/phone/update.do">
        <table width="591" height="433" border="0" align="center">
          <tr>
            <td colspan="3"><div align="center"><span class="STYLE2">修改机型</span></div></td>
          </tr>
          <input type="hidden" name="id" id="id" value="${phone.id}"/>
          <tr>
            <td  align="right">机型名称:</td> 
     	    <td ><input type="text" id="phoneName" name="phoneName"  value="${phone.phoneName }" /></td> 
     		<td ><div id="userNameTip" ></div></td> 
          </tr>
          <tr>
            <td  align="right">品牌名称:</td> 
     	    <td >
    			<select name="brandName" id="brandName" )">
    				<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="s">
    					<option value="${phonebrand.id}">${phonebrand.brandName }</option>
    				</c:forEach>
    				<option index="1" value="${phone.phonebrand.id }"> ${phone.phonebrand.brandName }</option>
    				<option index="1">--品牌--</option>
    			</select>
    		</td>
    		<td ><div id="brandNameTip" ></div></td> 
    	  </tr>
          <tr>
            <td  align="right">平台名称:</td> 
     	    <td >
    			<select name="platformName" id="platformName" )">
    				<c:forEach items="${platformList}" var="platform" varStatus="s">
    					<option value="${platform.id }">${platform.platformName }</option>
    				</c:forEach>
    				<option index="1" value="${phone.platform.id }"> ${phone.platform.platformName }</option>
    			</select>
    		<td ><div id="platformNameTip" ></div></td> 
    	  </tr>
          
          <tr>
            <td  align="right">机型简称:</td> 
     	    <td ><input type="text" id="phoneHeadName" name="phoneHeadName"  value="${phone.phoneHeadName }" /></td> 
     		<td ><div id="phoneHeadNameTip" ></div></td> 
          </tr>
          <tr>
            <td  align="right">机型分辨率:</td> 
     	    <td ><input type="text" id="phoneResolution" name="phoneResolution"  value="${phone.phoneResolution }" /></td> 
     		<td ><div id="phoneResolutionTip" ></div></td> 
          </tr>
          <tr>
		      <td align="right">机型图片:</td> 
		      <td><input type="file" name="phoneimage1" id="phoneimage1" /></td>
		      <td><img src="${phone.phoneImage }" /></td> 
          </tr>

          <tr>
          <td align="right">机型状态:</td> 
          <td colspan=2>
          	激活<input type="radio" <c:if test="${phone.phoneStatus==0}">checked</c:if> value="0" name="phoneStatus">
          	未激活<input type="radio" <c:if test="${phone.phoneStatus==1}">checked</c:if> value="1" name="phoneStatus">
          </tr>
          <tr>
            <td align="right" valign="top">机型排序:</td> 
            <td colspan="2"><input type="text" name="phoneDesc" id="phoneDesc" value="${phone.phoneDesc }"/> 
            <!-- 
    	  <td colspan="2" valign="top"> <textarea id="phoneDesc" name="phoneDesc" cols="50" rows="10">这里写机型描述。。。</textarea>  
     	     -->         
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input type="submit" name="button" id="button" value="提交" /> </td>
            <td>&nbsp;</td>
          </tr>
  		
        </table>
       </form>
    <!-- -------------------------------------------------- -->
      </TD>  
    </TR> 
  </TBODY>

</TABLE>
</body>
</html>
