package data;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import model.Cliente;
import model.Reserva;
import model.ServicioTuristico;

/**
 * Aplica las reglas de creación y eliminación de reservas.
 */
public class GestorReservas {

    private final Repositorio<Reserva> reservas;

    public GestorReservas() {
        reservas = new Repositorio<>();
    }

    public Reserva crearReserva(String id, Cliente cliente, ServicioTuristico servicio,
            LocalDate fecha, int cantidadPersonas, boolean incluyeTransporte) {
        if (reservas.obtenerPorId(id) != null) {
            throw new IllegalArgumentException("Ya existe una reserva con ID " + id + ".");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente indicado no existe.");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("El servicio indicado no existe.");
        }
        if (!servicio.tieneCupos(cantidadPersonas)) {
            throw new IllegalStateException("No hay cupos suficientes para crear la reserva.");
        }

        Reserva reserva = new Reserva(id, cliente, servicio, fecha,
                cantidadPersonas, incluyeTransporte);
        servicio.reservarCupos(cantidadPersonas);

        if (!reservas.agregar(reserva)) {
            servicio.liberarCupos(cantidadPersonas);
            throw new IllegalArgumentException("No fue posible agregar la reserva.");
        }
        reserva.registrar();
        return reserva;
    }

    public Reserva buscarReservaPorId(String id) {
        return reservas.obtenerPorId(id);
    }

    public Reserva obtenerReservaPorIndice(int indice) {
        return reservas.obtenerPorIndice(indice);
    }

    public boolean eliminarReserva(String id) {
        Reserva reserva = reservas.obtenerPorId(id);
        if (reserva == null) {
            return false;
        }

        if (reservas.eliminarPorId(id)) {
            reserva.getServicio().liberarCupos(reserva.getCantidadPersonas());
            return true;
        }
        return false;
    }

    public List<Reserva> listarReservas() {
        return reservas.listar();
    }

    public List<Reserva> filtrarReservasPorCliente(String idCliente) {
        if (idCliente == null || idCliente.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String buscado = idCliente.trim().toUpperCase(Locale.ROOT);
        return reservas.listar().stream()
                .filter(reserva -> reserva.getCliente().getId().equalsIgnoreCase(buscado))
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean hayReservas() {
        return !reservas.estaVacio();
    }
}
