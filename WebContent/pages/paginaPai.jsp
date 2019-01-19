<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<title>Pagina Pai</title>
</head>
<body>
	<h1>Página Pai load JQuery</h1>
	<input type="button" value="Carregar página" onclick="carregar();">
	
	<div id="mostrarPaginaFilha"></div><!-- local de carregamento da página filha -->
</body>

<script type="text/javascript">
	function carregar(){
		$("#mostrarPaginaFilha").load('https://www.google.com');
	}

</script>
</html>