<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
"JSONDATA":{
"pageSize":${pageSize},
"currentPageNum":${currentPageNum},
"pageCount":${pageCount},
"totalCount":${totalCount},
"platforms":
			[
				<c:forEach items="${phoneList}" var="phone" varStatus="s" >
				 {"id":${phone.id},
				  "phoneName":"${phone.phoneName }",
				  "platformName":"${phone.platform.platformName}",
				  "brandName":"${phone.phonebrand.brandName }",
				  "phoneHeadName":"${phone.phoneHeadName }",
				  "hotPhone":"${phone.hotPhone }",
				  "phoneImage":"${phone.phoneImage }",
				  "phoneResolution":"${phone.phoneResolution }",
				  "phoneDesc":${phone.phoneDesc }
				  "phoneAddDate":"${phone.phoneAddDate }"
				  }<c:if test="${s.index!=pageSize}">,</c:if>
				</c:forEach>			  
			]
}