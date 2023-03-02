package model.reports;

import java.io.IOException;
import java.util.HashMap;

import model.dao.Banco;
import model.vo.VendaVO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class Relatorio {

	public void gerarRelatorioListaPedidos() {
        try {
            String currentPath = "";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            JasperRunManager.runReportToPdfFile(currentPath + "/lista_pedido.jasper", currentPath + "/relatorio_lista_pedido.pdf", null, Banco.getConnection());
            System.out.println("Relatorio gerado em " + currentPath + "/relatorio_lista_pedido.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
        }
	}

	public void gerarRelatorioAcompanhamentoPedidos(VendaVO vendaVO) {
        try {
            String currentPath = "";
            try {
                currentPath = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            System.out.println(currentPath);
            HashMap<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("pIDVENDA", vendaVO.getIdVenda());
            JasperRunManager.runReportToPdfFile(currentPath + "/acompanhamento_pedido.jasper", currentPath + "/relatorio_acompanhamento_pedido.pdf", parameters, Banco.getConnection());
            System.out.println("Relatorio gerado em " + currentPath + "/relatorio_acompanhamento_pedido.pdf");
        } catch (JRException ex) {
            System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
        }
	}

}
