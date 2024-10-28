public class CadastrarOficina {
    private int idOficina;
    private String email;
    private String senha;
    private int cnpj;
    private String endereco;
    public int getIdOficina() {
        return idOficina;
    }
    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getCnpj() {
        return cnpj;
    }
    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public CadastrarOficina(int idOficina, String email, String senha, int cnpj, String endereco) {
        this.idOficina = idOficina;
        this.email = email;
        this.senha = senha;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }
    public void verificarCnpj(){
        // irá fazer a verificação para garantir que o cnpj é válido
    }
    public void verificarEmail(){
        // irá fazer a verificação do email, para saber se ja existe uma conta criada com ele.
    }
    public void verificarEndereco(){
        // irá fazer a verificação do endereço.
    }
}
