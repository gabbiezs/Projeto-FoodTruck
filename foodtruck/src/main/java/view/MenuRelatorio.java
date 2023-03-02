package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.RelatorioController;
import model.dto.VendasCanceladasDTO;
import model.vo.VendaVO;

public class MenuRelatorio {
	
	// apos o andre definir o relatorio, retornar aqui pra acrescentar mais variaveis e adicionar tambem a opção
	// no metodo apresentarOpcoesMenu
	private static final int OPCAO_MENU_RELATORIO_VENDAS_CANCELADAS = 1;
	private static final int OPCAO_MENU_RELATORIO_LISTA_DE_PEDIDOS = 2;
	private static final int OPCAO_MENU_RELATORIO_ACOMPANHAMENTO_PEDIDOS = 3;
	private static final int OPCAO_MENU_VOLTAR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuRelatorio() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_RELATORIO_VENDAS_CANCELADAS: {
					this.gerarRelatorioVendasCanceladas();
					break;
				}
				case OPCAO_MENU_RELATORIO_LISTA_DE_PEDIDOS: {
					this.gerarRelatorioListaPedidos();
					break;
				}
				case OPCAO_MENU_RELATORIO_ACOMPANHAMENTO_PEDIDOS: {
					this.gerarRelatorioAcompanhamentoPedidos();
					break;
				}
				default: {
					System.out.println("\nOpção Inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
		
	}


	private int apresentarOpcoesMenu() {
		System.out.println("\n---------- Sistema FoodTruck ----------");
		System.out.println("---------- Menu de Relatorios ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_RELATORIO_VENDAS_CANCELADAS + " - Relatório de Vendas Canceladas");
		System.out.println(OPCAO_MENU_RELATORIO_LISTA_DE_PEDIDOS + " - Relatório de Lista de Pedidos");
		System.out.println(OPCAO_MENU_RELATORIO_ACOMPANHAMENTO_PEDIDOS + " - Relatório de Acompanhamento de Pedidos");
		System.out.println(OPCAO_MENU_VOLTAR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private void gerarRelatorioVendasCanceladas() {
		RelatorioController relatorioController = new RelatorioController();
		ArrayList<VendasCanceladasDTO> listaVendasCanceladasDTO = relatorioController.gerarRelatorioVendasCanceladasController();
		System.out.print("\n---------- RELATÓRIO ----------");
		System.out.printf("\n%-20s  %-24s  %12s  %12s  %12s  ", "NOME", "DATA CANCELAMENTO", "SUBTOTAL", "TAXA ENTREGA", "TOTAL");
		for(VendasCanceladasDTO venda : listaVendasCanceladasDTO) {
			venda.imprimir();
		}
		System.out.println();
	}

	private void gerarRelatorioListaPedidos() {
		RelatorioController relatorioController = new RelatorioController();
		relatorioController.gerarRelatorioListaPedidos();
	}
	
	private void gerarRelatorioAcompanhamentoPedidos() {
		VendaVO vendaVO = new VendaVO();
		System.out.print("Informe o código da venda: ");
		vendaVO.setIdVenda(Integer.parseInt(teclado.nextLine())) ;
		RelatorioController relatorioController = new RelatorioController();
		relatorioController.gerarRelatorioAcompanhamentoPedidos(vendaVO);
	}

}
