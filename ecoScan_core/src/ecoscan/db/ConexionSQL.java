
package ecoscan.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author emont
 */
public class ConexionSQL {
    
    
    Connection con;
    
    public Connection open() throws Exception{
        
        // ruta de conexion a mysql
        String url = "jdbc:mysql://127.0.0.1:3306/ecoscan?useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf-8";
        String usser = "root";
        String password = "root";
        
        // registar el driver de mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // abrir la conexion con mysql
        con = (Connection) DriverManager.getConnection(url, usser, password);
        
        // devolver la conexion
        return con;
    }
    
    public void close() throws Exception{
        // revisar si hay conexión activa
        if(con != null)
            con.close();
    }
    
}
