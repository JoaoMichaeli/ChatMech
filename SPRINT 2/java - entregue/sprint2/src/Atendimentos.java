public class Atendimentos extends Menu{
    public String regiao;
    public String mecanicasPareceiras;
    public String servicos;
    public String descricao;
    public String getRegiao() {
        return regiao;
    }
    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
    public String getMecanicasPareceiras() {
        return mecanicasPareceiras;
    }
    public void setMecanicasPareceiras(String mecanicasPareceiras) {
        this.mecanicasPareceiras = mecanicasPareceiras;
    }
    public String getServicos() {
        return servicos;
    }
    public void setServicos(String servicos) {
        this.servicos = servicos;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Atendimentos(String regiao, String mecanicasPareceiras, String servicos, String descricao) {
        this.regiao = regiao;
        this.mecanicasPareceiras = mecanicasPareceiras;
        this.servicos = servicos;
        this.descricao = descricao;
    }

}