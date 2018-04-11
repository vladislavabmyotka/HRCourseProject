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
    <title><fmt:message key="delete.title" /></title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/main.css' %>
    </style>
</head>
<body>

    <c:set var="attachment" value="${pageContext.session.getAttribute(\"role\").attachment}"/>
    <c:choose>
        <c:when test="${attachment == 'c'}">
            <c:set var="page" value="candidateHome"/>
        </c:when>
        <c:otherwise>
            <c:set var="page" value="employerHome"/>
        </c:otherwise>
    </c:choose>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">HR System. ${pageContext.session.getAttribute("role").login}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${page}"><fmt:message key="home" /></a>
                </li>
                <li class="nav-item">
                    <a href="contact" class="nav-link" target="_blank"><fmt:message key="contact" /></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown personal-account">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                        <fmt:message key="general.personal.cabinet" /><b class="caret"></b></a>
                    <ul id="menu1" class="dropdown-menu">
                        <li><a href="editAccount"><fmt:message key="general.personal.cabinet.edit" /></a></li>
                        <li><a href="#"><fmt:message key="general.personal.cabinet.delete" /></a></li>
                    </ul>
                </li>
                <li>
                    <form class="margin">
                        <select class="form-control" title="language" id="language" name="language"
                                onchange="location=this.options[this.selectedIndex].value">
                            <option value="http://localhost:8080/hr/deleteAccount?language=ru"
                            ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="http://localhost:8080/hr/deleteAccount?language=en"
                            ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </form>
                </li>
                <li>
                    <form class="form-signin" action="FrontController" method="post">
                        <input type="hidden" name="command" value="">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="exit" />
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <br><br>
    <div class="container">
        <form class="form-signin" action="FrontController">
            <h1 class="form-signin-heading"><fmt:message key="delete.h1" /></h1>
            <h4 class="form-signin-heading"><fmt:message key="delete.undone" /></h4>

            <input type="hidden" name="command" value="delete_account">

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="delete.delete" />
            </button>
            <a href="${page}"><button class="btn btn-lg btn-primary btn-block" type="button">
                <fmt:message key="delete.cancel" /></button></a>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script>
        <%@include file='../js/bootstrapDropdown.js' %>
    </script>
</body>
</html>
