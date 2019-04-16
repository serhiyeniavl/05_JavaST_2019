<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="https://www.teslarati.com/wp-content/uploads/2018/03/tesla-twin-turbine-21in-wheel-silver.jpg"
          type="image/jpg">
    <link rel="stylesheet" href="http://localhost:8080/freeride/css/index.css"
          type="text/css"/>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <link href="https://fonts.googleapis.com/css?family=Roboto:700" rel="stylesheet">

    <title>
        Free ride
    </title>
    <style>
        .a01 {
            color: rgb(251,251,251);
        }
        .a01:hover {
            color: rgb(52,57,62);
        }
    </style>

</head>

<body style="background: url(https://cdn1.savepice.ru/uploads/2019/4/16/5b0d7dae051d4532ef4e60fa8e560142-full.jpg) no-repeat; background-size: 100%;">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-bottom">
    <div class="container">
        <a class="navbar-brand" href="" style="font-family: 'Roboto', sans-serif; font-size: 23px">FreeRide</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/freeride/jsp/cars.jsp">Cars</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/freeride/jsp/managers.jsp">Mangers</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">User orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contact</a>
                </li>
                <%--<li class="nav-item">--%>
                    <%--<input value="Sign in" type="button" onclick="window.location='http://localhost:8080/freeride/jsp/signin.jsp'"--%>
                            <%--class="btn btn-success navbar-btn btn-circle"--%>
                            <%--style="margin-left: 12px;">--%>
                <%--</li>--%>
                <%--<li class="nav-item"><input value="Sign up" type="button"--%>
                                            <%--class="btn btn-success navbar-btn btn-circle"--%>
                                            <%--style="margin-left: 12px;"></li>--%>


                <li>
                    <div class="btn-group dropup">
                        <input type="button" value="Vladislav" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="margin-left: 12px;"/>
                        <div class="dropdown-menu" style="background-color: rgb(52,57,62);">
                            <a class="dropdown-item a01" href="#">Profile</a>
                            <a class="dropdown-item a01" href="#">Your orders</a>
                            <p class="dropdown-item a01">Your discount: 15%</p>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item a01" href="#">Logout</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<section style="margin-top: 60px; margin-right: 20px;">
    <div class="container">
        <div class="row">
            <div class="col-lg-6"
                 style="background-color: rgba(10,13,14,0.4); color: white;">
                <h1 class="mt-2" style="color: rgb(241,284,71)">We appreciate your time</h1>
                <p style="font-size: 23px">Vehicle rental the way it should be.</p>
            </div>
        </div>
    </div>

    <div class="container" style="margin-top: 7px;">
        <div class="row">
            <div class="col-lg-6"
                 style="background-color: rgba(10,13,14,0.4); color: white;">
                <p style="font-size: 23px">Clear pricing and simple process.</p>
                <br>
                <a href="http://localhost:8080/freeride/jsp/cars.jsp" class="butt">Book online</a>
            </div>
        </div>
    </div>
</section>

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
