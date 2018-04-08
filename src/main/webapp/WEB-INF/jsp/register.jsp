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
    <title><fmt:message key="index.nav.register" /></title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/main.css' %>
    </style>
    <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
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
                    <a class="nav-link" href="#"><fmt:message key="index.nav.register" />
                        <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp"><fmt:message key="index.nav.authorization" /></a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form>
                    <input type="hidden" name="command" value="register_reload">
                    <select class="form-control" title="language" id="language" name="language" onchange="submit()">
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>
            </ul>
        </div>
    </nav>

    <div class="container">
        <form class="form-signin" action="FrontController" onsubmit="return checkPasswordsMatch(this);">
            <h1 class="form-signin-heading"><fmt:message key="register.container.h1" /></h1>

            <input type="hidden" name="command" value="register">

            <label for="inputLogin" class="sr-only"></label>
            <input name="login" type="text" id="inputLogin" class="form-control" pattern="[a-zA-Z][\s\w]{4,25}"
                   placeholder=<fmt:message key="index.container.login" /> required="" autofocus="">

            <label for="inputPassword" class="sr-only"></label>
            <input name="password" type="password" id="inputPassword" class="form-control"
                   pattern="^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$"
                   placeholder=<fmt:message key="index.container.password" /> required="">

            <label for="repeatPassword" class="sr-only"></label>
            <input name="repeatPassword" type="password" id="repeatPassword" class="form-control"
                   pattern="^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,25}$"
                   placeholder="<fmt:message key="register.repeat.password" />" required="">

            <div class="radio">
                <label><fmt:message key="register.container.attachment" /></label>
                <label>
                    <input type="radio" name="attachment" id="optionsRadios1" value="c" checked>
                    <fmt:message key="register.container.attachment.candidate" />
                </label>
            </div>
            <div class="radio">
                <label>
                    <input type="radio" name="attachment" id="optionsRadios2" value="e">
                    <fmt:message key="register.container.attachment.employer" />
                </label>
            </div><br/>
            <div class="g-recaptcha" data-sitekey="6LenFEIUAAAAAIofSHxlWmPwJw6gFUqnkKxtesst"></div>

            <br/>
            <h6 class="form-signin-heading error"> ${errorMessage} </h6>
            <br/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="register.container.sign" />
            </button>
            <button class="btn btn-lg btn-primary btn-block" type="reset"><fmt:message key="index.container.reset" />
            </button>
        </form>
    </div>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script>
        <%@include file='../js/main.js' %>
    </script>
</body>
</html>
