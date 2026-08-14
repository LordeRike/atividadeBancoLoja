public class Conselho {
    private int id_registro;
    private int id_conselho;
    private String conselho;
    private int data_consulta;
    private String tipo_consulta;

    public Conselho(int id_registro, int id_conselho, String conselho, int data_consulta, String tipo_consulta) {
        this.id_registro = id_registro;
        this.id_conselho = id_conselho;
        this.conselho = conselho;
        this.data_consulta = data_consulta;
        this.tipo_consulta = tipo_consulta;
    }

    public int getId_registro() { return id_registro; }
    public void setId_registro(int id_registro) { this.id_registro = id_registro; }
    public int getId_conselho() { return id_conselho; }
    public void setId_conselho(int id_conselho) { this.id_conselho = id_conselho; }
    public String getConselho() { return conselho; }
    public void setConselho(String conselho) { this.conselho = conselho; }
    public int getData_consulta() { return data_consulta; }
    public void setData_consulta(int data_consulta) { this.data_consulta = data_consulta; }
    public String getTipo_consulta() { return tipo_consulta; }
    public void setTipo_consulta(String tipo_consulta) { this.tipo_consulta = tipo_consulta; }

}
