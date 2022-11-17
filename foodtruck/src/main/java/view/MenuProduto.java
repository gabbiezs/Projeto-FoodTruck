package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ProdutoController;
import model.vo.ProdutoVO;
import model.vo.TipoProdutoVO;


public class MenuProduto {
	
	private static final int OPCAO_MENU_CADASTRAR_PRODUTO = 1;
	private static final int OPCAO_MENU_CONSULTAR_PRODUTO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_PRODUTO = 3;
	private static final int OPCAO_MENU_EXCLUIR_PRODUTO = 4;
	private static final int OPCAO_MENU_USUARIO_PRODUTO = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_PRODUTOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_PRODUTO = 2;
	private static final int OPCAO_MENU_CONSULTAR_PRODUTO_VOLTAR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuProduto() {
		int opcao = this.apresentarOpcoesMenuProduto();
		while (opcao != OPCAO_MENU_USUARIO_PRODUTO) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_PRODUTO :{
					ProdutoVO produtoVO = new ProdutoVO();
					this.cadastrarProduto(produtoVO);
					break;
				}
				case OPCAO_MENU_CONSULTAR_PRODUTO :{
					this.consultarProduto();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_PRODUTO :{
					this.atualizarProduto();
					break;
				}
				case OPCAO_MENU_EXCLUIR_PRODUTO :{
					this.excluirProduto();
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenuProduto();
		}
	}
	
	private int apresentarOpcoesMenuProduto() {
		System.out.println("\n---------- Sistema FoodTruck ----------");
		System.out.println("---------- Menu de Produtos ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_CADASTRAR_PRODUTO + " - Cadastrar Produto");
		System.out.println(OPCAO_MENU_CONSULTAR_PRODUTO + " - Consultar Produto");
		System.out.println(OPCAO_MENU_ATUALIZAR_PRODUTO + " - Atualizar Produto");
		System.out.println(OPCAO_MENU_EXCLUIR_PRODUTO + " - Excluir Produto");
		System.out.println(OPCAO_MENU_USUARIO_PRODUTO + " - Voltar ao Menu de Usuário");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	// Método que o admin cadastra o produto
	private void cadastrarProduto(ProdutoVO produtoVO) {
		if (produtoVO.getTipoProduto() == null) {
			do {
				produtoVO.setTipoProduto(TipoProdutoVO.getTipoProdutoVOPorValor(this.apresentarOpcoesMenuProduto()));
			} while(produtoVO.getTipoProduto() == null);
		}
		System.out.print("\nDigite o nome: ");
		produtoVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o preço: ");
		produtoVO.setPreco(teclado.nextDouble());
		produtoVO.setDataCadastro(LocalDateTime.now());

		
		if (this.validarCamposProduto(produtoVO)) {
			ProdutoController produtoController = new ProdutoController();
			produtoVO = produtoController.cadastrarProdutoController(produtoVO);
			if (produtoVO.getIdProduto() != 0) {
				System.out.println("Produto cadastrado com sucesso!");
			} else {
				System.out.println("Não foi possível cadastrar o produto!");
			}
		}
	}

	private int apresentarOpcoesTipoProduto() {
		ProdutoController produtoController = new ProdutoController();
		ArrayList<TipoProdutoVO> listaTipoProdutoVO = produtoController.consultarTipoProduto();
		System.out.println("\n---- Tipo de Produtos ----");
		System.out.println("Opções: ");
		for (int i = 0; i < listaTipoProdutoVO.size(); i++) {
			System.out.println(listaTipoProdutoVO.get(i).getValor() + " - " + listaTipoProdutoVO.get(i));
		}
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private boolean validarCamposProduto(ProdutoVO produtoVO) {
		boolean resultado = true;
		System.out.println();
		if (produtoVO.getNome() == null || produtoVO.getNome().isEmpty()) {
			System.out.println("O campo nome é obrigatório!");
			resultado = false;
		}
		if (produtoVO.getPreco() == 0) {
			System.out.println("O campo preço é obrigatório!");
			resultado = false;
		}
		if (produtoVO.getDataCadastro() == null) {
			System.out.println("O campo data de cadastro é obrigatório!");
			resultado = false;
		}
		return resultado;
	}

	private void consultarProduto() {
		System.out.println("Consultando o produto...");
	}

	private void atualizarProduto() {
		System.out.println("Atualizando o produto...");
	}

	private void excluirProduto() {
		System.out.println("Excluindo o produto...");
	}
}

