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
    <title><fmt:message key="contact" /></title>
    <link rel="icon" href="../image/hr.jpg" type="image/x-icon">
    <link rel="shortcut icon" href="../image/hr.jpg" type="image/x-icon">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/main.css' %>
        <%@include file='../css/contact.css' %>
    </style>
</head>
<body class="w3-light-grey">
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">HR System. ${pageContext.session.getAttribute("role").login}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">

            <ul class="navbar-nav mr-auto"></ul>

            <ul class="nav navbar-nav navbar-right">
                <form class="margin">
                    <select class="form-control" title="language" id="language" name="language"
                            onchange="location=this.options[this.selectedIndex].value">
                        <option value="${pageContext.request.requestURL}?language=ru"
                        ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="${pageContext.request.requestURL}?language=en"
                        ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>

                <form class="form-signin" action="FrontController" method="post">
                    <input type="hidden" name="command" value="">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="exit" />
                    </button>
                </form>
            </ul>
        </div>
    </nav><br/><br/>
    <!-- Page Container -->
    <div class="w3-content w3-margin-top page-container">

        <!-- The Grid -->
        <div class="w3-row-padding">

            <!-- Left Column -->
            <div class="w3-third">

                <div class="w3-white w3-text-grey w3-card-4">
                    <div class="w3-display-container">
                        <h2 class="name">
                            <fmt:message key="contact.first.name" /><br>
                            <fmt:message key="contact.second.name" />
                        </h2>
                    </div>
                    <div class="w3-container">
                        <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>
                            <fmt:message key="contact.beginning.java.developer" /></p>
                        <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i>
                            <fmt:message key="contact.location" /></p>
                        <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>
                            <fmt:message key="contact.email" /></p>
                        <p><i class="fa fa-phone fa-fw w3-margin-right w3-large w3-text-teal"></i>
                            <fmt:message key="contact.phone" /></p>
                        <hr>

                        <p class="w3-large"><b><i class="fa fa-asterisk fa-fw w3-margin-right w3-text-teal"></i>
                            <fmt:message key="contact.skills" /></b></p>
                        <p><fmt:message key="contact.skills.java" /></p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal java">80%</div>
                        </div>
                        <p><fmt:message key="contact.skills.web" /></p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal web">
                                <div class="w3-center w3-text-white">60%</div>
                            </div>
                        </div>
                        <p><fmt:message key="contact.skills.sql" /></p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal sql">75%</div>
                        </div>
                        <p><fmt:message key="contact.skills.c" /></p>
                        <div class="w3-light-grey w3-round-xlarge w3-small">
                            <div class="w3-container w3-center w3-round-xlarge w3-teal c">50%</div>
                        </div>
                        <br>

                        <p class="w3-large w3-text-theme"><b><i class="fa fa-globe fa-fw w3-margin-right w3-text-teal">
                            </i><fmt:message key="contact.languages" /></b></p>
                        <p><fmt:message key="contact.languages.russian" /></p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal russian"></div>
                        </div><br/>
                        <p><fmt:message key="contact.languages.english" /></p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal english"></div>
                        </div><br/>
                        <p><fmt:message key="contact.languages.belarusian" /></p>
                        <div class="w3-light-grey w3-round-xlarge">
                            <div class="w3-round-xlarge w3-teal belarusian"></div>
                        </div>
                        <br>
                    </div>
                </div><br>

                <!-- End Left Column -->
            </div>

            <!-- Right Column -->
            <div class="w3-twothird">

                <div class="w3-container w3-card w3-white w3-margin-bottom">
                    <h2 class="w3-text-grey w3-padding-16">
                        <i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>
                        <fmt:message key="contact.work.experience" /></h2>
                    <div class="w3-container">
                        <h5 class="w3-opacity"><b><fmt:message key="contact.work.experience.java.back" /></b></h5>
                        <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>
                            <fmt:message key="contact.work.experience.begin" /> - <span class="w3-tag w3-teal w3-round">
                                <fmt:message key="contact.current" /></span></h6>
                        <p><fmt:message key="contact.work.experience.java.back.description" /></p>
                        <hr>
                    </div>
                    <div class="w3-container">
                        <h5 class="w3-opacity"><b><fmt:message key="contact.work.experience.front" /></b></h5>
                        <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>
                            <fmt:message key="contact.work.experience.begin" /> - <span class="w3-tag w3-teal w3-round">
                                <fmt:message key="contact.current" /></span></h6>
                        <p><fmt:message key="contact.work.experience.front.description" /></p>
                        <hr>
                    </div>
                    <div class="w3-container">
                        <h5 class="w3-opacity"><b><fmt:message key="contact.work.experience.sql" /></b></h5>
                        <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>
                            <fmt:message key="contact.work.experience.begin" /> - <span class="w3-tag w3-teal w3-round">
                                <fmt:message key="contact.current" /></span></h6>
                        <p><fmt:message key="contact.work.experience.sql.description" /></p><br>
                    </div>
                </div>

                <div class="w3-container w3-card w3-white">
                    <h2 class="w3-text-grey w3-padding-16">
                        <i class="fa fa-certificate fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>
                        <fmt:message key="contact.education" /></h2>
                    <div class="w3-container">
                        <h5 class="w3-opacity"><b><fmt:message key="contact.education.bsuir" /></b></h5>
                        <h6 class="w3-text-teal"><i class="fa fa-calendar fa-fw w3-margin-right"></i>
                            <fmt:message key="contact.education.bsuir.time" /></h6>
                        <p><fmt:message key="contact.education.bsuir.description" /></p>
                        <hr>
                    </div>
                </div>

                <!-- End Right Column -->
            </div>

            <!-- End Grid -->
        </div>

        <!-- End Page Container -->
    </div>

    <footer class="w3-container w3-teal w3-center w3-margin-top">
        <p><fmt:message key="contact.social" /></p>
        <a class="link" href="https://www.instagram.com" target="_blank">
            <i class="fa fa-instagram w3-hover-opacity"></i></a>
        <a class="link" href="https://twitter.com" target="_blank">
            <i class="fa fa-twitter w3-hover-opacity"></i></a>
        <a class="link" href="https://github.com" target="_blank">
            <i class="fa fa-github w3-hover-opacity"></i></a>
        <a class="link" href="https://vk.com" target="_blank">
            <i class="fa fa-vk w3-hover-opacity"></i></a>
    </footer>
</body>
</html>
