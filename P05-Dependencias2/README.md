# Guía P05-Dependencias2

## Ejercicio 1

### Dependencias externas

1. getCalendario() -> clase GestorLlamadas
2. getHoraActual() -> clase Calendario

### SUT testable

### Implementación de los dobles

Necesitamos dos dobles. Uno parcial para el método getCalendario de la clase GestorLlamadas y otro para la clase Calendario.

Usaremos un IMockControl para controlar el orden de las invocaciones.

1. glmock -> mock parcial de GestorLlamadas
2. cmock -> mock de Calendario

Las expectativas son que primero se llame a getCalendario() que devuelve cmock y luego a cmock.getHoraActual() que devuelve 10 para el primer test y 21 para el segundo.

### Implementación de los tests

Cambiamos al modo replay para que se verifiquen las expectativas. Invocamos al SUT y comprobamos con assertEquals que el resultado es el esperado. Comprobamos con ctl.verify() que se han invocado en el orden correcto.


## Ejercicio 2

### Dependencias externas
1. generaNumero() -> clase Premio
2. obtenerPremio() -> clase ClienteWebService

### SUT testable

### Implementación de los dobles
Necesitaremos un doble parcial de la clase Premio para la dependencia 1 y un doble de la clase ClienteWebService para la dependencia 2. Controlaremos el orden de las invocaciones con un IMockControl.

1. pmock -> mock parcial de Premio
2. cwmock -> mock de ClienteWebService

Las expectativas deberán ir dentro de un AssertDoesNotThrow().

### Implementación de los tests
Dentro de cada test programamos las expectativas correspondientes para los mocks, cambiamos al modo replay y comprobamos que el resultado es el esperado. Verificamos que se han invocado en el orden correcto con ctl.verify().

## Ejercicio 3

### Dependencias externas

1. read() -> clase FileReader
2. close() -> clase FileReader

### SUT no testable

Hay un new FileReader, hay que refactorizar. Debemos crear una factoría para poder inyectar el doble. La factoría local también será una dependencia externa.

### Implementación de los dobles

Hay que crear dos dobles. Uno para el FileReader y otro para la factoría, que será un mock parcial de la clase FicheroTexto. Usaremos un IMockControl para controlar el orden de las invocaciones.

1. frmock -> mock de FileReader
2. fmock -> mock parcial de FicheroTexto

### Implementación de los tests

Usaremos assertEquals, assertThrows y verify().

## Ejercicio 4

### Dependencias externas

1. compruebaPermisos() -> clase Reserva
2. getOperacion() -> clase FactoriaBO
3. operacionReserva() -> clase IOperacionBO

### SUT no testable

Hay un new FactoriaBO. Hay que sustituirlo por un setter para cumplir con los requisitos del enunciado y poder inyectar el doble.

### Implementación de los dobles

Hay que implementar tres dobles. Un doble parcial de la clase Reserva para la dependencia 1 y un doble de la clase FactoriaBO para la dependencia 2 y un doble de la clase IOperacionBO 3. Usaremos un IMockControl para controlar el orden de las invocaciones.

### Implementación de los tests

Usaremos assertEquals, assertDoesNotThrows y verify().