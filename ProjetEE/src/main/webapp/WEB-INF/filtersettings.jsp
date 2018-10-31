<%--
  Created by IntelliJ IDEA.
  User: Pourfex
  Date: 30/10/2018
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<html>
<head>
    <title>Filter Settings</title>
</head>
<body>
    <h1> Hello in filter settings</h1>
    <h1> session ! Your username is  : ${sessionScope.username} </h1>


    <h1> Revenir à l'écran principal</h1>
    <!-- le go back to main page button -->
    <button onclick="window.history.back()">GoBackToMainPage</button>


    <h1> Se deconnecter </h1>


    <!-- le logout button -->
    <form action="/logout" method="post">
        <input type="submit" value="logout">
    </form >
</body>
</html>
