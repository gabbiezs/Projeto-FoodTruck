package model.bo;


import model.dao.VendaDAO;
import model.vo.VendaVO;

public class VendaBO {
	

	public VendaVO cadastrarVendaBO(VendaVO vendaVO) {
		VendaDAO vendaDAO = new VendaDAO();
		if (vendaDAO.verificarExistenciaRegistroPorNumeroPedidoDAO(vendaVO)) {
			System.out.println("\nVenda já cadastrada!");
		} else {
			vendaVO = vendaDAO.cadastrarVendaDAO(vendaVO);
		}
		return vendaVO;
	}

}
