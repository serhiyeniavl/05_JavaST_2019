<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:url var="home" value="/home"/>
<c:url var="signup" value="/signup" />


<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="ctx" value="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}"/>


<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/css/sign_in.css"
          type="text/css"/>
    <link rel="icon" href="https://cdn1.savepice.ru/uploads/2019/4/17/5ae5758165638c1db1af157878d0e2a9-full.png"
          type="image/jpg">

    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css"
          rel="stylesheet" id="bootstrap-css">
    <title>
        Free ride
    </title>
</head>

<body style="background: url(https://cdn1.savepice.ru/uploads/2019/4/17/c7994e2db99daa99566ce32cce7ddeb0-full.jpg) no-repeat; background-size: 100%;">


<div class="container" style="margin-top: 55px;">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall" style="background-color: rgba(247,247,247,0.9);">
                <h1 class="text-center login-title" style="font-size: 23px">Sign in to continue to
                    Free ride</h1>
                <img style="margin-top: 20px;" class="profile-img"
                     src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                     alt="">
                <form class="form-signin" method="post"
                      action="${home}">
                    <input type="hidden" name="command" value="signin"/>
                    <input name="email" type="email" class="form-control"
                           placeholder="Email" required autofocus>
                    <input name="pass" type="password" class="form-control"
                           placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block"
                            type="submit">
                        Sign in
                    </button>
                    <label class="checkbox pull-left" style="margin-right: 70px;">
                        <input type="checkbox" value="remember-me">
                        Remember me
                    </label>
                    <a href="${signup}" class="text-center new-account">Create an account </a>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</body>
</html>
