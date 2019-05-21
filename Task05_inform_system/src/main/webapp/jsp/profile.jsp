<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:if test="${param.language == 'en'}">
    <fmt:setLocale value="en" scope="session"/>
</c:if>
<c:if test="${param.language == 'ru'}">
    <fmt:setLocale value="ru" scope="session"/>
</c:if>
<c:if test="${param.language == 'de'}">
    <fmt:setLocale value="de" scope="session"/>
</c:if>
<fmt:bundle basename="profile_page">
<c:url value="/home" var="home"/>
<c:url value="/cars" var="cars"/>
<c:url value="/contact" var="contact"/>
<c:url value="/orders" var="orders"/>
<c:url value="/my_orders" var="user_orders"/>
<c:url value="/users" var="users"/>

<c:url value="/signin" var="signin"/>
<c:url value="/signup" var="signup"/>
<c:url value="/profile" var="profile"/>

<c:set var="user" value="${sessionScope.user}"/>
<c:set var="data" value="${requestScope.data}"/>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="ctx"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>


<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon"
          href="${ctx}/img/wheel.png"
          type="image/jpg">
    <link rel="stylesheet" href="${ctx}/css/profile.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <link href="https://fonts.googleapis.com/css?family=Roboto:700"
          rel="stylesheet">

    <title>
        Free ride
    </title>
    <style>
        .a01 {
            color: rgb(251, 251, 251);
        }

        .a01:hover {
            color: rgb(52, 57, 62);
        }
        .back-img {
            background: url(${ctx}/img/main_page_background.jpg) no-repeat; background-size: 100%; -moz-background-size: 100%; /* Firefox 3.6+ */
            -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
            -o-background-size: 100%;
        }
    </style>

</head>


<body class="back-img">

<div class="container h-75 d-flex justify-content-center"
     style="margin-top: 3%;">
    <div class="jumbotron my-auto"
         style="width: 70%; background-color: #F5F5F5">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a class="nav-item nav-link active" id="nav-home-tab"
                   data-toggle="tab" href="#nav-home" role="tab"
                   aria-controls="nav-home" aria-selected="true"><fmt:message key="profile"/> </a>
                <a class="nav-item nav-link" id="nav-profile-tab"
                   data-toggle="tab" href="#nav-profile" role="tab"
                   aria-controls="nav-profile" aria-selected="false"><fmt:message key="change_email"/></a>
                <a class="nav-item nav-link" id="nav-contact-tab"
                   data-toggle="tab" href="#nav-contact" role="tab"
                   aria-controls="nav-contact" aria-selected="false"><fmt:message key="change_password"/></a>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-home" role="tabpanel"
                 aria-labelledby="nav-home-tab">
                <table class="table table-hover" style="margin-top: 3%;">
                    <tbody>
                    <tr>
                        <th scope="row"><fmt:message key="fname"/>:</th>
                        <td>${data.getUserData().FName}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="lname"/>:</th>
                        <td>${data.getUserData().LName}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="email"/>:</th>
                        <td>${data.email}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="address"/>:</th>
                        <td>${data.getUserData().address}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="passport_num"/>:</th>
                        <td>${data.getUserData().passport.serie}${data.userData.passport.number}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="passport_id"/>:</th>
                        <td>${data.getUserData().passport.idNumber}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="issue_date"/>:</th>
                        <td>${data.getUserData().passport.showIssueDate()}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="end_date"/>:</th>
                        <td>${data.getUserData().passport.showEndDate()}</td>
                    </tr>
                    </tbody>
                </table>
                <c:if test="${not empty incorrect_data}">
                    <h5 style="color: red; text-align: center">${incorrect_data}
                        <br>If you have any questions, please,
                        <a href="${contact}" style="color: red; text-decoration: underline">contact</a> us.</h5>
                </c:if>
                <c:if test="${not empty info}">
                    <h5 style="color: green; text-align: center">${info}

                </c:if>
            </div>
            <div class="tab-pane fade" id="nav-profile" role="tabpanel"
                 aria-labelledby="nav-profile-tab">
                <form action="${profile}" method="post" style="padding-top: 7%;">
                    <input type="hidden" name="command" value="change_email">
                    <div class="form-group">
                        <label for="exampleInputEmail1"><fmt:message key="new_email"/></label>
                        <input type="email" class="form-control" name="email"
                               id="exampleInputEmail1" required
                               aria-describedby="emailHelp"
                               placeholder="<fmt:message key="enter_email"/>">
                        <small id="emailHelp" class="form-text text-muted"><fmt:message key="small"/>
                        </small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1"><fmt:message key="password"/></label>
                        <input type="password" class="form-control" name="password"
                               id="exampleInputPassword1" required
                               placeholder="<fmt:message key="password"/>">
                        <small class="form-text text-muted"><fmt:message key="confrim_password"/>
                        </small>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="submit"/>
                    </button>
                </form>
            </div>
            <div class="tab-pane fade" id="nav-contact" role="tabpanel"
                 aria-labelledby="nav-contact-tab">
                <form action="${profile}" method="post"
                      style="padding-top: 7%;">
                    <input type="hidden" name="command" value="change_password">
                    <div class="form-group">
                        <label for="exampleInputEmail1"><fmt:message key="old_password"/></label>
                        <input type="password" name="old_password"
                               class="form-control" required
                               aria-describedby="emailHelp"
                               placeholder="<fmt:message key="enter_old_password"/>">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1"><fmt:message key="new_password"/></label>
                        <input type="password" name="new_password1"
                               class="form-control" required
                               placeholder="<fmt:message key="password"/>">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1"><fmt:message key="confrim_password"/></label>
                        <input type="password" name="new_password2"
                               class="form-control" required
                               placeholder="<fmt:message key="password"/>">
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="submit"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-bottom">
    <div class="container">
        <a class="navbar-brand" href="${home}"
           style="font-family: 'Roboto', sans-serif; font-size: 23px">FreeRide</a>
        <button class="navbar-toggler" type="button"
                data-toggle="collapse"
                data-target="#navbarResponsive"
                aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${home}"><fmt:message
                            key="home"/>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${cars}"><fmt:message
                            key="cars"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contact}"><fmt:message
                            key="contact"/></a>
                </li>
                <c:if test="${not empty user && user.role.value()==2}">
                    <li class="nav-item">
                        <a class="nav-link" href="${orders}"><fmt:message
                                key="orders"/></a>
                    </li>
                </c:if>
                <c:if test="${not empty user && user.role.value()==3}">
                    <li class="nav-item">
                        <a class="nav-link" href="${users}"><fmt:message
                                key="users"/></a>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${user==null}">
                        <li class="nav-item">
                            <input value="<fmt:message
                                key="signin"/>" type="button"
                                   onclick="window.location='${signin}'"
                                   class="btn btn-success navbar-btn btn-circle"
                                   style="margin-left: 12px;">
                        </li>
                        <li class="nav-item"><input value="<fmt:message
                                key="signup"/>"
                                                    type="button"
                                                    onclick="window.location='${signup}'"
                                                    class="btn btn-success navbar-btn btn-circle"
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
                                    <c:if test="${user.role.value() != 3}">
                                        <a class="dropdown-item a01"
                                           href="${profile}"><fmt:message
                                                key="profile"/></a>
                                        <a class="dropdown-item a01"
                                           href="${user_orders}"><fmt:message
                                                key="my_orders"/>
                                        </a>
                                        <div class="dropdown-divider"></div>
                                    </c:if>
                                    <form style="margin-bottom: 0px;"
                                          action="${signin}"
                                          method="post">
                                        <input type="hidden"
                                               name="command"
                                               value="signout"/>
                                        <input type="submit"
                                               class="dropdown-item a01"
                                               value="<fmt:message
                                key="logout"/>">
                                    </form>
                                </div>
                            </div>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li style="margin-left: auto; margin-right: 0;">
                    <div class="btn-group dropup">
                        <input type="button"
                               value="<fmt:message
                                key="language"/>"
                               class="btn btn-secondary dropdown-toggle"
                               data-toggle="dropdown"
                               aria-haspopup="true"
                               aria-expanded="false"
                               style="margin-left: 12px;"/>
                        <div class="dropdown-menu"
                             style="background-color: rgb(52,57,62);">
                            <form style="margin-bottom: 0px;" action="#">
                                <input type="hidden" name="language"
                                       value="en">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="En">
                            </form>
                            <form style="margin-bottom: 0px;" action="#">
                                <input type="hidden" name="language"
                                       value="ru">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="Ru">
                            </form>
                            <form style="margin-bottom: 0px;" action="#">
                                <input type="hidden" name="language"
                                       value="de">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="De">
                            </form>
                        </div>
                    </div>
                </li>
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
</body>
</html>
</fmt:bundle>