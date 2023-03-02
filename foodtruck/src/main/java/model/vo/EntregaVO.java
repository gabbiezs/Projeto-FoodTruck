package model.vo;

import java.time.LocalDateTime;

public class EntregaVO {

	private int idEntrega;
	private int idVenda;
	private int idEntregador;
	private SituacaoEntregaVO idSituacaoEntrega;
	private LocalDateTime dataEntrega;
	
	public EntregaVO(int idEntrega, int idVenda, int idEntregador, SituacaoEntregaVO idSituacaoEntrega,
			LocalDateTime dataEntrega) {
		super();
		this.idEntrega = idEntrega;
		this.idVenda = idVenda;
		this.idEntregador = idEntregador;
		this.idSituacaoEntrega = idSituacaoEntrega;
		this.dataEntrega = dataEntrega;
	}

	public EntregaVO() {
		super();
	}

	public int getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(int idEntregador) {
		this.idEntregador = idEntregador;
	}

	public SituacaoEntregaVO getIdSituacaoEntrega() {
		return idSituacaoEntrega;
	}

	public void setIdSituacaoEntrega(SituacaoEntregaVO idSituacaoEntrega) {
		this.idSituacaoEntrega = idSituacaoEntrega;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
}
