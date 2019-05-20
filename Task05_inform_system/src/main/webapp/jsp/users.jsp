<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
<fmt:bundle basename="users_page">
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

<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="ctx"
       value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>


<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon"
          href="${ctx}/img/wheel.png"
          type="image/jpg">
    <link rel="stylesheet" href="${ctx}/css/index.css"
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

        #center {
            position: absolute;
            padding-top: 5%;
            width: 78%;
            top: 40%;
            text-align: center;
        }
    </style>

</head>

<body style="background-color: #F5F5F5">

<h1 align="center" style="color: #030005">Users</h1>
<br/>
<br/>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="">Control panel</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="${users}?page=1">All users</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${users}?show=managers&page=1">Managers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${users}?show=customers&page=1">Customers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${users}?show=blackList&page=1">Black
                        list</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" action="${users}?id">
                <input class="form-control mr-sm-2" type="search" required autocomplete="off"
                       name="id" placeholder="ID" aria-label="Search" style="width: 24%">
                <button class="btn btn-outline-success my-2 my-sm-0"
                        type="submit">Search
                </button>
            </form>
            <form class="form-inline my-2 my-lg-0" action="${users}?email">
                <input class="form-control mr-sm-2" type="search" required autocomplete="off"
                       name ="email" placeholder="Email" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0"
                        type="submit">Search
                </button>
            </form>
        </div>
    </nav>
    <br/>
    <table class="table ">
        <thead>
        <tr style="font-size: larger; border: #030005; color: #030005;">
            <th>ID</th>
            <th>Email</th>
            <th>Full name</th>
            <th>Role</th>
            <c:choose>
                <c:when test="${empty blackList}">
                    <th>Address</th>
                    <th>Passport ID</th>
                    <th></th>
                </c:when>
                <c:otherwise>
                    <th>Reason</th>
                    <th>Lock date</th>
                    <th>Unlock date</th>
                    <th></th>
                </c:otherwise>
            </c:choose>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
        <c:when test="${empty userList}">
        </tbody>
    </table>
    <div id="center">
        <h2>There are no users.</h2>
    </div>
    </c:when>
    <c:otherwise>
        <c:forEach items="${userList}" var="u">
            <tr style="color: black; font-size: 17px">
                <c:choose>
                    <c:when test="${empty blackList}">
                        <td>${u.id}</td>
                        <td>${u.email}</td>
                        <td>${u.userData.FName} ${u.userData.LName}</td>
                        <td>${u.role.toString()}</td>
                        <td>${u.userData.address}</td>
                        <td>${u.userData.passport.idNumber}</td>
                        <td width="50px">
                            <c:choose>
                                <c:when test="${u.role.toString() != 'MANAGER'}">
                                    <form action="${users}" method="post"
                                          style="margin: 0">
                                        <input type="hidden" name="command"
                                               value="make_manager">
                                        <button type="submit" name="user_id"
                                                value="${u.id}"
                                                class="btn btn-outline-primary">Make a manager
                                        </button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="${users}" method="post"
                                          style="margin: 0">
                                        <input type="hidden" name="command"
                                               value="make_user">
                                        <button type="submit" name="user_id"
                                                value="${u.id}"
                                                class="btn btn-outline-primary">Make an user
                                        </button>
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td width="50px">
                            <c:if test="${not u.role.toString() != 'ADMIN'}">
                                <form action="${users}" method="post"
                                      style="margin: 0"
                                      onsubmit="return confirm('Account and all info with it will be delete permanently.');">
                                    <input type="hidden" name="command"
                                           value="delete_user">
                                    <button type="submit" name="user_id"
                                            value="${u.id}"
                                            class="btn btn-outline-danger">Delete
                                    </button>
                                </form>
                            </c:if>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>${u.user.id}</td>
                        <td>${u.user.email}</td>
                        <td>${u.user.userData.FName} ${u.user.userData.LName}</td>
                        <td>${u.user.role.toString()}</td>
                        <td>${u.reason.value()}</td>
                        <td>${u.showLockDate()}</td>
                        <td>${u.showUnlockDate()}</td>
                        <td width="50px">
                            <c:if test="${not u.user.role.toString() != 'ADMIN'}">
                                <form action="${users}" method="post"
                                      style="margin: 0"
                                      onsubmit="return confirm('Are you sure to delete account from black list?');">
                                    <input type="hidden" name="command"
                                           value="delete_user_black_list">
                                    <button type="submit" name="user_id"
                                            value="${u.user.id}"
                                            class="btn btn-outline-danger">Delete from list
                                    </button>
                                </form>
                            </c:if>
                        </td>
                    </c:otherwise>
                </c:choose>

            </tr>
        </c:forEach>
        </tbody>
        </table>
    </c:otherwise>
    </c:choose>

<c:if test="${not empty userList}">
<nav aria-label="Page navigation example">
    <ul class="pagination pagination-sm justify-content-center">
        <c:if test="${current_page != 1 && not empty current_page}">
            <li class="page-item"><a class="page-link a-nav"
                                     href="${users}?page=${current_page - 1}&show=${show}">Previous</a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${num_of_pages}" var="i">
            <c:choose>
                <c:when test="${current_page eq i}">
                    <li class="page-item disabled"><a
                            class="page-link a-nav"
                            href="${users}?page=${i}&show=${show}">${i}</a>
                        <span class="sr-only">(current)</span></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link a-nav"
                                             href="${users}?page=${i}&show=${show}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${current_page lt num_of_pages}">
            <li class="page-item"><a class="page-link a-nav"
                                     href="${users}?page=${current_page + 1}&show=${show}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>
</c:if>
</div>
<br/>
<br/>


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
                        <a class="nav-link active" href="${users}"><fmt:message
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
                            <form style="margin-bottom: 0px;" action="${users}?language=en">
                                <input type="hidden" name="language"
                                       value="en">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="En">
                            </form>
                            <form style="margin-bottom: 0px;" action="${users}?language=ru">
                                <input type="hidden" name="language"
                                       value="ru">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="Ru">
                            </form>
                            <form style="margin-bottom: 0px;" action="${users}?language=de">
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