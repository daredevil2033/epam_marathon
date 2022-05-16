<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operator ${operator_id}</title>
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
<%--<div>Operator id: ${operator_id}</div>--%>
<div class="page-wrapper">
    <div class="registration-form-wrapper">
        <div class="form-card flex-column">
            <div class="form-wrapper">
                <div>User phone: ${phone}</div>
                <div>User name: ${name}</div>
                <div>User request: ${request}</div>
                <form action="${pageContext.request.contextPath}/operator" method="post">
                    <input type="hidden" name="operator_id" value="${operator_id}">
                    <input type="hidden" name="request_id" value="${request_id}">
                    <input class="form-submit-button" type="submit" value="Complete">
                </form>
                <form action="${pageContext.request.contextPath}/operator" method="get">
                    <input type="hidden" name="operator_id" value="${operator_id}">
                    <input class="form-submit-button" type="submit" value="Next">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
