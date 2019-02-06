<%--
  Created by IntelliJ IDEA.
  User: Keith Levi
  Date: 11/19/2017
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <title>index</title>
</head>
<body>
<form action="login" method="post">
    <div class="form-group">
        <label for="user">Username:</label>
        <input name="username" type="email"  class="form-control" placeholder="Username" id="user" required="required" value="${username}">
    </div>
    <div>
        <label for="pass">Password:</label>
        <input name="password" type="password" id="pass" class="form-control" placeholder="*******" required="required" value="${password}">
    </div>
    <div style="display: ${error != null && error == true ? 'block':'none'}" class="alert alert-danger" >
        <Strong>ERROR!</Strong> ${errorMessage != null ? errorMessage : ''}
    </div>
    <div class="form-group">
        <input name="login" type="submit" value="LOGIN">
    </div>
</form>
</body>
</html>

