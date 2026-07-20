package app;

import data.CargadorDatos;
import data.GestorEntidades;
import data.GestorReservas;
import data.InformeCarga;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Cliente;
import model.Direccion;
import model.Persona;
import model.Reserva;
import model.Rut;
import model.ServicioTuristico;
import exceptions.RutInvalidoException;
import interfaces.Registrable;
import utils.FormatoFecha;
import utils.ValidadorDatos;

/**
 * Punto de entrada por consola de LlanquihueTourApp
 */
public class Main {

    private static final Path RECURSOS = Path.of("resources");

    private final Scanner scanner;
    private final GestorEntidades gestorEntidades;
    private final GestorReservas gestorReservas;
    private final CargadorDatos cargadorDatos;
    private boolean cargaRealizada;

    public Main(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("El lector de consola es obligatorio.");
        }
        this.scanner = scanner;
        gestorEntidades = new GestorEntidades();
        gestorReservas = new GestorReservas();
        cargadorDatos = new CargadorDatos(gestorEntidades, gestorReservas);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            new Main(scanner).ejecutar();
        }
    }

    public void ejecutar() {
        int opcion;
        System.out.println("Llanquihue Tour");
        try {
            do {
                mostrarMenu();
                opcion = leerEntero("Seleccione una opción: ");
                try {
                    ejecutarOpcion(opcion);
                } catch (OperacionCanceladaException ex) {
                    System.out.println(ex.getMessage());
                } catch (RutInvalidoException | DateTimeParseException
                        | IllegalArgumentException | IllegalStateException
                        | IndexOutOfBoundsException ex) {
                    System.out.println("No fue posible completar la operación: " + ex.getMessage());
                }
            } while (opcion != 0);
        } catch (NoSuchElementException ex) {
            System.out.println();
            System.out.println("Entrada finalizada. Aplicación cerrada.");
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("1. Cargar datos desde archivos");
        System.out.println("2. Listar personas, servicios y reservas");
        System.out.println("3. Registrar cliente");
        System.out.println("4. Crear reserva");
        System.out.println("5. Buscar o acceder a un registro");
        System.out.println("6. Filtrar servicios por precio máximo");
        System.out.println("7. Eliminar reserva por ID");
        System.out.println("8. Mostrar listado general");
        System.out.println("0. Salir");
    }

    private void ejecutarOpcion(int opcion) throws RutInvalidoException {
        switch (opcion) {
            case 1:
                cargarDatos();
                break;
            case 2:
                listarTodo();
                break;
            case 3:
                registrarCliente();
                break;
            case 4:
                crearReserva();
                break;
            case 5:
                buscarRegistro();
                break;
            case 6:
                filtrarServicios();
                break;
            case 7:
                eliminarReserva();
                break;
            case 8:
                mostrarRegistrosPolimorficos();
                break;
            case 0:
            System.out.println("Aplicación finalizada.");
                break;
            default:
                System.out.println("Opción inválida. Ingrese un número entre 0 y 8.");
        }
    }

    private void cargarDatos() {
        if (cargaRealizada) {
            System.out.println("Los archivos ya se cargaron en esta sesión.");
            return;
        }

        InformeCarga informe = cargadorDatos.cargarTodo(RECURSOS);
        cargaRealizada = informe.getRegistrosCargados() > 0;
        System.out.println(informe.generarResumen());
        if (!cargaRealizada) {
            System.out.println("No se cargaron registros. Puede corregir los archivos e intentarlo nuevamente.");
        }
    }

    private void listarTodo() {
        if (!existenDatos()) {
            return;
        }
        mostrarLista("Personas", gestorEntidades.listarPersonas());
        mostrarLista("Servicios", gestorEntidades.listarServicios());
        mostrarLista("Reservas", gestorReservas.listarReservas());
    }

    private void registrarCliente() throws RutInvalidoException {
        String id = leerTextoConVolver("ID");
        Rut rut = new Rut(leerTextoConVolver("RUT (12345678-5)"));
        String nombre = leerTextoConVolver("Nombre");
        String telefono = leerTelefonoConVolver("Teléfono");
        String correo = leerTextoConVolver("Correo");
        String calle = leerTextoConVolver("Calle");
        int numero = leerEnteroPositivoConVolver("Número");
        String comuna = leerTextoConVolver("Comuna");
        String region = leerTextoConVolver("Región");
        String preferencia = leerTextoConVolver("Preferencia");

        Cliente cliente = new Cliente(id, nombre, rut,
                new Direccion(calle, numero, comuna, region),
                telefono, correo, preferencia);
        if (gestorEntidades.agregarCliente(cliente)) {
            System.out.println("Cliente registrado: " + cliente.mostrarDatos());
        } else {
            System.out.println("No se agregó el cliente: su ID o RUT ya existe.");
        }
    }

    private void crearReserva() {
        if (!gestorEntidades.hayClientes() || !gestorEntidades.hayServicios()) {
            System.out.println("Debe cargar al menos un cliente y un servicio antes de crear reservas.");
            return;
        }
        String id = leerTextoConVolver("ID de reserva");
        Cliente cliente = gestorEntidades.buscarClientePorId(
                leerTextoConVolver("ID de cliente"));
        ServicioTuristico servicio = gestorEntidades.buscarServicioPorId(
                leerTextoConVolver("ID de servicio"));
        LocalDate fecha = LocalDate.parse(
                leerTextoConVolver("Fecha (DD-MM-YYYY)"),
                FormatoFecha.DD_MM_YYYY);
        int cantidad = leerEnteroPositivoConVolver("Cantidad de personas");
        boolean transporte = leerSiNoConVolver("¿Incluye transporte? (S/N)");

        Reserva reserva = gestorReservas.crearReserva(id, cliente, servicio,
                fecha, cantidad, transporte);
        System.out.println("Reserva creada: " + reserva.mostrarDatos());
    }

    private void buscarRegistro() {
        if (!existenDatos()) {
            return;
        }
        System.out.println("1. Persona por ID o RUT");
        System.out.println("2. Servicio por ID");
        System.out.println("3. Servicios por destino");
        System.out.println("4. Reserva por ID");
        System.out.println("5. Reservas por cliente");
        System.out.println("6. Reserva por índice");
        System.out.println("0. Volver al menú principal");
        int opcion = leerEntero("Tipo de búsqueda: ");

        switch (opcion) {
            case 0:
                System.out.println("Volviendo al menú principal.");
                break;
            case 1:
                String criterio = leerTextoConVolver("ID o RUT");
                Persona persona = gestorEntidades.buscarPersonaPorId(criterio);
                if (persona == null) {
                    persona = gestorEntidades.buscarPersonaPorRut(criterio);
                }
                mostrarResultado(persona);
                break;
            case 2:
                mostrarResultado(gestorEntidades.buscarServicioPorId(
                        leerTextoConVolver("ID de servicio")));
                break;
            case 3:
                mostrarLista("Servicios encontrados", gestorEntidades
                        .buscarServiciosPorDestino(leerTextoConVolver("Destino")));
                break;
            case 4:
                mostrarResultado(gestorReservas.buscarReservaPorId(
                        leerTextoConVolver("ID de reserva")));
                break;
            case 5:
                mostrarLista("Reservas del cliente", gestorReservas
                        .filtrarReservasPorCliente(leerTextoConVolver("ID de cliente")));
                break;
            case 6:
                mostrarResultado(gestorReservas.obtenerReservaPorIndice(
                        leerEnteroConVolver("Índice desde 0")));
                break;
            default:
                System.out.println("Tipo de búsqueda inválido.");
        }
    }

    private void filtrarServicios() {
        if (!gestorEntidades.hayServicios()) {
            System.out.println("No hay servicios cargados para filtrar.");
            return;
        }
        double precioMaximo = leerDoublePositivoConVolver("Precio máximo");
        mostrarLista("Servicios dentro del presupuesto",
                gestorEntidades.filtrarServiciosPorPrecio(precioMaximo));
    }

    private void eliminarReserva() {
        if (!gestorReservas.hayReservas()) {
            System.out.println("No hay reservas cargadas para eliminar.");
            return;
        }
        String id = leerTextoConVolver("ID de reserva a eliminar");
        if (gestorReservas.eliminarReserva(id)) {
            System.out.println("Reserva " + id + " eliminada y cupos restaurados.");
        } else {
            System.out.println("No existe una reserva con ID " + id + ".");
        }
    }

    private void mostrarRegistrosPolimorficos() {
        if (!existenDatos()) {
            return;
        }
        List<Registrable> registros = gestorEntidades.listarRegistrosPolimorficos();
        System.out.println(gestorEntidades.generarRecorridoPolimorfico(registros));
    }

    private boolean existenDatos() {
        if (!gestorEntidades.hayDatos() && !gestorReservas.hayReservas()) {
            System.out.println("No hay datos. Ejecute la carga o registre un cliente primero.");
            return false;
        }
        return true;
    }

    private void mostrarResultado(Registrable registro) {
        System.out.println(registro == null ? "No se encontró el registro."
                : registro.mostrarDatos());
    }

    private void mostrarLista(String titulo, List<?> elementos) {
        System.out.println("--- " + titulo + " ---");
        if (elementos == null || elementos.isEmpty()) {
            System.out.println("No hay elementos para mostrar.");
            return;
        }
        for (Object elemento : elementos) {
            System.out.println(elemento);
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("El valor no puede estar vacío.");
        }
    }

    private String leerTextoConVolver(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " (0 para volver): ");
            String valor = scanner.nextLine().trim();
            if (valor.equals("0")) {
                throw new OperacionCanceladaException("Operación cancelada. Volviendo al menú principal.");
            }
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("El valor no puede estar vacío.");
        }
    }

    private String leerTelefonoConVolver(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " (0 para volver): ");
            String telefono = scanner.nextLine().trim();
            if (telefono.equals("0")) {
                throw new OperacionCanceladaException("Operación cancelada. Volviendo al menú principal.");
            }
            try {
                return ValidadorDatos.telefono(telefono);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String valor = scanner.nextLine().trim();
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private int leerEnteroConVolver(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " (0 para volver): ");
            String valor = scanner.nextLine().trim();
            if (valor.equals("0")) {
                throw new OperacionCanceladaException("Operación cancelada. Volviendo al menú principal.");
            }
            try {
                return Integer.parseInt(valor);
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println("El número debe ser mayor que cero.");
        }
    }

    private int leerEnteroPositivoConVolver(String etiqueta) {
        while (true) {
            int valor = leerEnteroConVolver(etiqueta);
            if (valor > 0) {
                return valor;
            }
            System.out.println("El número debe ser mayor que cero.");
        }
    }

    private double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim().replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (Double.isFinite(valor) && valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException ex) {
                // Se informa el mismo mensaje para cualquier decimal inválido.
            }
            System.out.println("Ingrese un número mayor que cero.");
        }
    }

    private double leerDoublePositivoConVolver(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " (0 para volver): ");
            String texto = scanner.nextLine().trim().replace(',', '.');
            if (texto.equals("0")) {
                throw new OperacionCanceladaException("Operación cancelada. Volviendo al menú principal.");
            }
            try {
                double valor = Double.parseDouble(texto);
                if (Double.isFinite(valor) && valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException ex) {
                // Se informa el mismo mensaje para cualquier decimal inválido.
            }
            System.out.println("Ingrese un número mayor que cero.");
        }
    }

    private boolean leerSiNo(String mensaje) {
        while (true) {
            String respuesta = leerTexto(mensaje);
            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }
            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Responda S o N.");
        }
    }

    private boolean leerSiNoConVolver(String etiqueta) {
        while (true) {
            System.out.print(etiqueta + " (0 para volver): ");
            String respuesta = scanner.nextLine().trim();
            if (respuesta.equals("0")) {
                throw new OperacionCanceladaException("Operación cancelada. Volviendo al menú principal.");
            }
            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }
            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Responda S o N.");
        }
    }

    private static class OperacionCanceladaException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        OperacionCanceladaException(String mensaje) {
            super(mensaje);
        }
    }
}
