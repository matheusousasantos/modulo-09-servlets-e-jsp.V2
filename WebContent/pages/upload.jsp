<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<title>Upload files</title>
</head>

<body>
	<input type="file" id="file" name="file" onchange="uploadFile();">
	<img alt="Imagem" src="" id="target" width="200" height="200">
	<br>
	<br>
	<br>
	<br>
	<a href="fileUpload">Carregar Imagens</a>
	
	<table>
	
		<c:forEach var="usuario" items="${listaUserImagem}">
		<tr>
			<td>Id:</td>
			<td><c:out value="${usuario.id}"/></td>
			
			<td>Login:</td>
			<td><c:out value="${usuario.login}"/></td>
		
			<td>Img:</td>
			<td><c:out value="${usuario.imagem}"/></td>		
		</tr>
		</c:forEach>
	
	</table>
	
	<br>
	<br>
	<br>
	
	
</body>

<script type="text/javascript">
	
	function uploadFile(){
	
		var target = document.querySelector("img"); 
		var file = document.querySelector("input[type=file]").files[0]; 
		
		var reader = new FileReader(); 
		
		reader.onloadend = function () { //Precisa ser execultado por último
			
			target.src = reader.result;
			
			$.ajax({
				method: "POST",
				url : "fileUpload",
				data : {
					fileUpload : reader.result 
				}
			  }
			)
			
			.done(function(response) { 
				alert("Sucesso:" + response); 
			   } 
			)
			
			.fail(function(xhr, status, errorThrown) { 
				alert("Error:" + xhr.response.Text); 
			   } 
			);
			
		};
		
		if(file) {
			reader.readAsDataURL(file);
		}else{
			target.src=""
		}
	  	
	}
		
	</script>

</html>