package ui;

import data.GestorArchivo;
import model.PersonaVinculada;
import service.PersonaService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Ruta del archivo externo desde donde se cargan las personas vinculadas.
        String archivoPersonas = "resources/personas.txt";

        // Se crea el gestor encargado de leer y validar los datos del archivo.
        GestorArchivo gestorArchivo = new GestorArchivo();
        ArrayList<PersonaVinculada> personas = gestorArchivo.cargarPersonas(archivoPersonas);

        // Si no hay datos validos, el programa finaliza de forma controlada.
        if (personas.isEmpty()) {
            System.out.println("No existen datos validos para ejecutar busquedas o filtros.");
            return;
        }

        // Servicio que administra la coleccion y permite listar, buscar y filtrar.
        PersonaService personaService = new PersonaService(personas);

        // Recorrido completo de la coleccion cargada desde el archivo.
        System.out.println("===== PERSONAS VINCULADAS A LLANQUIHUE TOUR =====");
        personaService.mostrarTodas();

        // Busqueda directa por identificador.
        System.out.println("===== BUSQUEDA POR ID: 3 =====");
        PersonaVinculada personaEncontrada = personaService.buscarPorId(3);
        mostrarResultado(personaEncontrada);

        // Busqueda parcial por nombre o apellido (Soto).
        System.out.println("\n===== BUSQUEDA POR NOMBRE: Soto =====");
        ArrayList<PersonaVinculada> personasPorNombre = personaService.buscarPorNombre("Soto");
        mostrarLista(personasPorNombre);

        // Filtro por tipo de persona vinculada.
        System.out.println("\n===== FILTRO: GUIAS =====");
        ArrayList<PersonaVinculada> guias = personaService.filtrarPorTipo("guia");
        mostrarLista(guias);
    }

    // Muestra una persona encontrada o informa que no existe coincidencia.
    private static void mostrarResultado(PersonaVinculada persona) {
        if (persona == null) {
            System.out.println("No se encontraron personas para la busqueda solicitada.");
        } else {
            System.out.println(persona);
        }
    }

    // Muestra una lista de resultados o informa que no hay coincidencias.
    private static void mostrarLista(ArrayList<PersonaVinculada> personas) {
        if (personas.isEmpty()) {
            System.out.println("No se encontraron personas para la busqueda solicitada.");
        } else {
            for (PersonaVinculada persona : personas) {
                System.out.println(persona);
                System.out.println();
            }
        }
    }
}
