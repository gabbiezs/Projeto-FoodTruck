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

	public ArrayList<ProdutoVO> consultarTodosProdutosController() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosBO();
	}

	public ProdutoVO consultarProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarProdutoBO(produtoVO);
	}

	public boolean atualizarProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.atualizarProdutoBO(produtoVO);
	}
	
	public boolean excluirProdutoController(ProdutoVO produtoVO) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.excluirProdutoBO(produtoVO);
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosVigentesController() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosVigentesBO();
	}

}
