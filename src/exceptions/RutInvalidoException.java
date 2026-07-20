package exceptions;

/**
 * Informa que un RUT no tiene un formato válido o presenta un dígito
 * verificador incorrecto.
 */
public class RutInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    public RutInvalidoException(String mensaje) {
        super(mensaje);
    }
}
