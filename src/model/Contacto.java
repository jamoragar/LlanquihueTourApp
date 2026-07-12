package model;

/**
 * Representa la información de contacto de una persona vinculada.
 */
public class Contacto {

    private String telefono;
    private String email;

    /**
     * Constructor vacío de la clase Contacto.
     */
    public Contacto() {
    }

    /**
     * Constructor de la clase Contacto.
     *
     * @param telefono teléfono de contacto
     * @param email correo electrónico de contacto
     */
    public Contacto(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene el teléfono de contacto.
     *
     * @return teléfono registrado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el teléfono de contacto.
     *
     * @param telefono teléfono a registrar
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electrónico de contacto.
     *
     * @return email registrado
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna el correo electrónico de contacto.
     *
     * @param email email a registrar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Entrega la información de contacto como texto.
     *
     * @return información formateada del contacto
     */
    @Override
    public String toString() {
        return "Teléfono: " + telefono + " | Email: " + email;
    }
}
