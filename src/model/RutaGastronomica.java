package model;

import utils.ValidadorDatos;

/**
 * Servicio centrado en preparaciones y productos locales.
 */
public final class RutaGastronomica extends ServicioTuristico {

    private String especialidad;

    public RutaGastronomica(String id, String nombre, String destino,
            double precioBase, int cuposDisponibles, Itinerario itinerario,
            String especialidad) {
        super(id, nombre, destino, precioBase, cuposDisponibles, itinerario);
        setEspecialidad(especialidad);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = ValidadorDatos.textoObligatorio(
                especialidad, "especialidad gastronómica");
    }

    @Override
    public String mostrarDatos() {
        return "Ruta gastronómica | " + datosComunes() + " | Especialidad: " + especialidad;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
