package ui;

import data.GestorDatos;
import model.Tour;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Ruta del archivo de texto
        String archivoTours = "resources/tours.txt";

        // Se crea un objeto de la clase GestorDatos
        GestorDatos gestor = new GestorDatos();

        // Se carga la lista de tours desde el archivo
        ArrayList<Tour> tours = gestor.cargarTours(archivoTours);

        // Mostrar todos los tours cargados
        System.out.println("===== CATALOGO COMPLETO DE LLANQUIHUE TOUR =====");

        // Recorrer una lista de objetos
        for (Tour tour : tours) {
            System.out.println(tour);
        }

        // Filtrar tours de tipo gastronomico
        System.out.println("\n===== TOURS GASTRONOMICOS =====");

        for (Tour tour : tours) {
            if (tour.getTipo().equalsIgnoreCase("gastronomico")) {
                System.out.println(tour);
            }
        }
    }
}
