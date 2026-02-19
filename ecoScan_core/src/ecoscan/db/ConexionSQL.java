
package ecoscan.db;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author emont
 */
public class ConexionSQL {
    
    
    private static Connection con;
    
    public static Connection open() throws Exception{
        
        // ruta de conexion a mysql
        String url = "jdbc:mysql://127.0.0.1:3306/ecoscan?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8";
        String user = "ecoscan";
        String password = "1234";
        
        // registar el driver de mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // abrir la conexion con mysql
        con = (Connection) DriverManager.getConnection(url, user, password);
        
        // devolver la conexion
        return con;
    }
    
    public static void close() throws Exception{
        // revisar si hay conexión activa
        if(con != null)
            con.close();
    }
    
}