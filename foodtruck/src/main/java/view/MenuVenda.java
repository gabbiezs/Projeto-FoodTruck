package view;

import java.time.LocalDateTime;
import java.util.Scanner;

import controller.VendaController;
import model.vo.VendaVO;

public class MenuVenda {
	
	private static final int OPCAO_MENU_CADASTRAR_VENDA = 1;
	private static final int OPCAO_MENU_CANCELAR_VENDA = 2;
	private static final int OPCAO_MENU_VENDA_VOLTAR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuVenda() {
		int opcao = this.apresentarOpcoesMenuVenda();
		while (opcao != OPCAO_MENU_VENDA_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_VENDA :{
					VendaVO vendaVO = new VendaVO();
					this.cadastrarVenda(vendaVO);
					break;
				}
				case OPCAO_MENU_CANCELAR_VENDA :{
					this.consultarVenda();
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenuVenda();
		}
		
		
	}
	
	private int apresentarOpcoesMenuVenda() {
		System.out.println("\n---------- Sistema FoodTruck ----------");
		System.out.println("---------- Menu de Venda ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_CADASTRAR_VENDA + " - Cadastrar Venda");
		System.out.println(OPCAO_MENU_CANCELAR_VENDA + " - Cancelar Venda");
		System.out.println(OPCAO_MENU_VENDA_VOLTAR + " - Voltar ao Menu");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	public void cadastrarNovaVenda(VendaVO vendaVO) {
		this.cadastrarVenda(vendaVO);
	}
	
	private void cadastrarVenda(VendaVO vendaVO) {

		System.out.print("\nDigite id da venda: ");
		vendaVO.setIdVenda(teclado.nextInt());
		System.out.print("\nDigite id do usuario: ");
		vendaVO.setIdUsuario(teclado.nextInt());
		System.out.print("\nDigite o numero do pedido: ");
		vendaVO.setNumeroPedido(teclado.nextInt());
		System.out.print("\nDigite a data da venda: ");
		vendaVO.setDataVenda(LocalDateTime.now());
		System.out.print("\nDigite o flag de entrega: ");
		vendaVO.setFlagEntrega(teclado.nextBoolean());
		System.out.print("\nDigite a taxa de entrega: ");
		vendaVO.setTaxaEntrega(teclado.nextDouble());

		
		if (this.validarCamposCadastro(vendaVO)) {
			VendaController vendaController = new VendaController();
			vendaVO = vendaController.cadastrarVendaController(vendaVO);
			if (vendaVO.getIdVenda() != 0) {
				System.out.println("Venda cadastrada com sucesso!");
			} else {
				System.out.println("Não foi possível cadastrar a venda!");
			}
		}
	}

	
	private boolean validarCamposCadastro(VendaVO vendaVO) {
		boolean resultado = true;
		System.out.println();
		if (vendaVO.getNumeroPedido() == 0) {
			System.out.println("O campo numero do pedido é obrigatório!");
			resultado = false;
		}
		if (vendaVO.getDataVenda() == null) {
			System.out.println("O campo data da venda é obrigatório!");
			resultado = false;
		}
		if (vendaVO.getTaxaEntrega() == 0) {
			System.out.println("O campo taxa de entrega é obrigatório!");
			resultado = false;
		}

		return resultado;
	}

	private void consultarVenda() {
		System.out.println("Consultando a venda...");
	}

	private void atualizarVenda() {
		System.out.println("Atualizando a venda...");
	}

	private void excluirVenda() {
		System.out.println("Cancelando a venda...");
	}

}
