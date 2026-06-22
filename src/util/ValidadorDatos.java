package util;

public class ValidadorDatos {

    public static boolean esTextoValido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    public static boolean esTipoValido(String tipo) {
        return esTextoValido(tipo) &&
                (tipo.equalsIgnoreCase("guia") ||
                tipo.equalsIgnoreCase("operador") ||
                tipo.equalsIgnoreCase("proveedor"));
    }

    public static boolean esEmailValido(String email) {
        if (!esTextoValido(email)) {
            return false;
        }

        int posicionArroba = email.indexOf("@");
        int posicionPunto = email.lastIndexOf(".");

        return posicionArroba > 0 && posicionPunto > posicionArroba + 1 && posicionPunto < email.length() - 1;
    }

    public static boolean esTelefonoValido(String telefono) {
        if (!esTextoValido(telefono)) {
            return false;
        }

        for (int i = 0; i < telefono.length(); i++) {
            if (!Character.isDigit(telefono.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static int convertirId(String valor) throws NumberFormatException {
        int id = Integer.parseInt(valor.trim());

        if (id <= 0) {
            throw new NumberFormatException("El ID debe ser mayor que cero");
        }

        return id;
    }
}
