
package ecoscan.dao;

import ecoscan.db.ConexionSQL;
import ecoscan.model.DiaSemana;
import ecoscan.model.Horario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emont
 */
public class HorarioDAO {
    
    // dar de alta horario de la rp
    public void addHoursRP(Horario horario) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "INSERT INTO horario (dia_semana, hora_apertura, hora_cierre, id_recicladora) " +
                     "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        // Convertir enum a String para la BD
        ps.setString(1, horario.getDiaSemana().name());
        ps.setTime(2, Time.valueOf(horario.getHoraApertura()));
        ps.setTime(3, Time.valueOf(horario.getHoraCierre()));
        ps.setInt(4, horario.getIdRecicladora());
        
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            horario.setIdHorario(rs.getInt(1));
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
    }
    
    // traer los horarios de la rp por día de la semana
    public List<Horario> getHoursByRP(Integer idRecicladora) throws Exception {
        List<Horario> horarios = new ArrayList<>();
        Connection conn = ConexionSQL.open();
        
        // ordenar según el orden del enum: LUNES(0), MARTES(1), etc.
        String sql = "SELECT id_horario, dia_semana, hora_apertura, hora_cierre, id_recicladora " +
                     "FROM horario WHERE id_recicladora = ? " +
                     "ORDER BY FIELD(dia_semana, 'LUNES', 'MARTES', 'MIERCOLES', 'JUEVES', 'VIERNES', 'SABADO', 'DOMINGO')";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, idRecicladora);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Horario h = new Horario();
            h.setIdHorario(rs.getInt("id_horario"));
            // Convertir String de BD a enum
            h.setDiaSemana(DiaSemana.valueOf(rs.getString("dia_semana")));
            h.setHoraApertura(rs.getTime("hora_apertura").toLocalTime());
            h.setHoraCierre(rs.getTime("hora_cierre").toLocalTime());
            h.setIdRecicladora(rs.getInt("id_recicladora"));
            horarios.add(h);
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
        
        return horarios;
    }
    
    // actualizar los horarios
    public void updateHours(Horario horario) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "UPDATE horario SET dia_semana = ?, hora_apertura = ?, hora_cierre = ? " +
                     "WHERE id_horario = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setString(1, horario.getDiaSemana().name());  // enum → String
        ps.setTime(2, Time.valueOf(horario.getHoraApertura()));
        ps.setTime(3, Time.valueOf(horario.getHoraCierre()));
        ps.setInt(4, horario.getIdHorario());
        
        ps.executeUpdate();
        
        ps.close();
        ConexionSQL.close();
    }
    
    // eliminar horario
    public void deleteHours(Integer idHorario) throws Exception {
        Connection conn = ConexionSQL.open();
        
        String sql = "DELETE FROM horario WHERE id_horario = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, idHorario);
        ps.executeUpdate();
        
        ps.close();ConexionSQL.close();;
    }
    
    // verificar si ya existe horario para ese día en esa recicladora
    public boolean existsHours(Integer idRecicladora, DiaSemana diaSemana) throws Exception {
        boolean existe = false;
        Connection conn = ConexionSQL.open();
        
        String sql = "SELECT COUNT(*) as total FROM horario WHERE id_recicladora = ? AND dia_semana = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ps.setInt(1, idRecicladora);
        ps.setString(2, diaSemana.name());  // enum → String
        
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            existe = rs.getInt("total") > 0;
        }
        
        rs.close();
        ps.close();
        ConexionSQL.close();
        
        return existe;
    }
    
}
