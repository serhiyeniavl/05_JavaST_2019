<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>XML parsers</title>
    <link rel="icon" href="http://localhost:8080/web-parsing/img/favicon.ico"
          type="image/x-icon">
</head>
<body>

<span style="parser: ${parser.value}"/>

<div style="text-align: center">
    <h2 style="font-size: 30px">Choose the parser</h2>
    <form method="post"
          action="http://localhost:8080/web-parsing/candies_table">
        <label for="parser">
            <select name="parser" id="parser" style="font-size: 25px">
                <option value="dom">DOM</option>
                <option value="sax">SAX</option>
                <option value="stax">StAX</option>
            </select>
        </label>
        <input type="submit" value="Choose" style="width:10%; font-size: 23px">
    </form>
</div>
</body>
</html>
