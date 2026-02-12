package ecoscan.model;

import java.time.LocalTime;

/**
 *
 * @author Benyi Uriel
 */
public class Horario {

    private Integer idHorario;
    private DiaSemana diaSemana;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private Integer idRecicladora;

    public Horario() {
    }

    public Horario(Integer idHorario, DiaSemana diaSemana, LocalTime horaApertura, LocalTime horaCierre, Integer idRecicladora) {
        this.idHorario = idHorario;
        this.diaSemana = diaSemana;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
        this.idRecicladora = idRecicladora;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Integer getIdRecicladora() {
        return idRecicladora;
    }

    public void setIdRecicladora(Integer idRecicladora) {
        this.idRecicladora = idRecicladora;
    }
    
}
