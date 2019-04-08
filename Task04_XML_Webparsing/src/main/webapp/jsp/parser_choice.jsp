<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.locale == 'ru'}">
    <fmt:setLocale value="ru" scope="session"/>
</c:if>
<c:if test="${sessionScope.locale == 'en'}">
    <fmt:setLocale value="en" scope="session"/>
</c:if>
<fmt:bundle basename="main_page">

    <html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/parser_choice.css" type="text/css"/>
        <title><fmt:message key="title"/></title>
        <link rel="icon"
              href="http://localhost:8080/web-parsing/img/favicon.ico"
              type="image/x-icon">
    </head>

    <body>
    <header>
        <div class="main__title"><fmt:message key="title_pars"/></div>
        <div class="parser_choice">
            <div class="parser__opt">
                <form method="post"
                      action="http://localhost:8080/web-parsing/candies_table">
                    <label for="parser">
                        <select name="parser" id="parser" class="parser__opt">
                            <option disabled><fmt:message
                                    key="title_pars"/></option>
                            <option value="dom" selected>DOM</option>
                            <option value="sax">SAX</option>
                            <option value="stax">StAX</option>
                        </select>
                        <p><input class="load__file" type="file"
                                  accept=".xml"
                                  name="fload" id="file">
                    </label>
                    <div class="select__button">
                        <input class="select__button" type="submit"
                               value="<fmt:message key="choice_but"/>"/>
                    </div>
                </form>
            </div>
        </div>
    </header>


    <div class="lang_choice">
        <p><fmt:message key="language"/>:</p>
        <form method="get"
              action="http://localhost:8080/web-parsing/candies_table">
            <label for="lang">
                <select class="lang_select" name="lang" id="lang">
                    <option value="en">English</option>
                    <option value="ru">Русский</option>
                </select>
                <input class="lang_sub_button" type="submit"
                       value="<fmt:message key="lang_choice"/>">
            </label>
        </form>

    </div>

    </body>

    </html>
</fmt:bundle>