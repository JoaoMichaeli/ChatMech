package br.com.chatmech.DAO;

import br.com.chatmech.ConnectionFactory.CriaConexaoBD;
import br.com.chatmech.Model.Veiculo;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    Connection conn;

    public VeiculoDAO() {
        conn = CriaConexaoBD.pegarConexao();
    }

    private final String SQL_GET_ALL = "SELECT * FROM tbl_veiculos";
    private final String SQL_DELETE_WHERE = "DELETE FROM tbl_veiculos WHERE placa = ?";

    private final String SQL_SELECT_WHERE = "SELECT * FROM tbl_veiculos WHERE placa = ?";

    private final String SQL_UPDATE_WHERE = "UPDATE tbl_veiculos SET placa = ?, modelo = ?, fabricante = ?, ano = ?, quilometragem_atual = ?, url_imagem = ? WHERE placa = ?";

    public List<Veiculo> buscarTodos() throws SQLException {
        List<Veiculo> lVeiculos = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL_GET_ALL);
        while (rs.next()) {
            Veiculo v = new Veiculo(
                    rs.getString("placa"),
                    rs.getString("url_imagem"),
                    rs.getString("modelo"),
                    rs.getString("fabricante"),
                    rs.getInt("ano"),
                    rs.getDouble("quilometragem_atual")
            );
            lVeiculos.add(v);
        }
        return lVeiculos;
    }


    public boolean deletaVeiculo(String placa) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement(SQL_DELETE_WHERE)){
            ps.setString(1, placa);
            Integer resultado = ps.executeUpdate();
            return resultado.equals(1);
        }
    }

    public Veiculo buscaVeiculo(String placa) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_SELECT_WHERE)){
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            Veiculo v = null;
            while (rs.next()){
                v = new Veiculo(rs.getString("placa"),
                        rs.getString("url_imagem"),
                        rs.getString("modelo"),
                        rs.getString("fabricante"),
                        rs.getInt("ano"),
                        rs.getDouble("quilometragem_atual")
                );
            }
            return v;
        }
    }

    public Integer editaVeiculo(String placa, Veiculo veiculo) throws SQLException{
        try(PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_WHERE)){
            ps.setString(1, veiculo.getPlaca());
            ps.setString(2, veiculo.getModelo());
            ps.setString(3, veiculo.getFabricante());
            ps.setInt(4, veiculo.getAno());
            ps.setDouble(5, veiculo.getQuilometragemAtual());
            ps.setString(6, veiculo.getImagem());
            ps.setString(7, placa);
            ps.executeUpdate();
            return (ps.executeUpdate());
        }
    }

}


