<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
"JSONDATA":{
"pageSize":${pageSize},
"currentPageNum":${currentPageNum},
"pageCount":${pageCount},
"totalCount":${totalCount},
"platforms":
			[
				<c:forEach items="${platformList}" var="platform" varStatus="s" >
				 {"id":${platform.id},
				  "platformName":"${platform.platformName}",
				  "platformShortName":"${platform.platformShortName}",
				  "platformStatus":${platform.platformStatus},
				  "platformInfo":"${platform.platformInfo}",
				  "platformAddDate":"${platform.platformAddDate}",
				  }<c:if test="${s.index!=pageSize}">,</c:if>
				</c:forEach>
				  
			]
}
