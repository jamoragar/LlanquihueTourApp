package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ValidadorDatos;

/**
 * Programa de actividades que forma parte de un servicio turístico.
 */
public final class Itinerario {

    private String nombre;
    private final List<Actividad> actividades;

    public Itinerario(String nombre) {
        this(nombre, Collections.emptyList());
    }

    public Itinerario(String nombre, List<Actividad> actividades) {
        this.actividades = new ArrayList<>();
        setNombre(nombre);
        setActividades(actividades);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = ValidadorDatos.textoObligatorio(nombre, "nombre de itinerario");
    }

    public List<Actividad> getActividades() {
        return Collections.unmodifiableList(new ArrayList<>(actividades));
    }

    public void setActividades(List<Actividad> actividades) {
        if (actividades == null) {
            throw new IllegalArgumentException("La lista de actividades no puede ser nula.");
        }
        if (actividades.contains(null)) {
            throw new IllegalArgumentException("El itinerario no admite actividades nulas.");
        }
        this.actividades.clear();
        this.actividades.addAll(actividades);
    }

    public void agregarActividad(Actividad actividad) {
        if (actividad == null) {
            throw new IllegalArgumentException("La actividad no puede ser nula.");
        }
        actividades.add(actividad);
    }

    @Override
    public String toString() {
        return nombre + " - " + actividades.size()
                + (actividades.size() == 1 ? " actividad: " : " actividades: ")
                + actividades;
    }
}
