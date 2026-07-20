# LlanquihueTourApp

- **Versión:** Evaluación Final Transversal
- **Clase principal:** `app.Main`
- **Fecha de entrega:** 19 de julio de 2026

## Datos académicos

- Autor: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orientado a Objetos I
- Repositorio: <https://github.com/jamoragar/LlanquihueTourApp>

## Descripción

Llanquihue Tour administra excursiones, rutas gastronómicas y paseos lacustres. El prototipo reemplaza registros manuales por un modelo orientado a objetos que carga datos desde `resources/*.txt` y gestiona personas, servicios, itinerarios y reservas mediante una interfaz de consola con entradas validadas.

La aplicación no utiliza base de datos ni dependencias externas. Los errores recuperables relacionados con archivos o entradas se informan sin cerrar el programa, y cada fila válida se conserva aunque otra fila del mismo archivo sea defectuosa.

## Estructura

```text
LlanquihueTourApp/
|-- src/
|   |-- app/
|   |   `-- Main.java
|   |-- data/
|   |   |-- CargadorDatos.java
|   |   |-- GestorEntidades.java
|   |   |-- GestorReservas.java
|   |   |-- InformeCarga.java
|   |   `-- Repositorio.java
|   |-- exceptions/
|   |   `-- RutInvalidoException.java
|   |-- interfaces/
|   |   |-- Identificable.java
|   |   `-- Registrable.java
|   |-- model/
|   |   |-- Persona.java y sus subclases
|   |   |-- ServicioTuristico.java y sus subclases
|   |   `-- Rut, Direccion, Actividad, Itinerario y Reserva
|   `-- utils/
|       |-- LectorArchivos.java
|       `-- ValidadorDatos.java
|-- resources/
|   |-- clientes.txt
|   |-- guias.txt
|   |-- proveedores.txt
|   |-- servicios.txt
|   `-- reservas.txt
|-- build.xml
|-- manifest.mf
`-- nbproject/
```

## Modelo y POO

### Composición

- `Persona` contiene un `Rut` validado y una `Direccion`.
- `Itinerario` encapsula una lista no modificable de objetos `Actividad`.
- `ServicioTuristico` contiene su `Itinerario`.
- `Reserva` referencia un `Cliente` y un `ServicioTuristico` sin duplicar sus datos.

### Herencia

```text
Persona
|-- Cliente
|-- GuiaTuristico
`-- Proveedor

ServicioTuristico
|-- RutaGastronomica
|-- PaseoLacustre
`-- ExcursionCultural
```

### Interfaces y polimorfismo

`Registrable` declara `registrar()` y `mostrarDatos()`.
`Identificable` declara `getId()` para desacoplar el repositorio de cada clase concreta. `GestorEntidades` reúne personas y servicios en una `List<Registrable>`, invoca `mostrarDatos()` polimórficamente y usa `instanceof` solo para indicar la categoría correspondiente al tipo real.

### Sobrecarga de precio

`ServicioTuristico` implementa y utiliza estas tres variantes:

```java
calcularPrecio();
calcularPrecio(int cantidadPersonas);
calcularPrecio(int cantidadPersonas, boolean incluyeTransporte);
```

`Reserva` utiliza la tercera firma para calcular su total. El gestor valida los cupos, los descuenta al crear la reserva y los repone al eliminarla.

### Repositorio genérico

`Repositorio<T extends Identificable>` es la estructura reutilizable del proyecto. Encapsula un `ArrayList<T>` para mantener el orden y un `HashMap<String, T>` para buscar por ID. Rechaza elementos nulos y duplicados, admite acceso por ID o índice, elimina de ambas estructuras y retorna listados no modificables.

### Validación de RUT

`Rut` admite el formato `12345678-5`, elimina los puntos, si están presentes, y valida la longitud y el dígito verificador mediante el módulo 11. Un valor inválido produce `RutInvalidoException`, tanto en el constructor como en sus setters.

## Archivos de datos

Todos los archivos utilizan UTF-8, separan campos con punto y coma y tienen un encabezado.

```text
clientes.txt
id;rut;nombre;telefono;correo;calle;numero;comuna;preferencia

guias.txt
id;rut;nombre;telefono;correo;calle;numero;comuna;idiomas;aniosExperiencia

proveedores.txt
id;rut;nombre;telefono;correo;calle;numero;comuna;razonSocial;tipoServicio

servicios.txt
id;tipo;nombre;destino;precioBase;cupos;detalleEspecifico

reservas.txt
idReserva;idCliente;idServicio;fecha;cantidadPersonas
```

Las fechas de `reservas.txt` y las ingresadas desde la consola usan el formato `DD-MM-YYYY`, por ejemplo, `18-07-2026`.

El orden de carga es el siguiente: clientes, guías, proveedores, servicios y reservas. Una reserva se omite si sus referencias no existen, si su fecha no es válida, si la cantidad de personas no es válida o si el servicio no dispone de cupos suficientes.


## Requisitos

- JDK 21 o superior.
- NetBeans con soporte para proyectos Java SE/Ant.
- Apache Ant para ejecutar la aplicación desde la terminal.

## Ejecución en NetBeans

1. Clonar o descargar el repositorio.
2. Abrir NetBeans y seleccionar `File > Open Project`.
3. Elegir la carpeta `LlanquihueTourApp`.
4. Verificar que el proyecto utilice JDK 21 o superior.
5. Ejecutar `Clean and Build Project`.
6. Ejecutar el proyecto. La clase configurada es `app.Main`.

La ruta `resources/` se resuelve de forma relativa a la raíz del proyecto. Para cargar los archivos, NetBeans debe usar esa carpeta como directorio de trabajo al ejecutar el proyecto, que es su configuración habitual.

## Ejecución desde terminal

```bash
ant clean compile
ant run
```

También puede compilarse manualmente:

```bash
javac --release 21 -d build/manual-classes src/app/*.java src/data/*.java \
  src/exceptions/*.java src/interfaces/*.java src/model/*.java src/utils/*.java
java -cp build/manual-classes app.Main
```

## Alcance

El proyecto es un prototipo académico en memoria.
