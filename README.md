# LlanquihueTourApp

Aplicacion Java de consola que carga tours turisticos desde un archivo de texto, crea objetos `Tour`, los almacena en un `ArrayList` y muestra un filtro por tipo gastronomico.

## Estructura

```text
LlanquihueTourApp/
|-- src/
|   |-- model/
|   |   `-- Tour.java
|   |-- data/
|   |   `-- GestorDatos.java
|   `-- ui/
|       `-- Main.java
`-- resources/
    `-- tours.txt
```

## Ejecucion

1. Abrir el proyecto en NetBeans.
2. Verificar que la clase principal sea `ui.Main`.
3. Ejecutar el proyecto.

El programa lee los datos desde `resources/tours.txt`, imprime el catalogo completo y luego muestra solamente los tours de tipo `gastronomico`.
