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
	
	var ganttData = [
		{
			id: 1, name: "Feature 1", series: [
				{ name: "Planned", start: new Date(2010,00,01), end: new Date(2010,00,03) },
				{ name: "Actual", start: new Date(2010,00,02), end: new Date(2010,00,05), color: "#f0f0f0" }
			]
		}, 
		{
			id: 2, name: "Feature 2", series: [
				{ name: "Planned", start: new Date(2010,00,05), end: new Date(2010,00,20) },
				{ name: "Actual", start: new Date(2010,00,06), end: new Date(2010,00,17), color: "#f0f0f0" },
				{ name: "Projected", start: new Date(2010,00,06), end: new Date(2010,00,17), color: "#e0e0e0" }
			]
		}, 
		{
			id: 3, name: "Feature 3", series: [
				{ name: "Planned", start: new Date(2010,00,11), end: new Date(2010,01,03) },
				{ name: "Actual", start: new Date(2010,00,15), end: new Date(2010,01,03), color: "#f0f0f0" }
			]
		}, 
		{
			id: 4, name: "Feature 4", series: [
				{ name: "Planned", start: new Date(2010,01,01), end: new Date(2010,01,03) },
				{ name: "Actual", start: new Date(2010,01,01), end: new Date(2010,01,05), color: "#f0f0f0" }
			]
		},
		{
			id: 5, name: "Feature 5", series: [
				{ name: "Planned", start: new Date(2010,02,01), end: new Date(2010,03,20) },
				{ name: "Actual", start: new Date(2010,02,01), end: new Date(2010,03,26), color: "#f0f0f0" }
			]
		}]
	
		$(function () {
			$("#ganttChart").ganttView({ 
				data: ganttData,
				slideWidth: 200,
				behavior: {
					onClick: function (data) { 
						var msg = "You clicked on an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onResize: function (data) { 
						var msg = "You resized an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) { 
						var msg = "You dragged an event: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					}
				}
			});
			
		   //$("#ganttChart").ganttView("setSlideWidth", 600);
		});
	</script>

</body>
</html>