<%--
  Created by IntelliJ IDEA.
  User: Pourfex
  Date: 24/10/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@ page session="true" --%>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    </head>
    <body>
        <h1> Description </h1>

        <form action="login" method="post">
            Username:<input type="text" name="username">
            Password:<input type="password" name="password">
            <input type="submit" value="login">
        </form >

    <h2> Erreurs : ${form.errors} </h2>
    </body>
</html>