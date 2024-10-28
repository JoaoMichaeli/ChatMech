package DAO;

import Model.Cliente;
import Model.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements FechaConexao {
    private Connection connection;

    public FuncionarioDAO() {
        this.connection = ConexaoBD.criarConexao();
    }

    public void inserir(Funcionario funcionario) {
        String sqlCadastro = "INSERT INTO TBL_CADASTRO (usuario, email, telefone, senha, nome, cpf, rua, numero, complemento, bairro, cep, estado, cidade, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlFuncionario = "INSERT INTO TBL_FUNCIONARIOS (id_cadastro, cargo) VALUES (?, ?)";

        try {
            // Inserir na tabela TBL_CADASTRO
            try (PreparedStatement stmtCadastro = connection.prepareStatement(sqlCadastro, Statement.RETURN_GENERATED_KEYS)) {
                stmtCadastro.setString(1, funcionario.getUsuario());
                stmtCadastro.setString(2, funcionario.getEmail());
                stmtCadastro.setString(3, funcionario.getTelefone());
                stmtCadastro.setString(4, funcionario.getSenha());
                stmtCadastro.setString(5, funcionario.getNome());
                stmtCadastro.setString(6, funcionario.getCpf());
                stmtCadastro.setString(7, funcionario.getRua());
                stmtCadastro.setInt(8, funcionario.getNumero());
                stmtCadastro.setString(9, funcionario.getComplemento());
                stmtCadastro.setString(10, funcionario.getBairro());
                stmtCadastro.setString(11, funcionario.getCep());
                stmtCadastro.setString(12, funcionario.getEstado());
                stmtCadastro.setString(13, funcionario.getCidade());
                stmtCadastro.setString(14, funcionario.getTipo());

                stmtCadastro.executeUpdate();

                // Obter o ID gerado para TBL_CADASTRO

                ResultSet rs = stmtCadastro.executeQuery("SELECT * FROM TBL_CADASTRO WHERE cpf = " + funcionario.getCpf());
                if (rs.next()) {
                    int idCadastro = rs.getInt("id_cadastro");
                    funcionario.setIdCadastro(idCadastro);

                    // Inserir na tabela TBL_CLIENTES com o id_cadastro gerado
                    try (PreparedStatement stmtCliente = connection.prepareStatement(sqlFuncionario)) {
                        stmtCliente.setInt(1, idCadastro);
                        stmtCliente.setString(2, funcionario.getCargo());
                        stmtCliente.executeUpdate();
                    }
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Funcionario buscarPorId(int idUsuario) {
        String sqlUsuario = "SELECT * FROM TBL_CADASTRO WHERE id_cadastro = ?";
        String sqlFuncionario = "SELECT * FROM TBL_FUNCIONARIO WHERE id_cadastro = ?";
        Funcionario funcionario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sqlUsuario)) {
            stmt.setInt(1, idUsuario);
//            ResultSet rs = stmt.executeQuery();
            ResultSet rsFuncionario = stmt.executeQuery();
            if (rsFuncionario.next()) {
                funcionario = new Funcionario(
                        rsFuncionario.getString("usuario"),
                        rsFuncionario.getString("email"),
                        rsFuncionario.getString("telefone"),
                        rsFuncionario.getString("senha"),
                        rsFuncionario.getString("nome"),
                        rsFuncionario.getString("cpf"),
                        rsFuncionario.getString("rua"),
                        rsFuncionario.getInt("numero"),
                        rsFuncionario.getString("complemento"),
                        rsFuncionario.getString("bairro"),
                        rsFuncionario.getString("cep"),
                        rsFuncionario.getString("estado"),
                        rsFuncionario.getString("cidade"),
                        rsFuncionario.getString("tipo"),
                        rsFuncionario.getInt("id_cadastro"),
                        rsFuncionario.getString("cargo")
                );
            }
            rsFuncionario.close();

            // Segunda consulta para TBL_CLIENTES
            try (PreparedStatement statement = connection.prepareStatement(sqlFuncionario)) {
                statement.setInt(1, idUsuario);
                ResultSet rsCliente = statement.executeQuery();
                if (rsCliente.next()) {
                    funcionario.setIdFuncionario(rsCliente.getInt(2));
                }
                rsCliente.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

            return funcionario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void fechaConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
