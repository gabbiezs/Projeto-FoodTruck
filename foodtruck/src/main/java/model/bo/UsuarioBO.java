package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;

public class UsuarioBO {

	public UsuarioVO realizarLoginBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.realizarLoginDAO(usuarioVO);
	}

	public ArrayList<TipoUsuarioVO> consultarTipoUsuarioBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipoUsuarioDAO();
	}

	public UsuarioVO cadastrarUsuarioBO(UsuarioVO usuarioVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarExistenciaRegistroPorCpfDAO(usuarioVO)) {
			System.out.println("\nUsuário já cadastrado!");
		} else {
			usuarioVO = usuarioDAO.cadastrarUsuarioDAO(usuarioVO);
		}
		return usuarioVO;
	}

	public boolean excluirUsuarioBO(UsuarioVO usuarioVO) {
		boolean resultado = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(usuarioVO.getIdUsuario())) {
			if (usuarioDAO.verificarDesligamentoUsuarioPorIdUsuarioDAO(usuarioVO.getIdUsuario())) {
				System.out.println("\nUsuário já se encontra desligado na base de dados!");
			} else {
				resultado = usuarioDAO.excluirUsuarioDAO(usuarioVO);
			}
		} else {
			System.out.println("\nUsuário não existe na base de dados!");
		}
		return resultado;
	}

	public boolean atualizarUsuarioBO(UsuarioVO usuarioVO) {
		boolean resultado = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(usuarioVO.getIdUsuario())) {
			if (usuarioDAO.verificarDesligamentoUsuarioPorIdUsuarioDAO(usuarioVO.getIdUsuario())) {
				System.out.println("\nUsuário já se encontra desligado na base de dados!");
			} else {
				resultado = usuarioDAO.atualizarUsuarioDAO(usuarioVO);
			}
		} else {
			System.out.println("\nUsuário não existe na base de dados!");
		}
		return resultado;
	}
}
