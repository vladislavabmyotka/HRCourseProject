<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang.app" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="employer.home" /></title>
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

            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#"><fmt:message key="home" />
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><fmt:message key="add" /></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="employer_add_information">
                            <button type="submit" class="btn btn-default empl-add cursor dropdown-item">
                                <fmt:message key="employer.add.info" /></button>
                        </form>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="employer_add_vacancy">
                            <button type="submit" class="btn btn-default empl-add cursor dropdown-item">
                                <fmt:message key="employer.add.vacancy" /></button>
                        </form>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><fmt:message key="view.edit" /></a>
                    <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="employer_view_edit_information">
                            <button type="submit" class="btn btn-default empl-add cursor dropdown-item">
                                <fmt:message key="employer.view.edit.info" /></button>
                        </form>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="employer_view_vacancy">
                            <button type="submit" class="btn btn-default empl-add cursor dropdown-item">
                                <fmt:message key="employer.view.edit.vacancy" /></button>
                        </form>
                    </div>
                </li>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="employer_interview">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="interview" />
                        </button>
                    </li>
                </form>
                <li class="nav-item">
                    <a class="nav-link" href="contact" target="_blank"><fmt:message key="contact" /></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown personal-account">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                        <fmt:message key="general.personal.cabinet" /><b class="caret"></b></a>
                    <ul id="menu1" class="dropdown-menu">
                        <li><a href="editAccount"><fmt:message key="general.personal.cabinet.edit" /></a></li>
                        <li><a href="deleteAccount"><fmt:message key="general.personal.cabinet.delete" /></a></li>
                    </ul>
                </li>
                <li>
                    <form class="margin">
                        <select class="form-control" title="language" id="language" name="language"
                                onchange="location=this.options[this.selectedIndex].value">
                            <option value="http://localhost:8080/hr/employerHome?language=ru"
                            ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="http://localhost:8080/hr/employerHome?language=en"
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

    <ctg:mainPage language="${language}" attachment="e" login="${pageContext.session.getAttribute(\"role\").login}"
                  error="${errorMessage}"/>

    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script>
        <%@include file='../js/bootstrap.min.js' %>
    </script>
</body>
</html>
