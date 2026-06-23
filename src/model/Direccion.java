package model;

/**
 * Representa la direccion asociada a una persona vinculada.
 */
public class Direccion {

    private String calle;
    private int numero;
    private String ciudad;
    private String region;

    /**
     * Constructor vacio de la clase Direccion.
     */
    public Direccion() {
    }

    /**
     * Constructor de la clase Direccion.
     *
     * @param calle nombre de la calle
     * @param numero numero de la dirección
     * @param ciudad ciudad de la dirección
     * @param region region de la dirección
     */
    public Direccion(String calle, int numero, String ciudad, String region) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.region = region;
    }

    /**
     * Obtiene la calle de la dirección.
     *
     * @return calle registrada
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Asigna la calle de la dirección.
     *
     * @param calle calle a registrar
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene el número de la dirección.
     *
     * @return número registrado
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Asigna el número de la dirección.
     *
     * @param numero número a registrar
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la ciudad de la dirección.
     *
     * @return ciudad registrada
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Asigna la ciudad de la dirección.
     *
     * @param ciudad ciudad a registrar
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene la región de la dirección.
     *
     * @return región registrada
     */
    public String getRegion() {
        return region;
    }

    /**
     * Asigna la región de la dirección.
     *
     * @param region region a registrar
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Entrega la dirección completa como texto.
     *
     * @return información formateada de la dirección
     */
    @Override
    public String toString() {
        return calle + " #" + numero + ", " + ciudad + ", Región de " + region;
    }
}
