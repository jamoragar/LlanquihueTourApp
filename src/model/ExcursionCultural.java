package model;

import utils.ValidadorDatos;

/**
 * Servicio dedicado al patrimonio y la historia local.
 */
public final class ExcursionCultural extends ServicioTuristico {

    private String enfoqueCultural;

    public ExcursionCultural(String id, String nombre, String destino,
            double precioBase, int cuposDisponibles, Itinerario itinerario,
            String enfoqueCultural) {
        super(id, nombre, destino, precioBase, cuposDisponibles, itinerario);
        setEnfoqueCultural(enfoqueCultural);
    }

    public String getEnfoqueCultural() {
        return enfoqueCultural;
    }

    public void setEnfoqueCultural(String enfoqueCultural) {
        this.enfoqueCultural = ValidadorDatos.textoObligatorio(
                enfoqueCultural, "enfoque cultural");
    }

    @Override
    public String mostrarDatos() {
        return "Excursión cultural | " + datosComunes()
                + " | Enfoque: " + enfoqueCultural;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
