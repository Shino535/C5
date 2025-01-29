<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/header_footer/header.jsp"%>

<c:if test="${not empty sessionScope.dbConError}">
    <div class="error">${sessionScope.dbConError}</div>
    <c:remove var="dbConError" scope="session"/>
</c:if>
<c:if test="${not empty sessionScope.logout}">
	<c:out value="${sessionScope.logout}"/>
	<c:remove var="logout" scope="session"/>
</c:if>

<%@include file="/header_footer/footer.jsp"%>
