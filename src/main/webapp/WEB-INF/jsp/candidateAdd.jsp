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
    <title><fmt:message key="candidate.add.title" /></title>
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
                    <a class="nav-link" href="#"><fmt:message key="candidate.add" />
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="candidateHome"><fmt:message key="home" /></a>
                </li>
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
                        <input type="hidden" name="command" value="candidate_add">
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
            <input type="hidden" name="command" value="candidate_add_save">
            <div class="form-group">
                <label class="control-label col-sm-2" for="surname"><fmt:message key="surname" />:
                </label>
                <div class="col-sm-10">
                    <input name="surname" type="text" class="form-control" id="surname"
                           pattern="[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}" placeholder="<fmt:message key="enter.surname" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="name"><fmt:message key="name" />:
                </label>
                <div class="col-sm-10">
                    <input name="name" type="text" class="form-control" id="name"
                           pattern="[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}" placeholder="<fmt:message key="enter.name" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="lastname"><fmt:message key="lastname" />:
                </label>
                <div class="col-sm-10">
                    <input name="lastname" type="text" class="form-control" id="lastname"
                           placeholder="<fmt:message key="enter.lastname" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="age"><fmt:message key="age" />:
                </label>
                <div class="col-sm-10">
                    <input name="age" type="number" class="form-control" id="age" min="1" max="120" required
                           placeholder="<fmt:message key="enter.age" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email"><fmt:message key="email" />:
                </label>
                <div class="col-sm-10">
                    <input name="email" type="email" class="form-control" id="email"
                           pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                           placeholder="<fmt:message key="enter.email" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="address"><fmt:message key="address" />:
                </label>
                <div class="col-sm-10">
                    <input name="address" type="text" class="form-control" id="address" required
                           placeholder="<fmt:message key="enter.address" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="citizenship">
                    <fmt:message key="citizenship" />:</label>
                <div class="col-sm-10">
                    <input name="citizenship" type="text" class="form-control" id="citizenship"
                           pattern="[A-ZА-ЯЁa-zа-яё\s]+" placeholder="<fmt:message key="enter.citizenship" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="phone"><fmt:message key="phone" />:
                </label>
                <div class="col-sm-10">
                    <input name="phone" type="tel" class="form-control" id="phone"
                           pattern="^[+]?((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,12}$"
                           placeholder="<fmt:message key="enter.phone" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="post"><fmt:message key="post" />:
                </label>
                <div class="col-sm-10">
                    <input name="post" type="text" class="form-control" id="post" required
                           placeholder="<fmt:message key="enter.post" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="education"><fmt:message key="education" />:
                </label>
                <div class="col-sm-10">
                    <input name="education" type="text" class="form-control" id="education"
                           placeholder="<fmt:message key="enter.education" />">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="experience"><fmt:message key="experience" />:
                </label>
                <div class="col-sm-10">
                    <input name="experience" type="number" class="form-control" id="experience" min="0" max="100"
                           placeholder="<fmt:message key="enter.experience" />" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="english"><fmt:message key="english" />:
                </label>
                <div class="col-sm-10">
                    <select class="custom-select form-control" name="english" id="english" >
                        <option selected value="Не указано"><fmt:message key="enter.english.non" /></option>
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
                <label class="control-label col-sm-2" for="skill"><fmt:message key="skill" />:
                </label>
                <div class="col-sm-10">
                        <textarea name="skill" class="form-control" id="skill" rows="4"
                                  placeholder="<fmt:message key="enter.skill" />"></textarea>
                </div>
            </div>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="add"/></button>
            <button class="btn btn-lg btn-primary btn-block" type="reset">
                <fmt:message key="reset" /></button>
            <a href="candidateHome"><button type="button" class="btn btn-lg btn-primary btn-block cancel-mrgn">
                <fmt:message key="delete.cancel" /></button></a>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
