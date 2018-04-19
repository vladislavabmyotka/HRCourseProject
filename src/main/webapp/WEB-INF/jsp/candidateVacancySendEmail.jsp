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
    <title><fmt:message key="send.email" /></title>
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
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="home" /></a>
                </li>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="candidate_add">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="candidate.add" />
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="candidate_view_edit">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor">
                            <fmt:message key="candidate.view.edit" /></button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="candidate_vacancy_view">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor">
                            <fmt:message key="vacancies" /></button>
                    </li>
                </form>
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
                        <li><a href="deleteAccount"><fmt:message key="general.personal.cabinet.delete" /></a></li>
                    </ul>
                </li>
                <li>
                    <form class="margin" action="FrontController" method="post">
                        <input type="hidden" name="command" value="candidate_vacancy_send_email">
                        <input type="hidden" name="employerId" value="${employer.employerId}">
                        <select class="form-control" title="language" id="language" name="language" onchange="submit()">
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
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

    <br/><br/>
    <div class="container">
        <form class="form-horizontal" action="FrontController" method="post">
            <input type="hidden" name="command" value="candidate_vacancy_send_email_submit">
            <input type="hidden" name="employerId" value="${employer.employerId}">
            <div class="form-group">
                <label class="control-label col-sm-2" for="to"><fmt:message key="to" />:
                </label>
                <div class="col-sm-10">
                    <input name="to" type="text" class="form-control" id="to"
                           pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                           placeholder="<fmt:message key="enter.email" />" value="${employer.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="theme"><fmt:message key="theme" />:
                </label>
                <div class="col-sm-10">
                    <input name="theme" type="text" class="form-control" id="theme">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="message"><fmt:message key="message" />:
                </label>
                <div class="col-sm-10">
                    <textarea name="message" class="form-control" id="message" rows="6"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="password"><fmt:message key="password.for.email" />:
                </label>
                <div class="col-sm-10">
                    <input type="password" name="password" class="form-control" id="password">
                </div>
            </div>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="send"/></button>
            <button class="btn btn-lg btn-primary btn-block" type="reset"><fmt:message key="reset" /></button>
        </form>
        <form class="margin" action="FrontController" method="post">
            <input type="hidden" name="command" value="candidate_vacancy_view">
            <button type="submit" class="btn btn-lg btn-primary btn-block cancel-mrgn"><fmt:message key="back" />
            </button>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
