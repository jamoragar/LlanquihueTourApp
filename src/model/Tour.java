package model;

/**
 * Representa un tour turistico de la primera iteracion del proyecto.
 */
public class Tour {

    private int id;
    private String nombre;
    private String tipo;
    private double precio;

    /**
     * Constructor vacio de la clase Tour.
     */
    public Tour() {
    }

    /**
     * Constructor de la clase Tour.
     *
     * @param id identificador del tour
     * @param nombre nombre del tour
     * @param tipo tipo de tour
     * @param precio precio del tour
     */
    public Tour(int id, String nombre, String tipo, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    /**
     * Obtiene el identificador del tour.
     *
     * @return id registrado
     */
    public int getId() {
        return id;
    }

    /**
     * Asigna el identificador del tour.
     *
     * @param id identificador a registrar
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del tour.
     *
     * @return nombre registrado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del tour.
     *
     * @param nombre nombre a registrar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el tipo del tour.
     *
     * @return tipo registrado
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Asigna el tipo del tour.
     *
     * @param tipo tipo a registrar
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el precio del tour.
     *
     * @return precio registrado
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio del tour.
     *
     * @param precio precio a registrar
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Entrega la informacion del tour como texto.
     *
     * @return informacion formateada del tour
     */
    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Tipo: " + tipo +
                " | Precio: $" + precio;
    }
}
