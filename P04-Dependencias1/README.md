# Guía P04-Dependencias-1

Estamos implementando drivers de pruebas dinámicas con verificación basada en ESTADO. Usaremos STUBS.

Las dependencias externas son métodos que se llaman desde el método que estamos probando. Estos métodos pueden ser de la propia clase, de otras clases o de librerías externas. Están en el cuerpo del SUT

Si dentro del SUT hay una clase externa, pero hay 3 llamadas a métodos de esa clase, entonces hay 3 dependencias externas.

## Ejercicio 1

**Dependencias externas:**

1. método getHoraActual() de la clase GestorLlamadas
SUT testable, no se refactoriza.

**Implementación del doble:**
Implementamos un doble: GestorLlamadasTestable y sobreescribimos el método getHoraActual() para inyectar la hora actual con un setter o un constructor.

**Implementación del driver:**
Invocamos la SUT y el doble usando GestorLlamadasTestable.

## Ejercicio 2

**Dependencias externas:**
1. método getCalendario() -> es el punto de inyección para 2
2. método getHoraActual() de la clase Calendario

**SUT testable:**

Ya tiene implementada factoría local.

**Implementación del doble:**

1. Creamos una clase GestorLlamadasTestable que hereda de GestorLlamadas y sobreescribimos el método getCalendario() para inyectar el doble de Calendario.
2. Creamos una clase StubCalendario que hereda de Calendario y sobreescribimos el método getHoraActual() para inyectar la hora actual.

**Implementación del driver:**

Inyectamos el doble de Calendario en el SUT testable e invocamos la SUT desde GestorLlamadasTestable.

## Ejercicio 3

**Dependencias externas:**

1. método servicio.consultaPrecio(tipo) -> servicio es de tipo IService
2. método calendario.es_festivo(otroDia) -> calendario es de tipo Calendario

Sólo hay que refactorizar Servicio porque Calendario es protected.

**SUT no testable:**

1. movemos el new Servicio() a un método factoría local: getServicio()
2. se puede inyectar el doble de Calendario a través del atributo protected.

**Implementación del doble:**
1. creamos una clase AlquilaCochesTestable que hereda de AlquilaCoches y sobreescribimos el método getServicio() para inyectar el doble de Servicio.
2. creamos una clase CalendarioStub que hereda de Calendario y sobreescribimos el método es_festivo(). Como el doble se invoca varias veces podemos inyectarle desde el driver:
    1. un array de fechas festivos: ArrayList
    2. un array de fechas que lanzan excepción: ArrayList

**Implementación del driver:**
Usaremos la clase testable para el doble 1 y para ejecutar la SUT. Inyectaremos el doble de Calendario a través del atributo protected.

## Ejercicio 4

**Dependencias externas:**
1. método compruebaPermisos() -> método local
2. método io.operacionReserva(socio,isbn) -> io es de tipo IOperacionBO

**SUT no testable:**
1. No necesita refactorización
2. Sustituir el new por una llamada a la clase Factoria

Tenemos que usar una clase Factoria para devolver un objeto IOperacionBO que contendrá nuestro doble.

**Implementación del doble:**

1. Creamos una clase ReservaTestable y sobreescribimos compruebaPermisos() para inyectar el resultado de la comprobación de permisos.
2. Para implementar el doble 1 creamos una clase que implementa IOperacionBO. Como se invocará varias veces, podemos inyectar desde el driver:
    1. ArrayList con los socios válidos
    2. ArrayList con los isbns validos
    3. booleano para lanzar o no la excepcion

