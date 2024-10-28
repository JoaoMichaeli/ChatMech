package Model;

import DAO.ClienteDAO;
import DAO.ServicoDAO;
import DAO.VeiculoDAO;

public class Cliente extends Usuario {
    private int idCliente;
    private int idCadastro; // é definido após inserção no bd

    // Construtor

    public Cliente(String usuario, String email, String telefone, String senha, String nome, String cpf, String rua, int numero, String complemento, String bairro, String cep, String estado, String cidade, String tipo) {
        super(usuario, email, telefone, senha, nome, cpf, rua, numero, complemento, bairro, cep, estado, cidade, tipo);

    }


    // Getters e Setters

    public int getIdCliente() {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.buscaIdCliente(this.getCpf());
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int getIdCadastro() {
        return idCadastro;
    }

    @Override
    public void setIdCadastro(int idCadastro) {
        this.idCadastro = idCadastro;
    }

    // Métodos Veículo

    // Cadastrar novo veículo
    public void cadastrarNovoVeiculo(String placa, String modelo, String marca, int ano, int quilometragem, String dono, int idCliente) {
            System.out.println("Cadastrando novo veículo.");
            Veiculo novoVeiculo = new Veiculo(placa, modelo, marca, ano, quilometragem, dono, idCliente);
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculoDAO.inserir(novoVeiculo);
            System.out.println("Veículo cadastrado com sucesso!");
//            veiculoDAO.fechaConexao();
    }

    // Editar veículo
    public void editarVeiculo(String placaAtual,String placaNova, String modeloAtual, String modeloNovo, String dono, String marca, int ano, int quilometragem, int idCliente){
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.editarVeiculo(placaAtual, placaNova, modeloAtual, modeloNovo, dono, marca, ano, quilometragem, idCliente);
//        veiculoDAO.fechaConexao();
    }

    // Excluir veículo
    public void excluirVeiculo(String placa){
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.excluirVeiculo(placa);
    }


    // Métodos Servico
    public void agendarNovoServico(int idMecanica, int idCliente, String inicioServico, String descricao){

        System.out.println("Agendando novo serviço...");

        ServicoDAO servicoDAO = new ServicoDAO();
        Servico servicoNovo = new Servico(idMecanica,  idCliente,  inicioServico, descricao);
        servicoDAO.inserir(servicoNovo);

        System.out.println("Serviço agendado com sucesso!");
    }

    public void cancelarServicoAgendado(int idServico){
        ServicoDAO servicoDAO = new ServicoDAO();
        servicoDAO.removerServico(idServico);

    }
}
