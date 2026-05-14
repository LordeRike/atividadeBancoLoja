public class Produto{
    private int idProduto;
    private String descricao;
    private int quantidadeEstoque;

    // Construtores
    public Produto() {}
    public Produto(int idProduto, String descricao, int quantidadeEstoque) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public int getIdProduto() { return idProduto; }
    public void setIdProduto(int idProduto) { this.idProduto = idProduto; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}