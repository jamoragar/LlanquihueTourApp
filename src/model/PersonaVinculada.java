package model;

/**
 * Representa a una persona vinculada a la agencia, como guia, operador o proveedor.
 */
public class PersonaVinculada {

    private int id;
    private String nombre;
    private String tipo;
    private String comuna;
    private Contacto contacto;
    private Direccion direccion;

    /**
     * Constructor vacio de la clase PersonaVinculada.
     */
    public PersonaVinculada() {
    }

    /**
     * Constructor de la clase PersonaVinculada.
     *
     * @param id identificador de la persona
     * @param nombre nombre completo de la persona
     * @param tipo tipo de persona vinculada
     * @param comuna comuna asociada al registro
     * @param contacto informacion de contacto de la persona
     * @param direccion direccion asociada a la persona
     */
    public PersonaVinculada(int id, String nombre, String tipo, String comuna, Contacto contacto, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.comuna = comuna;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    /**
     * Obtiene el identificador de la persona.
     *
     * @return id registrado
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el identificador de la persona.
     *
     * @param id identificador a registrar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     *
     * @return nombre registrado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre de la persona.
     *
     * @param nombre nombre a registrar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo de persona vinculada.
     *
     * @return tipo registrado
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Asigna el tipo de persona vinculada.
     *
     * @param tipo tipo a registrar
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene la comuna asociada al registro.
     *
     * @return comuna registrada
     */
    public String getComuna() {
        return comuna;
    }

    /**
     * Asigna la comuna asociada al registro.
     *
     * @param comuna comuna a registrar
     */
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    /**
     * Obtiene la informacion de contacto de la persona.
     *
     * @return contacto registrado
     */
    public Contacto getContacto() {
        return contacto;
    }

    /**
     * Asigna la informacion de contacto de la persona.
     *
     * @param contacto contacto a registrar
     */
    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    /**
     * Obtiene la direccion asociada a la persona.
     *
     * @return direccion registrada
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Asigna la direccion asociada a la persona.
     *
     * @param direccion direccion a registrar
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Entrega la informacion completa de la persona como texto.
     *
     * @return informacion formateada de la persona
     */
    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Tipo: " + tipo +
                " | Comuna: " + comuna +
                "\nContacto: " + contacto +
                "\nDireccion: " + direccion;
    }
}
