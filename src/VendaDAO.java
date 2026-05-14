import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;



public class VendaDAO {
    private Connection conexao;

    // Construtor que recebe a conexão por parâmetro
    public VendaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    //Consultar id de venda
    public void Venda(Venda venda) throws SQLException {
        int pID = 0;
        String sql = "SELECT nextval('venda_item_id_venda_item_seq'::regclass) as id_venda";
        //String sqlId = "SELECT nextval('venda_id_venda_seq')";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                pID = rs.getInt("id_venda"); 
                venda.setIdVenda(pID);
            }
        }
        //return pID;
        System.out.println("== Tela de Vendas == ");
        sql = "INSERT INTO venda (id_venda, id_cliente, data_venda) VALUES (?,? , now())";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, pID);
            stmt.setInt(2, venda.getIdCliente());
            //stmt.setInt(2, venda.getDataVenda());
            stmt.executeUpdate();
        }

    }

    public void notaFiscal(int idVenda) throws SQLException {
        String sqlVenda = "SELECT v.data_venda, c.nome FROM venda v " +
                          "JOIN cliente c ON v.id_cliente = c.id_cliente WHERE v.id_venda = ?";
        
        String sqlItens = "SELECT p.descricao, vi.quantidade, vi.valor_unitario, " +
                          "(vi.quantidade * vi.valor_unitario) AS valor_total_item " +
                          "FROM venda_item vi " +
                          "JOIN produtos p ON vi.id_produto = p.id_produto " +
                          "WHERE vi.id_venda = ?";
    
        try (PreparedStatement stmtV = conexao.prepareStatement(sqlVenda)) {
            stmtV.setInt(1, idVenda);
            try (ResultSet rsV = stmtV.executeQuery()) {
                if (rsV.next()) {
                    System.out.println("\n===========================================");
                    System.out.println("                NOTA FISCAL                ");
                    System.out.println("===========================================");
                    System.out.println("Data: " + rsV.getTimestamp("data_venda"));
                    System.out.println("Cliente: " + rsV.getString("nome"));
                    System.out.println("-------------------------------------------");
                    System.out.printf("%-15s %-5s %-10s %-10s%n", "Produto", "Qtd", "Unit", "Total");
    
                    double totalGeral = 0;
                    try (PreparedStatement stmtI = conexao.prepareStatement(sqlItens)) {
                        stmtI.setInt(1, idVenda);
                        try (ResultSet rsI = stmtI.executeQuery()) {
                            while (rsI.next()) {
                                String desc = rsI.getString("descricao");
                                int qtd = rsI.getInt("quantidade");
                                double unit = rsI.getDouble("valor_unitario");
                                double totalItem = rsI.getDouble("valor_total_item");
                                totalGeral += totalItem;
    
                                System.out.printf("%-15s %-5d R$%-8.2f R$%-8.2f%n", desc, qtd, unit, totalItem);
                            }
                        }
                    }
                    System.out.println("-------------------------------------------");
                    System.out.printf("TOTAL DA VENDA: R$%.2f%n", totalGeral);
                    System.out.println("===========================================\n");
                }
            }
        }
    }

    
}
