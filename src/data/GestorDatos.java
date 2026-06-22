package data;

import model.Tour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Clase encargada de leer los datos desde un archivo de texto
public class GestorDatos {

    // Metodo que recibe la ruta del archivo y retorna una lista de tours
    public ArrayList<Tour> cargarTours(String rutaArchivo) {

        // Lista dinamica donde se almacenaran los tours
        ArrayList<Tour> listaTours = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("Error: no se encontro el archivo " + rutaArchivo);
            return listaTours;
        }

        if (archivo.length() == 0) {
            System.out.println("Aviso: el archivo no contiene registros.");
            return listaTours;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {

            String linea;
            int numeroLinea = 0;

            // Se lee el archivo linea por linea
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;

                // Se separa la linea usando punto y coma ;
                String[] partes = linea.split(";", -1);

                // Se valida que la linea tenga 4 datos
                if (partes.length == 4) {

                    try {
                        // Se convierten los datos al tipo correspondiente
                        int id = Integer.parseInt(partes[0].trim());
                        String nombre = partes[1].trim();
                        String tipo = partes[2].trim();
                        double precio = Double.parseDouble(partes[3].trim());

                        // Se crea un objeto Tour con los datos leidos
                        Tour tour = new Tour(id, nombre, tipo, precio);

                        // Se agrega el objeto a la lista
                        listaTours.add(tour);

                    } catch (NumberFormatException e) {
                        System.out.println("Linea " + numeroLinea + " rechazada: formato numerico invalido.");
                    }
                } else {
                    System.out.println("Linea " + numeroLinea + " rechazada: formato incorrecto.");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontro el archivo " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Se retorna la lista con los tours cargados
        return listaTours;
    }
}
