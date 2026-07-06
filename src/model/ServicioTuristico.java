package model;

/**
 * Clase base para los servicios turísticos de Llanquihue Tour.
 */
public class ServicioTuristico {

    private String nombre;
    private double duracionHoras;
    private UbicacionServicio ubicacion;

    public ServicioTuristico() {
        this.nombre = "Sin información";
        this.duracionHoras = 1;
        this.ubicacion = new UbicacionServicio();
    }

    public ServicioTuristico(String nombre, double duracionHoras) {
        setNombre(nombre);
        setDuracionHoras(duracionHoras);
        this.ubicacion = new UbicacionServicio();
    }

    public ServicioTuristico(String nombre, double duracionHoras, UbicacionServicio ubicacion) {
        setNombre(nombre);
        setDuracionHoras(duracionHoras);
        setUbicacion(ubicacion);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre del servicio no puede estar vacío. Se asigna valor por defecto.");
            this.nombre = "Sin información";
        }
    }

    public double getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(double duracionHoras) {
        if (duracionHoras > 0) {
            this.duracionHoras = duracionHoras;
        } else {
            System.out.println("La duración debe ser mayor que cero. Se asigna 1 hora por defecto.");
            this.duracionHoras = 1;
        }
    }

    public UbicacionServicio getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionServicio ubicacion) {
        if (ubicacion != null) {
            this.ubicacion = ubicacion;
        } else {
            System.out.println("La ubicación no puede ser nula. Se asigna ubicación por defecto.");
            this.ubicacion = new UbicacionServicio();
        }
    }

    public String mostrarInformacion() {
        return toString();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                " | Duración: " + duracionHoras + " horas" +
                " | Ubicación: " + ubicacion;
    }
}
