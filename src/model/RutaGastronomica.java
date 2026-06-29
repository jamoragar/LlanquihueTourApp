package model;

/**
 * Servicio turístico enfocado en experiencias gastronómicas.
 */
public class RutaGastronomica extends ServicioTuristico {

    private int numeroDeParadas;

    public RutaGastronomica() {
        super();
        this.numeroDeParadas = 1;
    }

    public RutaGastronomica(String nombre, double duracionHoras, UbicacionServicio ubicacion, int numeroDeParadas) {
        super(nombre, duracionHoras);
        setUbicacion(ubicacion);
        setNumeroDeParadas(numeroDeParadas);
    }

    public int getNumeroDeParadas() {
        return numeroDeParadas;
    }

    public void setNumeroDeParadas(int numeroDeParadas) {
        if (numeroDeParadas > 0) {
            this.numeroDeParadas = numeroDeParadas;
        } else {
            System.out.println("El número de paradas debe ser mayor que cero. Se asigna 1 por defecto.");
            this.numeroDeParadas = 1;
        }
    }

    @Override
    public String toString() {
        return "Ruta Gastronómica: " + super.toString() +
                " | Número de paradas: " + numeroDeParadas;
    }
}
