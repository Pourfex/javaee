<%--
  Created by IntelliJ IDEA.
  User: ibro
  Date: 24/10/18
  Time: 08:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <%@include file="../jspf/dependencies.jspf" %>
        <title>Capteur</title>

    </head>
    <body>
        <div id="wrapper">
            <header>
                <%@ include file="/jspf/header.jspf" %>
            </header>
            <div id="content">
                <div id="snackbar"></div>
                <div class="loader"></div>
                <div class="seach_bar">
                    <input id="search_id" type="text" placeholder="(Id,Id,...) ou all">
                    <input id="search_tag" type="text" placeholder="(Tag,Tag,...) ou all">
                    <input id="search_type" type="text" placeholder="(Type,Type,...) ou all">
                    <button id="btn_search"><i class="fa fa-search"></i></button>
                </div>
                <div id="alerts">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-2">
                                    <i class="fa fa-warning fa-5x"></i>
                                </div>
                                <div class="col-md-10 text-left">
                                    <div id="nb_alerts" class="huge"></div>
                                    <div id="alerts_detail"></div>
                                </div>

                            </div>
                        </div>
                        <a id="show_details" href="#">
                            <div class="panel-footer">
                                <span class="pull-left">Afficher les détails</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-down"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                    <ul id="alert_list"></ul>
                </div>
                <div class="charts">
                    <div class="panel-heading chart_title">
                        <i class="fa fa-line-chart fa-fw"></i> Données capteurs
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <ul id="chart_list">
                        </ul>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->

            </div>
            <footer>
                <%@ include file="/jspf/footer.jspf" %>
            </footer>
        </div>

    </body>
</html>
