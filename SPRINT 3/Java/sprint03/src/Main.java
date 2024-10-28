import Model.*;
import DAO.*;

import java.sql.Timestamp;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Criar instâncias das classes

        Cliente cliente = new Cliente("usuario2", "email2@example.com", "11947195735", "senha2", "Dodock", "09876543211",
                "Rua Exemplo", 456, "Complemento", "Bairro Exemplo", "87654321", "RJ", "Cidade", "C");
//
        Funcionario funcionario = new Funcionario("usuario3", "email3@example.com", "567890123", "senha3",
                "Eliseu o Mecanico", "12345678902", "Rua Exemplo", 789, "Complemento", "Bairro Exemplo", "54321678", "SP", "Cidade", "F", 3, "Mecanico");
//
        Mecanica mecanica = new Mecanica("123456789", "email4@example.com", "Nome Mecanica", "Rua Exemplo", 123,
                "Bairro Exemplo", "12345678", "SP", "Cidade");
//
        Servico servico = new Servico(1,1,new Date(), new Date(), new Date(), "Correia dentada, óleo", "Troca de correia e óleo");

        // DAO
        ClienteDAO clienteDAO = new ClienteDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        MecanicaDAO mecanicaDAO = new MecanicaDAO();
        ServicoDAO servicoDAO = new ServicoDAO();

        // Inserir dados no banco
        clienteDAO.inserir(cliente);
        funcionarioDAO.inserir(funcionario);
        mecanicaDAO.inserir(mecanica);
        servicoDAO.inserir(servico);


        // Métodos 'relevantes'

//        cliente.cadastrarNovoVeiculo("DPX1018", "Hb20", "Hyundai", 2020, 10000, "Henrique", cliente.getIdCliente());

//        cliente.editarVeiculo("DPX1018", "BBK2019", "Hb20", "Corolla",  "Saes", "Toyota", 2024, 12000, cliente.getIdCliente());

//        cliente.excluirVeiculo("BBK2019");

//        cliente.agendarNovoServico(1, cliente.getIdCliente(), "15/09/24", "Troca de óleo.");

//        cliente.cancelarServicoAgendado(2);
    }
}
