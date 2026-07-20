package model;

import java.time.LocalDate;
import interfaces.Identificable;
import interfaces.Registrable;
import utils.FormatoFecha;
import utils.ValidadorDatos;

/**
 * Asociación trazable entre un cliente y un servicio turístico.
 */
public final class Reserva implements Registrable, Identificable {

    private final String id;
    private Cliente cliente;
    private ServicioTuristico servicio;
    private LocalDate fecha;
    private int cantidadPersonas;
    private boolean incluyeTransporte;
    private double total;
    private boolean registrada;

    public Reserva(String id, Cliente cliente, ServicioTuristico servicio,
            LocalDate fecha, int cantidadPersonas, boolean incluyeTransporte) {
        this.id = ValidadorDatos.id(id);
        setCliente(cliente);
        setServicio(servicio);
        setFecha(fecha);
        setCantidadPersonas(cantidadPersonas);
        setIncluyeTransporte(incluyeTransporte);
    }

    @Override
    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        asegurarNoRegistrada();
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente de la reserva es obligatorio.");
        }
        this.cliente = cliente;
    }

    public ServicioTuristico getServicio() {
        return servicio;
    }

    public void setServicio(ServicioTuristico servicio) {
        asegurarNoRegistrada();
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio de la reserva es obligatorio.");
        }
        this.servicio = servicio;
        recalcularTotal();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        asegurarNoRegistrada();
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la reserva es obligatoria.");
        }
        this.fecha = fecha;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        asegurarNoRegistrada();
        this.cantidadPersonas = ValidadorDatos.enteroPositivo(
                cantidadPersonas, "cantidad de personas");
        recalcularTotal();
    }

    public boolean isIncluyeTransporte() {
        return incluyeTransporte;
    }

    public void setIncluyeTransporte(boolean incluyeTransporte) {
        asegurarNoRegistrada();
        this.incluyeTransporte = incluyeTransporte;
        recalcularTotal();
    }

    public double getTotal() {
        return total;
    }

    public boolean isRegistrada() {
        return registrada;
    }

    @Override
    public void registrar() {
        registrada = true;
    }

    private void recalcularTotal() {
        if (servicio != null && cantidadPersonas > 0) {
            total = servicio.calcularPrecio(cantidadPersonas, incluyeTransporte);
        }
    }

    private void asegurarNoRegistrada() {
        if (registrada) {
            throw new IllegalStateException(
                    "Una reserva registrada no puede modificarse; elimínela y cree una nueva.");
        }
    }

    @Override
    public String mostrarDatos() {
        return "Reserva | ID: " + id + " | Cliente: " + cliente.getNombre()
                + " (" + cliente.getId() + ") | Servicio: " + servicio.getNombre()
                + " (" + servicio.getId() + ") | Fecha: "
                + fecha.format(FormatoFecha.DD_MM_YYYY)
                + " | Personas: " + cantidadPersonas + " | Transporte: "
                + (incluyeTransporte ? "Sí" : "No") + " | Total: $"
                + String.format("%.0f", total);
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
