# LlanquihueTourApp

Aplicación Java de consola para gestionar personas vinculadas a la agencia Llanquihue Tour. El sistema carga datos desde un archivo externo, crea objetos, los almacena en un `ArrayList` y permite mostrar, buscar y filtrar registros por consola.

## Datos del Autor

- Nombre: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orientado a Objetos I
- Fecha de entrega: 22/06/2026

## Problemática

Llanquihue Tour trabaja con guías turísticos, operadores y proveedores locales. El crecimiento de la agencia requiere una herramienta simple que permita consultar datos, evitar duplicados, validar información y automatizar búsquedas básicas.

## Funcionalidades Implementadas

- Organización del código en paquetes funcionales.
- Lectura de personas desde `resources/personas.txt`.
- Carga de objetos en `ArrayList<PersonaVinculada>`.
- Composición entre `PersonaVinculada` y `Contacto`.
- Validación de archivo inexistente o vacío.
- Validación de registros con campos incompletos o datos inválidos.
- Detección de ID duplicado.
- Búsqueda por ID.
- Búsqueda por nombre.
- Filtro por tipo de persona.
- Salida clara por consola desde `ui.Main`.

## Estructura del Proyecto

```text
LlanquihueTourApp/
|-- src/
|   |-- data/
|   |   |-- GestorArchivo.java
|   |   `-- GestorDatos.java
|   |-- model/
|   |   |-- Contacto.java
|   |   |-- PersonaVinculada.java
|   |   `-- Tour.java
|   |-- service/
|   |   `-- PersonaService.java
|   |-- ui/
|   |   `-- Main.java
|   `-- util/
|       `-- ValidadorDatos.java
|-- resources/
|   |-- personas.txt
|   `-- tours.txt
|-- docs/
|   `-- guion-video.md
|-- build.xml
|-- manifest.mf
`-- nbproject/
```


## Clases Principales

- `Contacto`: representa teléfono y email de una persona.
- `PersonaVinculada`: representa guía, operador o proveedor. Contiene un objeto `Contacto`, aplicando composición.
- `GestorArchivo`: lee `resources/personas.txt`, valida cada línea y crea objetos válidos.
- `ValidadorDatos`: centraliza reglas de validación para texto, tipo, email, teléfono e ID.
- `PersonaService`: administra la colección y entrega operaciones de listado, búsqueda y filtro.
- `Main`: ejecuta la carga, muestra los registros, realiza búsquedas y filtra guías.
- `Tour` y `GestorDatos`: se conservan como parte de la iteración anterior del proyecto.

## Archivo de Datos

Ubicación:

```text
resources/personas.txt
```

Formato obligatorio:

```text
id;nombre;tipo;comuna;telefono;email
```

Ejemplo:

```text
1;Ana Soto;guia;Puerto Varas;987654321;ana.soto@email.cl
```

Reglas del archivo:

- Cada línea debe tener exactamente 6 campos.
- El separador debe ser punto y coma `;`.
- El ID debe ser numérico, positivo y único.
- El tipo debe ser `guia`, `operador` o `proveedor`.
- Nombre, comuna, teléfono y email no pueden estar vacíos.
- El teléfono debe contener solo dígitos.
- El email debe contener `@` y dominio.

## Requisitos para Ejecutar

- Java JDK instalado.
- NetBeans o una terminal con Apache Ant disponible.
- No requiere base de datos ni frameworks externos.
- No requiere Maven ni Gradle.

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

También se puede compilar con:

```bash
ant clean jar
```

## Instrucciones para Clonar

```bash
git clone https://github.com/USUARIO/LlanquihueTourApp.git
cd LlanquihueTourApp
```

Luego abrir el proyecto en NetBeans o ejecutar `ant run` desde terminal.

## Ejemplo de Salida Esperada

```text
===== PERSONAS VINCULADAS A LLANQUIHUE TOUR =====
ID: 1 | Nombre: Ana Soto | Tipo: guia | Comuna: Puerto Varas
Contacto: Telefono: 987654321 | Email: ana.soto@email.cl

===== BUSQUEDA POR ID: 3 =====
ID: 3 | Nombre: Maria Perez | Tipo: proveedor | Comuna: Frutillar
Contacto: Telefono: 965432109 | Email: maria.perez@email.cl

===== BUSQUEDA POR NOMBRE: Soto =====
ID: 1 | Nombre: Ana Soto | Tipo: guia | Comuna: Puerto Varas
Contacto: Telefono: 987654321 | Email: ana.soto@email.cl

ID: 10 | Nombre: Felipe Soto | Tipo: guia | Comuna: Llanquihue
Contacto: Telefono: 978765432 | Email: felipe.soto@email.cl

===== FILTRO: GUIAS =====
ID: 1 | Nombre: Ana Soto | Tipo: guia | Comuna: Puerto Varas
Contacto: Telefono: 987654321 | Email: ana.soto@email.cl
```

Si no hay coincidencias, el sistema muestra:

```text
No se encontraron personas para la busqueda solicitada.
```
