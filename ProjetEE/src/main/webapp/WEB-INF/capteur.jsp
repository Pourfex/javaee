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
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="This is the capteur page ">
        <meta name="author" content="Alexis Delforges">

        <title>Info Capteur n : <%=request.getParameter("id_capteur")%> - ${type}</title>

    </head>

    <body>
        <div id="wrapper">
            <header>
                <%@ include file="/jspf/header_capteur.jspf" %>
            </header>
            <div id="content_capteur">
                <div>Données du capteur n : <%=request.getParameter("id_capteur")%>
                </div>
                <div>Mesure de ${type}</div>

                <div id="chart_detail" class="cols-sm-5">
                    <canvas id="myChart"></canvas>
                </div>
                <div id="buttons_change">
                    <button value="" class="btn btn-primary changetime">Voir sur 2 heures</button>
                    <button value="day" class="btn btn-primary changetime">Voir sur 1 jour</button>
                    <button value="week" class="btn btn-primary changetime">Voir sur 1 semaine</button>
                    <div id="btn_edit_div">
                        <button value="month" class="btn btn-primary changetime">Voir sur 1 mois</button>
                    </div>
                </div>



                <script>
                    var ctx = document.getElementById('myChart').getContext('2d');
                    var values = <%=request.getAttribute("values")%>;
                    var labels = new Array();
                    <c:forEach items="${timestamps}" var="timestamp" varStatus="status">
                    labels.push("${timestamp}");
                    </c:forEach>

                    Chart.pluginService.register({
                        afterDraw: function (chart) {
                            if (typeof chart.config.options.lineAt != 'undefined') {
                                var lineAt = chart.config.options.lineAt;
                                var ctxPlugin = chart.chart.ctx;
                                var xAxe = chart.scales[chart.config.options.scales.xAxes[0].id];
                                var yAxe = chart.scales[chart.config.options.scales.yAxes[0].id];

                                ctxPlugin.strokeStyle = "red";
                                ctxPlugin.beginPath();
                                lineAt = yAxe.top + ((yAxe.max - lineAt) / (yAxe.max - yAxe.min)) * (yAxe.bottom - yAxe.top);
                                ctxPlugin.moveTo(xAxe.left, lineAt);
                                ctxPlugin.lineTo(xAxe.right, lineAt);
                                ctxPlugin.stroke();

                                ctxPlugin.textAlign = 'center';
                                ctxPlugin.fillStyle = 'red',
                                ctxPlugin.fillText("SEUIL DE DANGER", xAxe.left * 2 + 35, lineAt + 10);
                            }
                        }
                    });
                    var chart = new Chart(ctx, {
                        // Th
                        type: 'line',
                        // The data for our dataset
                        data: {
                            labels: labels,
                            datasets: [{
                                label: "${type}",
                                backgroundColor: 'rgb(160, 255, 201, 0.3)',
                                borderColor: 'rgb(66, 206, 126)',
                                data: values,
                                pointRadius: 8,
                                pointHoverRadius: 16
                            }]
                        },
                        // Configuration options go here
                        options: {
                            lineAt: 50,
                            responsive: false,
                            maintainAspectRatio: true,
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: false
                                    }
                                }]
                            }
                        }
                    });
                    chart.data.datasets[0].data.forEach(function (point) {
                        if (point.value > 50) {
                            point.fillColor = "red";
                        }
                    });
                    $(".changetime").click(function () {
                        location.href = "/capteur?id=<%=request.getParameter("id")%>&span=" + this.value;
                    });
                </script>
            </div>

            <footer>
                <%@ include file="/jspf/footer.jspf" %>
            </footer>
        </div>
    </body>
</html>