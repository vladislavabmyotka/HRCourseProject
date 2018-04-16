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
    <title><fmt:message key="admin.employer.management" /></title>
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
                    <a class="nav-link" href="#"><fmt:message key="admin.employer" />
                        <span class="sr-only">(current)</span></a>
                </li>
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
                <form class="margin">
                    <input type="hidden" name="command" value="admin_employer_view">
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
        <h6 class="form-signin-heading error"> ${errorMessage} </h6> <br/>
        <table class="table table-bordered table-hover table-mrgn">
            <thead class="thead-default">
            <tr>
                <th>#</th>
                <th><fmt:message key="admin.employer.surname" /></th>
                <th><fmt:message key="admin.employer.name" /></th>
                <th><fmt:message key="admin.employer.lastname" /></th>
                <th><fmt:message key="admin.employer.address" /></th>
                <th><fmt:message key="admin.employer.phone" /></th>
                <th><fmt:message key="admin.employer.email" /></th>
                <th><fmt:message key="admin.employer.company" /></th>
                <th><fmt:message key="action" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employer" items="${employerList}" varStatus="id">
                <tr>
                    <th scope="row"><c:out value="${id.count}"/></th>
                    <td><c:out value="${employer.surname}"/></td>
                    <td><c:out value="${employer.name}"/></td>
                    <td><c:out value="${employer.lastname}"/></td>
                    <td><c:out value="${employer.address}"/></td>
                    <td><c:out value="${employer.phone}"/></td>
                    <td><c:out value="${employer.email}"/></td>
                    <td><c:out value="${employer.company}"/></td>
                    <td><form action="FrontController" method="post">
                        <input type="hidden" name="command" value="admin_employer_delete">
                        <button name="adminEmployerDelete" class="btn btn-link" type="submit"
                                value="${employer.employerId}"><fmt:message key="delete.delete" />
                        </button></form>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="admin_employer_edit">
                            <button name="adminEmployerEdit" class="btn btn-link" type="submit"
                                    value="${employer.employerId}"><fmt:message key="edit" />
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
