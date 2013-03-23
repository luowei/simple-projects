<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>编辑平台</TITLE>
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
  <TBODY>
    <TR>
      <TD colspan="3"  >
      <form name="form1" id="form1" method="post" action="${ctx }/manage/manageInfo/platform/update.do">
        <table width="591" height="433" border="0" align="center">
          <tr>
            <td colspan="3"><div align="center"><span class="STYLE2">编辑平台</span></div></td>
            </tr>
          <tr>
            <td width="101" align="right">平台名:</td> 
     	    <td width="120"><input type="text" id="platformName" name="platformName" style="width:120px" value="${platform.platformName}" /></td> 
     		<td width="342"><div id="userNameTip" style="width:250px"></div></td> 
          </tr>
          <tr>
		      <td align="right">平台简称:</td> 
		      <td><input type="text" name="platformShortName" id="platformShortName" style="width:120px" value="${platform.platformShortName }"/></td> 
		      <td><div id="platformShortNameTip" style="width:250px"></div></td> 
          </tr>
          
          <tr>
          		<td align="right">平台状态:</td> 
    	        <td colspan=2>激活<input type="radio" <c:if test="${platform.platformStatus==1}">checked</c:if> value="1" name="platformStatus">未激活<input type="radio" <c:if test="${platform.platformStatus==0}">checked</c:if> value="0" name="platformStatus">
          </tr>

          <tr>
          <td align="right">平台详情:</td> 
    	  <td colspan="2" valign="top"> <textarea id="platformInfo" name="platformInfo" cols="50" rows="10" value="${platform.platformInfo }">这里是十个中文字符哦</textarea>      	             
          </tr>

          <tr>
            <td>&nbsp;</td>
            <td><input type="submit" name="button" id="button" value="提交" /> </td>
            <td>&nbsp;</td>
          </tr>
  		<input type="hidden" name="id" id="id" value="${platform.id}" />
        </table>
       </form>
      </TD>  
    </TR> 
    
  </TBODY>
</TABLE>
</BODY>
</HTML>
