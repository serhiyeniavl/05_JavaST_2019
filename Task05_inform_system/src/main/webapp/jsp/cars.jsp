<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.training.info_system.entity.Car" %>
<%@ page import="by.training.info_system.entity.data.CarInfo" %>
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
          href="https://cdn1.savepice.ru/uploads/2019/4/17/5ae5758165638c1db1af157878d0e2a9-full.png"
          type="image/jpg">
    <link rel="stylesheet" href="${ctx}/css/cars.css"
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

        .jumbotron {
            background-color: #f4511e;
            color: #fff;
            padding: 100px 25px;
        }

        .container-fluid {
            padding: 60px 50px;
        }

        .button5 {
            background-color: white;
            padding-top: 5px;
            padding-bottom: 5px;
            width: 110px;
            color: black;
            border: 2px solid #555555;
        }

        .button5:hover {
            background-color: #555555;
            color: white;
        }

        @media screen and (max-width: 768px) {
            .col-sm-4 {
                text-align: center;
                margin: 25px 0;
            }
        }

    </style>

</head>

<body>
<div class="jumbotron text-center">
    <h1 style="font-size: 43px">FreeRide</h1>
    <p style="font-size: 23px">We specialize in rental cars</p>
</div>

<div class="container-fluid text-center">
    <h2>SERVICES</h2>
    <h4>What we offer</h4>
    <br>
    <c:forEach var="car" items="${rental_cars}" step="3" varStatus="loop">
        <div class="row">
            <c:if test="${not empty rental_cars.get(loop.index)}">
                <form class="col-sm-4" method="post" action="${cars}">
                    <input type="hidden" name="command" value="rent_car"/>
                    <img src="${ctx}/img/cars/${car.imagePath}"
                         style="width: 65%; height: 32%;"
                         alt="${car.brandName}">
                    <h4>${car.brandName}</h4>
                    <p>${car.carInfo.description}</p>
                    <p>Year: ${car.carInfo.yearMade}</p>
                    <p>Rent price: ${car.rentPrice}$</p>
                    <button type="submit" name="rent_butt"
                            value="${car.vinCode}" class="button button5">RENT
                    </button>
                </form>
            </c:if>
            <c:if test="${rental_cars.size() > loop.index + 1}">
                <c:if test="${not empty rental_cars.get(loop.index + 1)}">
                    <c:set var="car"
                           value="${rental_cars.get(loop.index + 1)}"/>
                    <form class="col-sm-4" method="post" action="${cars}">
                        <input type="hidden" name="command" value="rent_car"/>
                        <img src="${ctx}/img/cars/${car.imagePath}"
                             style="width: 65%; height: 32%;"
                             alt="${car.brandName}">
                        <h4>${car.brandName}</h4>
                        <p>${car.carInfo.description}</p>
                        <p>Year: ${car.carInfo.yearMade}</p>
                        <p>Rent price: ${car.rentPrice}$</p>
                        <button type="submit" name="rent_butt"
                                value="${car.vinCode}" class="button button5">
                            RENT
                        </button>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${rental_cars.size() > loop.index + 2}">
                <c:if test="${not empty rental_cars.get(loop.index + 2)}">
                    <c:set var="car"
                           value="${rental_cars.get(loop.index + 2)}"/>
                    <form class="col-sm-4" method="post" action="${cars}">
                        <input type="hidden" name="command" value="rent_car"/>
                        <img src="${ctx}/img/cars/${car.imagePath}"
                             style="width: 65%; height: 32%;"
                             alt="${car.brandName}">
                        <h4>${car.brandName}</h4>
                        <p>${car.carInfo.description}</p>
                        <p>Year: ${car.carInfo.yearMade}</p>
                        <p>Rent price: ${car.rentPrice}$</p>
                        <button type="submit" name="rent_butt"
                                value="${car.vinCode}" class="button button5">
                            RENT
                        </button>
                    </form>
                </c:if>
            </c:if>
        </div>
        <br><br>
    </c:forEach>
</div>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-bottom">
    <div class="container">
        <a class="navbar-brand" href="${home}"
           style="font-family: 'Roboto', sans-serif; font-size: 23px">FreeRide</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${home}">Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="${cars}">Cars
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contact}">Contact</a>
                </li>
                <c:if test="${not empty user && user.role.value()==2}">
                    <li class="nav-item">
                        <a class="nav-link" href="${user_orders}">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${users}">Users</a>
                    </li>
                </c:if>
                <c:if test="${not empty user && user.role.value()==3}">
                    <li class="nav-item">
                        <a class="nav-link" href="${user_orders}">Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${users}">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${managers}">Mangers</a>
                    </li>
                </c:if>

                <c:choose>
                    <c:when test="${user==null}">
                        <li class="nav-item">
                            <input value="Sign in" type="button"
                                   onclick="window.location='${signin}'"
                                   class="btn btn-success navbar-btn btn-circle"
                                   style="margin-left: 12px;">
                        </li>
                        <li class="nav-item"><input value="Sign up"
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
                                    <a class="dropdown-item a01"
                                       href="${profile}">Profile</a>
                                    <a class="dropdown-item a01"
                                       href="${user_orders}">Your orders</a>
                                    <p class="dropdown-item a01">Your
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
</body>
</html>
