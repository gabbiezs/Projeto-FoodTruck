package model.bo;


import model.dao.EntregaDAO;
import model.dao.ProdutoDAO;
import model.dao.UsuarioDAO;
import model.dao.VendaDAO;
import model.vo.EntregaVO;
import model.vo.ItemVendaVO;
import model.vo.SituacaoEntregaVO;
import model.vo.VendaVO;

public class VendaBO {
	

	public VendaVO cadastrarVendaBO(VendaVO vendaVO) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorIdUsuarioDAO(vendaVO.getIdUsuario())) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			boolean listaItensEValida = true;
			for (ItemVendaVO itemVendaVO : vendaVO.getListaItemVendaVO()) {
				if(!produtoDAO.verificarExistenciaRegistroProdutoPorIdProdutoDAO(itemVendaVO.getIdProduto())) {
					System.out.println("O produto de id " + itemVendaVO.getIdProduto() + " não existe na base de dados.");
					listaItensEValida = false;
				}
			}
			if (listaItensEValida) {
				VendaDAO vendaDAO = new VendaDAO();
				vendaVO = vendaDAO.cadastrarVendaDAO(vendaVO);
				if(vendaVO.getIdVenda() != 0) {
					boolean resultado = vendaDAO.cadastrarItemVendaDAO(vendaVO);
					if(!resultado) {
						System.out.println("\nNão foi possível incluir algum item do produto.");
					}
					if(vendaVO.isFlagEntrega()) {
						EntregaBO entregaBO = new EntregaBO();
						resultado = entregaBO.cadastrarEntregaBO(vendaVO.getIdVenda());
						if(!resultado) {
							System.out.println("\nNão foi possivel cadastrar a Entrega.");
						}
					}
				} else {
					System.out.println("\nNão foi possível cadastrar a Venda.");
				}
			}
		} else {
			System.out.println("O usuário desta venda não existe na base de dados.");
		}
		return vendaVO;
	}

	public boolean cancelarVendaBO(VendaVO vendaVO) {
		boolean retorno = false;
		EntregaDAO entregaDAO = new EntregaDAO();
		VendaDAO vendaDAO = new VendaDAO();
		VendaVO vendaBanco = vendaDAO.consultarVendaDAO(vendaVO);
		if(vendaBanco != null) {
			if(vendaBanco.getDataCancelamento() == null) {
				if(vendaBanco.getDataVenda().isBefore(vendaVO.getDataCancelamento())) {
					if(vendaBanco.isFlagEntrega()) {
						EntregaVO entregaVO = entregaDAO.consultarEntregaPorIdVendaDAO(vendaVO.getIdVenda());
						if(entregaVO.getIdSituacaoEntrega().getValor() <= SituacaoEntregaVO.PREPARANDO_PEDIDO.getValor()) {
							boolean resultado = entregaDAO.cancelarEntregaDAO(vendaVO, SituacaoEntregaVO.PEDIDO_CANCELADO.getValor());
							if(resultado) {
								retorno = vendaDAO.cancelarVendaDAO(vendaVO);
							} else {
								System.out.println("\nNão foi possível alterar a situação da entrega para Cancelada.");
							}
						} else {
							System.out.println("\nO pedido já se encontra em processo de entregue/entrega.");
						}
					} else {
						retorno = vendaDAO.cancelarVendaDAO(vendaVO);
					}
				} else {
					System.out.println("\nA data de cancelamento é anterior a data de cadastro da venda.");
				}
			} else {
				System.out.println("\nVenda já se encontra cancelada na base de dados.");
			}
		}
		return retorno;
	}

	public boolean verificarVendaParaAtualizarSituacaoEntrega(VendaVO vendaVO) {
		boolean retorno = false;
		EntregaDAO entregaDAO = new EntregaDAO();
		VendaDAO vendaDAO = new VendaDAO();
		if(vendaDAO.verificarExistenciaRegistroPorIdVendaDAO(vendaVO.getIdVenda())) {
			if(vendaDAO.verificarVendaPossuiEntrega(vendaVO.getIdVenda())) {
				if(!vendaDAO.verificarCancelamentoPorIdVendaDAO(vendaVO.getIdVenda())) {
					if(entregaDAO.consultarEntregaPorIdVendaDAO(vendaVO.getIdVenda()).getDataEntrega() == null) {
						retorno = true;
					} else {
						System.out.println("\nVenda já teve a entrega realizada.");
					}
				} else {
					System.out.println("\nVenda já se encontra cancelada na base de dados.");
				}
			} else {
				System.out.println("\nVenda não possui entrega.");
			}
		} else {
			System.out.println("\nVenda não localizada na base de dados.");
		}
		return retorno;
	}

}
