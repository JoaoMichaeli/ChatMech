package br.com.chatmech.DAO;

import br.com.chatmech.ConnectionFactory.CriaConexaoBD;
import br.com.chatmech.Model.Mecanica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MecanicaDAO {

    Connection conn;
    public MecanicaDAO(){
        this.conn = CriaConexaoBD.pegarConexao();
    }

    private final String SQL_GET_ALL = "SELECT * FROM tbl_mecanicas";

    public List<Mecanica> buscaTodos() throws SQLException {
        List<Mecanica> lMecanica = new ArrayList<>();
        Mecanica m = null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL_GET_ALL);
        while(rs.next()){
            m = new Mecanica(
                    rs.getInt("id_mecanica"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getString("imagem")
            );
            lMecanica.add(m);
        }
        return lMecanica;
    }



}
