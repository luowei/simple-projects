<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>修改审核未通过的机型</TITLE>
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
      <TD >
    <!-- -------------------------------------------------- -->
      <form name="form1" id="form1" enctype="multipart/form-data" method="post" action="${ctx }/manage/verifyPhone/updateUnpassPhones.do">
        <table width="591" height="433" border="0" align="center">
          <tr>
            <td colspan="3"><div align="center"><span class="STYLE2">修改机型</span></div></td>
          </tr>
          <input type="hidden" name="id" id="id" value="${tPhone.id}"/>"
          <tr>
            <td  align="right">机型名称:</td> 
     	    <td ><input type="text" id="phoneName" name="phoneName"  value="${tPhone.phoneName }" /></td> 
     		<td ><div id="userNameTip" ></div></td> 
          </tr>
          <tr>
            <td  align="right">品牌名称:</td> 
     	    <td >
    			<select name="brandName" id="brandName" )">
    				<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="s">
    					<option value="${phonebrand.brandName}">${phonebrand.brandName }</option>
    				</c:forEach>
    				<option index="1" value="${tPhone.brandName }" selected="selected"> ${tPhone.brandName }</option>
    				<option index="1">--品牌--</option>
    			</select>
    		</td>
    		<td ><div id="brandNameTip" ></div></td> 
    	  </tr>
          <tr>
            <td  align="right">平台名称:</td> 
     	    <td >
    			<select name="phoneOs" id="phoneOs" )">
    				<c:forEach items="${platformList}" var="platform" varStatus="s">
    					<option value="${platform.platformName }">${platform.platformName }</option>
    				</c:forEach>
    				<option index="1" value="${tPhone.phoneOs }" selected="selected"> ${tPhone.phoneOs }</option>
    			</select>
    		<td ><div id="platformNameTip" ></div></td> 
    	  </tr>
          <tr>
            <td  align="right">机型尺寸:</td> 
     	    <td ><input type="text" id="phoneSize" name="phoneSize"  value="${tPhone.phoneSize }" /></td> 
     		<td ><div id="phoneSize" ></div></td> 
          </tr>
          <tr>
		      <td align="right">机型图片:</td> 
		      <td><input type="file" name="phoneimage1" id="phoneimage1" /></td>
		      <td><img src="${tPhone.phonePic }" /></td> 
          </tr>
			<input type="hidden" name="primevalPic" id="primevalPic" value="${tPhone.phonePic }"/>"
          <tr>
            <td  align="right">触摸屏:</td> 
     	    <td ><input type="text" id="phoneTouchType" name="phoneTouchType"  value="${tPhone.phoneTouchType }" /></td> 
     		<td ><div id="phoneTouchType" ></div></td> 
          </tr>
          <tr>
            <td  align="right">摄像头:</td> 
     	    <td ><input type="text" id="phoneCamera" name="phoneCamera"  value="${tPhone.phoneCamera }" /></td> 
     		<td ><div id="phoneCamera" ></div></td> 
          </tr>
          <tr>
            <td  align="right">手机热度:</td> 
     	    <td ><input type="text" id="phoneHot" name="phoneHot"  value="${tPhone.phoneHot }" /></td> 
     		<td ><div id="phoneHot" ></div></td> 
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input type="submit" name="button" id="button" value="确定修改" /> </td>
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
