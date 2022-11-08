package model.vo;

import java.time.LocalDateTime;

public class EntregaVO {

	private int idEntrega;
	private VendaVO idVenda;
	private UsuarioVO idEntregador;
	private SituacaoEntregaVO idSituacaoEntrega;
	private LocalDateTime dataEntrega;
	
	public EntregaVO(int idEntrega, VendaVO idVenda, UsuarioVO idEntregador, SituacaoEntregaVO idSituacaoEntrega,
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

	public VendaVO getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(VendaVO idVenda) {
		this.idVenda = idVenda;
	}

	public UsuarioVO getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(UsuarioVO idEntregador) {
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
