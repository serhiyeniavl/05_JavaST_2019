<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="https://cdn1.savepice.ru/uploads/2019/4/17/5ae5758165638c1db1af157878d0e2a9-full.png"
          type="image/jpg">
    <link rel="stylesheet" href="http://localhost:8080/freeride/css/managers.css"
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

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-bottom">
    <div class="container">
        <a class="navbar-brand" href="http://localhost:8080/freeride/jsp/index.jsp" style="font-family: 'Roboto', sans-serif; font-size: 23px">FreeRide</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarResponsive" aria-controls="navbarResponsive"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/freeride/jsp/index.jsp">Home
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/freeride/jsp/cars.jsp">Cars</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="">Mangers</a>
                    <span class="sr-only">(current)</span>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">User orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/freeride/jsp/contact.jsp">Contact</a>
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
                            <a class="dropdown-item a01" href="http://localhost:8080/freeride/jsp/profile.jsp">Profile</a>
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
