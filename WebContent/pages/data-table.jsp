<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Table JQuery</title>

<link href="http://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">

<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

</head>
<body>

<table id="minhatabela" class="display" style="width:100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
            </tr>
        </thead>
	</table>

</body>
<script type="text/javascript">

	$(document).ready(function() { //Esse método(function) fará o processamento na hora que abrir a tela.
	    $('#minhatabela').DataTable( {//Cria a tabela e todo o processamento do Ajax
	        "processing": true,
	        "serverSide": true,
	        "ajax": "carregarDadosDataTable"// 'URL' retorno dos dados do servidor. URL de retorno JSON
	    } );
	} );
	
</script>

</html>