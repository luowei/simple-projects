<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="../../../commons/head.jsp" %>
<%@ include file="../../../commons/js.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>修改品牌</TITLE>
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
      <form name="form1" id="form1" method="post"  enctype="multipart/form-data" action="${ctx }/manage/verifyPhonebrand/updateUnpassPhonebrand.do">
        <input type="hidden" name="id" value="${tPhonebrand.id}">
        <table width="591" height="433" border="0" align="center">
          <tr>
            <td colspan="3"><div align="center"><span class="STYLE2">修改品牌</span></div></td>
            </tr>
          <tr>
            <td  align="right">品牌名:</td> 
     	    <td ><input type="text" id="phonebrandName" name="phonebrandName"  value="${tPhonebrand.phonebrandName}" /></td> 
     		<td ><div id="userNameTip" ></div></td> 
          </tr>
          <tr>
		      <td align="right">品牌图片:</td> 
		      <td><input type="file" name="phonebrandimage1" id="brandImage" /></td>
		          <input type="hidden" name="brandImage" id="brandImage" value="${tPhonebrand.phonebrandImage }"/> 
		      <td><img alt="" src="${tPhonebrand.phonebrandImage}" /></td><div id="platformShortNameTip" ></div></td> 
          </tr>

          <tr>
          <td align="right">品牌url:</td> 
    	  <td colspan=2> <input type="text" id="relateURL" name="relateURL"  value="${tPhonebrand.relateURL}" />
          </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input type="submit" name="button" id="button" value="提交" /> </td>
            <td>&nbsp;</td>
          </tr>
        </table>
       </form>
      </TD>  
    </TR>   
  </TBODY>

</TABLE>

</BODY>
</HTML>

