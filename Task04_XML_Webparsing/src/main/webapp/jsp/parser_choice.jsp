<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:bundle basename="main_page">

    <c:if test="${sessionScope.locale == 'ru'}">
        <fmt:setLocale value="ru" scope="session"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'en'}">
        <fmt:setLocale value="en" scope="session"/>
    </c:if>
    <%--<c:if test="${sessionScope.locale == null}">--%>
    <%--<c:set var="locale" value="en" scope="session"/>--%>
    <%--</c:if>--%>
    <%--<fmt:setLocale value="${sessionScope.locale}"/>--%>

    <html>
    <head>
            <%--<c:choose>--%>
            <%--<c:when test="${sessionScope.locale == 'ru'}">--%>
            <%--<fmt:setLocale value="ru" scope="session"/>--%>
            <%--</c:when>--%>
            <%--<c:when test="${sessionScope.locale == 'en'}">--%>
            <%--<fmt:setLocale value="en" scope="session"/>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<fmt:setLocale value="en" scope="session"/>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
        <meta charset="UTF-8">
        <title><fmt:message key="title"/></title>
        <link rel="icon"
              href="http://localhost:8080/web-parsing/img/favicon.ico"
              type="image/x-icon">
    </head>
    <body>

    <div style="text-align: center">
        <h2 style="font-size: 30px"><fmt:message key="title_pars"/></h2>
        <form method="post"
              action="http://localhost:8080/web-parsing/candies_table">
            <label for="parser">
                <select name="parser" id="parser" style="font-size: 25px">
                    <option value="dom">DOM</option>
                    <option value="sax">SAX</option>
                    <option value="stax">StAX</option>
                </select>
            </label>
            <input type="submit" value="<fmt:message key="choice_but"/>"
                   style="width:10%; font-size: 23px">
        </form>
        <br>
        <p style="font-size: 15px; font-family: Arial, sans-serif"><fmt:message
                key="language"/>:</p>
        <form method="get"
              action="http://localhost:8080/web-parsing/candies_table">
            <label for="lang">
                <select name="lang" id="lang" style="font-size: 15px">
                    <option value="en">English</option>
                    <option value="ru">Русский</option>
                </select>
                <input type="submit" value="<fmt:message key="lang_choice"/>"
                       style="width: 7%; font-size: 10px">
            </label>
        </form>
    </div>

    </body>
    </html>
</fmt:bundle>