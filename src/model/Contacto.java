package model;

/**
 * Representa la informacion de contacto de una persona vinculada.
 */
public class Contacto {

    private String telefono;
    private String email;

    /**
     * Constructor vacio de la clase Contacto.
     */
    public Contacto() {
    }

    /**
     * Constructor de la clase Contacto.
     *
     * @param telefono telefono de contacto
     * @param email correo electronico de contacto
     */
    public Contacto(String telefono, String email) {
        this.telefono = telefono;
        this.email = email;
    }

    /**
     * Obtiene el telefono de contacto.
     *
     * @return telefono registrado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el telefono de contacto.
     *
     * @param telefono telefono a registrar
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el correo electronico de contacto.
     *
     * @return email registrado
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asigna el correo electronico de contacto.
     *
     * @param email email a registrar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Entrega la informacion de contacto como texto.
     *
     * @return informacion formateada del contacto
     */
    @Override
    public String toString() {
        return "Telefono: " + telefono + " | Email: " + email;
    }
}
