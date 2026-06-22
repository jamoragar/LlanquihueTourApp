package model;

public class PersonaVinculada {

    private int id;
    private String nombre;
    private String tipo;
    private String comuna;
    private Contacto contacto;

    public PersonaVinculada() {
    }

    public PersonaVinculada(int id, String nombre, String tipo, String comuna, Contacto contacto) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.comuna = comuna;
        this.contacto = contacto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nombre: " + nombre +
                " | Tipo: " + tipo +
                " | Comuna: " + comuna +
                "\nContacto: " + contacto;
    }
}
