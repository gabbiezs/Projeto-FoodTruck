package view;

import java.util.Scanner;

import controller.UsuarioController;
import model.vo.UsuarioVO;

public class Login {
	
	private static final int OPCAO_MENU_LOGIN = 1;
	private static final int OPCAO_MENU_CRIAR_CONTA = 2;
	private static final int OPCAO_MENU_SAIR = 9;
	
	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuLogin() {
		int opcao = this.apresentarOpcoesMenu();
		while(opcao != OPCAO_MENU_SAIR) {
			switch (opcao) {
				case OPCAO_MENU_LOGIN: {
					UsuarioVO usuarioVO = this.realizarLogin();
					if (usuarioVO.getIdUsuario() != 0 && usuarioVO.getDataExpiracao() == null) {
						System.out.println("\nUsuário Logado: " + usuarioVO.getLogin());
						System.out.println("Perfil: " + usuarioVO.getTipoUsuario());
					}
					break;
				}
				case OPCAO_MENU_CRIAR_CONTA: {
					System.out.println("Criando uma conta...");
					break;
				}
				default: {
					System.out.println("\nOperação inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}
	
	private UsuarioVO realizarLogin() {
		UsuarioVO usuarioVO = new UsuarioVO();
		System.out.println("---------- Informações ----------");
		System.out.print("Login: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.print("Senha: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		if (usuarioVO.getLogin().isEmpty() || usuarioVO.getSenha().isEmpty()) {
			System.out.println("Os campos de Login e Senha são obrigatórios!");
		} else {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVO = usuarioController.realizarLoginController(usuarioVO);
			if(usuarioVO.getIdUsuario() == 0) {
				System.out.println("Usuário não encontrado!");
			} if(usuarioVO.getDataExpiracao() != null) {
				System.out.println("Usuário Expirado!");
			}
		}
		
		return usuarioVO;
	}

	private int apresentarOpcoesMenu() {
		System.out.println("--------------- Sistema FoodTruck ---------------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_LOGIN + " - Entrar");
		System.out.println(OPCAO_MENU_CRIAR_CONTA + " - Criar conta");
		System.out.println(OPCAO_MENU_SAIR + " - Sair");
		System.out.println("\nDigite uma opção: ");
		
		return Integer.parseInt(teclado.nextLine());
	}
}