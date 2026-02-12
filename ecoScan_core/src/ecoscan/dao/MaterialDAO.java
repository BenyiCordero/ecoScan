
package ecoscan.dao;

import java.sql.Connection;
import ecoscan.db.ConexionSQL;
import ecoscan.model.Material;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emont
 */
public class MaterialDAO {
    
    // dar de alta el material
    public void register(Material material) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "INSERT INTO material (nombre_material) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ps.setString(1, material.getNombreMaterial());
        ps.executeUpdate();
        
        // Obtener el ID generado automáticamente
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            material.setIdMaterial(rs.getInt(1));
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
    }
    
    // traer todos los materiales
    public List<Material> getAll() throws Exception {
        List<Material> materiales = new ArrayList<>();
        
        Connection conn = ConexionSQL.open();
        
        String sql = "SELECT * FROM material";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
            Material material = new Material();
            material.setIdMaterial(rs.getInt("id_material"));
            material.setNombreMaterial(rs.getString("nombre_material"));
            materiales.add(material);
        }
        
        rs.close();
        stmt.close();
        ConexionSQL.close();
        
        return materiales;
    }
    
    // traer un material por su id
    public Material getById(Integer idMaterial) throws Exception {
        Material material = null;
        Connection conn = ConexionSQL.open();
        
        String sql = "SELECT * FROM material WHERE id_material = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idMaterial);
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            material = new Material();
            material.setIdMaterial(rs.getInt("id_material"));
            material.setNombreMaterial(rs.getString("nombre_material"));
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
        
        return material;
    }
    
    // actualizar material
    public void update(Material material) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "UPDATE material SET nombre_material = ? WHERE id_material = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, material.getNombreMaterial());
        ps.setInt(2, material.getIdMaterial());
        ps.executeUpdate();
        
        ps.close();
        ConexionSQL.close();
    }
    
    // eliminar material
    public void delete(Integer idMaterial) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "DELETE FROM material WHERE id_material = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, idMaterial);
        ps.executeUpdate();
        
        ps.close();
        ConexionSQL.close();
    }
}
