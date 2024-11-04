package br.com.chatmech.DAO;

import br.com.chatmech.ConnectionFactory.CriaConexaoBD;
import br.com.chatmech.Model.Cadastro;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
    private Connection conn;

    //    private Endereco endereco;
    public CadastroDAO() {
        this.conn = CriaConexaoBD.pegarConexao();
    }

//    private final String SQL_SELECT_ALL = "SELECT * FROM tbl_cadastros ORDER BY tbl_cadastros.tipo";
    private final String SQL_SELECT_TIPO_BY_ID = "SELECT tipo FROM tbl_cadastros WHERE id_cadastro = ?";

    private final String SQL_INSERT_CADASTRO = "INSERT INTO tbl_cadastros  (email, senha, nome, cep) VALUES (?, ?, ?, ?)";
    private final String SQL_DELETE_BY_ID = "UPDATE tbl_cadastros SET situacao = 0 WHERE id_cadastro = ?";
    private final String SQL_LOGIN = "SELECT * FROM tbl_cadastros WHERE email = ?";
    private final String SQL_ALTERAR_SENHA = "UPDATE tbl_cadastros SET senha = ? WHERE email = ?";

//    public List<Cadastro> buscarTodos() throws SQLException {
//        List<Cadastro> listaCadastros = new ArrayList<>();
//
//        try (Statement st = conn.createStatement()) {
//            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
//            while (rs.next()) {
//                Cadastro cadastro = new Cadastro();
//                cadastro.setNome(rs.getString("nome"));
//                cadastro.setEmail(rs.getString("email"));
//                cadastro.setCep(rs.getString("cep"));
//                listaCadastros.add(cadastro);
//            }
//
//        }
//
//        return listaCadastros;
//    }


    public void inserirUsuario(Cadastro cadastro) throws SQLException {
        System.out.println("Cadastrando usuário...");
        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT_CADASTRO, new String[]{"id_cadastro"})) {
            ps.setString(1, cadastro.getEmail());
            ps.setString(2, cadastro.getSenha());
            ps.setString(3, cadastro.getNome());
            ps.setString(4, cadastro.getCep());
            ps.executeUpdate();
        }
    }

    public void desativarPorId(Integer idCadastro) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_SELECT_TIPO_BY_ID)) {
            ps.setInt(1, idCadastro);
            ResultSet tipo = ps.executeQuery();
            if (tipo.next()) {
                try(PreparedStatement ps2 = conn.prepareStatement(SQL_DELETE_BY_ID)){
                    ps2.setInt(1, tipo.getInt("id_cadastro"));
                }
            } else {
                throw new RuntimeException("Nenhum usuário com este id!");
            }
        }
    }


    public Cadastro autorizaLogin(String email, String senha) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_LOGIN)) {
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            String senhaBd = "0";
            Integer idCadastro;
            Cadastro c = new Cadastro();
            while (result.next()) {
                c.setSenha(result.getString("senha"));
                c.setIdCadastro(result.getInt("id_cadastro"));
                c.setEmail(result.getString("email"));
                c.setCep(result.getString("cep"));
                c.setNome(result.getString("nome"));
            }
            if (senha.equals(c.getSenha())){
                return c;
            }
        }
        return null;
    }

    public Integer alterarSenha(String email, String senha) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(SQL_ALTERAR_SENHA)){
            ps.setString(1, senha);
            ps.setString(2, email);
            return ps.executeUpdate();
        }
    }
}

