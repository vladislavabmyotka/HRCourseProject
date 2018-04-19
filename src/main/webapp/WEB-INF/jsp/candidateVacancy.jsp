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
    <title><fmt:message key="vacancies" /></title>
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
                    <a class="nav-link" href="#"><fmt:message key="vacancies" />
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="candidateHome"><fmt:message key="home" /></a>
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
                        <input type="hidden" name="command" value="candidate_vacancy_view">
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
        <form class="form-inline my-2 my-lg-0 search" action="FrontController" method="post">
            <input type="hidden" name="command" value="candidate_vacancy_search">
            <input name="search" class="form-control mr-sm-2" type="text" placeholder="<fmt:message key="search" />"
                   aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search" /></button>
        </form>
        <h6 class="form-signin-heading error"> ${errorMessage} </h6> <br/>
        <h6 class="form-signin-heading"> ${notificationMessage} </h6> <br/>
        <table class="table table-bordered table-hover table-sm table-mrgn">
            <thead class="thead-default">
            <tr>
                <th>#</th>
                <th><fmt:message key="post" /></th>
                <th><fmt:message key="company" /></th>
                <th><fmt:message key="salary" /></th>
                <th><fmt:message key="location" /></th>
                <th><fmt:message key="experience" /></th>
                <th><fmt:message key="english" /></th>
                <th><fmt:message key="text" /></th>
                <th><fmt:message key="condition" /></th>
                <th><fmt:message key="info.employer" /></th>
                <th><fmt:message key="action" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="vacancy" items="${vacancyList}" varStatus="id">
                <tr>
                    <th scope="row"><c:out value="${id.count}"/></th>
                    <td><c:out value="${vacancy.post}"/></td>
                    <td><c:out value="${vacancy.company}"/></td>
                    <td><c:out value="${vacancy.salary}"/></td>
                    <td><c:out value="${vacancy.location}"/></td>
                    <td><c:out value="${vacancy.experience}"/></td>
                    <td><c:out value="${vacancy.english}"/></td>
                    <td><c:out value="${vacancy.text}"/></td>
                    <td><c:out value="${vacancy.conditionVacancy}"/></td>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="candidate_vacancy_send_email">
                            <c:out value="${vacancy.employerInfo}"/><br/>
                            <button name="employerId" class="btn btn-link" type="submit"
                                    value="${vacancy.employerId}"><fmt:message key="send.email" />
                            </button>
                        </form>
                    </td>
                    <td><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="candidate_vacancy_apply">
                        <button name="candidateVacancyApply" class="btn btn-link" type="submit"
                                value="${vacancy.vacancyId}"><fmt:message key="vacancy.apply" />
                        </button></form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
