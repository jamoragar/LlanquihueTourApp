package data;

import model.Tour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Clase encargada de leer los datos desde un archivo de texto
public class GestorDatos {

    // Metodo que recibe la ruta del archivo y retorna una lista de tours
    public ArrayList<Tour> cargarTours(String rutaArchivo) {

        // Lista dinamica donde se almacenaran los tours
        ArrayList<Tour> listaTours = new ArrayList<>();

        try {
            // Se abre el archivo para lectura
            BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo));

            // Lectura linea por linea
            String linea;

            // Se lee el archivo linea por linea
            while ((linea = lector.readLine()) != null) {

                // Se separa la linea usando punto y coma ;
                String[] partes = linea.split(";");

                // Se valida que la linea tenga 4 datos
                if (partes.length == 4) {

                    // Se convierten los datos al tipo correspondiente
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String tipo = partes[2];
                    double precio = Double.parseDouble(partes[3]);

                    // Se crea un objeto Tour con los datos leidos
                    Tour tour = new Tour(id, nombre, tipo, precio);

                    // Se agrega el objeto a la lista
                    listaTours.add(tour);
                }
            }

            // Se cierra el lector
            lector.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Se retorna la lista con los tours cargados
        return listaTours;
    }
}
