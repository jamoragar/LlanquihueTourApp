package model;

/**
 * Representa la ubicación asociada a un servicio turístico.
 */
public class UbicacionServicio {

    private String comuna;
    private String puntoEncuentro;

    public UbicacionServicio() {
        this.comuna = "Sin información";
        this.puntoEncuentro = "Sin información";
    }

    public UbicacionServicio(String comuna, String puntoEncuentro) {
        setComuna(comuna);
        setPuntoEncuentro(puntoEncuentro);
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        if (comuna != null && !comuna.trim().isEmpty()) {
            this.comuna = comuna;
        } else {
            System.out.println("La comuna no puede estar vacía. Se asigna valor por defecto.");
            this.comuna = "Sin información";
        }
    }

    public String getPuntoEncuentro() {
        return puntoEncuentro;
    }

    public void setPuntoEncuentro(String puntoEncuentro) {
        if (puntoEncuentro != null && !puntoEncuentro.trim().isEmpty()) {
            this.puntoEncuentro = puntoEncuentro;
        } else {
            System.out.println("El punto de encuentro no puede estar vacío. Se asigna valor por defecto.");
            this.puntoEncuentro = "Sin información";
        }
    }

    @Override
    public String toString() {
        return comuna + " - " + puntoEncuentro;
    }
}
