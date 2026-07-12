package model;

/**
 * Representa un guía turístico disponible para los servicios de la agencia.
 */
public class GuiaTuristico extends RecursoAgencia implements Registrable {

    private String idioma;
    private int aniosExperiencia;

    public GuiaTuristico() {
        this(1, "Sin información", "Sin información", 1);
    }

    public GuiaTuristico(int id, String nombre, String idioma, int aniosExperiencia) {
        super(id, nombre);
        setIdioma(idioma);
        setAniosExperiencia(aniosExperiencia);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        if (idioma != null && !idioma.trim().isEmpty()) {
            this.idioma = idioma.trim();
        } else {
            this.idioma = "Sin información";
        }
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        if (aniosExperiencia >= 0) {
            this.aniosExperiencia = aniosExperiencia;
        } else {
            this.aniosExperiencia = 0;
        }
    }

    @Override
    public String mostrarResumen() {
        return "Guía turístico: " + getNombre()
                + " | Idioma: " + idioma
                + " | Experiencia: " + aniosExperiencia + " años";
    }

    @Override
    public String toString() {
        return super.toString() + " | Idioma: " + idioma
                + " | Experiencia: " + aniosExperiencia + " años";
    }
}
