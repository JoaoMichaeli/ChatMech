package DAO;

import Model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements FechaConexao {
    private Connection connection;

    public VeiculoDAO() {
        this.connection = ConexaoBD.criarConexao();
    }

    public void inserir(Veiculo veiculo) {
        String sqlModelo = "INSERT INTO TBL_MODELO (modelo, marca, ano, quilometragem) VALUES (?, ?, ?, ?)";
        String sqlVeiculo = "INSERT INTO TBL_VEICULOS (placa, id_cliente, modelo, dono) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmtModelo = connection.prepareStatement(sqlModelo)) {
            stmtModelo.setString(1, veiculo.getModelo());
            stmtModelo.setString(2, veiculo.getMarca());
            stmtModelo.setInt(3, veiculo.getAno());
            stmtModelo.setInt(4, veiculo.getQuilometragem());
            stmtModelo.executeUpdate();
            stmtModelo.close();
            try (PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo)) {
                stmtVeiculo.setString(1, veiculo.getPlaca());
                stmtVeiculo.setInt(2, veiculo.getIdCliente());
                stmtVeiculo.setString(3, veiculo.getModelo());
                stmtVeiculo.setString(4, veiculo.getDono());
                stmtVeiculo.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Veiculo buscarPorId(String placa) {
        String sql = "SELECT * FROM TBL_VEICULOS WHERE placa = ?";
        Veiculo veiculo = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery("SELECT placa, modelo, dono, id_cliente FROM TBL_VEICULOS; SELECT marca, ano, quilometragem FROM TBL_MODELO");
            if (rs.next()) {
                veiculo = new Veiculo(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getString("dono"),
                        rs.getInt("id_cliente"),
                        rs.getInt("quilometragem"),
                        rs.getString("marca"),
                        rs.getInt("ano")
                );
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return veiculo;
    }


    public void editarVeiculo(String placaAtual, String placaNova, String modeloAtual, String modeloNovo, String dono, String marca, int ano, int quilometragem, int idCliente) {

        String sqlModelo = "UPDATE TBL_MODELO SET modelo = ?, marca = ?, ano = ?, quilometragem = ? WHERE modelo = ?";
        String sqlVeiculo = "INSERT INTO TBL_VEICULOS (placa, modelo, dono, id_cliente) VALUES (?, ?, ?, ?)";
        String sqlDelete = "DELETE FROM TBL_VEICULOS WHERE modelo = ?";

        // Deletando veículo do BD para poder atualizar o modelo
        try (PreparedStatement stmtDelete = connection.prepareStatement(sqlDelete)) {
            System.out.println("Deletando veículo atual da tabela...");
            stmtDelete.setString(1, modeloAtual);
            stmtDelete.executeUpdate();

            System.out.println("Atualizando Veículo e Modelo...");

            try (PreparedStatement stmtModelo = connection.prepareStatement(sqlModelo)) {
                stmtModelo.setString(1, modeloNovo);
                stmtModelo.setString(2, marca);
                stmtModelo.setInt(3, ano);
                stmtModelo.setInt(4, quilometragem);
                stmtModelo.setString(5, modeloAtual);
                stmtModelo.executeUpdate();
                System.out.println("Modelo atualizado...");

                try (PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo)) {
                    stmtVeiculo.setString(1, placaNova);
                    stmtVeiculo.setString(2, modeloNovo);
                    stmtVeiculo.setString(3, dono);
                    stmtVeiculo.setInt(4, idCliente);
                    stmtVeiculo.executeUpdate();
                    System.out.println("Veículo atualizado....");
                }
                System.out.println("Veículo e Modelo atualizados com sucesso!");
            }
        } catch (SQLException | RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void excluirVeiculo(String placa) {
        String sqlVeiculo = "DELETE FROM TBL_VEICULOS WHERE placa = ?";
        String sqlBuscaModelo = "SELECT modelo FROM TBL_VEICULOS WHERE placa = ?";
        String sqlModelo = "DELETE FROM TBL_MODELO WHERE modelo = ?";
        String modelo = null;

        System.out.println("Excluindo veículo e modelo...");

        try (PreparedStatement stmtBusca = connection.prepareStatement(sqlBuscaModelo)) {
            stmtBusca.setString(1, placa);

            ResultSet rsBusca = stmtBusca.executeQuery();
            if (rsBusca.next()) {
                modelo = rsBusca.getString("modelo");
                System.out.println("Modelo encontrado...");
            }

            try (PreparedStatement stmtVeiculo = connection.prepareStatement(sqlVeiculo)) {
                stmtVeiculo.setString(1, placa);
                stmtVeiculo.executeUpdate();

                System.out.println("Veiculo deletado...");

                try (PreparedStatement stmtModelo = connection.prepareStatement(sqlModelo)){
                    stmtModelo.setString(1, modelo);
                    stmtModelo.executeUpdate();

                    System.out.println("Modelo deletado....");
                }
            }

            System.out.println("Exclusão realizada com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

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
