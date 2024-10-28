public class CadastrarCliente implements Login{
    private int idUsuario;
    private String email;
    private String senha;
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
    public CadastrarCliente(int idUsuario, String email, String senha) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
    }
    public void verificarEmail(){
        // irá fazer a verificação do email, para saber se ja existe uma conta criada com ele.
    }
    public void excluitUsuario(){
        // irá excluir os dados do usuario no sistema quando solicitado
    }
    @Override
    public void verificaLogin(String email, String senha) {
    }
}
