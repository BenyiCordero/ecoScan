package ecoscan.dao;

import ecoscan.db.ConexionSQL;
import ecoscan.model.Recicladora;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emont
 */
public class RecicladoraDAO {

    // dar de alta recicladora
    public void registerRP(Recicladora recicladora) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "INSERT INTO recicladora (nombre_recicladora, direccion, latitud, longitud, activa) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, recicladora.getNombreRecicladora());
        ps.setString(2, recicladora.getDireccion());
        ps.setBigDecimal(3, BigDecimal.valueOf(recicladora.getLatitud()));
        ps.setBigDecimal(4, BigDecimal.valueOf(recicladora.getLongitud()));
        ps.setBoolean(5, true);

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            recicladora.setIdRecicladora(rs.getInt(1));
        }

        rs.close();
        ps.close();
        ConexionSQL.close();
    }

    // traer todas las recicldoras
    public List<Recicladora> getAllRP() throws Exception {
        List<Recicladora> recicladoras = new ArrayList<>();
        Connection conn = ConexionSQL.open();

        String sql = "SELECT id_recicladora, nombre_recicladora, direccion, latitud, longitud, activa FROM recicladora";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            recicladoras.add(mapearRecicladora(rs));
        }

        rs.close();
        stmt.close();
        ConexionSQL.close();

        return recicladoras;
    }

    // traer la recicladora por su id
    public Recicladora getByIdRP(Integer idRecicladora) throws Exception {
        Recicladora recicladora = null;
        Connection conn = ConexionSQL.open();

        String sql = "SELECT id_recicladora, nombre_recicladora, direccion, latitud, longitud, activa "
                + "FROM recicladora WHERE id_recicladora = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            recicladora = mapearRecicladora(rs);
        }

        rs.close();
        ps.close();
        ConexionSQL.close();

        return recicladora;
    }

    // traer a todas las recicladoras activas o no
    public List<Recicladora> getByEstadoRP(Boolean activa) throws Exception {
        List<Recicladora> recicladoras = new ArrayList<>();
        Connection conn = ConexionSQL.open();

        String sql = "SELECT id_recicladora, nombre_recicladora, direccion, latitud, longitud, activa "
                + "FROM recicladora WHERE activa = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setBoolean(1, activa);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            recicladoras.add(mapearRecicladora(rs));
        }

        rs.close();
        ps.close();
        ConexionSQL.close();

        return recicladoras;
    }

    // actualizar a recicladora, sus datos
    public void updateRP(Recicladora recicladora) throws Exception {
        Connection conn = ConexionSQL.open();

        String sql = "UPDATE recicladora SET nombre_recicladora = ?, direccion = ?, "
                + "latitud = ?, longitud = ? WHERE id_recicladora = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, recicladora.getNombreRecicladora());
        ps.setString(2, recicladora.getDireccion());
        ps.setBigDecimal(3, BigDecimal.valueOf(recicladora.getLatitud()));
        ps.setBigDecimal(4, BigDecimal.valueOf(recicladora.getLongitud()));
        ps.setInt(5, recicladora.getIdRecicladora());

        ps.executeUpdate();

        ps.close();
        ConexionSQL.close();
    }

    // eliminar recicladora, lógico
    public void deleteRP(Integer idRecicladora) throws Exception {
        Connection conn = ConexionSQL.open();

        String sql = "UPDATE recicladora SET activa = false WHERE id_recicladora = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ps.executeUpdate();

        ps.close();
        ConexionSQL.close();
    }

    // reactivar recicladora
    public void reactivateRP(Integer idRecicladora) throws Exception {
        Connection conn = ConexionSQL.open();

        String sql = "UPDATE recicladora SET activa = true WHERE id_recicladora = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, idRecicladora);
        ps.executeUpdate();

        ps.close();
        ConexionSQL.close();
    }

    // mapear ResultSet a objeto Recicladora para no repetir código
    private Recicladora mapearRecicladora(ResultSet rs) throws Exception {
        Recicladora r = new Recicladora();
        r.setIdRecicladora(rs.getInt("id_recicladora"));
        r.setNombreRecicladora(rs.getString("nombre_recicladora"));
        r.setDireccion(rs.getString("direccion"));
        r.setLatitud(rs.getBigDecimal("latitud").doubleValue());
        r.setLongitud(rs.getBigDecimal("longitud").doubleValue());
        r.setActiva(rs.getBoolean("activa"));
        return r;
    }

}
