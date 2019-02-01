<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Barra de progresso</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
/*
fundo da barra de progresso - cor cinza*/
#myProgress {
	width: 100%;
	background-color: #ddd; /*Fundo da barra de progresso*/
}

/*
cor da barra de progresso*/
#myBar {
	width: 1%;
	height: 30px;
	background-color: #4CAF50;
}

.ui-progressbar {
	position: relative;
}

.progress-label {
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px #fff;
}

</style>

</head>
<body>
	<h1>Exemplo com java script</h1>
	<div id="myProgress"><!-- Fundo da barra -->
		<div id="myBar"></div><!-- barra de progresso -->
	</div>
	<br />
	
	<button onclick="exibirBarra()">Inicar a barra de progresso</button>
	
	
	
	<br />
	<h1>Barra de progresso com jQuery</h1>	
	<div id="progressbar">
		<div class="progress-label">
			Carregando...
		</div>
	</div>
	
</body>

<script type="text/javascript">
		// script da barra de progressso por jQuery
		$(function() {
			var progressbar = $("#progressbar"),  progresslabel = $(".progress-label");
			
			progressbar.progressbar ({
				value : false,
				change : function (){
					progresslabel.text (progressbar.progressbar('value') + "%");
				}
			})
		})
		

		// script da barra de progressso por javascript
		function exibirBarra() {
			var elem = document.getElementById("myBar"); //Pega a barra de progresso
			var width = 1; //Controla a largura
			var id = setInterval(frame, 10); // (função criada por mim , tempo)

			function frame() {
				
				if (width >= 100) { //Função que escreve a barra
					
					clearInterval(id); //Para o crecimento da barra
					
				} else {
					
					width++;
					
					elem.style.width = width + '%';// incremento a variável de controle fazendo o width da div crescer
				}
			}
		}
	</script>

</html>