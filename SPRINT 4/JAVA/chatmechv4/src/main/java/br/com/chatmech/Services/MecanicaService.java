package br.com.chatmech.Services;

import br.com.chatmech.DAO.MecanicaDAO;
import br.com.chatmech.Model.Mecanica;

import java.sql.SQLException;
import java.util.List;

public class MecanicaService {

    MecanicaDAO mecanicaDAO = new MecanicaDAO();

    public List<Mecanica> buscarTodos() throws SQLException {
        return mecanicaDAO.buscaTodos();
    }
}
