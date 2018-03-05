<!DOCTYPE html>

<%@ page language= "java" contentType= "text/html; charset=UTF-8" pageEncoding= "UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "spring" uri= "http://www.springframework.org/tags"%>

<html>
<head>
    <meta charset= "utf-8">
    <title>Login Page</title>
    <spring:url value= "/resources/css/bootstrap.css" var= "bootstrap" />
    <spring:url value= "/resources/css/signin.css" var= "signin" />
    <link href= "${bootstrap}" rel= "stylesheet" />
    <link href= "${signin}" rel= "stylesheet" />
</head>
<body>
<form name= "form" action= "j_spring_security_check" method= "post" class= "form-signin">
    <h2 class= "form-signin-heading">Пожалуйста войдите</h2>

    <label for= "inputEmail" class= "sr-only"><spring:message text= "Email"/></label>
    <input id= "inputEmail" class= "form-control" name= "j_username" value= "4" required autofocus/>

    <label for= "inputPassword" class= "sr-only"><spring:message text= "Password"/></label>
    <input type= "password" id= "inputPassword" class= "form-control" name= "j_password" value= "12345678" required/>

    <div class= "checkbox">
        <label>
            <input type= "checkbox" id= "rememberme"  name= "_spring_security_remember_me"/>Запомнить меня
        </label>
    </div>
    <input type= "submit" value= "Войти" class= "btn btn-lg btn-primary btn-block" >
    <br/>
    <a href= "javascript:history.back()">Назад</a>

</form>
</body>

</html>