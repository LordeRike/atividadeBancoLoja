import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conexao;

    // Construtor que recebe a conexão por parâmetro
    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // CREATE - Inserir cliente
    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (nome, endereco) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.executeUpdate();
        }
    }

    // READ - Listar todos os clientes
    public List<Cliente> listarTodos() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY id_cliente";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setNome(rs.getString("nome"));
                c.setEndereco(rs.getString("endereco"));
                clientes.add(c);
            }
        }
        return clientes;
    }

    // LIKE - Buscar cliente por nome
    public List<Cliente> listarUm(String nome) throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nome like ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, "%"+nome+"%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setNome(rs.getString("nome"));
                    c.setEndereco(rs.getString("endereco"));
                    clientes.add(c);
                }
            }
        }
        return clientes;
    }

    // UPDATE - Atualizar dados do cliente
    public void atualizar(Cliente cliente) throws SQLException {        

        String sql = "UPDATE cliente SET nome = ?, endereco = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setInt(3, cliente.getIdCliente());
            int val = stmt.executeUpdate();

            if (val == 0) {
                throw new IllegalArgumentException("Cliente não exite no banco de dados.");
            }
        }
    }

    // DELETE - Remover cliente por ID
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int val = stmt.executeUpdate();

            if (val == 0) {
                throw new IllegalArgumentException("Cliente com o ID "+ id 
                + " não exite no banco de dados.");  
            }
        }
    }

}
