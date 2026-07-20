package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Resume los registros cargados y los errores recuperables de los archivos.
 */
public class InformeCarga {

    private int registrosCargados;
    private final List<String> errores;

    public InformeCarga() {
        errores = new ArrayList<>();
    }

    public int getRegistrosCargados() {
        return registrosCargados;
    }

    public void registrarCarga() {
        registrosCargados++;
    }

    public void agregarError(String error) {
        if (error != null && !error.trim().isEmpty()) {
            errores.add(error.trim());
        }
    }

    public void combinar(InformeCarga otro) {
        if (otro != null) {
            registrosCargados += otro.registrosCargados;
            errores.addAll(otro.errores);
        }
    }

    public String generarResumen() {
        StringBuilder resumen = new StringBuilder("Registros cargados: ")
                .append(registrosCargados);
        if (errores.isEmpty()) {
            return resumen.append(". Sin errores.").toString();
        }

        resumen.append(". Errores recuperables: ").append(errores.size()).append(".");
        for (String error : errores) {
            resumen.append(System.lineSeparator()).append("- ").append(error);
        }
        return resumen.toString();
    }
}
