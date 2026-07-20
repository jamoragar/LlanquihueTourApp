package utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee archivos de texto UTF-8 sin interpretar el significado de sus filas.
 */
public class LectorArchivos {

    public List<String> leerLineas(Path ruta) throws IOException {
        if (ruta == null) {
            throw new IOException("La ruta del archivo es obligatoria.");
        }
        if (!Files.exists(ruta) || !Files.isRegularFile(ruta)) {
            throw new IOException("No existe el archivo " + ruta + ".");
        }

        List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
        if (lineas.isEmpty()) {
            throw new IOException("El archivo " + ruta + " está vacío.");
        }

        List<String> lineasUtiles = new ArrayList<>();
        for (String linea : lineas) {
            if (linea != null && !linea.trim().isEmpty()
                    && !linea.trim().startsWith("#")) {
                lineasUtiles.add(linea.trim());
            }
        }
        if (lineasUtiles.isEmpty()) {
            throw new IOException("El archivo " + ruta + " no contiene datos utilizables.");
        }
        return lineasUtiles;
    }
}
