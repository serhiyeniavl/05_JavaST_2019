<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:bundle basename="table" prefix="header.">

    <c:if test="${sessionScope.locale == 'ru'}">
        <fmt:setLocale value="ru" scope="session"/>
    </c:if>
    <c:if test="${sessionScope.locale == 'en'}">
        <fmt:setLocale value="en" scope="session"/>
    </c:if>

    <html>
    <head>
        <title>Candies table</title>
        <link rel="stylesheet" href="css/candies_parser.css" type="text/css"/>
        <link rel="icon"
              href="http://localhost:8080/web-parsing/img/favicon.ico"
              type="image/x-icon">
    </head>
    <script>
        function back() {
            window.history.back()
        }
    </script>

    <body>

    <table>
        <tr>
            <th><fmt:message key="name"/></th>
            <th><fmt:message key="production"/></th>
            <th><fmt:message key="filling"/></th>
            <th><fmt:message key="energy"/></th>
            <th><fmt:message key="water"/></th>
            <th><fmt:message key="sugar"/></th>
            <th><fmt:message key="fructose"/></th>
            <th><fmt:message key="vanillin"/></th>
            <th><fmt:message key="protein"/></th>
            <th><fmt:message key="carbohydrates"/></th>
            <th><fmt:message key="fats"/></th>
            <th><fmt:message key="date"/></th>
            <th><fmt:message key="type"/></th>
        </tr>
        <c:forEach var="elem" items="${res}" varStatus="status">
            <tr>
                <td><c:out value="${ elem.name }"/></td>
                <td><c:out value="${ elem.production }"/></td>
                <td><c:out value="${ elem.filling }"/></td>
                <td><c:out value="${ elem.energy }"/></td>
                <td><c:out value="${ elem.ingredients.water }"/></td>
                <td><c:out value="${ elem.ingredients.sugar }"/></td>
                <td><c:out value="${ elem.ingredients.fructose }"/></td>
                <td><c:out value="${ elem.ingredients.vanillin }"/></td>
                <td><c:out value="${ elem.value.protein }"/></td>
                <td><c:out value="${ elem.value.carbohydrates }"/></td>
                <td><c:out value="${ elem.value.fats }"/></td>
                <td><c:out value="${ elem.date }"/></td>
                <c:choose>
                    <c:when test="${elem.getClass().getSimpleName() == 'ChocolateCandie'}">
                        <td><c:out value="${ elem.chocolateType.value }"/></td>
                    </c:when>
                    <c:otherwise>
                        <td><c:out value="${ elem.fruitType.value }"/></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
    <a onclick="back()">
        <button class="b01"><fmt:message key="back_button"/></button>
    </a>
    </body>
    </html>
</fmt:bundle>