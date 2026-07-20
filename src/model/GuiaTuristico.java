package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.ValidadorDatos;

/**
 * Persona encargada de guiar experiencias turísticas.
 */
public final class GuiaTuristico extends Persona {

    private final List<String> idiomas;
    private int aniosExperiencia;

    public GuiaTuristico(String id, String nombre, Rut rut, Direccion direccion,
            String telefono, String correo, List<String> idiomas, int aniosExperiencia) {
        super(id, nombre, rut, direccion, telefono, correo);
        this.idiomas = new ArrayList<>();
        setIdiomas(idiomas);
        setAniosExperiencia(aniosExperiencia);
    }

    public List<String> getIdiomas() {
        return Collections.unmodifiableList(new ArrayList<>(idiomas));
    }

    public void setIdiomas(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            throw new IllegalArgumentException("El guía debe indicar al menos un idioma.");
        }

        List<String> idiomasValidados = new ArrayList<>();
        for (String idioma : idiomas) {
            idiomasValidados.add(ValidadorDatos.textoObligatorio(idioma, "idioma"));
        }
        this.idiomas.clear();
        this.idiomas.addAll(idiomasValidados);
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = ValidadorDatos.enteroNoNegativo(
                aniosExperiencia, "años de experiencia");
    }

    @Override
    public String mostrarDatos() {
        return "Guía turístico | " + datosComunes() + " | Idiomas: " + idiomas
                + " | Experiencia: " + aniosExperiencia
                + (aniosExperiencia == 1 ? " año" : " años");
    }

    @Override
    public String toString() {
        return mostrarDatos();
    }
}
