# LlanquihueTourApp

Aplicacion Java de consola para gestionar personas vinculadas a la agencia Llanquihue Tour. El sistema carga datos desde un archivo externo, crea objetos, los almacena en un `ArrayList` y permite mostrar, buscar y filtrar registros por consola.

## Datos del Autor

- Nombre: Javier A. Moraga Rojas
- Carrera: Analista Programador Computacional
- Asignatura: Desarrollo Orietado a Obejtos I
- Fecha de entrega: 22/06/2026

## Problematica

Llanquihue Tour trabaja con guias turisticos, operadores y proveedores locales. El crecimiento de la agencia requiere una herramienta simple que permita consultar datos, evitar duplicados, validar informacion y automatizar busquedas basicas.

## Funcionalidades Implementadas

- Organizacion del codigo en paquetes funcionales.
- Lectura de personas desde `resources/personas.txt`.
- Carga de objetos en `ArrayList<PersonaVinculada>`.
- Composicion entre `PersonaVinculada` y `Contacto`.
- Validacion de archivo inexistente o vacio.
- Validacion de registros con campos incompletos o datos invalidos.
- Deteccion de ID duplicado.
- Busqueda por ID.
- Busqueda por nombre.
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

Se mantiene `ui.Main` como clase principal para continuar la estructura usada en la primera iteracion del proyecto. El paquete `ui` cumple el rol de punto de entrada y coordinador de la ejecucion por consola.

## Clases Principales

- `Contacto`: representa telefono y email de una persona.
- `PersonaVinculada`: representa guia, operador o proveedor. Contiene un objeto `Contacto`, aplicando composicion.
- `GestorArchivo`: lee `resources/personas.txt`, valida cada linea y crea objetos validos.
- `ValidadorDatos`: centraliza reglas de validacion para texto, tipo, email, telefono e ID.
- `PersonaService`: administra la coleccion y entrega operaciones de listado, busqueda y filtro.
- `Main`: ejecuta la carga, muestra los registros, realiza busquedas y filtra guias.
- `Tour` y `GestorDatos`: se conservan como parte de la iteracion anterior del proyecto.

## Archivo de Datos

Ubicacion:

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

- Cada linea debe tener exactamente 6 campos.
- El separador debe ser punto y coma `;`.
- El ID debe ser numerico, positivo y unico.
- El tipo debe ser `guia`, `operador` o `proveedor`.
- Nombre, comuna, telefono y email no pueden estar vacios.
- El telefono debe contener solo digitos.
- El email debe contener `@` y dominio.

## Validaciones Implementadas

- Archivo inexistente: informa el error y finaliza de forma controlada.
- Archivo vacio: informa que no hay registros.
- Linea con cantidad incorrecta de campos: rechaza la linea y continua.
- ID no numerico o menor que 1: rechaza la linea.
- ID duplicado: conserva el primer registro y rechaza el repetido.
- Tipo desconocido: rechaza la linea.
- Telefono o email invalidos: rechaza la linea.
- Integridad de carga: una linea incorrecta no detiene la lectura completa.

## Requisitos Para Ejecutar

- Java JDK instalado.
- NetBeans o una terminal con Apache Ant disponible.
- No requiere base de datos ni frameworks externos.
- No requiere Maven ni Gradle.

## Ejecucion En NetBeans

1. Abrir NetBeans.
2. Seleccionar `File > Open Project`.
3. Abrir la carpeta `LlanquihueTourApp`.
4. Verificar que la clase principal sea `ui.Main`.
5. Ejecutar el proyecto.

## Ejecucion Desde Terminal

Desde la carpeta del proyecto:

```bash
ant run
```

Tambien se puede compilar con:

```bash
ant clean jar
```

## Instrucciones Para Clonar

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

