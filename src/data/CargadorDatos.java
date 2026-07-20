package data;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import utils.FormatoFecha;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import model.Actividad;
import model.Cliente;
import model.Direccion;
import model.ExcursionCultural;
import model.GuiaTuristico;
import model.Itinerario;
import model.PaseoLacustre;
import model.Proveedor;
import model.Rut;
import model.RutaGastronomica;
import model.ServicioTuristico;
import exceptions.RutInvalidoException;
import utils.LectorArchivos;

/**
 * Convierte en objetos las filas de los archivos de {@code resources} y conserva
 * los registros válidos.
 */
public class CargadorDatos {

    private static final String REGION = "Región de Los Lagos";

    private final GestorEntidades gestorEntidades;
    private final GestorReservas gestorReservas;
    private final LectorArchivos lectorArchivos;

    public CargadorDatos(GestorEntidades gestorEntidades, GestorReservas gestorReservas) {
        if (gestorEntidades == null || gestorReservas == null) {
            throw new IllegalArgumentException("Los gestores de carga son obligatorios.");
        }
        this.gestorEntidades = gestorEntidades;
        this.gestorReservas = gestorReservas;
        this.lectorArchivos = new LectorArchivos();
    }

    public InformeCarga cargarTodo(Path directorioRecursos) {
        if (directorioRecursos == null) {
            throw new IllegalArgumentException("El directorio de recursos es obligatorio.");
        }

        InformeCarga informe = new InformeCarga();
        informe.combinar(cargarClientes(directorioRecursos.resolve("clientes.txt")));
        informe.combinar(cargarGuias(directorioRecursos.resolve("guias.txt")));
        informe.combinar(cargarProveedores(directorioRecursos.resolve("proveedores.txt")));
        informe.combinar(cargarServicios(directorioRecursos.resolve("servicios.txt")));
        informe.combinar(cargarReservas(directorioRecursos.resolve("reservas.txt")));
        return informe;
    }

    public InformeCarga cargarClientes(Path ruta) {
        return procesarArchivo(ruta, "id", 9, (campos, informe) -> {
            Cliente cliente = new Cliente(
                    campos[0], campos[2], new Rut(campos[1]),
                    crearDireccion(campos, 5), campos[3], campos[4], campos[8]);
            agregarOInformar(gestorEntidades.agregarCliente(cliente),
                    "cliente", cliente.getId(), informe);
        });
    }

    public InformeCarga cargarGuias(Path ruta) {
        return procesarArchivo(ruta, "id", 10, (campos, informe) -> {
            List<String> idiomas = Arrays.asList(campos[8].split("-"));
            GuiaTuristico guia = new GuiaTuristico(
                    campos[0], campos[2], new Rut(campos[1]),
                    crearDireccion(campos, 5), campos[3], campos[4], idiomas,
                    Integer.parseInt(campos[9]));
            agregarOInformar(gestorEntidades.agregarGuia(guia),
                    "guía", guia.getId(), informe);
        });
    }

    public InformeCarga cargarProveedores(Path ruta) {
        return procesarArchivo(ruta, "id", 10, (campos, informe) -> {
            Proveedor proveedor = new Proveedor(
                    campos[0], campos[2], new Rut(campos[1]),
                    crearDireccion(campos, 5), campos[3], campos[4],
                    campos[8], campos[9]);
            agregarOInformar(gestorEntidades.agregarProveedor(proveedor),
                    "proveedor", proveedor.getId(), informe);
        });
    }

    public InformeCarga cargarServicios(Path ruta) {
        return procesarArchivo(ruta, "id", 7, (campos, informe) -> {
            String id = campos[0];
            String tipo = campos[1].trim().toUpperCase(Locale.ROOT);
            String nombre = campos[2];
            String destino = campos[3];
            double precio = Double.parseDouble(campos[4]);
            int cupos = Integer.parseInt(campos[5]);
            String detalle = campos[6];
            Itinerario itinerario = crearItinerario(nombre, detalle);

            ServicioTuristico servicio;
            switch (tipo) {
                case "GASTRONOMICA":
                    servicio = new RutaGastronomica(id, nombre, destino,
                            precio, cupos, itinerario, detalle);
                    break;
                case "LACUSTRE":
                    servicio = new PaseoLacustre(id, nombre, destino,
                            precio, cupos, itinerario, detalle);
                    break;
                case "CULTURAL":
                    servicio = new ExcursionCultural(id, nombre, destino,
                            precio, cupos, itinerario, detalle);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de servicio desconocido: " + tipo);
            }
            agregarOInformar(gestorEntidades.agregarServicio(servicio),
                    "servicio", servicio.getId(), informe);
        });
    }

    public InformeCarga cargarReservas(Path ruta) {
        return procesarArchivo(ruta, "idReserva", 5, (campos, informe) -> {
            Cliente cliente = gestorEntidades.buscarClientePorId(campos[1]);
            ServicioTuristico servicio = gestorEntidades.buscarServicioPorId(campos[2]);
            if (cliente == null) {
                throw new IllegalArgumentException("No existe el cliente " + campos[1] + ".");
            }
            if (servicio == null) {
                throw new IllegalArgumentException("No existe el servicio " + campos[2] + ".");
            }

            gestorReservas.crearReserva(campos[0], cliente, servicio,
                    LocalDate.parse(campos[3], FormatoFecha.DD_MM_YYYY),
                    Integer.parseInt(campos[4]), false);
            informe.registrarCarga();
        });
    }

    private InformeCarga procesarArchivo(Path ruta, String primeraColumna,
            int columnasEsperadas, ProcesadorFila procesador) {
        InformeCarga informe = new InformeCarga();
        List<String> lineas;
        try {
            lineas = lectorArchivos.leerLineas(ruta);
        } catch (IOException ex) {
            informe.agregarError(ex.getMessage());
            return informe;
        }

        int inicio = esEncabezado(lineas.get(0), primeraColumna) ? 1 : 0;
        if (inicio == lineas.size()) {
            informe.agregarError("El archivo " + ruta + " no contiene líneas de datos.");
            return informe;
        }

        for (int i = inicio; i < lineas.size(); i++) {
            String[] campos = lineas.get(i).split(";", -1);
            int numeroLinea = i + 1;
            if (campos.length != columnasEsperadas) {
                String verbo = campos.length == 1 ? "se encontró" : "se encontraron";
                informe.agregarError(nombre(ruta) + ", línea " + numeroLinea
                        + ": se esperaban " + columnasEsperadas + " columnas y "
                        + verbo + " " + campos.length + ".");
                continue;
            }

            try {
                procesador.procesar(campos, informe);
            } catch (RutInvalidoException | DateTimeParseException | IllegalArgumentException
                    | IllegalStateException ex) {
                informe.agregarError(nombre(ruta) + ", línea " + numeroLinea
                        + ": " + ex.getMessage());
            }
        }
        return informe;
    }

    private Direccion crearDireccion(String[] campos, int indiceCalle) {
        return new Direccion(campos[indiceCalle],
                Integer.parseInt(campos[indiceCalle + 1]),
                campos[indiceCalle + 2], REGION);
    }

    private Itinerario crearItinerario(String nombreServicio, String detalle) {
        Itinerario itinerario = new Itinerario("Itinerario " + nombreServicio);
        itinerario.agregarActividad(new Actividad(detalle, LocalTime.of(9, 0), 120));
        return itinerario;
    }

    private void agregarOInformar(boolean agregado, String tipo,
            String id, InformeCarga informe) {
        if (!agregado) {
            throw new IllegalArgumentException("No se pudo agregar el " + tipo + " " + id
                    + " porque ya existe un registro con ese ID o RUT.");
        }
        informe.registrarCarga();
    }

    private boolean esEncabezado(String linea, String primeraColumna) {
        String[] campos = linea.split(";", -1);
        return campos.length > 0 && campos[0].trim().equalsIgnoreCase(primeraColumna);
    }

    private String nombre(Path ruta) {
        return ruta.getFileName() == null ? ruta.toString() : ruta.getFileName().toString();
    }

    @FunctionalInterface
    private interface ProcesadorFila {

        void procesar(String[] campos, InformeCarga informe) throws RutInvalidoException;
    }
}
