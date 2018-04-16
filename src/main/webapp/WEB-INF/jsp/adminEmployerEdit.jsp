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
    <title><fmt:message key="admin.employer.edit.title" /></title>
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
                    <a class="nav-link" href="adminHome"><fmt:message key="home" /></a>
                </li>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_candidate_view">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="admin.candidate" />
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_employer_view">
                    <li class="nav-item active">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="admin.employer" />
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_vacancy_view">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="vacancies" />
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_interview_view">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="interview" />
                        </button>
                    </li>
                </form>
                <li class="nav-item">
                    <a href="contact" class="nav-link" target="_blank"><fmt:message key="contact" /></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <form class="margin" action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_employer_edit">
                    <input type="hidden" name="adminEmployerEdit" value="${employer.employerId}">
                    <select class="form-control" title="language" id="language" name="language" onchange="submit()">
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>

                <form class="form-signin" action="FrontController" method="post">
                    <input type="hidden" name="command" value="">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="exit" />
                    </button>
                </form>
            </ul>
        </div>
    </nav>
    <br/><br/>
    <div class="container">
        <form class="form-horizontal" action="FrontController" method="post">
            <input type="hidden" name="command" value="admin_employer_edit_save">
            <input type="hidden" name="employerId" value="${employer.employerId}">
            <div class="form-group">
                <label class="control-label col-sm-2" for="surname"><fmt:message key="surname" />:
                </label>
                <div class="col-sm-10">
                    <input name="surname" type="text" class="form-control" id="surname"
                           pattern="[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}"
                           placeholder="<fmt:message key="enter.surname" />" value="${employer.surname}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name"><fmt:message key="name" />:
                </label>
                <div class="col-sm-10">
                    <input name="name" type="text" class="form-control" id="name"
                           pattern="[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}"
                           placeholder="<fmt:message key="enter.name" />" value="${employer.name}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="lastname"><fmt:message key="lastname" />:
                </label>
                <div class="col-sm-10">
                    <input name="lastname" type="text" class="form-control" id="lastname"
                           placeholder="<fmt:message key="enter.lastname" />" value="${employer.lastname}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="address"><fmt:message key="address" />:
                </label>
                <div class="col-sm-10">
                    <input name="address" type="text" class="form-control" id="address" required
                           placeholder="<fmt:message key="enter.address" />" value="${employer.address}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="phone"><fmt:message key="phone" />:
                </label>
                <div class="col-sm-10">
                    <input name="phone" type="tel" class="form-control" id="phone" required
                           pattern="^[+]?((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,12}$"
                           placeholder="<fmt:message key="enter.phone" />" value="${employer.phone}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email"><fmt:message key="email" />:
                </label>
                <div class="col-sm-10">
                    <input name="email" type="email" class="form-control" id="email" required
                           pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                           placeholder="<fmt:message key="enter.email" />" value="${employer.email}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="company"><fmt:message key="company" />:
                </label>
                <div class="col-sm-10">
                    <input name="company" type="text" class="form-control" id="company"
                           placeholder="<fmt:message key="enter.company" />" value="${employer.company}">
                </div>
            </div>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="save"/></button>
            <button class="btn btn-lg btn-primary btn-block" type="reset">
                <fmt:message key="return.data" /></button>
        </form>
        <form action="FrontController" method="post">
            <input type="hidden" name="command" value="admin_employer_view">
            <button type="submit" class="btn btn-lg btn-primary btn-block cancel-mrgn">
                <fmt:message key="delete.cancel" /></button>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
