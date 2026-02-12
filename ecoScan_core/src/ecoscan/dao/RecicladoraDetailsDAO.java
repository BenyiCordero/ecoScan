package ecoscan.dao;

import ecoscan.db.ConexionSQL;
import ecoscan.model.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emont
 */
public class RecicladoraDetailsDAO {

    // RP = recycling plant (recicladora)
    // dar de alta materiales que acepte la recicladora
    public void addMaterialInRP(Integer idRecicladora, Integer idMaterial) throws Exception {
        // verificar que no sea repetido
        if (existMaterialInRP(idRecicladora, idMaterial)) {
            throw new Exception("Este material ya está registrado en la recicladora");
        }

        Connection conn = ConexionSQL.open();

        String sql = "INSERT INTO recicladora_details (id_recicladora, id_material) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ps.setInt(2, idMaterial);
        ps.executeUpdate();

        ps.close();
        ConexionSQL.close();
    }

    // eliminar material ligado a la recicladora
    public void deleteMaterialInRP(Integer idRecicladora, Integer idMaterial) throws Exception {
        Connection conn = ConexionSQL.open();

        String sql = "DELETE FROM recicladora_details WHERE id_recicladora = ? AND id_material = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ps.setInt(2, idMaterial);
        ps.executeUpdate();

        ps.close();
        ConexionSQL.close();
    }
    
    // comprobar que no sea repetido el materia en la recicladora
    public boolean existMaterialInRP(Integer idRecicladora, Integer idMaterial) throws Exception {
        boolean existe = false;
        Connection conn = ConexionSQL.open();

        String sql = "SELECT COUNT(*) FROM recicladora_details WHERE id_recicladora = ? AND id_material = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ps.setInt(2, idMaterial);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            existe = rs.getInt(1) > 0;
        }

        rs.close();
        ps.close();
        ConexionSQL.close();

        return existe;
    }
    
    // traer todos los materiales aceptados por la recicladora
    public List<Material> getAllMaterialInRP(Integer idRecicladora) throws Exception {
        List<Material> materiales = new ArrayList<>();
        Connection conn = ConexionSQL.open();

        String sql = "SELECT m.* FROM material m "
                + "INNER JOIN recicladora_details rd ON m.id_material = rd.id_material "
                + "WHERE rd.id_recicladora = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Material material = new Material();
            material.setIdMaterial(rs.getInt("id_material"));
            material.setNombreMaterial(rs.getString("nombre_material"));
            materiales.add(material);
        }

        rs.close();
        ps.close();
        ConexionSQL.close();

        return materiales;
    }
    
    // eliminar todos los materiales de una recicladora
    public void deleteAllMaterialInRP(Integer idRecicladora) throws Exception {
        Connection conn = ConexionSQL.open();

        String sql = "DELETE FROM recicladora_details WHERE id_recicladora = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ps.executeUpdate();

        ps.close();
        ConexionSQL.close();
    }

}
