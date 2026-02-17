package ecoscan.controller;

import ecoscan.dao.HorarioDAO;
import ecoscan.dao.RecicladoraDAO;
import ecoscan.dao.RecicladoraDetailsDAO;
import ecoscan.model.DiaSemana;
import ecoscan.model.Horario;
import ecoscan.model.Material;
import ecoscan.model.Recicladora;
import java.util.List;

/**
 *
 * @author emont
 */
public class RecicladoraController {
    
    private RecicladoraDAO recicladoraDAO;
    private RecicladoraDetailsDAO detailsDAO;
    private HorarioDAO horarioDAO;
    
    public RecicladoraController() {
        this.recicladoraDAO = new RecicladoraDAO();
        this.detailsDAO = new RecicladoraDetailsDAO();
        this.horarioDAO = new HorarioDAO();
    }
    
    // dar de alta la recicladora
    public String registrarRP(String nombre, String direccion, Double latitud, Double longitud) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new Exception("La dirección es obligatoria");
        }
        if (latitud == null || longitud == null) {
            throw new Exception("Las coordenadas son obligatorias");
        }
        
        Recicladora recicladora = new Recicladora(nombre, direccion, latitud, longitud);
        recicladoraDAO.registerRP(recicladora);
        
        return "Recicladora registrada correctamente con ID: " + recicladora.getIdRecicladora();
    }
    
    // dar de alta materiales que acepte la recicladora
    public String agregarMaterial(Integer idRecicladora, Integer idMaterial) throws Exception {
        Recicladora r = recicladoraDAO.getByIdRP(idRecicladora);
        if (r == null) {
            throw new Exception("Recicladora no encontrada");
        }
        if (!r.getActiva()) {
            throw new Exception("No se pueden agregar materiales a una recicladora inactiva");
        }
        
        detailsDAO.addMaterialInRP(idRecicladora, idMaterial);
        return "Material agregado correctamente a la recicladora";
    }
    
    
    // dar de alta horario de la recicladora, usando enum diasemana
    public String agregarHorario(Integer idRecicladora, String diaSemanaStr, 
                                  String horaApertura, String horaCierre) throws Exception {
        // Validar que existe la recicladora
        Recicladora r = recicladoraDAO.getByIdRP(idRecicladora);
        if (r == null) {
            throw new Exception("Recicladora no encontrada");
        }
        
        // Convertir String a enum
        DiaSemana diaSemana;
        try {
            diaSemana = DiaSemana.valueOf(diaSemanaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Día de semana inválido. Use: LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO");
        }
        
        // Validar que no exista horario para ese día
        if (horarioDAO.existsHours(idRecicladora, diaSemana)) {
            throw new Exception("Ya existe un horario para el día " + diaSemana);
        }
        
        Horario horario = new Horario();
        horario.setDiaSemana(diaSemana);
        horario.setHoraApertura(java.time.LocalTime.parse(horaApertura));
        horario.setHoraCierre(java.time.LocalTime.parse(horaCierre));
        horario.setIdRecicladora(idRecicladora);
        
        horarioDAO.addHoursRP(horario);
        return "Horario agregado correctamente: " + diaSemana + " de " + horaApertura + " a " + horaCierre;
    }
    
    // eliminar material ligado a la recicladora
    public String eliminarMaterial(Integer idRecicladora, Integer idMaterial) throws Exception {
        detailsDAO.deleteMaterialInRP(idRecicladora, idMaterial);
        return "Material eliminado de la recicladora";
    }
    
    // traer todas las recicladoras
    public List<Recicladora> obtenerTodasRP() throws Exception {
        return recicladoraDAO.getAllRP();
    }
    
    // traer rp por su id
    public Recicladora obtenerPorIdRP(Integer idRecicladora) throws Exception {
        Recicladora r = recicladoraDAO.getByIdRP(idRecicladora);
        if (r == null) {
            throw new Exception("Recicladora no encontrada");
        }
        return r;
    }
    
    
    // traer todos los materiales aceptados por la recicladora
    public List<Material> obtenerMateriales(Integer idRecicladora) throws Exception {
        if (recicladoraDAO.getByIdRP(idRecicladora) == null) {
            throw new Exception("Recicladora no encontrada");
        }
        return detailsDAO.getAllMaterialInRP(idRecicladora);
    }
    
    
    // traer rp por us estado
    public List<Recicladora> obtenerPorEstado(Boolean activas) throws Exception {
        return recicladoraDAO.getByEstadoRP(activas);
    }
    
    // traer rp por horario
    public List<Horario> obtenerHorarios(Integer idRecicladora) throws Exception {
        if (recicladoraDAO.getByIdRP(idRecicladora) == null) {
            throw new Exception("Recicladora no encontrada");
        }
        return horarioDAO.getHoursByRP(idRecicladora);
    }
    
    // actualizar rp
    public String actualizar(Integer idRecicladora, String nombre, String direccion, 
                            Double latitud, Double longitud) throws Exception {
        Recicladora existente = recicladoraDAO.getByIdRP(idRecicladora);
        if (existente == null) {
            throw new Exception("Recicladora no encontrada");
        }
        
        existente.setNombreRecicladora(nombre);
        existente.setDireccion(direccion);
        existente.setLatitud(latitud);
        existente.setLongitud(longitud);
        
        recicladoraDAO.updateRP(existente);
        return "Recicladora actualizada correctamente";
    }
    
    // actualizar horarios, usando enum
    public String actualizarHorario(Integer idHorario, String diaSemanaStr, 
                                     String horaApertura, String horaCierre) throws Exception {
        // Convertir String a enum
        DiaSemana diaSemana;
        try {
            diaSemana = DiaSemana.valueOf(diaSemanaStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Exception("Día de semana inválido");
        }
        
        Horario horario = new Horario();
        horario.setIdHorario(idHorario);
        horario.setDiaSemana(diaSemana);
        horario.setHoraApertura(java.time.LocalTime.parse(horaApertura));
        horario.setHoraCierre(java.time.LocalTime.parse(horaCierre));
        
        horarioDAO.updateHours(horario);
        return "Horario actualizado correctamente";
    }
    
    // eliminar rp logico
    public String desactivar(Integer idRecicladora) throws Exception {
        Recicladora r = recicladoraDAO.getByIdRP(idRecicladora);
        if (r == null) {
            throw new Exception("Recicladora no encontrada");
        }
        if (!r.getActiva()) {
            throw new Exception("La recicladora ya está inactiva");
        }
        
        recicladoraDAO.deleteRP(idRecicladora);
        return "Recicladora desactivada correctamente";
    }
    
    // eliminar horario
    public String eliminarHorario(Integer idHorario) throws Exception {
        horarioDAO.deleteHours(idHorario);
        return "Horario eliminado correctamente";
    }
    
}
