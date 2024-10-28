public class Agendamento extends Menu{
    private int idVeiculo;
    private int idDono;
    private int idOficina;
    public String servico;
    public String dia;
    public String horas;
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public String getHoras() {
        return horas;
    }
    public void setHoras(String horas) {
        this.horas = horas;
    }
    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public int getIdDono() {
        return idDono;
    }
    public void setIdDono(int idDono) {
        this.idDono = idDono;
    }
    public int getIdOficina() {
        return idOficina;
    }
    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }
    public String getServico() {
        return servico;
    }
    public void setServico(String servico) {
        this.servico = servico;
    }
    public Agendamento(int idVeiculo, int idDono, int idOficina, String servico, String dia, String horas) {
        this.idVeiculo = idVeiculo;
        this.idDono = idDono;
        this.idOficina = idOficina;
        this.servico = servico;
        this.dia = dia;
        this.horas = horas;
    }
}
