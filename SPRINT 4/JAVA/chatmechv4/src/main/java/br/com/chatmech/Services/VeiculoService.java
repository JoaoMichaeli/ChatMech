package br.com.chatmech.Services;

import br.com.chatmech.DAO.VeiculoDAO;
import br.com.chatmech.Model.Veiculo;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

public class VeiculoService {

    VeiculoDAO vd = new VeiculoDAO();

    public List<Veiculo> buscarTodos() throws SQLException {
        return vd.buscarTodos();
    }

    public boolean deletaVeiculo(String placa) throws SQLException{
        return vd.deletaVeiculo(placa);
    }

    public Veiculo buscaVeiculo(String placa) throws SQLException {
        return vd.buscaVeiculo(placa);
    }

    public Integer editaVeiculo(String placa, Veiculo veiculo) throws SQLException {
        return vd.editaVeiculo(placa, veiculo);
    }
}
