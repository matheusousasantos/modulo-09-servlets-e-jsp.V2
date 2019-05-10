<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>gantt-view</title>

	<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery-ui-1.8.4.css" />
	<link rel="stylesheet" type="text/css" href="../scriptGanttView/reset.css" />
	<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery.ganttView.css" />
	
	<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
	<script type="text/javascript" src="../scriptGanttView//date.js"></script>
	<script type="text/javascript" src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
	<script type="text/javascript" src="../scriptGanttView/jquery.ganttView.js"></script>
	
	<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>

</head>
<body>
	<h1>Gantt View</h1>
	
	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>
	
	<script type="text/javascript">
	
	
	$(document).ready(function() { // Chamado quando abrir a tela.
		
		$.get( "buscarDatasPlanejamento", function(response) {
			
			var ganttDataResposta = JSON.parse(response);
			
			var ganttData = "";
			ganttData +="[";
			
				$.each(ganttDataResposta, function(index, projeto){ //'for' destinado para os projetos
					
					ganttData += "{ \"id\": \""+projeto.id+"\", \"name\": \""+projeto.nome+"\", \"series\": [";
					
					$.each(projeto.series, function(index2, serie) { //'for' destinado para os series
					
						var cores = "blue,green".split(',');
					
						var cor;
					
						if(index2 === 0){
							cor = "pink";
						} else {
							cor = Number.isInteger(index2 / 2) ? cores[0] : cores[1];
						}
					
						var dataInicial = serie.dataInicial.split('-');
						var dataFinal = serie.dataFinal.split('-');
					
					//Motando os dados da serie:
						ganttData += "{ \"name\": \""+serie.nome+"\", \"start\":\""+ new Date(dataInicial[0],dataInicial[1],dataInicial[2])+"\", \"end\":\""+ new Date(dataFinal[0],dataFinal[1],dataFinal[2])+"\", \"color\": \""+cor+"\" }"
					
						if(index2 < projeto.series.length - 1) {
							
						 	ganttData += ",";
							
						}
					
					});//Fim do For de series;
					
					ganttData += "]}"; //Fecha o array json de series
					
					if(index < ganttDataResposta.length - 1) {
						
						ganttData +=",";
						
					}
			
				});//Fim For dos projetos
			
			ganttData += "]";
			
			ganttData = JSON.parse(ganttData);

			
			$("#ganttChart").ganttView({ 
				data: ganttData,
				slideWidth: 1000,
				behavior: {
					onClick: function (data) { 
						var msg = "Evento de Click: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onResize: function (data) { 
						var msg = "Evento de redimencionar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) { 
						var msg = "Evento de Arrastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					}
				}
			});
			
		    $("#ganttChart").ganttView("setSlideWidth", 600);
		
		});
	
	});
	
	
		
	</script>

</body>
</html>