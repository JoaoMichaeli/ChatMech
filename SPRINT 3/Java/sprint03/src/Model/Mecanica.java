package Model;

public class Mecanica {
    private int idMecanica;
    private String telefone;
    private String email;
    
    private String nome;
    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String estado;
    private String cidade;

    // Construtor
    public Mecanica(String telefone, String email,  String nome, String rua, int numero, String bairro, String cep, String estado, String cidade) {
        this.telefone = telefone;
        this.email = email;

        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
    }

    // Getters e Setters
    public int getIdMecanica() {
        return idMecanica;
    }

    public void setIdMecanica(int idMecanica) {
        this.idMecanica = idMecanica;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
