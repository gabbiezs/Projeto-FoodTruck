package model.vo;

import java.time.LocalDateTime;

public class VendaVO {

	private int idVenda;
	private UsuarioVO idUsuario;
	private int numeroPedido;
	private LocalDateTime dataVenda;
	private LocalDateTime dataCancelamento;
	private boolean flagEntrega;
	private double taxaEntrega;
	
	public VendaVO(int idVenda, UsuarioVO idUsuario, int numeroPedido, LocalDateTime dataVenda,
			LocalDateTime dataCancelamento, boolean flagEntrega, double taxaEntrega) {
		super();
		this.idVenda = idVenda;
		this.idUsuario = idUsuario;
		this.numeroPedido = numeroPedido;
		this.dataVenda = dataVenda;
		this.dataCancelamento = dataCancelamento;
		this.flagEntrega = flagEntrega;
		this.taxaEntrega = taxaEntrega;
	}

	public VendaVO() {
		super();
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public UsuarioVO getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(UsuarioVO idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public LocalDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public boolean getFlagEntrega() {
		return flagEntrega;
	}

	public void setFlagEntrega(boolean flagEntrega) {
		this.flagEntrega = flagEntrega;
	}

	public double getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}
}
