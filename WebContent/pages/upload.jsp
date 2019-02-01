<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	
	<title>Upload files</title>
	</head>
	
	<body> 
		<input type="file" id="file" name="file" onchange="uploadFile();">
		<img alt="Imagem" src="" id="target" width="200" height="200">
	</body>
	
	<script type="text/javascript">
	
	function uploadFile(){
	
		var target = document.querySelector("img"); //Nossa imagem - quero pegar a imagem
		var file = document.querySelector("input[type=file]").files[0]; //quero pegar o primeiro input da p�gina / ser� retornado um array
//      preciso definir qual a posi��o

		var reader = new FileReader(); //Respons�vel por ler um arquivo/ FilReader() <- pr�pio do JS
		
		reader.onloadend = function (){//Evento quando adicionar uma imagem ou quando for trocar ele vai ser disparado chamando uma fun��o.
			target.src = reader.result;//Onde vamos mostrar a imagem pegando do arquivo lido.
		};
		
		if(file){//Se existe arquivo
			reader.readAsDataURL(file); //Vamos pegar o arquivo lido e transformar ele em um dado legivel pra ser enviado pro servidor.
			
//--------------------- upload em ajax  ------------------------/
//          Vamos invocar a fun�ao por ajax uma fun��o do JQuery
			$.ajax({
				method: "POST",
				url : "fileUpload",
				data : {
					fileUpload : target.src //Vai ser enviado a imagem
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
		
		}else{
			target.src="" //N�o mostra nada
		}
		
	}
		
	</script>

</html>