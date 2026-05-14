//import java.time.LocalDate;
import java.util.List;

public class Venda {
    private int idVenda;
    private int idCliente;
    private int dataVenda;

    private List<Item> itens;
    
    public Venda() {}
    public Venda(int idVenda, int idCliente, int dataVenda) {
        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.dataVenda = dataVenda;
    }
    public int getIdVenda() {return idVenda; }
    public void setIdVenda(int idVenda) {this.idVenda = idVenda; }
    public int getIdCliente() {return idCliente; }
    public void setIdCliente(int idCliente) {this.idCliente = idCliente; }
    public int getDataVenda() {return dataVenda; }
    public void setDataVenda(int dataVenda) {this.dataVenda = dataVenda; }
    
}
