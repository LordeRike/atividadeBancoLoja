public class Cliente {
    private int idCliente;
    private String nome;
    private String endereco;

    // Construtores
    public Cliente() {}
    public Cliente(int idCliente, String nome, String endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.endereco = endereco;
    }

    // Getters e Setters
    public int getIdCliente() { return idCliente; }
    public Integer setIdCliente(int idCliente) { return this.idCliente = idCliente; }
    public String getNome() { return nome; }
    public String setNome(String nome) { 
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode estar vazio.");            
        }
        String[] partesNome = nome.trim().split("\\s+");
        if (partesNome.length < 2) {
            throw new IllegalArgumentException("O nome deve conter pelo menos nome e sobrenome (ex: 'João Silva').");            
        }
        return this.nome = nome;
    }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode estar vazio.");
            
        }
        this.endereco = endereco;
    }
}