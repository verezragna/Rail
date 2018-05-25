<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Login Page</title>
</head>
<body>
<form name="form" action="login" method="post" class="form-signin">
    <h2 class="form-signin-heading">Пожалуйста войдите</h2>

    <label for="inputEmail" class="sr-only"><spring:message text="Login"/></label>
    <input id="inputEmail" class="form-control" name="j_username" autofocus/>

    <label for="inputPassword" class="sr-only"><spring:message text="Password"/></label>
    <input type="password" id="inputPassword" class="form-control" name="j_password"/>

    <div class="checkbox">
        <label>
            <input type="checkbox" id="rememberme" name="j_spring_security_remember_me"/>Запомнить меня
        </label>
    </div>
    <input type="submit" value="Войти" class="btn btn-lg btn-primary btn-block">
    <br/>
    <a href="javascript:history.back()">Назад</a>

</form>
</body>

</html>