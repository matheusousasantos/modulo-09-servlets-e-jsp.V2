<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barra de Progresso</title>
<style type="text/css">

	#myProgress {
		width: 100%;
		background-color: #ddd;
	}
	
	#myBar {
		width: 1%;
		height: 30px;
		background-color: #4CAF50;
	}

</style>
</head>
<body>

	<h1>Exemplo com Java Script</h1>
	<div id="myProgress"><!-- Fundo da barra de progresso -->
		<div id="myBar"></div><!-- barra de progresso -->
	</div>
	<br>
	<button onclick="exibirBarra();"> Iniciar a barra de progresso</button>
</body>

<script type="text/javascript">

	function exibirBarra(){
		
		var elem = document.getElementById('myBar');
		var width = 1;
		var id = setInterval(frame, 30);
		
		function frame() {
			
			if(width >= 100){
				clearInterval(id);
			}else {
				width++;
				elem.style.width = width + "%";
			}
			
		}
	}

</script>

</html>
















