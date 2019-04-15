package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String FOLDER_RELATORIOS = "/relatorios"; //Vari�vel da pasta de relat�rios.
//	referencia a pasta de relat�rios( o pacote relatorios).
	
//	Relat�rios um dento do outro quando temos relacionamento de um para muitos.
	private static final String  SUBREPORT_DIR = "SUBREPORT_DIR"; 
	
//	definfindo o separador
	private String SEPARATOR = File.separator; //S�o as barras (/ ou \). pra entrar em pastas.

	private static final String caminhoArquivoRelatorio = null;
	
	private JRExporter exporter =  null;
	
	private String caminhoSubReport_Dir = "";
	
	private File arquivoGerado = null;
	
//	M�todo que gera o relat�rio. Primeiro recebemos a base de dados( Lista de usu�rios, lista de produtos)
//	que vai gerar o relat�rio.
	
//	Precisamos muitas veses pro relat�rio passar par�metros.
	
//	Nome do relat�rio Jasper, arquivo do Ireport pra gerar o relat�rio
	
//	Nome do relat�rio de sa�da, podemos receber um relat�rio com um nome e gerar ele diferente.
	
//	Servlet Content - trabalhar com caminho do servlet content
	
	public String gerarRelatorio(List<?> listaDataBeanColletion, HashMap parametrosRelatorio, 
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
		
//		Contruindo o m�todo:
//		Precisamos criar uma Lista de ConnectionDataSource de Beans que carrega os dados pro relat�rio:
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);//Lista de beans;
//		Obs: vai servir pra qualquer relat�rio por isso o m�todo recebe umaa lsita gen�rica.
		
		
//		Precisamos saber qual vai ser o caminho do nosso arquivo Jasper,  o arquivo do relat�rio;
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);//Vamos buscar nosso arquivo dentro da pasta FOLDER_RELATORIOS
//		Com esse processo eu tenho o caminho f�sico at� a pasta que cont�m os relat�rios .jasper ^^
		
//		Montar o arqivo que vai representar o nosso relat�rio .jasper
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
//		
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) { // Se o caminho do meu relat�rio n�o existir
			
//			usando pra capitar o caminho correto do relat�rio se algo der errado.
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath(); 
		
			SEPARATOR = "";
		
		}
		
//		Podemos passar uma imagem como par�metro
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
			
//		Caminho completo do teu relat�rio compilado e indicadoat� o relat�rio compilado e indicado.
		String caminhoArquviosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
			
//		Vamos fazer o carregamento desse relat�rio:
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquviosJasper);
			
//		Seta par�metros SUBREPORT_DIR com o caminho fisico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
			
//		Setar tbm pro Hashmap
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
			
//		Carregar o arquvo pra mem�ria:
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
//		1 - Arquivo
//		2 - Par�metros
//		3 - E nossa lista de dados
			
//		Tipo de relat�rio a ser exportado. Temos que definir qual o nosso tipo de exporta��o:
		exporter = new JRPdfExporter();
			
//		Caminho do relat�rios exportado:
		caminhoRelatorio =  SEPARATOR + nomeRelatorioSaida + ".pdf";
			
//		Criar novo arquivo exportado:
		arquivoGerado = new File(caminhoArquivoRelatorio); //J� executado e compilado.
			
//		Prepara a impress�o:
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			
//		Seta o nome para o objeto de sa�da:
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			
//		Executa a exporta��o:
		exporter.exportReport();
			
//		Remove o arquivo do servidor ap�s ser feito o download
		arquivoGerado.deleteOnExit();
		
//		Caminho do arquivo relat�rio:
		return caminhoArquivoRelatorio;
	}

}
