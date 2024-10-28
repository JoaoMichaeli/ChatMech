package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    public static Connection con=null;// variável de classe conexão
    static String server="oracle.fiap.com.br";
    static String port="1521";
    static String sid="ORCL";
    static String url = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;
//    static String user="RM555678";
    static String user="RM554456";
//    static String passwd="310302";
    static String passwd="080995";
    public static Connection criarConexao()  { //método que retorna a conexão
        if (con==null){
            try {
           Class.forName("oracle.jdbc.driver.OracleDriver");//verifica driver
           con = DriverManager.getConnection(url,user,passwd);//cria uma conexão
            }
            catch (SQLException | ClassNotFoundException e){
                throw new RuntimeException(e.getMessage());
            }
   }
    return con;//retorno da conexão criada.
    }
}
