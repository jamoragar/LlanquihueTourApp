package data;

import java.util.ArrayList;
import model.ColaboradorExterno;
import model.GuiaTuristico;
import model.Registrable;
import model.RutaGastronomica;
import model.ServicioTuristico;
import model.UbicacionServicio;
import model.Vehiculo;

/**
 * Administra los registros de distintas entidades operativas de la agencia.
 */
public class GestorEntidades {

    private ArrayList<Registrable> registros;

    public GestorEntidades() {
        registros = new ArrayList<>();
    }

    public void agregarRegistro(Registrable registro) {
        if (registro != null) {
            registros.add(registro);
        }
    }

    public ArrayList<Registrable> obtenerRegistros() {
        return registros;
    }

    /**
     * Carga entidades de distintos tipos para demostrar la colección común.
     */
    public void cargarDatosPrueba() {
        registros.clear();

        agregarRegistro(new GuiaTuristico(1, "Camila Soto", "Inglés", 5));
        agregarRegistro(new Vehiculo(2, "Van Ejecutiva", "LL-2025", 12));
        agregarRegistro(new ColaboradorExterno(3, "Marcelo Ríos", "Turismo Sur", "Alojamiento"));
        agregarRegistro(new RutaGastronomica(
                "Ruta Sabores del Lago",
                3.5,
                new UbicacionServicio("Llanquihue", "Costanera"),
                4));
    }

    /**
     * Genera un resumen polimórfico y clasifica cada objeto por su tipo real.
     *
     * @return texto para mostrar por consola o en la interfaz gráfica
     */
    public String generarResumenRegistros() {
        if (registros.isEmpty()) {
            return "No hay registros disponibles.";
        }

        StringBuilder resumen = new StringBuilder();

        for (Registrable registro : registros) {
            resumen.append(registro.mostrarResumen()).append("\n");

            if (registro instanceof GuiaTuristico) {
                resumen.append("Categoría: Guía turístico\n");
            } else if (registro instanceof Vehiculo) {
                resumen.append("Categoría: Vehículo\n");
            } else if (registro instanceof ColaboradorExterno) {
                resumen.append("Categoría: Colaborador externo\n");
            } else if (registro instanceof ServicioTuristico) {
                resumen.append("Categoría: Servicio turístico\n");
            } else {
                resumen.append("Categoría: Registro general\n");
            }

            resumen.append("\n");
        }

        return resumen.toString();
    }

    public void mostrarRegistrosConTipos() {
        System.out.println("===== REGISTROS LLANQUIHUE TOUR =====");
        System.out.println(generarResumenRegistros());
    }
}
