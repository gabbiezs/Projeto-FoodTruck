package model.vo;

public class ItemVendaVO {

	private int idItemVenda;
	private VendaVO idVenda;
	private ProdutoVO idProduto;
	private int quantidade;
	
	public ItemVendaVO(int idItemVenda, VendaVO idVenda, ProdutoVO idProduto, int quantidade) {
		super();
		this.idItemVenda = idItemVenda;
		this.idVenda = idVenda;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public ItemVendaVO() {
		super();
	}

	public int getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(int idItemVenda) {
		this.idItemVenda = idItemVenda;
	}

	public VendaVO getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(VendaVO idVenda) {
		this.idVenda = idVenda;
	}

	public ProdutoVO getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(ProdutoVO idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
