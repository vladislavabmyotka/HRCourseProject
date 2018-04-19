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
    <title><fmt:message key="vacancy.edit.title" /></title>
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
                <li class="nav-item active dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"><fmt:message key="view.edit" />
                        <span class="sr-only">(current)</span></a>
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
                        <input type="hidden" name="command" value="employer_vacancy_edit">
                        <input type="hidden" name="employerVacancyEdit" value="${vacancy.vacancyId}">
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
            <input type="hidden" name="command" value="employer_vacancy_edit_save">
            <input type="hidden" name="vacancyId" value="${vacancy.vacancyId}">
            <div class="form-group">
                <label class="control-label col-sm-2" for="post"><fmt:message key="post" />:
                </label>
                <div class="col-sm-10">
                    <input name="post" type="text" class="form-control" id="post" required
                           placeholder="<fmt:message key="enter.post" />" value="${vacancy.post}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="company"><fmt:message key="company" />:
                </label>
                <div class="col-sm-10">
                    <input name="company" type="text" class="form-control" id="company" required
                           placeholder="<fmt:message key="enter.company" />" value="${vacancy.company}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="salary"><fmt:message key="salary" />:
                </label>
                <div class="col-sm-10">
                    <input name="salary" type="text" class="form-control" id="salary" pattern="\d+\.\d{2}"
                           placeholder="<fmt:message key="enter.salary" />" value="${vacancy.salary}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="location"><fmt:message key="location" />:
                </label>
                <div class="col-sm-10">
                    <input name="location" type="text" class="form-control" id="location"
                           placeholder="<fmt:message key="enter.location" />" value="${vacancy.location}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="experience"><fmt:message key="experience" />:
                </label>
                <div class="col-sm-10">
                    <input name="experience" type="number" class="form-control" id="experience" min="0" max="100"
                           placeholder="<fmt:message key="enter.experience" />"
                           value="${vacancy.experience}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="english"><fmt:message key="english" />:
                </label>
                <div class="col-sm-10">
                    <select class="custom-select form-control" name="english" id="english" >
                        <option selected value="${vacancy.english}">
                            <fmt:message key="enter.english.current"/> ${vacancy.english}</option>
                        <option value="Не указано"><fmt:message key="enter.english.non" /></option>
                        <option value="A0 (Абсолютный новичок)"><fmt:message key="enter.english.a0" />
                        </option>
                        <option value="A1 (Базовый)"><fmt:message key="enter.english.a1" /></option>
                        <option value="A1+ (Базовый средний)"><fmt:message key="enter.english.a1+" /></option>
                        <option value="A2 (Выше базового)"><fmt:message key="enter.english.a2" /></option>
                        <option value="A2+ (Ниже среднего)"><fmt:message key="enter.english.a2+" /></option>
                        <option value="B1 (Средний)"><fmt:message key="enter.english.b1" /></option>
                        <option value="B1+ (Выше среднего)"><fmt:message key="enter.english.b1+" /></option>
                        <option value="B2 (Повышенный)"><fmt:message key="enter.english.b2" /></option>
                        <option value="B2+ (Ниже продвинутого)"><fmt:message key="enter.english.b2+" />
                        </option>
                        <option value="C1 (Продвинутый)"><fmt:message key="enter.english.c1" /></option>
                        <option value="C1+ (Выше продвинутого)"><fmt:message key="enter.english.c1+" />
                        </option>
                        <option value="C2 (Специалист)"><fmt:message key="enter.english.c2" /></option>
                        <option value="Носитель языка"><fmt:message key="enter.english.native" /></option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="text"><fmt:message key="text" />:
                </label>
                <div class="col-sm-10">
                    <textarea name="text" class="form-control" id="text" rows="4" required
                              placeholder="<fmt:message key="enter.text" />">${vacancy.text}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="conditionVacancy"><fmt:message key="condition" />:
                </label>
                <div class="col-sm-10">
                    <select class="custom-select form-control" name="conditionVacancy" id="conditionVacancy" >
                        <option selected value="${vacancy.conditionVacancy}">
                            <fmt:message key="enter.condition.current"/> ${vacancy.conditionVacancy}</option>
                        <option value="Не указано"><fmt:message key="enter.condition.non" /></option>
                        <option value="Открыта"><fmt:message key="enter.condition.open" />
                        </option>
                        <option value="Закрыта"><fmt:message key="enter.condition.close" /></option>
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
            <input type="hidden" name="command" value="employer_view_vacancy">
            <button type="submit" class="btn btn-lg btn-primary btn-block cancel-mrgn">
                <fmt:message key="delete.cancel" /></button>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script src="http://code.jquery.com/jquery.min.js"></script>
    <script>
        <%@include file='../js/bootstrap.min.js' %>
    </script>
</body>
</html>
