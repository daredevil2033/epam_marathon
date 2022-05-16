<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Leave request</title>
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
        <h2 class="form-title">What can we help you with?</h2>
        <div class="flex-column">
          <form class="flex-column" action="${pageContext.request.contextPath}/request" method="post">
            <label class="form-label" for="name">Name</label>
            <input class="form-input" type="text" id="name" name="name" placeholder="Your name" maxlength="50" required>
            <label class="form-label" for="phone">Phone</label>
            <input class="form-input" type="tel" id="phone" name="phone" pattern="\+380[0-9]{9}" placeholder="+380987654321" maxlength="13" required>
            <label class="form-label" for="request">Request</label>
            <input class="form-input" type="text" id="request" name="request" placeholder="Your problem" maxlength="100">
            <input class="form-submit-button" type="submit" value="Submit">
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
