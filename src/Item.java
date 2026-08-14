public class Item {
    private int idVendaItem;
    private int idVenda;    
    private Double valorUnitario;
    private Integer quantidade;
    private int idProduto;

    public Item() {}
    public Item(int idVendaItem,int idVenda,Double valorUnitario, int quantidade, int idProduto ) {
        this.idVendaItem = idVendaItem;
        this.idVenda = idVenda;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        
    }

    
    public Double getValorUnitario() {return valorUnitario; }
    public void setValorUnitario(Double valorUnitario) {
        if (valorUnitario == null || valorUnitario < 0.0) {
            throw new IllegalArgumentException("Este valor não pode ser negativo.");
        }
        this.valorUnitario = valorUnitario;
    }
    public int getQuantidade() {return quantidade; }
    public void setQuantidade(Integer quantidade) {
        if (quantidade < 0 || quantidade == null) {
            throw new IllegalArgumentException("Este valor não pode ser negativo.");
        }
        this.quantidade = quantidade;
    }
    public int getIdProduto() {return idProduto; }
    public void setIdProduto(int idProduto) {this.idProduto = idProduto; }
    public int getIdVenda() {return idVenda; }
    public void setIdVenda(int idVenda) {this.idVenda = idVenda; }
    public int getIdVendaItem() {return idVendaItem; }
    public void setIdVendaItem(int idVendaItem) {this.idVendaItem = idVendaItem; }

}
