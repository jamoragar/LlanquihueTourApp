package model;

import utils.ValidadorDatos;

/**
 * Servicio de navegación por lagos de la zona.
 */
public final class PaseoLacustre extends ServicioTuristico {

    private String tipoEmbarcacion;

    public PaseoLacustre(String id, String nombre, String destino,
            double precioBase, int cuposDisponibles, Itinerario itinerario,
            String tipoEmbarcacion) {
        super(id, nombre, destino, precioBase, cuposDisponibles, itinerario);
        setTipoEmbarcacion(tipoEmbarcacion);
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        this.tipoEmbarcacion = ValidadorDatos.textoObligatorio(
                tipoEmbarcacion, "tipo de embarcación");
    }

    @Override
    public String mostrarDatos() {
        return "Paseo lacustre | " + datosComunes()
                + " | Embarcación: " + tipoEmbarcacion;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
