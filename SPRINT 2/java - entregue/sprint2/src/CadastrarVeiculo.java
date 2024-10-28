public class CadastrarVeiculo {
    private int idDono;
    private int idVeiculo;
    private String placa;
    private String modelo;
    private String marca;
    private String anoFabricacao;
    public int getIdDono() {
        return idDono;
    }
    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }
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
    public CadastrarVeiculo(int idDono, int idVeiculo, String placa, String modelo, String marca, String anoFabricacao) {
        this.idDono = idDono;
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
    }
    public void alterarDados(){
        setIdDono(0);
        setIdVeiculo(0);
        setPlaca("");
        setModelo("");
        setMarca("");
        setAnoFabricacao("");
    }
}
