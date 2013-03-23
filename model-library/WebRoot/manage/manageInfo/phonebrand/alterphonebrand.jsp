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

      <TD>
      <form name="form1" id="form1" method="post"  enctype="multipart/form-data" action="${ctx }/manage/manageInfo/phonebrand/update.do">
        <input type="hidden" name="id" value="${phonebrand.id}">
        <table width="591" height="433" border="0" align="center">
          <tr>
            <td colspan="3"><div align="center"><span class="STYLE2">修改品牌</span></div></td>
            </tr>
          <tr>
            <td  align="right">品牌名:</td> 
     	    <td ><input type="text" id="brandName" name="brandName"  value="${phonebrand.brandName}" /></td> 
     		<td ><div id="userNameTip" ></div></td> 
          </tr>
          <tr>
		      <td align="right">品牌图片:</td> 
		      <td><input type="file" name="phonebrandimage1" id="brandImage" /></td>
		          <input type="hidden" name="brandImage" id="brandImage" value="${phonebrand.brandImage }"/> 
		      <td><img alt="" src="${phonebrand.brandImage}" /></td><div id="platformShortNameTip" ></div></td> 
          </tr>

          <tr>
          <td align="right">品牌状态:</td> 
    	  <td colspan=2>激活<input type="radio" <c:if test="${phonebrand.brandStatus==0}">checked</c:if> value="0" name="brandStatus">未激活<input type="radio" <c:if test="${phonebrand.brandStatus==1}">checked</c:if> value="1" name="brandStatus">   	             

          </td>
          </tr>
          <tr>
          <td align="right">品牌排序:</td> 
    	  <td colspan="2"><input type="text" name="brandDesc" id="brandDesc" value="${phonebrand.brandDesc}"/>      	             
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
