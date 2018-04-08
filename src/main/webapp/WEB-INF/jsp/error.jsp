<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang.app" />
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="error.title" /></title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/main.css' %>
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">HR System. ${pageContext.session.getAttribute("role").login}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto"></ul>
            <ul class="nav navbar-nav navbar-right">
                <form>
                    <select class="form-control" title="language" id="language" name="language"
                            onchange="location=this.options[this.selectedIndex].value">
                        <option value="http://localhost:8080/hr/error?language=ru"
                        ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="http://localhost:8080/hr/error?language=en"
                        ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>
            </ul>
        </div>
    </nav>
    <br><br><br><br><br>
    <div class="container">
        <h1 class="form-signin-heading"><fmt:message key="error.h1" /></h1>
        <p>
            <h3 class="form-signin-heading">
                <fmt:message key="error.request" /> ${pageContext.errorData.requestURI}
                    <fmt:message key="error.failed" /> <br>
                <fmt:message key="error.servlet" /> ${pageContext.errorData.servletName} <br>
                <fmt:message key="error.status" /> ${pageContext.errorData.statusCode} <br>
                <fmt:message key="error.exception" /> ${pageContext.exception} <br>
                <fmt:message key="error.message" /> ${pageContext.exception.message}
            </h3>
        </p>
        <a href="index.jsp"><button class="btn btn-lg btn-primary btn-block" type="submit">
            <fmt:message key="error.back" /></button></a>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
