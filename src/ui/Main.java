package ui;

import data.GestorEntidades;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GestorEntidades gestorEntidades = new GestorEntidades();
                gestorEntidades.cargarDatosPrueba();
                new VentanaRegistro(gestorEntidades).setVisible(true);
            }
        });
    }
}
