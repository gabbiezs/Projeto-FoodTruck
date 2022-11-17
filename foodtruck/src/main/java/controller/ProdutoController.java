package controller;

import java.util.ArrayList;

import model.bo.ProdutoBO;
import model.vo.TipoProdutoVO;
import model.vo.ProdutoVO;

public class ProdutoController {
	

	public ArrayList<TipoProdutoVO> consultarTipoProduto() {
		ProdutoBO PRODUTOBO = new ProdutoBO();
		return PRODUTOBO.consultarTipoProdutoBO();
	}

	public ProdutoVO cadastrarProdutoController(ProdutoVO usuarioVO) {
		ProdutoBO PRODUTOBO = new ProdutoBO();
		return PRODUTOBO.cadastrarProdutoBO(usuarioVO);
	}

}
