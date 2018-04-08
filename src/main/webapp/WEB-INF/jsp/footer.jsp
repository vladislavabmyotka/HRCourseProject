<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang.app" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/main.css' %>
    </style>
</head>
<body>
    <jsp:useBean id="now" class="java.util.Date"/>
    <footer class="footer">
        <div class="container">
            <span class="text-muted"><fmt:formatDate pattern = "Y" value = "${now}" /> <fmt:message key="footer1" />
            </span>
        </div>
    </footer>
</body>
</html>
