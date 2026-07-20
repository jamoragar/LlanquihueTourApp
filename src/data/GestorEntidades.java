package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import model.Cliente;
import model.GuiaTuristico;
import model.Persona;
import model.Proveedor;
import model.Rut;
import model.ServicioTuristico;
import interfaces.Registrable;

/**
 * Administra personas, servicios, búsquedas y recorridos polimórficos.
 */
public class GestorEntidades {

    private final Repositorio<Cliente> clientes;
    private final Repositorio<GuiaTuristico> guias;
    private final Repositorio<Proveedor> proveedores;
    private final Repositorio<ServicioTuristico> servicios;

    public GestorEntidades() {
        clientes = new Repositorio<>();
        guias = new Repositorio<>();
        proveedores = new Repositorio<>();
        servicios = new Repositorio<>();
    }

    public boolean agregarCliente(Cliente cliente) {
        return agregarPersona(cliente, clientes);
    }

    public boolean agregarGuia(GuiaTuristico guia) {
        return agregarPersona(guia, guias);
    }

    public boolean agregarProveedor(Proveedor proveedor) {
        return agregarPersona(proveedor, proveedores);
    }

    private <T extends Persona> boolean agregarPersona(T persona, Repositorio<T> repositorio) {
        if (persona == null || existeIdPersona(persona.getId())
                || buscarPersonaPorRut(persona.getRut()) != null) {
            return false;
        }

        if (repositorio.agregar(persona)) {
            persona.registrar();
            return true;
        }
        return false;
    }

    public boolean agregarServicio(ServicioTuristico servicio) {
        if (servicio != null && servicios.agregar(servicio)) {
            servicio.registrar();
            return true;
        }
        return false;
    }

    public Cliente buscarClientePorId(String id) {
        return clientes.obtenerPorId(id);
    }

    public Cliente buscarClientePorRut(String rut) {
        return buscarPorRut(clientes.listar(), rut);
    }

    public Persona buscarPersonaPorId(String id) {
        Persona persona = clientes.obtenerPorId(id);
        if (persona == null) {
            persona = guias.obtenerPorId(id);
        }
        if (persona == null) {
            persona = proveedores.obtenerPorId(id);
        }
        return persona;
    }

    public Persona buscarPersonaPorRut(Rut rut) {
        if (rut == null) {
            return null;
        }
        return buscarPersonaPorRut(rut.toString());
    }

    public Persona buscarPersonaPorRut(String rut) {
        Persona persona = buscarPorRut(clientes.listar(), rut);
        if (persona == null) {
            persona = buscarPorRut(guias.listar(), rut);
        }
        if (persona == null) {
            persona = buscarPorRut(proveedores.listar(), rut);
        }
        return persona;
    }

    private <T extends Persona> T buscarPorRut(List<T> personas, String rut) {
        if (rut == null) {
            return null;
        }
        String buscado = rut.trim().replace(".", "").toUpperCase(Locale.ROOT);
        for (T persona : personas) {
            if (persona.getRut().toString().equalsIgnoreCase(buscado)) {
                return persona;
            }
        }
        return null;
    }

    public ServicioTuristico buscarServicioPorId(String id) {
        return servicios.obtenerPorId(id);
    }

    public List<ServicioTuristico> buscarServiciosPorDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String buscado = destino.trim().toLowerCase(Locale.ROOT);
        return servicios.listar().stream()
                .filter(servicio -> servicio.getDestino().toLowerCase(Locale.ROOT)
                        .contains(buscado))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<ServicioTuristico> filtrarServiciosPorPrecio(double precioMaximo) {
        if (!Double.isFinite(precioMaximo) || precioMaximo <= 0) {
            throw new IllegalArgumentException("El precio máximo debe ser mayor que cero.");
        }
        return servicios.listar().stream()
                .filter(servicio -> servicio.calcularPrecio() <= precioMaximo)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Persona> listarPersonas() {
        List<Persona> personas = new ArrayList<>();
        personas.addAll(clientes.listar());
        personas.addAll(guias.listar());
        personas.addAll(proveedores.listar());
        return Collections.unmodifiableList(personas);
    }

    public List<ServicioTuristico> listarServicios() {
        return servicios.listar();
    }

    public boolean hayClientes() {
        return !clientes.estaVacio();
    }

    public boolean hayServicios() {
        return !servicios.estaVacio();
    }

    public List<Registrable> listarRegistrosPolimorficos() {
        List<Registrable> registros = new ArrayList<>();
        registros.addAll(listarPersonas());
        registros.addAll(servicios.listar());
        return Collections.unmodifiableList(registros);
    }

    public String generarRecorridoPolimorfico(List<? extends Registrable> registros) {
        if (registros == null || registros.isEmpty()) {
            return "No hay registros para mostrar.";
        }

        StringBuilder resultado = new StringBuilder();
        for (Registrable registro : registros) {
            resultado.append(registro.mostrarDatos()).append(System.lineSeparator());
            if (registro instanceof Cliente) {
                resultado.append("Categoría detectada: Cliente");
            } else if (registro instanceof GuiaTuristico) {
                resultado.append("Categoría detectada: Guía turístico");
            } else if (registro instanceof Proveedor) {
                resultado.append("Categoría detectada: Proveedor");
            } else if (registro instanceof ServicioTuristico) {
                resultado.append("Categoría detectada: Servicio turístico");
            } else {
                resultado.append("Categoría detectada: Registro general");
            }
            resultado.append(System.lineSeparator()).append(System.lineSeparator());
        }
        return resultado.toString().trim();
    }

    public boolean hayDatos() {
        return !clientes.estaVacio() || !guias.estaVacio()
                || !proveedores.estaVacio() || !servicios.estaVacio();
    }

    private boolean existeIdPersona(String id) {
        return buscarPersonaPorId(id) != null;
    }
}
