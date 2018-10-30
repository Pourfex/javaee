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
<!DOCTYPE html>
<html>
    <head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    </head>
    <body>
        <h1> Home </h1>
        /
        <h2> ////////////////////////////////////////Datas///////////////////////////////////////</h2>
        <br>
            <c:forEach var="entry" items="${capteur2.data}">

                <h3> /////////////////Data/////////////////</h3>
                <h3> Gps = ${entry.gps} </h3>
                <h3> Type = ${entry.type} </h3>
                <h3> Value = ${entry.value} </h3>
                <h3> Timestamp = ${entry.timestamp} </h3>

            </c:forEach>
        <br>

        <c:forEach var="capteur22" items="${capteur2s}">

            <h2> /////////////////Capteur/////////////////</h2>
            <h2> Capteur2 ville : ${capteur22.ville}</h2>
            <h2> Capteur2 pays : ${capteur22.pays}</h2>

            <c:forEach var="entry" items="${capteur22.data}">

                <h3> /////////////////Data/////////////////</h3>
                <h3> Gps = ${entry.gps} </h3>
                <h3> Type = ${entry.type} </h3>
                <h3> Value = ${entry.value} </h3>
                <h3> Timestamp = ${entry.timestamp} </h3>

            </c:forEach>

        </c:forEach>


        <c:forEach var="capteur222" items="${capteurs}">

            <h2> /////////////////Capteur/////////////////</h2>
            <h2> Capteur2 ville : ${capteur222.ville}</h2>
            <h2> Capteur2 pays : ${capteur222.pays}</h2>

            <c:forEach var="entry" items="${capteur222.data}">

                <h3> /////////////////Data/////////////////</h3>
                <h3> Gps = ${entry.gps} </h3>
                <h3> Type = ${entry.type} </h3>
                <h3> Value = ${entry.value} </h3>
                <h3> Timestamp = ${entry.timestamp} </h3>

            </c:forEach>

        </c:forEach>

        <h1> Se deconnecter </h1>

        <form action="/logout" method="post">
             <input type="submit" value="logout">
        </form >

    </body>
</html>