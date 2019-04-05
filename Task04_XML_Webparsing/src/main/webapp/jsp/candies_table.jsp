<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Candies table</title>
    <link rel="stylesheet" href="css/candies_parser.css" type="text/css"/>
    <link rel="icon" href="http://localhost:8080/web-parsing/img/favicon.ico"
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
        <th>Конфета</th>
        <th>Изготовитель</th>
        <th>Начинка</th>
        <th>Калорийность</th>
        <th>Вода</th>
        <th>Сахар</th>
        <th>Фруктоза</th>
        <th>Ваниллин</th>
        <th>Протеин</th>
        <th>Углеводы</th>
        <th>Жиры</th>
        <th>Дата</th>
        <th>Тип</th>
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
    <button class="b01">Backward</button>
</a>
</body>
</html>