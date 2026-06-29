package ui;

import data.GestorServicios;
import model.ServicioTuristico;

public class Main {

    public static void main(String[] args) {

        GestorServicios gestorServicios = new GestorServicios();
        ServicioTuristico[] servicios = gestorServicios.crearServiciosPrueba();

        System.out.println("===== SERVICIOS TURÍSTICOS LLANQUIHUE TOUR =====");
        System.out.println();

        for (ServicioTuristico servicio : servicios) {
            System.out.println(servicio);
            System.out.println();
        }
    }
}
