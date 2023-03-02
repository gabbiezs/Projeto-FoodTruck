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
			System.out.println("\nProduto já cadastrado!");
		} else {
			produtoVO = produtoDAO.cadastrarProdutoDAO(produtoVO);
		}
		return produtoVO;
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosBO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<ProdutoVO> listaProdutoVO = produtoDAO.consultarTodosProdutosDAO();
		if (listaProdutoVO.isEmpty()) {
			System.out.println("\nLista de produtos está vazia!");
		}
		return listaProdutoVO;
	}

	public ProdutoVO consultarProdutoBO(ProdutoVO produtoVO) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ProdutoVO produto = produtoDAO.consultarProdutoDAO(produtoVO);
		if(produto == null) {
			System.out.println("\nProduto não localizado!");
		}
		return produto;
	}

	public boolean atualizarProdutoBO(ProdutoVO produtoVO) {
		boolean resultado = false;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		if(produtoDAO.verificarExistenciaRegistroProdutoPorIdProdutoDAO(produtoVO.getIdProduto())) {
			if (produtoDAO.verificarDesligamentoProdutoPorIdProdutoDAO(produtoVO.getIdProduto())) {
				System.out.println("\nProduto já se encontra desligado na base de dados!");
			} else {
				resultado = produtoDAO.atualizarProdutoDAO(produtoVO);
			}
		} else {
			System.out.println("\nProduto não existe na base de dados!");
		}
		return resultado;
	}

	public boolean excluirProdutoBO(ProdutoVO produtoVO) {
		boolean resultado = false;
		ProdutoDAO produtoDAO = new ProdutoDAO();
		if(produtoDAO.verificarExistenciaRegistroProdutoPorIdProdutoDAO(produtoVO.getIdProduto())) {
			if (produtoDAO.verificarDesligamentoProdutoPorIdProdutoDAO(produtoVO.getIdProduto())) {
				System.out.println("\nProduto já se encontra excluído na base de dados!");
			} else {
				resultado = produtoDAO.excluirProdutoDAO(produtoVO);
			}
		} else {
			System.out.println("\nProduto não existe na base de dados!");
		}
		return resultado;
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosVigentesBO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<ProdutoVO> listaProdutosVO = produtoDAO.consultarTodosProdutosVigentesDAO();
		if(listaProdutosVO.isEmpty()) {
			System.out.println("\nLista de Produtos está vazia.");
		}
		return listaProdutosVO;
	}

}
