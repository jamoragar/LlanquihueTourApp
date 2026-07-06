# LlanquihueTourApp

Aplicación Java de consola para modelar servicios turísticos de Llanquihue Tour mediante herencia, composición, polimorfismo y colecciones genéricas.

## Datos del Autor

- Nombre: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orientado a Objetos I
- Iteración: 4.0, aplicando polimorfismo y colecciones genéricas
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
- `Main`: ejecuta la iteración 4.0 cargando y mostrando los servicios turísticos.

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

## Polimorfismo

La colección principal se declara usando la superclase:

```java
private List<ServicioTuristico> servicios;
```

En esta lista se almacenan objetos concretos de `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`. Al recorrerla, Java ejecuta el método `mostrarInformacion()` correspondiente al tipo real del objeto, aunque la referencia usada sea de tipo `ServicioTuristico`.

## Método mostrarInformacion()

`ServicioTuristico` define el método común `mostrarInformacion()`. Cada subclase lo sobrescribe con `@Override` para entregar la información específica de su tipo de servicio turístico.

El recorrido principal se realiza desde `GestorServicios`:

```java
for (ServicioTuristico servicio : servicios) {
    System.out.println(servicio.mostrarInformacion());
}
```

## Uso de super(...)

Cada subclase llama al constructor de la superclase para inicializar los atributos heredados:

```java
super(nombre, duracionHoras);
```

Luego se asigna la ubicación mediante `setUbicacion(ubicacion)` y se valida el atributo específico de la subclase.

## Uso de @Override, toString() y sobrescritura

Cada subclase sobrescribe `mostrarInformacion()` y mantiene `toString()` como apoyo para la representación textual. Por ejemplo, `RutaGastronomica` muestra nombre, duración, ubicación y número de paradas.

## Sobrecarga de Métodos

`GestorServicios` incluye dos versiones del método `mostrarServicios`:

```java
public void mostrarServicios()
public void mostrarServicios(String titulo)
```

La primera usa un título por defecto y la segunda permite recibir un encabezado personalizado.

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

La clase principal configurada para la ejecución es `ui.Main`.

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

Cada línea de servicio se obtiene mediante la invocación polimórfica de `mostrarInformacion()`.

## Observación

Las clases de iteraciones anteriores se conservan en el proyecto porque no interfieren con la ejecución principal de esta iteración. El recorrido principal no usa `instanceof`; la información específica se obtiene mediante sobrescritura y polimorfismo.
