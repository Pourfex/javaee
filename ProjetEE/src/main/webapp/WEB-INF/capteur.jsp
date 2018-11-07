<%--
  Created by IntelliJ IDEA.
  User: Pourfex
  Date: 24/10/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--@ page session="true" --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="This is the login page for ">
<meta name="author" content="Alexis Delforges">

<title>Info Capteur n° <%=request.getParameter("id")%> - ${type}</title>

<!-- Bootstrap Core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>

<body>

	<div>Données du capteur n° <%=request.getParameter("id")%></div>
	<div>Mesure de ${type}</div>

	<div class="cols-sm-10">
		<canvas id="myChart"></canvas>
	</div>

	<button value="" class="changetime">Voir sur 2 heures</button>
	<button value="day" class="changetime">Voir sur 1 jour</button>
	<button value="week" class="changetime">Voir sur 1 semaine</button>
	<button value="month" class="changetime">Voir sur 1 mois</button>

	<!-- jQuery -->
	<script src="../vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../vendor/metisMenu/metisMenu.min.js"></script>
		
	<!-- Moment.js -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/locale/fr.js"></script>

	<!-- Chart.js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	

	<script>
		var ctx = document.getElementById('myChart').getContext('2d');
		var values = <%=request.getAttribute("values")%>;
		var labels = new Array();

		<c:forEach items="${timestamps}" var="timestamp" varStatus="status">
		labels.push(new Date("${timestamp}".replace(/-/g, '/')));
		</c:forEach>
		
		Chart.pluginService.register({
		    afterDraw: function(chart) {
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
		            ctxPlugin.fillStyle = 'red'
		            ctxPlugin.fillText("SEUIL DE DANGER", xAxe.left*2 + 35, lineAt+10);
		        }
		    }
		});

		var chart = new Chart(ctx, {
			type : 'line',

			// The data for our dataset
			data : {
				labels : labels,
				datasets : [ {
					label : "${type}",
					backgroundColor : 'rgb(160, 255, 201, 0.3)',
					borderColor : 'rgb(66, 206, 126)',
					data : values,
					pointRadius: 3,
					pointHoverRadius: 16
				} ]
			},

			// Configuration options go here
			options : {
				lineAt: 50,
				responsive : true,
				maintainAspectRatio : true,
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : false
						}
					} ],
					xAxes: [{
		                type: 'time',
		                time: {
		                    unit: '<%=request.getParameter("span")%>',
		                    min: new Date("${begindate}")
		                }
		            }]
				}
			
			}
		});
		chart.data.datasets[0].data.forEach(function(point) {
			if (point.value > 50) {
			    point.fillColor =  "red";
			 }
		})

		$(".changetime").click(function() {
			location.href = "/capteur?id=<%=request.getParameter("id")%>&span=" + this.value;
		});
	</script>
</body>
</html>
