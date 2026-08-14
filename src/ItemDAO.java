import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private Connection conexao;

    // Construtor que recebe a conexão por parâmetro
    public ItemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    //Gerar um item venda

    public void inserirItem(Item item) throws SQLException {
        VendaDAO vendaDAO = new VendaDAO(conexao);
        if (!vendaDAO.existeVenda(item.getIdVenda())) {
            throw new IllegalArgumentException("O ID de venda " + item.getIdVenda() + " não existe no banco de dados."); 
        }
        System.out.println("== Inserir itens na Venda ==");
        String sql = "INSERT INTO venda_item ( id_venda, valor_unitario, quantidade, id_produto) VALUES (?,?,?,?) ";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            //stmt.setInt(1, item.getIdVendaItem());
            stmt.setInt(1, item.getIdVenda());
            stmt.setDouble(2, item.getValorUnitario());
            stmt.setInt(3, item.getQuantidade());
            stmt.setInt(4, item.getIdProduto());
            stmt.executeUpdate();
        }
    }

    public List<Item> listarItensPorVenda(int idVenda) throws SQLException {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT id_venda_item, id_venda, valor_unitario, quantidade, id_produto FROM venda_item WHERE id_venda = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idVenda);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item(
                        rs.getInt("id_venda_item"),
                        rs.getInt("id_venda"),
                        rs.getDouble("valor_unitario"),
                        rs.getInt("quantidade"),
                        rs.getInt("id_produto")
                    );
                    itens.add(item);
                }
            }
        }
        return itens;
    }

    public void atualizarItem(Item item) throws SQLException {
        String sql = "UPDATE venda_item SET quantidade = ?, valor_unitario = ? WHERE id_venda_item = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, item.getQuantidade());
            stmt.setDouble(2, item.getValorUnitario());
            stmt.setInt(3, item.getIdVendaItem());
            int val = stmt.executeUpdate();
            if (val == 0) {
                throw new IllegalArgumentException("Item da venda não encontrado!");
            }
        }
    }

    public void excluirItem(int idVendaItem) throws SQLException {
        String sql = "DELETE FROM venda_item WHERE id_venda_item = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idVendaItem);
            int val = stmt.executeUpdate();
            if (val == 0) {
                throw new IllegalArgumentException("Item da venda não encontrado!");
            }
        }
    }

}
