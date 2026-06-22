package service;

import model.PersonaVinculada;

import java.util.ArrayList;

public class PersonaService {

    private ArrayList<PersonaVinculada> personas;

    public PersonaService(ArrayList<PersonaVinculada> personas) {
        this.personas = personas;
    }

    public void mostrarTodas() {
        if (personas.isEmpty()) {
            System.out.println("No hay personas cargadas.");
        } else {
            for (PersonaVinculada persona : personas) {
                System.out.println(persona);
                System.out.println();
            }
        }
    }

    public PersonaVinculada buscarPorId(int id) {
        for (PersonaVinculada persona : personas) {
            if (persona.getId() == id) {
                return persona;
            }
        }

        return null;
    }

    public ArrayList<PersonaVinculada> buscarPorNombre(String nombre) {
        ArrayList<PersonaVinculada> personasEncontradas = new ArrayList<>();

        if (nombre == null || nombre.trim().isEmpty()) {
            return personasEncontradas;
        }

        String nombreBuscado = nombre.trim().toLowerCase();

        for (PersonaVinculada persona : personas) {
            if (persona.getNombre().toLowerCase().contains(nombreBuscado)) {
                personasEncontradas.add(persona);
            }
        }

        return personasEncontradas;
    }

    public ArrayList<PersonaVinculada> filtrarPorTipo(String tipo) {
        ArrayList<PersonaVinculada> personasFiltradas = new ArrayList<>();

        if (tipo == null || tipo.trim().isEmpty()) {
            return personasFiltradas;
        }

        for (PersonaVinculada persona : personas) {
            if (persona.getTipo().equalsIgnoreCase(tipo.trim())) {
                personasFiltradas.add(persona);
            }
        }

        return personasFiltradas;
    }

    public boolean existeId(int id) {
        return buscarPorId(id) != null;
    }
}
