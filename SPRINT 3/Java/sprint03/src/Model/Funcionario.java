package Model;

public class Funcionario extends Usuario {
    private int idCadastro;
    private int idFuncionario;
    private String cargo;

    // Construtor

    public Funcionario(String usuario, String email, String telefone, String senha, String nome, String cpf, String rua, int numero, String complemento, String bairro, String cep, String estado, String cidade, String tipo, int idCadastro, String cargo) {
        super(usuario, email, telefone, senha, nome, cpf, rua, numero, complemento, bairro, cep, estado, cidade, tipo);
        this.idCadastro = idCadastro;
        this.cargo = cargo;
    }


    // Getters e Setters

    @Override
    public int getIdCadastro() {
        return idCadastro;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
