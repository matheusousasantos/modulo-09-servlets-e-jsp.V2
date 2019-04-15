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
	
	private static final String FOLDER_RELATORIOS = "/relatorios"; //Variável da pasta de relatórios.
//	referencia a pasta de relatórios( o pacote relatorios).
	
//	Relatórios um dento do outro quando temos relacionamento de um para muitos.
	private static final String  SUBREPORT_DIR = "SUBREPORT_DIR"; 
	
//	definfindo o separador
	private String SEPARATOR = File.separator; //São as barras (/ ou \). pra entrar em pastas.

	private static final String caminhoArquivoRelatorio = null;
	
	private JRExporter exporter =  null;
	
	private String caminhoSubReport_Dir = "";
	
	private File arquivoGerado = null;
	
//	Método que gera o relatório. Primeiro recebemos a base de dados( Lista de usuários, lista de produtos)
//	que vai gerar o relatório.
	
//	Precisamos muitas veses pro relatório passar parâmetros.
	
//	Nome do relatório Jasper, arquivo do Ireport pra gerar o relatório
	
//	Nome do relatório de saída, podemos receber um relatório com um nome e gerar ele diferente.
	
//	Servlet Content - trabalhar com caminho do servlet content
	
	public String gerarRelatorio(List<?> listaDataBeanColletion, HashMap parametrosRelatorio, 
			String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
		
//		Contruindo o método:
//		Precisamos criar uma Lista de ConnectionDataSource de Beans que carrega os dados pro relatório:
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanColletion);//Lista de beans;
//		Obs: vai servir pra qualquer relatório por isso o método recebe umaa lsita genérica.
		
		
//		Precisamos saber qual vai ser o caminho do nosso arquivo Jasper,  o arquivo do relatório;
		String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);//Vamos buscar nosso arquivo dentro da pasta FOLDER_RELATORIOS
//		Com esse processo eu tenho o caminho físico até a pasta que contém os relatórios .jasper ^^
		
//		Montar o arqivo que vai representar o nosso relatório .jasper
		File file = new File(caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper");
		
//		
		if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) { // Se o caminho do meu relatório não existir
			
//			usando pra capitar o caminho correto do relatório se algo der errado.
			caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath(); 
		
			SEPARATOR = "";
		
		}
		
//		Podemos passar uma imagem como parâmetro
		parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);
		
			
//		Caminho completo do teu relatório compilado e indicadoaté o relatório compilado e indicado.
		String caminhoArquviosJasper = caminhoRelatorio + SEPARATOR + nomeRelatorioJasper + ".jasper";
			
//		Vamos fazer o carregamento desse relatório:
		JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquviosJasper);
			
//		Seta parâmetros SUBREPORT_DIR com o caminho fisico para subreport
		caminhoSubReport_Dir = caminhoRelatorio + SEPARATOR;
			
//		Setar tbm pro Hashmap
		parametrosRelatorio.put(SUBREPORT_DIR, caminhoSubReport_Dir);
			
//		Carregar o arquvo pra memória:
		JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio, jrbcds);
//		1 - Arquivo
//		2 - Parâmetros
//		3 - E nossa lista de dados
			
//		Tipo de relatório a ser exportado. Temos que definir qual o nosso tipo de exportação:
		exporter = new JRPdfExporter();
			
//		Caminho do relatórios exportado:
		caminhoRelatorio =  SEPARATOR + nomeRelatorioSaida + ".pdf";
			
//		Criar novo arquivo exportado:
		arquivoGerado = new File(caminhoArquivoRelatorio); //Já executado e compilado.
			
//		Prepara a impressão:
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
			
//		Seta o nome para o objeto de saída:
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
			
//		Executa a exportação:
		exporter.exportReport();
			
//		Remove o arquivo do servidor após ser feito o download
		arquivoGerado.deleteOnExit();
		
//		Caminho do arquivo relatório:
		return caminhoArquivoRelatorio;
	}

}
