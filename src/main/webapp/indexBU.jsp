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
    <link rel="stylesheet" href="resources/styles/loginStyle.css">
    <title>index</title>
</head>
<body>
<div class="container">
    <form action="login" method="post" class="form-signin">
        <h2 class="form-signin-heading"> Please Sign in </h2>
        <label class="sr-only" for="user">Username:</label>
        <input name="username" type="email"  class="form-control" placeholder="Username" id="user" required="required" value="${username}">


        <label class="sr-only" for="pass">Password:</label>
        <input name="password" type="password" id="pass" class="form-control" placeholder="*******" required="required" value="${password}">

        <div style="display: ${error != null && error == true ? 'block':'none'}" class="alert alert-danger" >
            <Strong>ERROR!</Strong> ${errorMessage != null ? errorMessage : ''}
        </div>

        <input class="btn btn-lg btn-primary btn-block" name="login" type="submit" value="LOGIN">

    </form>
</div>

</body>
</html>

