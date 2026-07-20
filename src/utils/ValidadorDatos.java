package utils;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Validaciones generales compartidas por el modelo de dominio.
 */
public final class ValidadorDatos {

    private static final Pattern CORREO = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern TELEFONO = Pattern.compile("^[0-9]{8,15}$");
    private static final Pattern ID = Pattern.compile("^[A-Z0-9_-]+$");

    private ValidadorDatos() {
    }

    public static String textoObligatorio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + campo + " es obligatorio.");
        }
        return valor.trim();
    }

    public static String id(String valor) {
        String id = textoObligatorio(valor, "ID").toUpperCase(Locale.ROOT);
        if (!ID.matcher(id).matches()) {
            throw new IllegalArgumentException(
                    "El ID solo puede contener letras, números, guion y guion bajo.");
        }
        return id;
    }

    public static String correo(String valor) {
        String correo = textoObligatorio(valor, "correo").toLowerCase(Locale.ROOT);
        if (!CORREO.matcher(correo).matches()) {
            throw new IllegalArgumentException("El correo no tiene un formato válido.");
        }
        return correo;
    }

    public static String telefono(String valor) {
        String telefono = textoObligatorio(valor, "teléfono");
        if (!TELEFONO.matcher(telefono).matches()) {
            throw new IllegalArgumentException("El teléfono debe contener entre 8 y 15 dígitos.");
        }
        return telefono;
    }

    public static int enteroPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor que cero.");
        }
        return valor;
    }

    public static int enteroNoNegativo(int valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException("El campo " + campo + " no puede ser negativo.");
        }
        return valor;
    }

    public static double decimalPositivo(double valor, String campo) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("El campo " + campo + " debe ser mayor que cero.");
        }
        return valor;
    }
}
