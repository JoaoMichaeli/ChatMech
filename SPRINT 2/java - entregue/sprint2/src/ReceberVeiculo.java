public class ReceberVeiculo{
    private int idVeiculo;
    private String placa;
    public String modelo;
    private String marca;
    public String anoFabricacao;
    public String servico;
    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getAnoFabricacao() {
        return anoFabricacao;
    }
    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    public String getServico() {
        return servico;
    }
    public void setServico(String servico) {
        this.servico = servico;
    }

    public ReceberVeiculo(int idVeiculo, String placa, String modelo, String marca, String anoFabricacao, String servico) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.servico = servico;
    }
}
