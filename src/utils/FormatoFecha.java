package utils;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Define el formato de fecha utilizado en toda la aplicación.
 */
public final class FormatoFecha {

    public static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter
            .ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

    private FormatoFecha() {
    }
}
