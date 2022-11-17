package model.bo;

import java.util.ArrayList;

import model.dao.ProdutoDAO;
import model.vo.TipoProdutoVO;
import model.vo.ProdutoVO;

public class ProdutoBO {
	

	public ArrayList<TipoProdutoVO> consultarTipoProdutoBO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		return produtoDAO.consultarTipoProdutoDAO();
	}

	public ProdutoVO cadastrarProdutoBO(ProdutoVO produtoVO) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		if (produtoDAO.verificarExistenciaRegistroPorNomeDAO(produtoVO)) {
			System.out.println("\nUsuário já cadastrado!");
		} else {
			produtoVO = produtoDAO.cadastrarProdutoDAO(produtoVO);
		}
		return produtoVO;
	}

}
