package model;

/**
 * Reúne los datos comunes de los recursos operativos de la agencia.
 */
public class RecursoAgencia {

    private int id;
    private String nombre;

    public RecursoAgencia() {
        this(1, "Sin información");
    }

    public RecursoAgencia(int id, String nombre) {
        setId(id);
        setNombre(nombre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            this.id = 1;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre.trim();
        } else {
            this.nombre = "Sin información";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }
}
