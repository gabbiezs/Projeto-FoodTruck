package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.vo.EntregaVO;
import model.vo.SituacaoEntregaVO;
import model.vo.VendaVO;

public class EntregaDAO {
	
	public boolean cadastrarEntregaDAO(EntregaVO entregaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "INSERT INTO entrega (idVenda, idEntregador, situacaoEntrega) VALUES ("
				+ entregaVO.getIdVenda() + ", "
				+ entregaVO.getIdEntregador() + ", "
				+ entregaVO.getIdSituacaoEntrega().getValor() + ")";
		
		try {
			int resultado = stmt.executeUpdate(query);
			if(resultado == 1) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método cadastrarEntregaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean atualizarSituacaoEntregaDAO(VendaVO vendaVO) {
		EntregaVO entregaVO = this.consultarEntregaPorIdVendaDAO(vendaVO.getIdVenda());
		entregaVO.setIdSituacaoEntrega(SituacaoEntregaVO.getSituacaoEntregaVOPorValor(entregaVO.getIdSituacaoEntrega().getValor()+1));
		if(entregaVO.getIdSituacaoEntrega().getValor() == SituacaoEntregaVO.PEDIDO_ENTREGUE.getValor()) {
			entregaVO.setDataEntrega(LocalDateTime.now());
		} 
		if(entregaVO.getIdSituacaoEntrega().getValor() > SituacaoEntregaVO.PEDIDO_ENTREGUE.getValor()) {
			return false;
		} else {
			return this.atualizarEntregaDAO(entregaVO);
		}
	}

	private boolean atualizarEntregaDAO(EntregaVO entregaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "UPDATE entrega SET situacaoEntrega = " + entregaVO.getIdSituacaoEntrega().getValor();
		if(entregaVO.getDataEntrega() == null) {
			query += " WHERE idEntrega = " + entregaVO.getIdEntrega();
		} else {
			query += ", dataEntrega = '" + entregaVO.getDataEntrega() + "' WHERE idEntrega = " + entregaVO.getIdEntrega();;
		}
		try {
			if(stmt.executeUpdate(query) == 1) {
			retorno = true;
		}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método atualizarEntregaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public EntregaVO consultarEntregaPorIdVendaDAO(int idVenda) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		EntregaVO entrega = new EntregaVO();
		String query = "SELECT e.idEntrega, e.idVenda, e.idEntregador, se.descricao, e.dataEntrega "
				+ "FROM entrega e, situacaoEntrega se  "
				+ "WHERE e.situacaoEntrega = se.ordem "
				+ "AND e.idVenda = " + idVenda;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				entrega.setIdEntrega(Integer.parseInt(resultado.getString(1)));
				entrega.setIdVenda(Integer.parseInt(resultado.getString(2)));
				entrega.setIdEntregador(Integer.parseInt(resultado.getString(3)));
				String valor = resultado.getString(4);
				entrega.setIdSituacaoEntrega(SituacaoEntregaVO.valueOf(valor));
				if(resultado.getString(5) != null) {
					entrega.setDataEntrega(LocalDateTime.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método consultarEntregaPorIdVendaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return entrega; 
	}
	
	public boolean cancelarEntregaDAO(VendaVO vendaVO, int situacao) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "UPDATE entrega SET situacaoEntrega = '" + situacao + "',"
				+ " dataEntrega = '" + vendaVO.getDataCancelamento()
				+ "' WHERE idVenda = " + vendaVO.getIdVenda();
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método cancelarEntregaDAO!");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}


}
