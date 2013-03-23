<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
"JSONDATA":{
"pageSize":${pageSize},
"currentPageNum":${currentPageNum},
"pageCount":${pageCount},
"totalCount":${totalCount},
"platforms":
			[
				<c:forEach items="${phonebrandList}" var="phonebrand" varStatus="s"  >
				 {"id":${phonebrand.id},
				  "brandName":"${phonebrand.brandName}",
				  "brandImage":"${phonebrand.brandImage}",
				  "brandStatus":${phonebrand.brandStatus},
				  "brandDesc":${phonebrand.brandDesc},
				  "brandAddDate":"${phonebrand.brandAddDate}"
				  }<c:if test="${s.index!=pageSize}">,</c:if>
				</c:forEach>
				  
			]
}