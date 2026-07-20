package model;

import utils.ValidadorDatos;

/**
 * Persona de contacto de un proveedor externo.
 */
public final class Proveedor extends Persona {

    private String razonSocial;
    private String tipoServicio;

    public Proveedor(String id, String nombre, Rut rut, Direccion direccion,
            String telefono, String correo, String razonSocial, String tipoServicio) {
        super(id, nombre, rut, direccion, telefono, correo);
        setRazonSocial(razonSocial);
        setTipoServicio(tipoServicio);
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = ValidadorDatos.textoObligatorio(razonSocial, "razón social");
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = ValidadorDatos.textoObligatorio(
                tipoServicio, "tipo de servicio ofrecido");
    }

    @Override
    public String mostrarDatos() {
        return "Proveedor | " + datosComunes() + " | Razón social: " + razonSocial
                + " | Servicio ofrecido: " + tipoServicio;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
