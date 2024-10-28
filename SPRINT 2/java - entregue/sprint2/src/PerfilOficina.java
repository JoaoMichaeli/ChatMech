public class PerfilOficina extends CadastrarOficina{
    public String nome;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public PerfilOficina(int idOficina,String nome, String email, String senha, int cnpj, String endereco) {
        super(idOficina, email, senha, cnpj, endereco);
        this.nome = nome;

    }
    public void alterarDados(){
        setCnpj(0);
        setEmail("");
        setEndereco("");
        setSenha("");
    }
}
