package model;

import utils.ValidadorDatos;

/**
 * Persona que contrata servicios turísticos.
 */
public final class Cliente extends Persona {

    private String preferencia;

    public Cliente(String id, String nombre, Rut rut, Direccion direccion,
            String telefono, String correo, String preferencia) {
        super(id, nombre, rut, direccion, telefono, correo);
        setPreferencia(preferencia);
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = ValidadorDatos.textoObligatorio(preferencia, "preferencia");
    }

    @Override
    public String mostrarDatos() {
        return "Cliente | " + datosComunes() + " | Preferencia: " + preferencia;
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
