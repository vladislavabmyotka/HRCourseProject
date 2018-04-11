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
    <title><fmt:message key="edit.title" /></title>
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
                        <c:set var="attachment" value="${pageContext.session.getAttribute(\"role\").attachment}"/>
                       <c:choose>
                           <c:when test="${attachment == 'c'}">
                               <a class="nav-link" href="candidateHome"><fmt:message key="home" /></a>
                           </c:when>
                           <c:otherwise>
                               <a class="nav-link" href="employerHome"><fmt:message key="home" /></a>
                           </c:otherwise>
                       </c:choose>
                </li>
                <li class="nav-item">
                    <a href="contact" class="nav-link" target="_blank"><fmt:message key="contact" /></a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown personal-account">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                        <fmt:message key="general.personal.cabinet" /><b class="caret"></b></a>
                    <ul id="menu1" class="dropdown-menu">
                        <li><a href="#"><fmt:message key="general.personal.cabinet.edit" /></a></li>
                        <li><a href="deleteAccount"><fmt:message key="general.personal.cabinet.delete" /></a></li>
                    </ul>
                </li>
                <li>
                    <form class="margin">
                        <select class="form-control" title="language" id="language" name="language"
                                onchange="location=this.options[this.selectedIndex].value">
                            <option value="http://localhost:8080/hr/editAccount?language=ru"
                            ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="http://localhost:8080/hr/editAccount?language=en"
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
    <br><br>
    <div class="container">
        <form class="form-signin" action="FrontController" onsubmit="return (checkPasswordsMatch(this));">
            <h1 class="form-signin-heading"><fmt:message key="edit.title" /></h1>

            <input type="hidden" name="command" value="edit_account_data">

            <label for="inputLogin" class="sr-only"></label>
            <input name="login" type="text" id="inputLogin" class="form-control" pattern="[a-zA-Z][\s\w]{4,25}"
                   value="${pageContext.session.getAttribute("role").login}" required="" autofocus="">

            <label for="inputOldPassword" class="sr-only"></label>
            <input name="oldPassword" type="password" id="inputOldPassword" class="form-control"
                   pattern="^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$"
                   placeholder="<fmt:message key="edit.password.old" />" required="">

            <label for="password" class="sr-only"></label>
            <input name="password" type="password" id="password" class="form-control"
                   pattern="^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$"
                   placeholder="<fmt:message key="edit.password.new" />" required="">

            <label for="repeatPassword" class="sr-only"></label>
            <input name="repeatPassword" type="password" id="repeatPassword" class="form-control"
                   pattern="^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$"
                   placeholder="<fmt:message key="edit.password.new.repeat" />" required="">

            <br/>
            <h6 class="form-signin-heading error"> ${errorLoginPassMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="edit.confirm" />
            </button>
            <button class="btn btn-lg btn-primary btn-block" type="reset"><fmt:message key="edit.reset" />
            </button>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script>
        <%@include file='../js/main.js' %>
        <%@include file='../js/bootstrapDropdown.js' %>
    </script>
</body>
</html>
