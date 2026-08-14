import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;

// Importações da biblioteca Minimal-JSON
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

public class ConselhoDAO {
    private Connection conexao;

    public ConselhoDAO(Connection conexao) {
        this.conexao = conexao;
    }
    

    public void consultarConselho(){

        String url = "https://api.adviceslip.com/advice";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            System.out.println("Conectando à API...");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonBody = response.body();

                System.out.println("Json: "+jsonBody);

                // 1. Transforma o texto bruto recebido da API em um Objeto JSON
                JsonObject objetoPrincipal = Json.parse(jsonBody).asObject();
                
                // 2. Entra no nó/objeto "slip"
                JsonObject slip = objetoPrincipal.get("slip").asObject();
                
                // 3. Extrai o ID como um número inteiro (int) do Java
                int id = slip.get("id").asInt();
                
                // 4. Extrai o conselho como uma String do Java
                String conselho = slip.get("advice").asString();

                // 5. Mostra os resultados separados
                System.out.println("\n--- CONSELHO ENCONTRADO ---");
                System.out.println("ID do Conselho: " + id);
                System.out.println("Conselho: \"" + conselho + "\"");
                System.out.println("---------------------------\n");
                String tipo_consulta = "aleatorio";

                // inserir o conselho no banco

                System.out.println("== Inserir Conselho ==");
                String sql = "INSERT INTO conselho( id_conselho, conselho, data_consulta, tipo_consulta) VALUES ( ?, ?, now(), ?); ";
                try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                    //stmt.setInt(1, item.getIdVendaItem());
                    //stmt.setInt(1, conselho.getId_registro());
                    stmt.setInt(1, id);
                    stmt.setString(2, conselho);
                    stmt.setString(3, tipo_consulta);
                    stmt.executeUpdate();
                }
                
            } else {
                System.out.println("Erro na requisição. Código: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }

    }
    
    public void inserirConselho(Conselho conselho) throws SQLException {
        //System.out.println("== Inserir Conselho ==");
        /*String sql = "INSERT INTO conselho( id_conselho, conselho, data_consulta) VALUES (?, ?, ?, now()); ";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            //stmt.setInt(1, item.getIdVendaItem());
            //stmt.setInt(1, conselho.getId_registro());
            stmt.setInt(1, conselho.getId_conselho());
            stmt.setString(2, conselho.getConselho());
            //stmt.setInt(4, conselho.getData_consulta());
            stmt.executeUpdate();
        }*/
    }

    public void conselhoAleatorio(int idEscolha) throws SQLException {
        
        String url = "https://api.adviceslip.com/advice/"+idEscolha+"";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            System.out.println("Conectando à API...");
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonBody = response.body();

                System.out.println("Json: "+jsonBody);

                // 1. Transforma o texto bruto recebido da API em um Objeto JSON
                JsonObject objetoPrincipal = Json.parse(jsonBody).asObject();
                
                // 2. Entra no nó/objeto "slip"
                JsonObject slip = objetoPrincipal.get("slip").asObject();
                
                // 3. Extrai o ID como um número inteiro (int) do Java
                int id = slip.get("id").asInt();
                
                // 4. Extrai o conselho como uma String do Java
                String conselho = slip.get("advice").asString();

                // 5. Mostra os resultados separados
                System.out.println("\n--- CONSELHO ENCONTRADO ---");
                System.out.println("ID do Conselho: " + id);
                System.out.println("Conselho: \"" + conselho + "\"");
                System.out.println("---------------------------\n");
                String tipo_consulta = "especifico";

                // inserir o conselho no banco

                System.out.println("== Inserir Conselho ==");
                String sql = "INSERT INTO conselho( id_conselho, conselho, data_consulta, tipo_consulta) VALUES ( ?, ?, now(), ?); ";
                try(PreparedStatement stmt = conexao.prepareStatement(sql)){
                    //stmt.setInt(1, item.getIdVendaItem());
                    //stmt.setInt(1, conselho.getId_registro());
                    stmt.setInt(1, id);
                    stmt.setString(2, conselho);
                    stmt.setString(3, tipo_consulta);
                    stmt.executeUpdate();
                }
                
            } else {
                System.out.println("Erro na requisição. Código: " + response.statusCode());
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}