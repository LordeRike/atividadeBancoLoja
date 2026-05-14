public class Item {
    private int idVendaItem;
    private int idVenda;    
    private Double valorUnitario;
    private int quantidade;
    private int idProduto;

    public Item(int idVendaItem,int idVenda,Double valorUnitario, int quantidade, int idProduto ) {
        this.idVendaItem = idVendaItem;
        this.idVenda = idVenda;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        
    }

    
    public Double getValorUnitario() {return valorUnitario; }
    public void setValorUnitario(Double valorUnitario) {this.valorUnitario = valorUnitario; }
    public int getQuantidade() {return quantidade; }
    public void setQuantidade(int quantidade) {this.quantidade = quantidade; }
    public int getIdProduto() {return idProduto; }
    public void setIdProduto(int idProduto) {this.idProduto = idProduto; }
    public int getIdVenda() {return idVenda; }
    public void setIdVenda(int idVenda) {this.idVenda = idVenda; }
    public int getIdVendaItem() {return idVendaItem; }
    public void setIdVendaItem(int idVendaItem) {this.idVendaItem = idVendaItem; }

}
