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
<fmt:bundle basename="myorders_page">
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
    <link rel="stylesheet" href="${ctx}/css/orders.css?"
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
            position:absolute;
            width:100%;
            top:40%;
            text-align:center;
        }
        .a-nav {
            color: #000;
        }
        .msg {
            color: #60c9a8;
        }
        .error_msg {
            color: red;
        }
        .pag {
            margin-bottom: 5%;
        }
        @media screen and (max-width: 1500px){
            .pag {
                margin-bottom: 7%;
            }
        }
        @media screen and (max-width: 1000px){
            .pag {
                margin-bottom: 10%;
            }
        }
        @media screen and (max-width: 768px){
            .pag {
                margin-bottom: 12%;
            }
        }
    </style>

</head>

<body style="background-color: #F5F5F5">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" style="margin-right: 3%; color: white"><fmt:message key="nav"/></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="find_by_status"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <c:forEach var="status" items="${order_status}">
                        <a class="dropdown-item" href="${user_orders}?page=1&show=${status.getValue().toLowerCase()}">${status.getValue()}</a>
                    </c:forEach>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" name="order_id" required placeholder="<fmt:message key="search_id"/>"  autocomplete="off" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="search_butt"/></button>
        </form>
    </div>
</nav>

<c:choose>
    <c:when test="${empty car_orders}">
        <div id="center">
            <h2>There are no orders.</h2>
        </div>
    </c:when>
    <c:otherwise>
        <div class="pag">
            <c:forEach var="order" items="${car_orders}" varStatus="status">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6">
                            <tr><img width="75%" height="45%"
                                     class="img-fluid rounded"
                                     src="${ctx}/img/cars/${order.car.imagePath}?"/>
                            </tr>
                        </div>
                        <div class="col-md-6">
                            <tr>
                                <fmt:message key="order"/>
                                <th><c:out value="#${ order.id }"/></th>
                                <br>
                                Car:
                                <th><c:out
                                        value="${ order.car.brandName }"/></th>
                                <br>
                                <fmt:message key="price"/>:
                                <th><c:out
                                        value="${ order.car.rentPrice }$"/></th>
                                <br>
                                <c:if test="${not empty order.issueDate}">
                                    <fmt:message key="issue"/>:
                                    <th><c:out
                                            value="${ order.showIssueDate()}"/></th>
                                    <br>
                                </c:if>
                                <c:if test="${not empty order.returnDate}">
                                    <fmt:message key="end"/>:
                                    <th><c:out
                                            value="${ order.showReturnDate()}"/></th>
                                    <br>
                                </c:if>
                                <c:if test="${not empty order.realReturnDate}">
                                    <fmt:message key="real_end"/>:
                                    <th><c:out
                                            value="${ order.showRealReturnDate()}"/></th>
                                    <br>
                                    <fmt:message key="final_price"/>:
                                    <th><c:out
                                            value="${ order.finalPrice}$"/></th>
                                    <br>
                                </c:if>
                                <fmt:message key="status"/>:
                                <th><c:out
                                        value="${ order.status.getValue()}"/></th>
                                <br>
                                <c:if test="${order.status.getValue() eq 'Confirmed'}">
                                    <form action="${user_orders}" method="post">
                                        <input type="hidden" name="command"
                                               value="accept_order">
                                        <td>
                                            <button class="btn btn-info"
                                                    type="submit" name="accept"
                                                    value="${order.id}"><fmt:message key="take"/>
                                            </button>
                                        </td>
                                    </form>
                                </c:if>
                                <c:if test="${not empty info && not empty order_id && order.id == order_id}">
                                    <h4 class="msg">${info}</h4>
                                </c:if>
                                <c:if test="${not empty incorrect_data}">
                                <h4 class="error_msg">${incorrect_data}
                                    </c:if>
                            </tr>
                        </div>
                    </div>
                </div>
                <hr>
            </c:forEach>

            <c:if test="${not empty current_page}">
            <nav aria-label="Page navigation example">
                <ul class="pagination pagination-lg justify-content-center">
                    <c:if test="${current_page != 1}">
                        <li class="page-item"><a class="page-link a-nav"
                                                 href="${user_orders}?page=${current_page - 1}&show=${show}"><fmt:message key="previous"/></a>
                        </li>
                    </c:if>
                    <c:forEach begin="1" end="${num_of_pages}" var="i">
                        <c:choose>
                            <c:when test="${current_page eq i}">
                                <li class="page-item disabled"><a
                                        class="page-link a-nav"
                                        href="${user_orders}?page=${i}&show=${show}">${i}</a>
                                    <span class="sr-only">(current)</span></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link a-nav"
                                                         href="${user_orders}?page=${i}&show=${show}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${current_page lt num_of_pages}">
                        <li class="page-item"><a class="page-link a-nav"
                                                 href="${user_orders}?page=${current_page + 1}&show=${show}"><fmt:message key="next"/></a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>

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
                            <form style="margin-bottom: 0px;" action="${user_orders}?language=en">
                                <input type="hidden" name="language"
                                       value="en">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="En">
                            </form>
                            <form style="margin-bottom: 0px;" action="${user_orders}?language=ru">
                                <input type="hidden" name="language"
                                       value="ru">
                                <input type="submit"
                                       class="dropdown-item a01"
                                       value="Ru">
                            </form>
                            <form style="margin-bottom: 0px;" action="${user_orders}?language=de">
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
</body>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</html>
</fmt:bundle>