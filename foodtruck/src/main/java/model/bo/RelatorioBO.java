package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.VendasCanceladasDTO;
import model.reports.Relatorio;
import model.vo.VendaVO;

public class RelatorioBO {

	public ArrayList<VendasCanceladasDTO> gerarRelatorioVendasCanceladasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
		return relatorioDAO.gerarRelatorioVendasCanceladasDAO();
	}

	public void gerarRelatorioListaPedidosBO() {
		Relatorio relatorio = new Relatorio();
		relatorio.gerarRelatorioListaPedidos();
	}

	public void gerarRelatorioAcompanhamentoPedidosBO(VendaVO vendaVO) {
		Relatorio relatorio = new Relatorio();
		relatorio.gerarRelatorioAcompanhamentoPedidos(vendaVO);
	}

}
