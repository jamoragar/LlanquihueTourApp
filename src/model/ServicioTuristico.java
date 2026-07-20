package model;

import interfaces.Identificable;
import interfaces.Registrable;
import utils.ValidadorDatos;

/**
 * Base común para los servicios ofrecidos por Llanquihue Tour.
 */
public abstract class ServicioTuristico implements Registrable, Identificable {

    private static final double TRANSPORTE_POR_PERSONA = 5_000;

    private final String id;
    private String nombre;
    private String destino;
    private double precioBase;
    private int cuposDisponibles;
    private Itinerario itinerario;
    private boolean registrado;

    protected ServicioTuristico(String id, String nombre, String destino,
            double precioBase, int cuposDisponibles, Itinerario itinerario) {
        this.id = ValidadorDatos.id(id);
        setNombre(nombre);
        setDestino(destino);
        setPrecioBase(precioBase);
        setCuposDisponibles(cuposDisponibles);
        setItinerario(itinerario);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = ValidadorDatos.textoObligatorio(nombre, "nombre del servicio");
    }

    public String getDestino() {
        return destino;
    }

    public final void setDestino(String destino) {
        this.destino = ValidadorDatos.textoObligatorio(destino, "destino");
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public final void setPrecioBase(double precioBase) {
        this.precioBase = ValidadorDatos.decimalPositivo(precioBase, "precio base");
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public final void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = ValidadorDatos.enteroNoNegativo(
                cuposDisponibles, "cupos disponibles");
    }

    public Itinerario getItinerario() {
        return itinerario;
    }

    public final void setItinerario(Itinerario itinerario) {
        if (itinerario == null) {
            throw new IllegalArgumentException("El itinerario es obligatorio.");
        }
        this.itinerario = itinerario;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    @Override
    public void registrar() {
        registrado = true;
    }

    public double calcularPrecio() {
        return precioBase;
    }

    public double calcularPrecio(int cantidadPersonas) {
        ValidadorDatos.enteroPositivo(cantidadPersonas, "cantidad de personas");
        return validarTotal(calcularPrecio() * cantidadPersonas);
    }

    public double calcularPrecio(int cantidadPersonas, boolean incluyeTransporte) {
        double subtotal = calcularPrecio(cantidadPersonas);
        return validarTotal(incluyeTransporte
                ? subtotal + TRANSPORTE_POR_PERSONA * cantidadPersonas
                : subtotal);
    }

    public boolean tieneCupos(int cantidadPersonas) {
        return cantidadPersonas > 0 && cantidadPersonas <= cuposDisponibles;
    }

    public void reservarCupos(int cantidadPersonas) {
        if (!tieneCupos(cantidadPersonas)) {
            throw new IllegalStateException("No hay cupos suficientes para la reserva.");
        }
        cuposDisponibles -= cantidadPersonas;
    }

    public void liberarCupos(int cantidadPersonas) {
        cuposDisponibles += ValidadorDatos.enteroPositivo(
                cantidadPersonas, "cantidad de personas");
    }

    private double validarTotal(double total) {
        if (!Double.isFinite(total) || total <= 0) {
            throw new IllegalArgumentException("El total calculado no es válido.");
        }
        return total;
    }

    protected String datosComunes() {
        return "ID: " + id + " | Nombre: " + nombre + " | Destino: " + destino
                + " | Precio base: $" + String.format("%.0f", precioBase)
                + " | Cupos: " + cuposDisponibles + " | Itinerario: " + itinerario;
    }

    @Override
    public String toString() {
        return datosComunes();
    }
}
