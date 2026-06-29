package data;

import model.ExcursionCultural;
import model.PaseoLacustre;
import model.RutaGastronomica;
import model.ServicioTuristico;
import model.UbicacionServicio;

public class GestorServicios {

    public ServicioTuristico[] crearServiciosPrueba() {
        UbicacionServicio costaneraLlanquihue = new UbicacionServicio("Llanquihue", "Costanera");
        UbicacionServicio cerveceriaLocal = new UbicacionServicio("Puerto Varas", "Cervecería local");
        UbicacionServicio muelleFrutillar = new UbicacionServicio("Frutillar", "Muelle patrimonial");
        UbicacionServicio puertoVaras = new UbicacionServicio("Puerto Varas", "Muelle turístico");
        UbicacionServicio teatroLago = new UbicacionServicio("Frutillar", "Teatro del Lago");
        UbicacionServicio museoColonial = new UbicacionServicio("Frutillar", "Museo Colonial Alemán");

        ServicioTuristico[] servicios = {
            new RutaGastronomica("Ruta Sabores del Lago", 3.5, costaneraLlanquihue, 4),
            new RutaGastronomica("Experiencia Cerveza Artesanal", 2.0, cerveceriaLocal, 3),
            new PaseoLacustre("Navegación Lago Llanquihue", 2.5, puertoVaras, "Catamarán"),
            new PaseoLacustre("Paseo Isla Loreley", 1.5, muelleFrutillar, "Lancha"),
            new ExcursionCultural("Circuito Patrimonial Frutillar", 2.0, teatroLago, "Teatro del Lago"),
            new ExcursionCultural("Tour Colonización Alemana", 3.0, museoColonial, "Museo Colonial Alemán")
        };

        return servicios;
    }
}
