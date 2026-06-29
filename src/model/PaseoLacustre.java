package model;

/**
 * Servicio turístico realizado en un entorno lacustre.
 */
public class PaseoLacustre extends ServicioTuristico {

    private String tipoEmbarcacion;

    public PaseoLacustre() {
        super();
        this.tipoEmbarcacion = "Sin información";
    }

    public PaseoLacustre(String nombre, double duracionHoras, UbicacionServicio ubicacion, String tipoEmbarcacion) {
        super(nombre, duracionHoras);
        setUbicacion(ubicacion);
        setTipoEmbarcacion(tipoEmbarcacion);
    }

    public String getTipoEmbarcacion() {
        return tipoEmbarcacion;
    }

    public void setTipoEmbarcacion(String tipoEmbarcacion) {
        if (tipoEmbarcacion != null && !tipoEmbarcacion.trim().isEmpty()) {
            this.tipoEmbarcacion = tipoEmbarcacion;
        } else {
            System.out.println("El tipo de embarcación no puede estar vacío. Se asigna valor por defecto.");
            this.tipoEmbarcacion = "Sin información";
        }
    }

    @Override
    public String toString() {
        return "Paseo Lacustre: " + super.toString() +
                " | Tipo de embarcación: " + tipoEmbarcacion;
    }
}
