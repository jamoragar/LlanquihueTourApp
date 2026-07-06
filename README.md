# LlanquihueTourApp

Aplicación Java de consola para modelar servicios turísticos de Llanquihue Tour mediante herencia, composición, polimorfismo y colecciones genéricas.

## Datos del Autor

- Nombre: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orientado a Objetos I
- Fecha de entrega: 05/07/2026
 

## Objetivo

Implementar una versión polimórfica del sistema de servicios turísticos ofrecidos por Llanquihue Tour. La solución utiliza una superclase con atributos comunes, subclases con atributos propios, una clase compuesta para representar la ubicación y una colección `List<ServicioTuristico>` para gestionar distintos servicios desde una misma referencia base.

## Problemática

Llanquihue Tour ofrece rutas gastronómicas, paseos lacustres y excursiones culturales. Estos servicios comparten datos como nombre, duración y ubicación, pero cada categoría necesita información específica. Además, la agencia necesita recorrerlos de forma dinámica desde una misma colección, sin depender de arreglos separados ni condicionales por tipo para mostrar la información principal.

## Funcionalidades Implementadas

- Superclase `ServicioTuristico` con atributos comunes.
- Subclases `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`.
- Composición entre `ServicioTuristico` y `UbicacionServicio`.
- Constructores con uso de `super(nombre, duracionHoras)`.
- Sobrescritura de `toString()` con `@Override`.
- Método `mostrarInformacion()` en la superclase y sobrescrito en cada subclase.
- Colección polimórfica `List<ServicioTuristico>` en `GestorServicios`.
- Recorrido con bucle `for-each` invocando `mostrarInformacion()` desde referencias `ServicioTuristico`.
- Sobrecarga de métodos en `GestorServicios` mediante `mostrarServicios()` y `mostrarServicios(String titulo)`.
- Validaciones simples en setters para evitar datos vacíos o inválidos.
- Creación de datos de prueba desde `GestorServicios`.
- Salida clara por consola desde `ui.Main`.

## Estructura del Proyecto

```text
LlanquihueTourApp/
|-- src/
|   |-- data/
|   |   |-- GestorArchivo.java
|   |   |-- GestorDatos.java
|   |   `-- GestorServicios.java
|   |-- model/
|   |   |-- Contacto.java
|   |   |-- Direccion.java
|   |   |-- ExcursionCultural.java
|   |   |-- PaseoLacustre.java
|   |   |-- PersonaVinculada.java
|   |   |-- RutaGastronomica.java
|   |   |-- ServicioTuristico.java
|   |   |-- Tour.java
|   |   `-- UbicacionServicio.java
|   |-- service/
|   |   `-- PersonaService.java
|   |-- ui/
|   |   `-- Main.java
|   `-- util/
|       `-- ValidadorDatos.java
|-- resources/
|   |-- personas.txt
|   `-- tours.txt
|-- build.xml
|-- manifest.mf
`-- nbproject/
```

## Clases Creadas

- `UbicacionServicio`: representa la comuna y el punto de encuentro de un servicio turístico.
- `ServicioTuristico`: superclase con `nombre`, `duracionHoras` y una `UbicacionServicio` compuesta.
- `RutaGastronomica`: subclase con el atributo específico `numeroDeParadas`.
- `PaseoLacustre`: subclase con el atributo específico `tipoEmbarcacion`.
- `ExcursionCultural`: subclase con el atributo específico `lugarHistorico`.
- `GestorServicios`: gestiona una lista polimórfica de servicios, carga datos de prueba y muestra la información por consola.
- `Main`: ejecuta mostrando los servicios turísticos.

## Jerarquía de Herencia

```text
ServicioTuristico
|-- RutaGastronomica
|-- PaseoLacustre
`-- ExcursionCultural
```

Las subclases reutilizan los atributos comunes definidos en `ServicioTuristico` y agregan un atributo propio según el tipo de servicio turístico.


## Requisitos para Ejecutar

- Java JDK instalado.
- NetBeans o una terminal con Apache Ant disponible.
- No requiere base de datos, Maven, Gradle ni frameworks externos.

## Ejecución en NetBeans

1. Abrir NetBeans.
2. Seleccionar `File > Open Project`.
3. Abrir la carpeta `LlanquihueTourApp`.
4. Verificar que la clase principal sea `ui.Main`.
5. Ejecutar el proyecto.

## Ejecución desde Terminal

Desde la carpeta del proyecto:

```bash
ant run
```

La clase principal configurada para la ejecución es `ui.Main`.

## Observación

Las clases de iteraciones anteriores se conservan en el proyecto porque no interfieren con la ejecución principal de esta iteración. El recorrido principal no usa `instanceof`; la información específica se obtiene mediante sobrescritura y polimorfismo.
