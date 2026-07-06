package data;

import java.util.ArrayList;
import java.util.List;
import model.ExcursionCultural;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;
import model.UbicacionServicio;

public class GestorServicios {

    private List<ServicioTuristico> servicios;

    public GestorServicios() {
        servicios = new ArrayList<>();
    }

    public void cargarServiciosPrueba() {
        servicios.clear();

        UbicacionServicio costaneraLlanquihue = new UbicacionServicio("Llanquihue", "Costanera");
        UbicacionServicio cerveceriaLocal = new UbicacionServicio("Puerto Varas", "Cervecería local");
        UbicacionServicio muelleFrutillar = new UbicacionServicio("Frutillar", "Muelle patrimonial");
        UbicacionServicio puertoVaras = new UbicacionServicio("Puerto Varas", "Muelle turístico");
        UbicacionServicio teatroLago = new UbicacionServicio("Frutillar", "Teatro del Lago");
        UbicacionServicio museoColonial = new UbicacionServicio("Frutillar", "Museo Colonial Alemán");

        servicios.add(new RutaGastronomica("Ruta Sabores del Lago", 3.5, costaneraLlanquihue, 4));
        servicios.add(new RutaGastronomica("Experiencia Cerveza Artesanal", 2.0, cerveceriaLocal, 3));
        servicios.add(new PaseoLacustre("Navegación Lago Llanquihue", 2.5, puertoVaras, "Catamarán"));
        servicios.add(new PaseoLacustre("Paseo Isla Loreley", 1.5, muelleFrutillar, "Lancha"));
        servicios.add(new ExcursionCultural("Circuito Patrimonial Frutillar", 2.0, teatroLago, "Teatro del Lago"));
        servicios.add(new ExcursionCultural("Tour Colonización Alemana", 3.0, museoColonial, "Museo Colonial Alemán"));
    }

    public List<ServicioTuristico> obtenerServicios() {
        return servicios;
    }

    public void mostrarServicios() {
        mostrarServicios("SERVICIOS TURÍSTICOS LLANQUIHUE TOUR");
    }

    public void mostrarServicios(String titulo) {
        System.out.println("===== " + titulo + " =====");
        System.out.println();

        for (ServicioTuristico servicio : servicios) {
            System.out.println(servicio.mostrarInformacion());
            System.out.println();
        }
    }
}
