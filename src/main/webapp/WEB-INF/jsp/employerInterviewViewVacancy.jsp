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
    <title><fmt:message key="vacancy.view" /></title>
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
                    <a class="nav-link" href="employerHome"><fmt:message key="home" /></a>
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
                        <input type="hidden" name="command" value="employer_interview_view_vacancy">
                        <input type="hidden" name="employerInterviewViewVacancy" value="${vacancy.vacancyId}">
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
        <form class="form-horizontal">
            <br/>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="post"><fmt:message key="post" />:</label>
                </div>
                <div class="col-auto">
                    <input name="post" type="text" class="form-control cand-view-edit-input-mrgn" id="post"
                           disabled value="${vacancy.post}">
                </div>
            </div><br/>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="company"><fmt:message key="company" />:</label>
                </div>
                <div class="col-auto">
                    <input name="company" type="text" class="form-control cand-view-edit-input-mrgn" id="company"
                           disabled value="${vacancy.company}">
                </div>
            </div><br/>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="salary"><fmt:message key="salary" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="salary" type="text" class="form-control cand-view-edit-input-mrgn" id="salary"
                           disabled value="${vacancy.salary}">
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="location"><fmt:message key="location" />:
                    </label>
                </div><br/>
                <div class="col-auto">
                    <input name="location" type="text" class="form-control cand-view-edit-input-mrgn" id="location"
                           disabled value="${vacancy.location}">
                </div>
            </div><br/>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="experience"><fmt:message key="experience" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="experience" type="text" class="form-control cand-view-edit-input-mrgn" id="experience"
                           disabled value="${vacancy.experience}">
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="english"><fmt:message key="english" />:</label>
                </div>
                <div class="col-auto">
                    <input name="english" type="text" class="form-control cand-view-edit-input-mrgn" id="english"
                           disabled value="${vacancy.english}">
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="text"><fmt:message key="text" />:</label>
                </div>
                <div class="col-auto">
                        <textarea name="text" class="form-control textarea-input-mrgn" id="text" rows="4"
                                  disabled>${vacancy.text}</textarea>
                </div>
            </div><br/>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="condition"><fmt:message key="condition" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="condition" type="text" class="form-control cand-view-edit-input-mrgn" id="condition"
                           disabled value="${vacancy.conditionVacancy}">
                </div>
            </div>

            <form action="FrontController" method="post">
                <input type="hidden" name="command" value="employer_interview">
                <button type="submit" class="btn btn-lg btn-primary btn-block cancel-mrgn">
                    <fmt:message key="back" /></button>
            </form>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script>
        <%@include file='../js/bootstrap.min.js' %>
    </script>
</body>
</html>
