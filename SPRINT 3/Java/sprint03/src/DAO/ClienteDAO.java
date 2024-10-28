package DAO;

import Model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements FechaConexao {
    private static Connection connection;

    public ClienteDAO() {
        this.connection = ConexaoBD.criarConexao();
    }

    public  void inserir(Cliente cliente) {
        String sqlCadastro = "INSERT INTO TBL_CADASTRO (usuario, email, telefone, senha, nome, cpf, rua, numero, complemento, bairro, cep, estado, cidade, tipo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlCliente = "INSERT INTO TBL_CLIENTES (id_cadastro) VALUES (?)";

        try {
            // Inserir na tabela TBL_CADASTRO
            try (PreparedStatement stmtCadastro = connection.prepareStatement(sqlCadastro, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmtCadastro.setString(1, cliente.getUsuario());
                stmtCadastro.setString(2, cliente.getEmail());
                stmtCadastro.setString(3, cliente.getTelefone());
                stmtCadastro.setString(4, cliente.getSenha());
                stmtCadastro.setString(5, cliente.getNome());
                stmtCadastro.setString(6, cliente.getCpf());
                stmtCadastro.setString(7, cliente.getRua());
                stmtCadastro.setInt(8, cliente.getNumero());
                stmtCadastro.setString(9, cliente.getComplemento());
                stmtCadastro.setString(10, cliente.getBairro());
                stmtCadastro.setString(11, cliente.getCep());
                stmtCadastro.setString(12, cliente.getEstado());
                stmtCadastro.setString(13, cliente.getCidade());
                stmtCadastro.setString(14, cliente.getTipo());

                stmtCadastro.executeUpdate();

                // Obter o ID gerado para TBL_CADASTRO
                ResultSet rs = stmtCadastro.executeQuery("SELECT * FROM TBL_CADASTRO WHERE cpf = " + cliente.getCpf());

                if (rs.next()) {
                    int idCadastro = rs.getInt("id_cadastro");
                    cliente.setIdCadastro(idCadastro);
                    rs.close();
                    // Inserir na tabela TBL_CLIENTES com o id_cadastro gerado
                }
            }
            try (PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente)) {
                stmtCliente.setInt(1, cliente.getIdCadastro());
                stmtCliente.executeUpdate();

                ResultSet rsCliente = stmtCliente.executeQuery("SELECT * FROM TBL_CLIENTES WHERE id_cadastro = " + cliente.getIdCadastro());
                if (rsCliente.next()) {
                    int idCliente = rsCliente.getInt("id_cliente");
                    cliente.setIdCliente(idCliente);
                }
                rsCliente.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Cliente buscarPorCPF(String cpf) {
        String sqlUsuario = "SELECT * FROM TBL_CADASTRO WHERE cpf = ?";
        String sqlCliente = "SELECT * FROM TBL_CLIENTES WHERE id_cadastro = ?";
        Cliente cliente = null;

        System.out.println("Buscando cliente...");

        try (PreparedStatement stmtUsuario = connection.prepareStatement(sqlUsuario)) {
            stmtUsuario.setString(1, cpf);
            ResultSet rsUsuario = stmtUsuario.executeQuery();
            if (rsUsuario.next()) {
                cliente = new Cliente(
                        rsUsuario.getString("usuario"),
                        rsUsuario.getString("email"),
                        rsUsuario.getString("telefone"),
                        rsUsuario.getString("senha"),
                        rsUsuario.getString("nome"),
                        rsUsuario.getString("cpf"),
                        rsUsuario.getString("rua"),
                        rsUsuario.getInt("numero"),
                        rsUsuario.getString("complemento"),
                        rsUsuario.getString("bairro"),
                        rsUsuario.getString("cep"),
                        rsUsuario.getString("estado"),
                        rsUsuario.getString("cidade"),
                        rsUsuario.getString("tipo")
                );
                cliente.setIdCadastro(rsUsuario.getInt("id_cadastro"));
            }
            rsUsuario.close();

            // Segunda consulta para TBL_CLIENTES
            try (PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente)) {
                stmtCliente.setString(1, cpf);
                ResultSet rsCliente = stmtCliente.executeQuery();
                if (rsCliente.next()) {
                    cliente.setIdCliente(rsCliente.getInt("id_cliente"));
                }
                rsCliente.close();
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

            System.out.println("Cliente encontrado com sucesso!");

            return cliente;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int buscaIdCliente(String cpf){
        String sqlCadastro = "SELECT id_cadastro FROM TBL_CADASTRO WHERE cpf = ?";
        String sqlCliente = "SELECT id_cliente FROM TBL_CLIENTES WHERE id_cadastro = ?";
        int idCadastro = 0;
        int idCliente = 0;

        try (PreparedStatement stmtCadastro = connection.prepareStatement(sqlCadastro)){
          stmtCadastro.setString(1, cpf);
          ResultSet rsCadastro = stmtCadastro.executeQuery();
          if (rsCadastro.next()){
              idCadastro = rsCadastro.getInt("id_cadastro");

              try(PreparedStatement stmtCliente = connection.prepareStatement(sqlCliente)){
                  stmtCliente.setInt(1, idCadastro);

                  ResultSet rsCliente = stmtCliente.executeQuery();
                  if (rsCliente.next()){
                      idCliente = rsCliente.getInt("id_cliente");
                      return idCliente;
                  }
              }
          }


        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
      return idCliente;
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
