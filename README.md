# LlanquihueTourApp

Aplicación Java de consola para modelar servicios turísticos de Llanquihue Tour mediante herencia simple y composición entre clases.

## Datos del Autor

- Nombre: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orientado a Objetos I
- Iteración: 3.1, creando jerarquías de clases con herencia simple
- Fecha de entrega: 29/06/2026

## Objetivo

Implementar una jerarquía de clases que represente distintos servicios turísticos ofrecidos por Llanquihue Tour. La solución utiliza una superclase con atributos comunes, subclases con atributos propios y una clase compuesta para representar la ubicación de cada servicio.

## Problemática

Llanquihue Tour ofrece rutas gastronómicas, paseos lacustres y excursiones culturales. Estos servicios comparten datos como nombre, duración y ubicación, pero cada categoría necesita información específica. Usar una sola clase generaría duplicidad y dificultaría la ampliación del sistema.

## Funcionalidades Implementadas

- Superclase `ServicioTuristico` con atributos comunes.
- Subclases `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`.
- Composición entre `ServicioTuristico` y `UbicacionServicio`.
- Constructores con uso de `super(nombre, duracionHoras)`.
- Sobrescritura de `toString()` con `@Override`.
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
- `GestorServicios`: crea servicios de prueba para demostrar la ejecución.
- `Main`: ejecuta la iteración y muestra los servicios por consola.

## Jerarquía de Herencia

```text
ServicioTuristico
|-- RutaGastronomica
|-- PaseoLacustre
`-- ExcursionCultural
```

Las subclases reutilizan los atributos comunes definidos en `ServicioTuristico` y agregan un atributo propio según el tipo de servicio turístico.

## Composición

La clase `ServicioTuristico` contiene un atributo privado de tipo `UbicacionServicio`:

```java
private UbicacionServicio ubicacion;
```

Esto permite asociar cada servicio turístico a una comuna y un punto de encuentro sin duplicar esos datos en cada subclase.

## Uso de super(...)

Cada subclase llama al constructor de la superclase para inicializar los atributos heredados:

```java
super(nombre, duracionHoras);
```

Luego se asigna la ubicación mediante `setUbicacion(ubicacion)` y se valida el atributo específico de la subclase.

## Uso de @Override y toString()

Cada subclase sobrescribe `toString()` para mostrar la información heredada y su atributo específico. Por ejemplo, `RutaGastronomica` muestra nombre, duración, ubicación y número de paradas.

## Validaciones

- `nombre`, `comuna`, `puntoEncuentro`, `tipoEmbarcacion` y `lugarHistorico` no pueden quedar vacíos.
- `duracionHoras` debe ser mayor que cero.
- `numeroDeParadas` debe ser mayor que cero.
- `ubicacion` no puede quedar en `null`.
- Cuando un dato no es válido, se informa por consola y se asigna un valor por defecto.

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

## Ejemplo de Salida Esperada

```text
===== SERVICIOS TURÍSTICOS LLANQUIHUE TOUR =====

Ruta Gastronómica: Nombre: Ruta Sabores del Lago | Duración: 3.5 horas | Ubicación: Llanquihue - Costanera | Número de paradas: 4

Ruta Gastronómica: Nombre: Experiencia Cerveza Artesanal | Duración: 2.0 horas | Ubicación: Puerto Varas - Cervecería local | Número de paradas: 3

Paseo Lacustre: Nombre: Navegación Lago Llanquihue | Duración: 2.5 horas | Ubicación: Puerto Varas - Muelle turístico | Tipo de embarcación: Catamarán

Paseo Lacustre: Nombre: Paseo Isla Loreley | Duración: 1.5 horas | Ubicación: Frutillar - Muelle patrimonial | Tipo de embarcación: Lancha

Excursión Cultural: Nombre: Circuito Patrimonial Frutillar | Duración: 2.0 horas | Ubicación: Frutillar - Teatro del Lago | Lugar histórico: Teatro del Lago

Excursión Cultural: Nombre: Tour Colonización Alemana | Duración: 3.0 horas | Ubicación: Frutillar - Museo Colonial Alemán | Lugar histórico: Museo Colonial Alemán
```

## Observación

Las clases de iteraciones anteriores se conservan en el proyecto porque no interfieren con la ejecución principal de esta iteración.
