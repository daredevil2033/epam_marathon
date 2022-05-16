<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
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
                <form class="space-between" action="${pageContext.request.contextPath}/administrator" method="get">
                    <input class="form-submit-button" type="submit" value="Show new" name="sbutton">
                    <input class="form-submit-button" type="submit" value="Show processed" name="sbutton">
                </form>
                <table>
                    <tr>
                        <th>Request_id</th>
                        <th>Phone</th>
                        <th>Name</th>
                        <th>Request</th>
                        <th>Status</th>
                        <th>Date</th>
                    </tr>
                    <c:forEach items="${requestList}" var="request">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.phone}</td>
                            <td>${request.name}</td>
                            <td>${request.request}</td>
                            <td>${request.status}</td>
                            <td>${request.date}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
