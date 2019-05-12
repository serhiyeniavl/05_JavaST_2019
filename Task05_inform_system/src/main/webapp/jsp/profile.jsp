<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="by.training.info_system.entity.User" %>
<%@ page import="by.training.info_system.entity.data.UserData" %>
<%@ page import="by.training.info_system.entity.Passport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:url value="/home" var="home"/>
<c:url value="/cars" var="cars"/>
<c:url value="/contact" var="contact"/>
<c:url value="/managers" var="managers"/>
<c:url value="/orders" var="orders"/>
<c:url value="/my_orders" var="user_orders"/>
<c:url value="/users" var="users"/>

<c:url value="/signin" var="signin"/>
<c:url value="/signup" var="signup"/>
<c:url value="/profile" var="profile"/>

<c:set var="user" value="${sessionScope.user}"/>
<c:set var="discount" value="${sessionScope.discount}"/>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="ctx"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>


<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon"
          href="${ctx}/img/wheel.png"
          type="image/jpg">

    <link rel="stylesheet" href="${ctx}/css/profile.css?" type="text/css"/>


    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <link rel="stylesheet" href="http://bootstraptema.ru/plugins/2015/bootstrap3/bootstrap.min.css" />


    <link href="https://fonts.googleapis.com/css?family=Roboto:700"
          rel="stylesheet">

    <title>
        Free ride
    </title>

    <style>
        .nav-l {
            font-size: 16px;
        }
        .links {
            padding-left: 65%;
        }
    </style>

</head>
<body style="background: url(${ctx}/img/main_page_background.jpg) no-repeat; background-size: 100%">

<div>
    <div class="container">
        <div id="main">
            <div class="row" id="real-estates-detail">
                <div class="col-sm-12" style="margin-top: 8%;">
                    <div class="panel">
                        <div class="panel-body">
                            <ul id="myTab" class="nav nav-pills">
                                <li class="active"><a href="#detail" data-toggle="tab">About Person</a></li>
                                <li class=""><a href="#contact" data-toggle="tab">Change information about person</a></li>
                                <li class=""><a href="#data" data-toggle="tab">Change password</a></li>
                            </ul>
                            <div id="myTabContent" class="tab-content">
                                <hr>
                                <div class="tab-pane fade active in" id="detail">
                                    <h4>Profile info</h4>
                                    <table class="table table-th-block">
                                        <tbody>
                                        <tr><td class="active">First name:</td><td><c:out value="${ elem.name }"/></td></tr>
                                        <tr><td class="active">Second name:</td><td><c:out value="${ elem.surname }"/></td></tr>
                                        <tr><td class="active">Patronymic:</td><td><c:out value="${ elem.patronymic }"/></td></tr>
                                        <tr><td class="active">Date of birth</td><td><c:out value="${ elem.dateOfBirth }"/></td></tr>
                                        <tr><td class="active">Person email:</td><td><c:out value="${ elem.email }"/></td></tr>
                                        <tr><td class="active">Person phone number:</td><td><c:out value="${ elem.phone }"/></td></tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="tab-pane fade" id="contact">
                                    <p></p>
                                    <c:url value="/changeValue" var="changeValueURL"/>
                                    <form action="${changeValueURL}" role="form" method="post">
                                        <div class="form-group">
                                            <label>Name</label>
                                            <input type="text" name="changeName" class="form-control rounded" placeholder="Write new name">
                                        </div>
                                        <div class="form-group">
                                            <label>Surname</label>
                                            <input type="text" name="changeSurname" class="form-control rounded" placeholder="Write new surname">
                                        </div>
                                        <div class="form-group">
                                            <label>Patronymic</label>
                                            <input type="text" name="changePatronymic" class="form-control rounded" placeholder="Write new patronymic">
                                        </div>
                                        <div class="form-group">
                                            <label>Date of birth</label>
                                            <input type="text" name="changeDateOfBirth" class="form-control rounded" placeholder="Write new date of birth">
                                        </div>
                                        <div class="form-group">
                                            <label>E-mail</label>
                                            <input type="email" name="changeEmail" class="form-control rounded" placeholder="Write new E-mail address">
                                        </div>
                                        <div class="form-group">
                                            <label>Phone number</label>
                                            <input type="text" name="changePhoneNumber" class="form-control rounded" placeholder="Write new phone number">
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-success" data-original-title="" title="" value="Change">
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="data">
                                    <p></p>
                                    <c:url value="/changePassword" var="changePasswordURL"/>
                                    <form action="${changePasswordURL}" role="form" method="post">
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" name="changePassword" class="form-control rounded" placeholder="Write new name" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Confirm Password</label>
                                            <input type="password"

                                                   name="changeConfirm" class="form-control rounded" placeholder="Write new surname" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" class="btn btn-success" data-original-title="" title="" value="Change">
                                        </div>
                                        <div style="color:#60c9a8; font-size: 18px;">
                                            ${errorPassword}
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!— /.main —>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-fixed-bottom">
    <div class="container">
        <a class="navbar-brand" href="${home}"
           style="font-family: 'Roboto', sans-serif; font-size: 23px">FreeRide</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto links">
                <li class="nav-item">
                    <a class="nav-link nav-l" href="${home}">Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-l" href="${cars}">Cars</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-l" href="${contact}">Contact</a>
                </li>
                <c:if test="${not empty user && user.role.value()==2}">
                    <li class="nav-item">
                        <a class="nav-link nav-l" href="${orders}">Orders</a>
                    </li>
                </c:if>
                <c:if test="${not empty user && user.role.value()==3}">
                    <li class="nav-item">
                        <a class="nav-link nav-l" href="${users}">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link nav-l" href="${managers}">Managers</a>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${user==null}">
                        <li class="nav-item">
                            <input value="Sign in" type="button"
                                   onclick="window.location='${signin}'"
                                   class="btn btn-success navbar-btn btn-circle nav-l"
                                   style="margin-left: 12px;">
                        </li>
                        <li class="nav-item"><input value="Sign up"
                                                    type="button"
                                                    onclick="window.location='${signup}'"
                                                    class="btn btn-success navbar-btn btn-circle nav-l"
                                                    style="margin-left: 12px;">
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <div class="btn-group dropup">
                                <input type="button"
                                       value="${user.userData.FName}"
                                       class="btn btn-info dropdown-toggle"
                                       data-toggle="dropdown"
                                       aria-haspopup="true"
                                       aria-expanded="false"
                                       style="margin-left: 12px;"/>
                                <div class="dropdown-menu"
                                     style="background-color: rgb(52,57,62);">
                                    <a class="dropdown-item a01"
                                       href="${profile}">Profile</a>
                                    <a class="dropdown-item a01"
                                       href="${user_orders}">My orders</a>
                                    <p class="dropdown-item a01">My
                                        discount: ${discount}</p>
                                    <div class="dropdown-divider"></div>
                                    <form style="margin-bottom: 0px;"
                                          action="${signin}" method="post">
                                        <input type="hidden" name="command"
                                               value="signout"/>
                                        <input type="submit"
                                               class="dropdown-item a01"
                                               value="Logout">
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

<script src="http://bootstraptema.ru/plugins/jquery/jquery-1.11.3.min.js"></script>
<script src="http://bootstraptema.ru/plugins/2015/b-v3-3-6/bootstrap.min.js"></script>

</body>
</html>