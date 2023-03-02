package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.dto.VendasCanceladasDTO;

public class RelatorioDAO {

	public ArrayList<VendasCanceladasDTO> gerarRelatorioVendasCanceladasDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<VendasCanceladasDTO> vendasCanceladasDTO = new ArrayList<VendasCanceladasDTO>();
		String query = "SELECT u.nome as NOME, v.datacancelamento as DATA_CANCELAMENTO, (SELECT SUM(p.preco * itv.quantidade) "
				+ "FROM itemvenda itv, produto p WHERE itv.idvenda = v.idvenda AND itv.idproduto = p.idproduto GROUP BY idvenda) as SUBTOTAL, "
				+ "v.taxaentrega as TAXA_ENTREGA, (v.taxaentrega + (SELECT SUM(p.preco * itv.quantidade) FROM itemvenda itv, produto p "
				+ "WHERE itv.idvenda = v.idvenda AND itv.idproduto = p.idproduto GROUP BY idvenda)) AS TOTAL FROM usuario u, venda v "
				+ "WHERE v.idusuario = u.idusuario AND v.datacancelamento IS NOT NULL";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()) {
				VendasCanceladasDTO vendaCancelada = new VendasCanceladasDTO();
				vendaCancelada.setNome(resultado.getString(1));
				vendaCancelada.setDataCancelamento(LocalDateTime.parse(resultado.getString(2), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				vendaCancelada.setSubtotal(resultado.getDouble(3));
				vendaCancelada.setTaxaEntrega(resultado.getDouble(4));
				vendaCancelada.setTotal(resultado.getDouble(5));
				vendasCanceladasDTO.add(vendaCancelada);
			}
		} catch (Exception erro) {
			System.out.println("Erro ao executar a query do método gerarRelatorioVendasCanceladasDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return vendasCanceladasDTO;
	}

}
