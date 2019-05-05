<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/home" var="home"/>
<c:url value="/signin" var="signin"/>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="ctx" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="https://cdn1.savepice.ru/uploads/2019/4/17/5ae5758165638c1db1af157878d0e2a9-full.png"
          type="image/jpg">
    <link rel="stylesheet" href="${ctx}/css/signup.css"
          type="text/css"/>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">


    <title>
        Free ride
    </title>

</head>

<body>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <header class="card-header">
                    <a href="${signin}" class="float-right btn btn-outline-primary mt-1">Sign in</a>
                    <h4 class="card-title mt-2">Sign up</h4>
                </header>
                <article class="card-body">
                    <form method="post" action="${home}">
                        <input type="hidden" name="command" value="signup"/>
                        <div class="form-row">
                            <div class="col form-group">
                                <label>First name </label>
                                <input name="fname" type="text" class="form-control" placeholder="">
                            </div> <!-- form-group end.// -->
                            <div class="col form-group">
                                <label>Last name</label>
                                <input name="lname" type="text" class="form-control" placeholder=" ">
                            </div> <!-- form-group end.// -->
                        </div> <!-- form-row end.// -->
                        <div class="form-row">
                            <div class="col form-group">
                                <label>Serie and passport number </label>
                                <input name="ps_number" type="text" class="form-control" placeholder="">
                            </div> <!-- form-group end.// -->
                        </div> <!-- form-row end.// -->
                        <div class="form-row">
                            <div class="col form-group">
                                <label>Passport issue date</label>
                                <input name="ps_issue" type="text" class="form-control" placeholder="">
                            </div> <!-- form-group end.// -->
                            <div class="col form-group">
                                <label>Passport end date</label>
                                <input name="ps_end" type="text" class="form-control" placeholder=" ">
                            </div> <!-- form-group end.// -->
                        </div> <!-- form-row end.// -->
                        <div class="form-group">
                            <label>Passport id number</label>
                            <input name="ps_id" type="text" class="form-control" placeholder="">
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Email address</label>
                            <input name="email" type="email" class="form-control" placeholder="">
                            <small class="form-text text-muted">We'll never share your email with anyone else.</small>
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Address</label>
                            <input name="address" type="text" class="form-control" placeholder="">
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <label>Create password</label>
                            <input name="password" type="password" class="form-control" placeholder="">
                        </div> <!-- form-group end.// -->
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block"> Register  </button>
                        </div> <!-- form-group// -->
                        <small class="text-muted">By clicking the 'Sign Up' button, you confirm that you accept our <br> Terms of use and Privacy Policy.</small>
                    </form>
                </article> <!-- card-body end .// -->
                <div class="border-top card-body text-center">Have an account? <a href="${signin}">Sign In</a></div>
            </div> <!-- card.// -->
        </div> <!-- col.//-->

    </div> <!-- row.//-->


</div>
<!--container end.//-->

<br><br>
<article class="bg-secondary mb-3">
    <div class="card-body text-center">
        <h3 class="text-white mt-3">FreeRide comp</h3>
        <p class="h5 text-white">Provide vehicle for you.</p>   <br>
        <p><a class="btn btn-warning" target="_blank" href="${home}"> FreeRide.com
            <i class="fa fa-window-restore "></i></a></p>
    </div>
    <br><br>
</article>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</body>
</html>
