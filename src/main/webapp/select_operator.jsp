<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Select operator</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.cdnfonts.com/css/roboto" rel="stylesheet">
</head>
<body>
<header class="header">
    <nav class="nav-bar secondary-text">
        <div class="nav-bar-wrapper">
            <div class="nav-logo-wrapper">
                <span class="nav-title">Mental Health Call Center</span>
            </div>
            <div class="nav-menu-wrapper">
                <a class="nav-menu-item" href="index.jsp">User</a>
                <a class="nav-menu-item" href="select_operator">Operators</a>
                <a class="nav-menu-item" href="admin.jsp">Administrator</a>
            </div>
        </div>
    </nav>
</header>
<div class="page-wrapper">
    <div class="registration-form-wrapper">
        <div class="form-card flex-column">
            <div class="form-wrapper">
                <form class="flex-column" action="${pageContext.request.contextPath}/operator" method="get">
                    <label class="form-label" for="operator_id">Select your name:</label>
                    <select name="operator_id" id="operator_id">
                        <c:forEach items="${operatorList}" var="operator">
                            <option value="${operator.key}">${operator.value}</option>
                        </c:forEach>
                    </select>
                    <input class="form-submit-button" type="submit" value="Choose"/>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
