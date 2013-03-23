<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<input id="num" type="hidden" value="${num}" />
<table border="1">
<tr>
<c:forEach items="${phonebrands}" var="phonebrand" varStatus="stauts" >
	<td>
		<label><input type="checkbox" name="selectedphonebrand" value="${phonebrand.phonebrandName}${phonebrand.relateURL}" onclick="change('${phonebrand.phonebrandName}@${phonebrand.relateURL}')"/>
		${phonebrand.phonebrandName}
		</label>
        
	</td>
<c:if test="${(stauts.index+1)%6==0}"></tr><tr></c:if>
</c:forEach>
<td><input type="button" value="确定" onclick="loadPhonePage()"/><input type="hidden" value="快速保存" onclick="loadPhonePageByPage()"/></td>
</tr>
</table>
