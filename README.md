# LlanquihueTourApp

Aplicación Java con GUI básica para gestionar recursos y servicios de la agencia Llanquihue Tour.

## Datos del Autor

- Nombre: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orientado a Objetos I
- Fecha de entrega: 05/07/2026
- Versión activa evaluada: Semana 8

## Descripción

Esta iteración amplía el sistema de servicios turísticos de Llanquihue Tour. Ahora permite registrar guías turísticos, vehículos, colaboradores externos y servicios turísticos dentro de una misma colección dinámica. La interfaz gráfica permite ingresar nuevas entidades y revisar todos los registros sin usar la consola.

## Problemática Abordada

La agencia necesitaba administrar entidades operativas distintas que no comparten una misma clase padre con los servicios turísticos. La solución usa una interfaz común para tratarlas de forma unificada, conserva las jerarquías existentes y diferencia el tipo real de cada objeto al mostrarlo.

## Clases e Interfaces Principales

- `Registrable`: contrato común que declara `mostrarResumen()`.
- `RecursoAgencia`: superclase con los atributos comunes `id` y `nombre`.
- `GuiaTuristico`: hereda de `RecursoAgencia` e implementa `Registrable`.
- `Vehiculo`: hereda de `RecursoAgencia` e implementa `Registrable`.
- `ColaboradorExterno`: hereda de `RecursoAgencia` e implementa `Registrable`.
- `ServicioTuristico`: mantiene la jerarquía anterior e implementa `Registrable`.
- `RutaGastronomica`, `PaseoLacustre` y `ExcursionCultural`: subclases de `ServicioTuristico`.
- `GestorEntidades`: administra los registros mediante `ArrayList<Registrable>`.
- `VentanaRegistro`: GUI Swing para ingresar y visualizar entidades.
- `ui.Main`: clase principal.

## Herencia

```text
RecursoAgencia
|-- GuiaTuristico
|-- Vehiculo
`-- ColaboradorExterno

ServicioTuristico
|-- RutaGastronomica
|-- PaseoLacustre
`-- ExcursionCultural
```

Las tres subclases de `RecursoAgencia` reutilizan el identificador y el nombre. La jerarquía de servicios turísticos se mantiene como continuidad de las iteraciones anteriores.

## Registrable y mostrarResumen()

La interfaz `Registrable` define un comportamiento común para cualquier entidad que pueda almacenarse en el registro:

```java
public interface Registrable {
    String mostrarResumen();
}
```

Cada clase implementa `mostrarResumen()` con los datos relevantes para personal no técnico. Por ejemplo, un guía turístico muestra idioma y años de experiencia, mientras que un vehículo muestra patente y capacidad.

## Colección Polimórfica e instanceof

`GestorEntidades` declara la colección central de la aplicación:

```java
private ArrayList<Registrable> registros;
```

La lista puede almacenar objetos de distintas clases porque todos cumplen el contrato `Registrable`. Durante el recorrido se invoca `mostrarResumen()` desde la referencia común y se usa `instanceof` para mostrar una categoría específica para `GuiaTuristico`, `Vehiculo`, `ColaboradorExterno` o `ServicioTuristico`.

## GUI

La clase `ui.VentanaRegistro` usa Swing y ofrece un formulario con los siguientes tipos:

- Guía turístico.
- Vehículo.
- Colaborador externo.

La ventana valida campos vacíos y datos numéricos antes de crear el objeto. Los botones permiten agregar entidades, mostrar el resumen de registros, limpiar los campos y salir de forma confirmada. Al iniciar se cargan datos de prueba de los distintos tipos para evidenciar la colección polimórfica.

## Estructura del Proyecto

```text
LlanquihueTourApp/
|-- src/
|   |-- data/
|   |   |-- GestorEntidades.java
|   |   `-- GestorServicios.java
|   |-- model/
|   |   |-- Registrable.java
|   |   |-- RecursoAgencia.java
|   |   |-- GuiaTuristico.java
|   |   |-- Vehiculo.java
|   |   |-- ColaboradorExterno.java
|   |   |-- ServicioTuristico.java
|   |   |-- RutaGastronomica.java
|   |   |-- PaseoLacustre.java
|   |   `-- ExcursionCultural.java
|   `-- ui/
|       |-- Main.java
|       `-- VentanaRegistro.java
|-- build.xml
|-- manifest.mf
`-- nbproject/
```

## Clases de Continuidad

- `UbicacionServicio`: representa la comuna y el punto de encuentro de un servicio turístico.
- `ServicioTuristico`: superclase con `nombre`, `duracionHoras` y una `UbicacionServicio` compuesta.
- `RutaGastronomica`: subclase con el atributo específico `numeroDeParadas`.
- `PaseoLacustre`: subclase con el atributo específico `tipoEmbarcacion`.
- `ExcursionCultural`: subclase con el atributo específico `lugarHistorico`.
- `GestorServicios`: gestiona una lista polimórfica de servicios, carga datos de prueba y muestra la información por consola.
- `ui.Main`: inicia la GUI de registro de entidades.

## Jerarquía de Servicios Turísticos

```text
ServicioTuristico
|-- RutaGastronomica
|-- PaseoLacustre
`-- ExcursionCultural
```

Las subclases reutilizan los atributos comunes definidos en `ServicioTuristico` y agregan un atributo propio según el tipo de servicio turístico.


## Requisitos de Ejecución

- Java JDK instalado.
- NetBeans o una terminal con Apache Ant disponible.
- No requiere base de datos, Maven, Gradle ni frameworks externos.

## Ejecución
La clase principal es `ui.Main`.

### Desde NetBeans

1. Abrir NetBeans.
2. Seleccionar `File > Open Project`.
3. Abrir la carpeta `LlanquihueTourApp`.
4. Verificar que la clase principal configurada sea `ui.Main`.
5. Ejecutar el proyecto.

### Desde Terminal

Desde la carpeta del proyecto ejecutar:

```bash
ant run
```

Se requiere Java JDK y Apache Ant. El proyecto no usa base de datos ni dependencias externas.

## Observación

Las clases de iteraciones anteriores se conservan en el proyecto porque no interfieren con la ejecución principal de esta iteración. El recorrido actual usa polimorfismo mediante `mostrarResumen()` y diferencia las entidades con `instanceof` desde `GestorEntidades`.

## Ejemplo de Uso

1. Seleccionar `Guía turístico` en el formulario.
2. Ingresar ID `5`, nombre `Paula Díaz`, idioma `Portugués` y experiencia `3`.
3. Presionar `Agregar entidad`.
4. Presionar `Mostrar registros` para ver el resumen y la categoría detectada con `instanceof`.

Ejemplo de salida:

```text
Guía turístico: Camila Soto | Idioma: Inglés | Experiencia: 5 años
Categoría: Guía turístico

Vehículo: Van Ejecutiva | Patente: LL-2025 | Capacidad: 12 pasajeros
Categoría: Vehículo
```
