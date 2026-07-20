package model;

import interfaces.Identificable;
import interfaces.Registrable;
import utils.ValidadorDatos;

/**
 * Base común de clientes, guías y proveedores.
 */
public abstract class Persona implements Registrable, Identificable {

    private final String id;
    private String nombre;
    private Rut rut;
    private Direccion direccion;
    private String telefono;
    private String correo;
    private boolean registrada;

    protected Persona(String id, String nombre, Rut rut, Direccion direccion,
            String telefono, String correo) {
        this.id = ValidadorDatos.id(id);
        setNombre(nombre);
        setRut(rut);
        setDireccion(direccion);
        setTelefono(telefono);
        setCorreo(correo);
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = ValidadorDatos.textoObligatorio(nombre, "nombre");
    }

    public Rut getRut() {
        return new Rut(rut);
    }

    public final void setRut(Rut rut) {
        if (registrada) {
            throw new IllegalStateException(
                    "El RUT de una persona registrada no puede modificarse.");
        }
        if (rut == null) {
            throw new IllegalArgumentException("El RUT es obligatorio.");
        }
        this.rut = new Rut(rut);
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public final void setDireccion(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public final void setTelefono(String telefono) {
        this.telefono = ValidadorDatos.telefono(telefono);
    }

    public String getCorreo() {
        return correo;
    }

    public final void setCorreo(String correo) {
        this.correo = ValidadorDatos.correo(correo);
    }

    public boolean isRegistrada() {
        return registrada;
    }

    @Override
    public void registrar() {
        registrada = true;
    }

    protected String datosComunes() {
        return "ID: " + id + " | Nombre: " + nombre + " | RUT: " + rut
                + " | Teléfono: " + telefono + " | Correo: " + correo
                + " | Dirección: " + direccion;
    }

    @Override
    public String toString() {
        return datosComunes();
    }
}
