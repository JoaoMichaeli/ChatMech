package DAO;

import Model.Mecanica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MecanicaDAO implements FechaConexao {
    private Connection connection;

    public MecanicaDAO() {
        this.connection = ConexaoBD.criarConexao();
    }

    public void inserir(Mecanica mecanica) {
        String sql = "INSERT INTO TBL_MECANICAS (telefone, email, nome, rua, numero, bairro, cep, estado, cidade) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mecanica.getTelefone());
            stmt.setString(2, mecanica.getEmail());
            stmt.setString(3, mecanica.getNome());
            stmt.setString(4, mecanica.getRua());
            stmt.setInt(5, mecanica.getNumero());
            stmt.setString(6, mecanica.getBairro());
            stmt.setString(7, mecanica.getCep());
            stmt.setString(8, mecanica.getEstado());
            stmt.setString(9, mecanica.getCidade());

            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            ResultSet rs = stmt.executeQuery("SELECT id_mecanica FROM TBL_MECANICAS");
            if (rs.next()) {
                mecanica.setIdMecanica(rs.getInt(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Mecanica buscarPorId(int idMecanica) {
        String sql = "SELECT * FROM TBL_MECANICAS WHERE id_mecanica = ?";
        Mecanica mecanica = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMecanica);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                mecanica = new Mecanica(
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("estado"),
                        rs.getString("cidade")
                );
                mecanica.setIdMecanica(rs.getInt("id_mecanica"));
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return mecanica;
    }

    @Override
    public void fechaConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
