package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import interfaces.Identificable;
import utils.ValidadorDatos;

/**
 * Colección genérica que conserva el orden de inserción y un índice por ID.
 *
 * @param <T> tipo de entidad identificable
 */
public class Repositorio<T extends Identificable> {

    private final List<T> elementos;
    private final Map<String, T> indicePorId;

    public Repositorio() {
        elementos = new ArrayList<>();
        indicePorId = new HashMap<>();
    }

    public boolean agregar(T elemento) {
        if (elemento == null) {
            return false;
        }

        String id = clave(elemento.getId());
        if (indicePorId.containsKey(id)) {
            return false;
        }

        elementos.add(elemento);
        indicePorId.put(id, elemento);
        return true;
    }

    public T obtenerPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }
        return indicePorId.get(clave(id));
    }

    public T obtenerPorIndice(int indice) {
        if (indice < 0 || indice >= elementos.size()) {
            throw new IndexOutOfBoundsException(
                    "Índice " + indice + " fuera de rango. Elementos disponibles: "
                    + elementos.size() + ".");
        }
        return elementos.get(indice);
    }

    public boolean eliminarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        T eliminado = indicePorId.remove(clave(id));
        return eliminado != null && elementos.remove(eliminado);
    }

    public List<T> listar() {
        return Collections.unmodifiableList(new ArrayList<>(elementos));
    }

    public int cantidad() {
        return elementos.size();
    }

    public boolean estaVacio() {
        return elementos.isEmpty();
    }

    private String clave(String id) {
        return ValidadorDatos.textoObligatorio(id, "ID").toUpperCase(Locale.ROOT);
    }
}
