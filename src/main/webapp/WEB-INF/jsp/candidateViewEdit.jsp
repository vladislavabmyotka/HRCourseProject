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
    <title><fmt:message key="candidate.view.edit" /></title>
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
                    <a class="nav-link" href="#"><fmt:message key="candidate.view.edit" />
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
                        <input type="hidden" name="command" value="candidate_view_edit">
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
            <input type="hidden" name="command" value="candidate_view_edit_save">
            <input type="hidden" name="candidateId" value="${candidate.candidateId}">
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="surname"><fmt:message key="surname" />:</label>
                </div>
                <div class="col-auto">
                    <input name="surname" type="text" class="form-control cand-view-edit-input-mrgn" id="surname"
                           disabled pattern="[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}"
                           placeholder="<fmt:message key="enter.surname" />" value="${candidate.surname}">
                </div>
                <div class="col-auto">
                    <button id="surnameUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('surname', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="name"><fmt:message key="name" />:</label>
                </div>
                <div class="col-auto">
                    <input name="name" type="text" class="form-control cand-view-edit-input-mrgn" id="name" disabled
                           pattern="[A-ZА-Я][a-zа-яёA-ZА-ЯЁ-]{1,40}"
                           placeholder="<fmt:message key="enter.name" />" value="${candidate.name}">
                </div>
                <div class="col-auto">
                    <button id="nameUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('name', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="lastname"><fmt:message key="lastname" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="lastname" type="text" class="form-control  cand-view-edit-input-mrgn" id="lastname"
                           disabled placeholder="<fmt:message key="enter.lastname" />" value="${candidate.lastname}">
                </div>
                <div class="col-auto">
                    <button id="lastnameUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('lastname', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="age"><fmt:message key="age" />:</label>
                </div>
                <div class="col-auto">
                    <input name="age" type="number" class="form-control  number-input-mrgn" id="age" min="1" max="120"
                           required disabled placeholder="<fmt:message key="enter.age" />" value="${candidate.age}">
                </div>
                <div class="col-auto">
                    <button id="ageUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('age', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="email"><fmt:message key="email" />:</label>
                </div>
                <div class="col-auto">
                    <input name="email" type="email" class="form-control  cand-view-edit-input-mrgn" id="email" disabled
                           pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                           placeholder="<fmt:message key="enter.email" />" value="${candidate.email}">
                </div>
                <div class="col-auto">
                    <button id="emailUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('email', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="address"><fmt:message key="address" />:</label>
                </div>
                <div class="col-auto">
                    <input name="address" type="text" class="form-control  cand-view-edit-input-mrgn" id="address"
                           required disabled placeholder="<fmt:message key="enter.address" />"
                           value="${candidate.address}">
                </div>
                <div class="col-auto">
                    <button id="addressUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('address', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="citizenship"><fmt:message key="citizenship" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="citizenship" type="text" class="form-control  cand-view-edit-input-mrgn"
                           id="citizenship" disabled pattern="[A-ZА-ЯЁa-zа-яё\s]+"
                           placeholder="<fmt:message key="enter.citizenship" />" value="${candidate.citizenship}">
                </div>
                <div class="col-auto">
                    <button id="citizenshipUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('citizenship', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="phone"><fmt:message key="phone" />:</label>
                </div>
                <div class="col-auto">
                    <input name="phone" type="tel" class="form-control  cand-view-edit-input-mrgn" id="phone" disabled
                           pattern="^[+]?((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,12}$"
                           placeholder="<fmt:message key="enter.phone" />" value="${candidate.phone}">
                </div>
                <div class="col-auto">
                    <button id="phoneUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('phone', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="post"><fmt:message key="post" />:</label>
                </div>
                <div class="col-auto">
                    <input name="post" type="text" class="form-control  cand-view-edit-input-mrgn" id="post" required
                           disabled placeholder="<fmt:message key="enter.post" />" value="${candidate.post}">
                </div>
                <div class="col-auto">
                    <button id="postUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('post', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="education"><fmt:message key="education" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="education" type="text" class="form-control  cand-view-edit-input-mrgn" id="education"
                           disabled placeholder="<fmt:message key="enter.education" />" value="${candidate.education}">
                </div>
                <div class="col-auto">
                    <button id="educationUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('education', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="experience"><fmt:message key="experience" />:
                    </label>
                </div>
                <div class="col-auto">
                    <input name="experience" type="number" class="form-control  number-input-mrgn" id="experience"
                           min="0" max="100" placeholder="<fmt:message key="enter.experience" />" required disabled
                           value="${candidate.experience}">
                </div>
                <div class="col-auto">
                    <button id="experienceUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('experience', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="english"><fmt:message key="english" />:</label>
                </div>
                <div class="col-auto">
                    <select class="custom-select form-control select-wdth" name="english" id="english" disabled>
                        <option selected value="${candidate.english}">
                            <fmt:message key="enter.english.current"/> ${candidate.english}</option>
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
                <div class="col-auto">
                    <button id="englishUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('english', this)"><fmt:message key="edit"/></button>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="control-label col-sm-2 lbl-wdth" for="skill"><fmt:message key="skill" />:</label>
                </div>
                <div class="col-auto">
                        <textarea name="skill" class="form-control textarea-input-mrgn" id="skill" rows="4" disabled
                                  placeholder="<fmt:message key="enter.skill" />">${candidate.skill}</textarea>
                </div>
                <div class="col-auto">
                    <button id="skillUndisable" type="button" class="btn btn-primary mb-2 btn-mrgn"
                            onclick="undisable('skill', this)"><fmt:message key="edit"/></button>
                </div>
            </div>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">
                <fmt:message key="save"/></button>
            <button class="btn btn-lg btn-primary btn-block" type="reset">
                <fmt:message key="return.data" /></button>
            <a href="candidateHome"><button type="button" class="btn btn-lg btn-primary btn-block cancel-mrgn">
                <fmt:message key="delete.cancel" /></button></a>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script>
        <%@include file='../js/main.js' %>
    </script>
</body>
</html>
