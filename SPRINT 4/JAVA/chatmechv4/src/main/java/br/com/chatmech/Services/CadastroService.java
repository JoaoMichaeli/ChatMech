package br.com.chatmech.Services;

import br.com.chatmech.DAO.CadastroDAO;
import br.com.chatmech.Model.Cadastro;

import java.sql.SQLException;
import java.util.List;

public class CadastroService {
    CadastroDAO cadastroDAO = new CadastroDAO();


//    public List<Cadastro> buscarTodos() throws SQLException {
//
//        return cadastroDAO.buscarTodos();
//    }

    public void cadastrarUsuario(Cadastro cadastro) throws SQLException {

        cadastroDAO.inserirUsuario(cadastro);
    }

    public void desativarConta(Integer idCadastro) throws SQLException {

        cadastroDAO.desativarPorId(idCadastro);
    }

    public Cadastro autorizaLogin(String email, String senha) throws SQLException {
        return cadastroDAO.autorizaLogin(email, senha);
    }

    public Integer alterarSenha(String email, String senha) throws SQLException {
        return cadastroDAO.alterarSenha(email, senha);
    }
}
