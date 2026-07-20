package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import utils.ValidadorDatos;

/**
 * Actividad programada dentro de un itinerario.
 */
public final class Actividad {

    private String nombre;
    private LocalTime horaInicio;
    private int duracionMinutos;

    public Actividad(String nombre, LocalTime horaInicio, int duracionMinutos) {
        setNombre(nombre);
        setHoraInicio(horaInicio);
        setDuracionMinutos(duracionMinutos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = ValidadorDatos.textoObligatorio(nombre, "nombre de actividad");
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        if (horaInicio == null) {
            throw new IllegalArgumentException("La hora de inicio es obligatoria.");
        }
        this.horaInicio = horaInicio;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = ValidadorDatos.enteroPositivo(
                duracionMinutos, "duración de actividad");
    }

    @Override
    public String toString() {
        return nombre + " a las " + horaInicio.format(DateTimeFormatter.ofPattern("HH:mm"))
                + " (" + duracionMinutos + " min)";
    }
}
