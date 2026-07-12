package model;

/**
 * Representa un vehículo disponible para trasladar pasajeros.
 */
public class Vehiculo extends RecursoAgencia implements Registrable {

    private String patente;
    private int capacidadPasajeros;

    public Vehiculo() {
        this(1, "Sin información", "Sin información", 1);
    }

    public Vehiculo(int id, String nombre, String patente, int capacidadPasajeros) {
        super(id, nombre);
        setPatente(patente);
        setCapacidadPasajeros(capacidadPasajeros);
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        if (patente != null && !patente.trim().isEmpty()) {
            this.patente = patente.trim().toUpperCase();
        } else {
            this.patente = "Sin información";
        }
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        if (capacidadPasajeros > 0) {
            this.capacidadPasajeros = capacidadPasajeros;
        } else {
            this.capacidadPasajeros = 1;
        }
    }

    @Override
    public String mostrarResumen() {
        return "Vehículo: " + getNombre()
                + " | Patente: " + patente
                + " | Capacidad: " + capacidadPasajeros + " pasajeros";
    }

    @Override
    public String toString() {
        return super.toString() + " | Patente: " + patente
                + " | Capacidad: " + capacidadPasajeros + " pasajeros";
    }
}
