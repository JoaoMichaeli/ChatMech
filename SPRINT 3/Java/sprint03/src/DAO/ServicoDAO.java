package DAO;

import Model.Servico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {
    private Connection connection;

    public ServicoDAO() {
        this.connection = ConexaoBD.criarConexao();
    }

    public void inserir(Servico servico) {
        String sql = "INSERT INTO TBL_SERVICOS (id_mecanica, id_cliente, data_conserto, inicio_servico, fim_servico, pecas_trocadas, descricao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, servico.getIdMecanica());
            stmt.setInt(2, servico.getIdCliente());
            stmt.setDate(3, new java.sql.Date(servico.getDataConserto().getTime()));
            stmt.setDate(4, new java.sql.Date(servico.getInicioServico().getTime()));
            stmt.setDate(5, new java.sql.Date(servico.getFimServico().getTime()));
            stmt.setString(6, servico.getPecasTrocadas());
            stmt.setString(7, servico.getDescricao());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Servico buscarPorId(int idServico) {
        String sql = "SELECT * FROM TBL_SERVICOS WHERE id_servico = ?";
        Servico servico = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idServico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                servico = new Servico(
                        rs.getInt("id_mecanica"),
                        rs.getInt("id_cliente"),
                        rs.getDate("data_conserto"),
                        rs.getDate("inicio_servico"),
                        rs.getDate("fim_servico"),
                        rs.getString("pecas_trocadas"),
                        rs.getString("descricao")
                );
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servico;
    }


    public void removerServico(int idServico){
        System.out.println("Removendo serviço...");

        String sql = "DELETE FROM TBL_SERVICOS WHERE id_servico = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1,idServico);
            stmt.executeUpdate();
            System.out.println("Serviço deletado com sucesso!");
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }

    }


    public void fechaConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}