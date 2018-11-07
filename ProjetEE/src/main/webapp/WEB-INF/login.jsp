<%--
  Created by IntelliJ IDEA.
  User: Pourfex
  Date: 24/10/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--@ page session="true" --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../jspf/dependencies.jspf" %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="This is the login page for ">
        <meta name="author" content="Alexis Delforges">
        <title>Se connecter</title>
    </head>

    <body>
        <div id="wrapper">
            <header>
                <%@ include file="/jspf/header_login.jspf" %>
            </header>
            <div id="content">
                <h1 align="center" id="login_title"> Bienvenue sur la page de connexion du site de surveillance du site SEVESO de Nantes</h1>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="login-panel panel panel-default" id="login_panel_id">
                            <div class="panel-heading">
                                <h2 class="panel-title">Veuillez-vous connecter</h2>
                            </div>
                            <div class="panel-body" id="login_body">
                                <form class="form-horizontal" action="login" role="form" method="post">
                                    <fieldset>

                                        <div class="form-group">
                                            <div class="cols-sm-10">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa"
                                                                                   aria-hidden="true"></i></span>
                                                    <input class="form-control" placeholder="Nom d'utilisateur"
                                                           name="username" type="text" autofocus>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="cols-sm-10">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-lock fa-lg"
                                                                                   aria-hidden="true"></i></span>
                                                    <input class="form-control" placeholder="Mot de passe"
                                                           name="password"
                                                           type="password" value="">
                                                </div>
                                            </div>
                                        </div>

                                        <a href="../forgotpass.html">Mot de passe oublié ?</a>

                                        <c:forEach var="entry" items="${form.errors.values()}">
                                            <div class="alert alert-warning">
                                                Erreur : ${entry}
                                            </div>
                                        </c:forEach>

                                        <!-- Change this to a button or input when using tis as a form -->
                                        <button id="login_btn" type="submit" class="btn btn-lg btn-success btn-block">Se
                                            connecter
                                        </button>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <footer>
                <%@ include file="/jspf/footer.jspf" %>
            </footer>
        </div>
    </body>
</html>
