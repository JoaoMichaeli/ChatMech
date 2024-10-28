package Model;

import java.util.Date;

public class Servico {
    private int idServico;
    private int idMecanica;
    private int idCliente;
    private Date dataConserto;
    private Date inicioServico;
    private Date fimServico;
    private String pecasTrocadas;
    private String descricao;


    // Construtor
    public Servico(){};

    public Servico(int idMecanica, int idCliente, String inicioServico, String descricao){
        //Construtor para agendarNovoServico()
        this.idMecanica = idMecanica;
        this.idCliente = idCliente;
        this.inicioServico = new Date(inicioServico) ;
        this.descricao = descricao;
        this.dataConserto = new Date();
        this.fimServico = new Date();
        this.pecasTrocadas = "";

    }

    public Servico(int idMecanica, int idCliente, Date dataConserto, Date inicioServico, Date fimServico, String pecasTrocadas, String descricao) {
        this.idMecanica = idMecanica;
        this.idCliente = idCliente;
        this.dataConserto = dataConserto;
        this.inicioServico = inicioServico;
        this.fimServico = fimServico;
        this.pecasTrocadas = pecasTrocadas;
        this.descricao = descricao;
    }



    // Getters e Setters

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(int idMecanica) {
        this.idMecanica = idMecanica;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getDataConserto() {
        return dataConserto;
    }

    public void setDataConserto(Date dataConserto) {
        this.dataConserto = dataConserto;
    }

    public Date getInicioServico() {
        return inicioServico;
    }

    public void setInicioServico(Date inicioServico) {
        this.inicioServico = inicioServico;
    }

    public Date getFimServico() {
        return fimServico;
    }

    public void setFimServico(Date fimServico) {
        this.fimServico = fimServico;
    }

    public String getPecasTrocadas() {
        return pecasTrocadas;
    }

    public void setPecasTrocadas(String pecasTrocadas) {
        this.pecasTrocadas = pecasTrocadas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
