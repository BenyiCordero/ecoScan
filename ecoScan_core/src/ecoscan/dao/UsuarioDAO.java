package ecoscan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import ecoscan.db.ConexionSQL;
import ecoscan.model.Usuario;
import java.sql.ResultSet;
import ecoscan.model.Rol;
import java.util.ArrayList;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author Benyi Uriel
 */
public class UsuarioDAO {
        
    public void register (Usuario usuario)throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "INSERT INTO usuario "
                + "(nombre_usuario, primer_apellido, segundo_apellido, rol, email, password) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        
        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getPrimerApellido());
        ps.setString(3, usuario.getSegundoApellido());
        ps.setString(4, usuario.getRol().name());
        ps.setString(5, usuario.getEmail());
        ps.setString(6, usuario.getPassword());
        
        ps.executeUpdate();
        
        ps.close();
        ConexionSQL.close();
    }
    
    public void updateUsuario (Usuario usuario) throws Exception{
        Connection conn = ConexionSQL.open();
        
        String sql = "UPDATE usuario SET "
                + "nombre_usuario = ? ,"
                + "primer_apellido = ? ,"
                + "segundo_apellido = ? "
                + "WHERE id_usuario = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, usuario.getNombre());        
        ps.setString(2, usuario.getPrimerApellido());
        ps.setString(3, usuario.getSegundoApellido());
        ps.setInt(4, usuario.getIdUsuario());
        
        int rows = ps.executeUpdate();
        
        if(rows == 0){
            throw new Exception("No se encontro el usuario para actualizar");
        }
        
        ps.close();
        ConexionSQL.close();
        
    }
    
    public Usuario findByEmail(String email) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "SELECT * FROM usuario WHERE email = ?";
        
        PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
        
        ps.setString(1, email);
        
        ResultSet rs = ps.executeQuery();
        
        Usuario usuario = null;
        
        if(rs.next()){
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setEmail(rs.getString("email"));
            usuario.setNombre(rs.getString("nombre_usuario"));
            usuario.setPrimerApellido(rs.getString("primer_apellido"));
            usuario.setSegundoApellido(rs.getString("segundo_apellido"));
            usuario.setPassword(rs.getString("password"));
            usuario.setRol(Rol.valueOf(rs.getString("rol")));
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
        
        return usuario;
    }
    
    public List<Usuario> getAll() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        
        Connection conn = ConexionSQL.open();
        
        String sql = "SELECT * FROM usuario";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre_usuario"));
            usuario.setPrimerApellido(rs.getString("primer_apellido"));
            usuario.setSegundoApellido(rs.getString("segundo_apellido"));
            usuario.setRol(Rol.valueOf(rs.getString("rol")));
            usuario.setEmail(rs.getString("email"));
            usuarios.add(usuario);
        }
        
        rs.close();
        stmt.close();
        ConexionSQL.close();
        
        return usuarios;
    }
    
    public Usuario getById(Integer idUsuario) throws Exception {
        Usuario usuario = null;
        Connection conn = ConexionSQL.open();
        
        String sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idUsuario);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre_usuario"));
            usuario.setPrimerApellido(rs.getString("primer_apellido"));
            usuario.setSegundoApellido(rs.getString("segundo_apellido"));
            usuario.setRol(Rol.valueOf(rs.getString("rol")));
            usuario.setEmail(rs.getString("email"));
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
        
        return usuario;
    }
    
    public void delete(Integer idUsuario) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, idUsuario);
        ps.executeUpdate();
        
        ps.close();
        ConexionSQL.close();
    }
    
}