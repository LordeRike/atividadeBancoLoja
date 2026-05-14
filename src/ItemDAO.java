import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

public class ItemDAO {
    private Connection conexao;

    // Construtor que recebe a conexão por parâmetro
    public ItemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    //Gerar um item venda

    public void inserirItem(Item item) throws SQLException {
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

}
