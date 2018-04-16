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
    <title><fmt:message key="interview.edit.title" /></title>
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
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="admin.candidate"/>
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_employer_view">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="admin.employer"/>
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_vacancy_view">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="vacancies"/>
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_interview_view">
                    <li class="nav-item active">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="interview"/>
                        </button>
                    </li>
                </form>
                <li class="nav-item">
                    <a href="contact" class="nav-link" target="_blank"><fmt:message key="contact" /></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <form class="margin" action="FrontController" method="post">
                    <input type="hidden" name="command" value="admin_interview_edit">
                    <input type="hidden" name="adminInterviewEdit" value="${interview.interviewId}">
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
            <input type="hidden" name="command" value="admin_interview_edit_save">
            <input type="hidden" name="interviewId" value="${interview.interviewId}">
            <div class="form-group">
                <label class="control-label col-sm-2" for="preResult"><fmt:message key="result.pre" />:
                </label>
                <div class="col-sm-10">
                    <select class="custom-select form-control" name="preResult" id="preResult" >
                        <option selected value="${interview.preResult}">
                            <fmt:message key="enter.result.current"/> ${interview.preResult}</option>
                        <option value="Не указано"><fmt:message key="enter.result.non" /></option>
                        <option value="Успешно"><fmt:message key="enter.result.successfully" /></option>
                        <option value="Есть вопросы"><fmt:message key="enter.result.questions" /></option>
                        <option value="Сомнительно"><fmt:message key="enter.result.doubt" /></option>
                        <option value="Провал"><fmt:message key="enter.result.fail" /></option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="finalResult"><fmt:message key="result.final" />:
                </label>
                <div class="col-sm-10">
                    <select class="custom-select form-control" name="finalResult" id="finalResult" >
                        <option selected value="${interview.finalResult}">
                            <fmt:message key="enter.result.current"/> ${interview.finalResult}</option>
                        <option value="Не указано"><fmt:message key="enter.result.non" /></option>
                        <option value="Успешно"><fmt:message key="enter.result.successfully" /></option>
                        <option value="Есть вопросы"><fmt:message key="enter.result.questions" /></option>
                        <option value="Сомнительно"><fmt:message key="enter.result.doubt" /></option>
                        <option value="Провал"><fmt:message key="enter.result.fail" /></option>
                    </select>
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
            <input type="hidden" name="command" value="admin_interview_view">
            <button type="submit" class="btn btn-lg btn-primary btn-block cancel-mrgn">
                <fmt:message key="delete.cancel" /></button>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
