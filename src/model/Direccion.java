package model;

import utils.ValidadorDatos;

/**
 * Domicilio reutilizable de una persona.
 */
public final class Direccion {

    private String calle;
    private int numero;
    private String comuna;
    private String region;

    public Direccion(String calle, int numero, String comuna, String region) {
        setCalle(calle);
        setNumero(numero);
        setComuna(comuna);
        setRegion(region);
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = ValidadorDatos.textoObligatorio(calle, "calle");
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = ValidadorDatos.enteroPositivo(numero, "número de dirección");
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = ValidadorDatos.textoObligatorio(comuna, "comuna");
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = ValidadorDatos.textoObligatorio(region, "región");
    }

    @Override
    public String toString() {
        return calle + " " + numero + ", " + comuna + ", " + region;
    }
}
