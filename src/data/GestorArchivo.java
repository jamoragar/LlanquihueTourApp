package data;

import model.Contacto;
import model.PersonaVinculada;
import util.ValidadorDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivo {

    public ArrayList<PersonaVinculada> cargarPersonas(String rutaArchivo) {
        ArrayList<PersonaVinculada> personas = new ArrayList<>();
        ArrayList<Integer> idsCargados = new ArrayList<>();
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("Error: no se encontro el archivo " + rutaArchivo);
            return personas;
        }

        if (archivo.length() == 0) {
            System.out.println("Aviso: el archivo no contiene registros.");
            return personas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    System.out.println("Linea " + numeroLinea + " rechazada: registro vacio.");
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 6) {
                    System.out.println("Linea " + numeroLinea + " rechazada: debe contener 6 campos.");
                    continue;
                }

                try {
                    int id = ValidadorDatos.convertirId(datos[0]);
                    String nombre = datos[1].trim();
                    String tipo = datos[2].trim();
                    String comuna = datos[3].trim();
                    String telefono = datos[4].trim();
                    String email = datos[5].trim();

                    if (idsCargados.contains(id)) {
                        System.out.println("Linea " + numeroLinea + " rechazada: ID duplicado " + id + ".");
                        continue;
                    }

                    if (!ValidadorDatos.esTextoValido(nombre) ||
                            !ValidadorDatos.esTextoValido(comuna) ||
                            !ValidadorDatos.esTelefonoValido(telefono) ||
                            !ValidadorDatos.esEmailValido(email) ||
                            !ValidadorDatos.esTipoValido(tipo)) {
                        System.out.println("Linea " + numeroLinea + " rechazada: datos invalidos.");
                        continue;
                    }

                    Contacto contacto = new Contacto(telefono, email);
                    PersonaVinculada persona = new PersonaVinculada(id, nombre, tipo, comuna, contacto);

                    personas.add(persona);
                    idsCargados.add(id);

                } catch (NumberFormatException e) {
                    System.out.println("Linea " + numeroLinea + " rechazada: ID invalido. " + e.getMessage());
                }
            }

            if (personas.size() < 5) {
                System.out.println("Aviso: se cargaron menos de 5 registros validos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: no se encontro el archivo " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return personas;
    }
}
