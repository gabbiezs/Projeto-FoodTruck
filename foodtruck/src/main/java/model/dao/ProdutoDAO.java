package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.TipoProdutoVO;
import model.vo.ProdutoVO;

public class ProdutoDAO {
	

	public ArrayList<TipoProdutoVO> consultarTipoProdutoDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<TipoProdutoVO> listaTipoProdutoVO = new ArrayList<TipoProdutoVO>();
		String query = "SELECT descricao FROM tipoproduto";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				TipoProdutoVO tipoProdutoVO = TipoProdutoVO.valueOf(resultado.getString(1));
				listaTipoProdutoVO.add(tipoProdutoVO);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarTipoProdutoDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaTipoProdutoVO;
	}

	public boolean verificarExistenciaRegistroPorNomeDAO(ProdutoVO produtoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT nome FROM produto WHERE nome = '" + produtoVO.getNome() + "' ";
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método verificarExistenciaRegistroPorNomeDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public ProdutoVO cadastrarProdutoDAO(ProdutoVO produtoVO) {
		String query = "INSERT INTO produto (idtipoproduto, nome, preco, datacadastro) "
				+ "VALUES (?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			pstmt.setInt(1, produtoVO.getTipoProduto().getValor());
			pstmt.setString(2, produtoVO.getNome());
			pstmt.setDouble(3, produtoVO.getPreco());
			pstmt.setObject(4, produtoVO.getDataCadastro());
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				produtoVO.setIdProduto(Integer.parseInt(resultado.getString(1)));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método cadastrarProdutoDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return produtoVO;
	}

	public boolean verificarExistenciaRegistroProdutoPorIdProdutoDAO(int idProduto) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT idProduto FROM produto WHERE idProduto = " + idProduto;
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				retorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método verificarExistenciaRegistroProdutoPorIdProdutoDAO");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean atualizarProdutoDAO(ProdutoVO produtoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "UPDATE produto SET idTipoProduto = " + produtoVO.getTipoProduto().getValor() 
				+ ", nome = '" + produtoVO.getNome() 
				+ "', preco = '" + produtoVO.getPreco()
				+ "', datacadastro = '" + produtoVO.getDataCadastro()
				+ "' WHERE idproduto = " + produtoVO.getIdProduto();
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}	
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método atualizarProdutoDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean excluirProdutoDAO(ProdutoVO produtoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		String query = "UPDATE produto SET dataexclusao = '" + produtoVO.getDataExclusao() 
				+ "' WHERE idproduto = " + produtoVO.getIdProduto();
		try {
			if(stmt.executeUpdate(query) == 1) {
				retorno = true;
			}	
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método excluirProdutoDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public ProdutoVO consultarProdutoDAO(ProdutoVO produtoVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ProdutoVO produto = new ProdutoVO();
		String query = "SELECT p.idproduto, tipo.descricao, p.nome, p.preco, p.datacadastro, p.dataexclusao "
				+ "FROM produto p, tipoproduto tipo "
				+ "WHERE p.idtipoproduto = tipo.idtipoproduto "
				+ "AND p.idproduto = " + produtoVO.getIdProduto();
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				produto.setIdProduto(Integer.parseInt(resultado.getString(1)));
				produto.setTipoProduto(TipoProdutoVO.valueOf(resultado.getString(2)));
				produto.setNome(resultado.getString(3));
				produto.setPreco(Double.parseDouble(resultado.getString(4)));
				produto.setDataCadastro(LocalDateTime.parse(resultado.getString(5), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				if(resultado.getString(6) != null) {
					produto.setDataExclusao(LocalDateTime.parse(resultado.getString(6), 
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarProdutoDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return produto;
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ProdutoVO> listaProdutoVO = new ArrayList<ProdutoVO>();
		String query = "SELECT p.idproduto, tipo.descricao, p.nome, p.preco, p.datacadastro, p.dataexclusao "
				+ "FROM produto p, tipoproduto tipo "
				+ "WHERE p.idtipoproduto = tipo.idtipoproduto ";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				ProdutoVO produto = new ProdutoVO();
				produto.setIdProduto(Integer.parseInt(resultado.getString(1)));
				produto.setTipoProduto(TipoProdutoVO.valueOf(resultado.getString(2)));
				produto.setNome(resultado.getString(3));
				produto.setPreco(Double.parseDouble(resultado.getString(4)));
				produto.setDataCadastro(LocalDateTime.parse(resultado.getString(5), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				if(resultado.getString(6) != null) {
					produto.setDataExclusao(LocalDateTime.parse(resultado.getString(6), 
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				}
				listaProdutoVO.add(produto);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarTodosProdutosDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaProdutoVO;
	}

	public boolean verificarDesligamentoProdutoPorIdProdutoDAO(int idProduto) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String querry = "SELECT dataexclusao FROM produto WHERE idproduto = " + idProduto;
		try {
			resultado = stmt.executeQuery(querry);
			if(resultado.next()){
				String dataExpiracao = resultado.getString(1);
				if (dataExpiracao != null) {
					retorno = true;
				}
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método verificarDesligamentoProdutoPorIdProdutoDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosVigentesDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ProdutoVO> listaProdutosVO = new ArrayList<ProdutoVO>();
		String querry = "SELECT p.idProduto, tipo.descricao, p.nome, p.preco, p.dataCadastro "
				+ "FROM produto p, tipoProduto tipo "
				+ "WHERE p.idTipoProduto = tipo.idTipoProduto "
				+ "AND p.dataExclusao is NULL";
		try {
			resultado = stmt.executeQuery(querry);
			while(resultado.next()) {
				ProdutoVO produtoVO = new ProdutoVO();
				produtoVO.setIdProduto(Integer.parseInt(resultado.getString(1)));
				produtoVO.setTipoProduto(TipoProdutoVO.valueOf(resultado.getString(2)));
				produtoVO.setNome(resultado.getString(3));
				produtoVO.setPreco(Double.parseDouble(resultado.getString(4)));
				produtoVO.setDataCadastro(LocalDateTime.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				listaProdutosVO.add(produtoVO);
				
				}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarTodosProdutosVigentesDAO");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaProdutosVO;
	}
}
