package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import controller.UsuarioController;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class MenuUsuario {
	
	private static final int OPCAO_MENU_CADASTRAR_USUARIO = 1;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO = 2;
	private static final int OPCAO_MENU_ATUALIZAR_USUARIO = 3;
	private static final int OPCAO_MENU_EXCLUIR_USUARIO = 4;
	private static final int OPCAO_MENU_USUARIO_VOLTAR = 9;
	
	private static final int OPCAO_MENU_CONSULTAR_TODOS_FUNCIONARIOS = 1;
	private static final int OPCAO_MENU_CONSULTAR_UM_USUARIO = 2;
	private static final int OPCAO_MENU_CONSULTAR_USUARIO_VOLTAR = 9;
	
	Scanner teclado = new Scanner(System.in);
	
	public void apresentarMenuUsuario() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_USUARIO_VOLTAR) {
			switch(opcao) {
				case OPCAO_MENU_CADASTRAR_USUARIO :{
					UsuarioVO usuarioVO = new UsuarioVO();
					this.cadastrarUsuario(usuarioVO);
					break;
				}
				case OPCAO_MENU_CONSULTAR_USUARIO :{
					this.consultarUsuario();
					break;
				}
				case OPCAO_MENU_ATUALIZAR_USUARIO :{
					this.atualizarUsuario();
					break;
				}
				case OPCAO_MENU_EXCLUIR_USUARIO :{
					this.excluirUsuario();
					break;
				}
				default: {
					System.out.println("\nOpção inválida!");
				}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}
	
	private int apresentarOpcoesMenu() {
		System.out.println("\n---------- Sistema FoodTruck ----------");
		System.out.println("---------- Menu de Usuário ----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_CADASTRAR_USUARIO + " - Cadastrar Usuário");
		System.out.println(OPCAO_MENU_CONSULTAR_USUARIO + " - Consultar Usuário");
		System.out.println(OPCAO_MENU_ATUALIZAR_USUARIO + " - Atualizar Usuário");
		System.out.println(OPCAO_MENU_EXCLUIR_USUARIO + " - Excluir Usuário");
		System.out.println(OPCAO_MENU_USUARIO_VOLTAR + " - Voltar");
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}

	// Método que cadastra um usuário externo - auto cadastro do cliente
	public void cadastrarNovoUsuario(UsuarioVO usuarioVO) {
		this.cadastrarUsuario(usuarioVO);
	}
	
	// Método que o admin cadastra o usuário
	private void cadastrarUsuario(UsuarioVO usuarioVO) {
		if (usuarioVO.getTipoUsuario() == null) {
			do {
				usuarioVO.setTipoUsuario(TipoUsuarioVO.getTipoUsuarioVOPorValor(this.apresentarOpcoesTipoUsuario()));
			} while(usuarioVO.getTipoUsuario() == null);
		}
		System.out.print("\nDigite o nome: ");
		usuarioVO.setNome(teclado.nextLine());
		System.out.print("\nDigite o CPF: ");
		usuarioVO.setCpf(teclado.nextLine());
		System.out.print("\nDigite o e-mail: ");
		usuarioVO.setEmail(teclado.nextLine());
		System.out.print("\nDigite o telefone: ");
		usuarioVO.setTelefone(teclado.nextLine());
		usuarioVO.setDataCadastro(LocalDateTime.now());
		System.out.print("\nDigite o Login: ");
		usuarioVO.setLogin(teclado.nextLine());
		System.out.print("\nDigite a Senha: ");
		usuarioVO.setSenha(teclado.nextLine());
		
		if (this.validarCamposCadastro(usuarioVO)) {
			UsuarioController usuarioController = new UsuarioController();
			usuarioVO = usuarioController.cadastrarUsuarioController(usuarioVO);
			if (usuarioVO.getIdUsuario() != 0) {
				System.out.println("Usuário cadastrado com sucesso!");
			} else {
				System.out.println("Não foi possível cadastrar o usuário!");
			}
		}
	}

	private int apresentarOpcoesTipoUsuario() {
		UsuarioController usuarioController = new UsuarioController();
		ArrayList<TipoUsuarioVO> listaTipoUsuarioVO = usuarioController.consultarTipoUsuario();
		System.out.println("\n---- Tipo de Usuários ----");
		System.out.println("Opções: ");
		for (int i = 0; i < listaTipoUsuarioVO.size(); i++) {
			System.out.println(listaTipoUsuarioVO.get(i).getValor() + " - " + listaTipoUsuarioVO.get(i));
		}
		System.out.print("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
	
	private boolean validarCamposCadastro(UsuarioVO usuarioVO) {
		boolean resultado = true;
		System.out.println();
		if (usuarioVO.getNome() == null || usuarioVO.getNome().isEmpty()) {
			System.out.println("O campo nome é obrigatório!");
			resultado = false;
		}
		if (usuarioVO.getCpf() == null || usuarioVO.getCpf().isEmpty()) {
			System.out.println("O campo CPF é obrigatório!");
			resultado = false;
		}
		if (usuarioVO.getEmail() == null || usuarioVO.getEmail().isEmpty()) {
			System.out.println("O campo e-mail é obrigatório!");
			resultado = false;
		}
		if (usuarioVO.getTelefone() == null || usuarioVO.getTelefone().isEmpty()) {
			System.out.println("O campo telefone é obrigatório!");
			resultado = false;
		}
		if (usuarioVO.getDataCadastro() == null) {
			System.out.println("O campo data de cadastro é obrigatório!");
			resultado = false;
		}
		if (usuarioVO.getLogin() == null || usuarioVO.getLogin().isEmpty()) {
			System.out.println("O campo login é obrigatório!");
			resultado = false;
		}
		if (usuarioVO.getSenha() == null || usuarioVO.getSenha().isEmpty()) {
			System.out.println("O campo senha é obrigatório!");
			resultado = false;
		}
		return resultado;
	}

	private void consultarUsuario() {
		System.out.println("Consultando o usuário...");
	}

	private void atualizarUsuario() {
		System.out.println("Atualizando o usuário...");
	}

	private void excluirUsuario() {
		System.out.println("Excluindo o usuário...");
	}
}
