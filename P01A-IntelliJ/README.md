# Guia de prácticas

## **Sesión P01A**

### **Ejercicio 1**

**A)** Estructura de directorios del proyecto:

* src
    * main
        * java
        * resources
    * test
        * java
* target
    * classes
    * generated-sources
    * generated-test-sources
    * maven-archiver
    * maven-status
    * surefire-reports
    * test-classes
* pom.xml
* .idea
* intellij-configurations

**B)** Coordenadas de los artefactos maven:

* *\<groupId>*: hace referencia a el nombre de la empresa
* *\<artifactId>*: hace referencia al nombre del archivo y normalmente es el mismo que el del proyecto
* *\<version>*: version del artefacto
* *\<package>*: extensión del fichero

Etiquetas del pom.xml:
* *\<properties>*: definir y/o asignar valores a determinadas variables usadas en el pom.xml
    * A partir de Maven 3 obligatorio definir *project.build.sourceEncoding*
* *\<scope>*: para indicar cuando se necesita el artefacto donde se especifica. Por defecto es "compile".
* *\<dependencies>*: las dependencias del proyecto
* *\<plugins>*: para incluir plugins que se necesiten en la construcción

El plugin **maven-surefire-plugin** se incluye por defecto con el empaquetado jar, pero lo especificamos para usar una versión posterior. El plugin **maven-compiler-plugin** se incluye por defecto también con la versión 3.10.1, pero necesitamos una versión posterior.

**C)** Las especificaciones que definen el conjunto S del método "tipo_triangulo()" son:

* Si alguno de los tres lados: a, b ó c excede el rango 1-200, entonces el método devolverá el mensaje "Valor x fuera del rango permitido" siendo x el carácter a, b ó c que representa el valor de entrada que incumple la condición.
* Si la suma de cualquiera dos de sus lados es siempre mayor que la del tercero y a!=b!=c devuelve "Escaleno".
* Si la suma de cualquiera dos de sus lados es siempre mayor que la del tercero y a==b==c devuelve "Equilatero".
* Si la suma de cualquiera dos de sus lados es siempre mayor que la del tercero y dos de ellos son iguales entre sí devuelve "Isosceles".
* Si la suma de cualquiera dos de sus lados no es siempre mayor que la del tercero devuelve "No es un triángulo".

**D)** Tabla de casos de prueba:

| ID Caso de prueba | a | b | c | Resultado esperado |
|:-----------------:|:-:|:-:|:-:|:------------------:|
| C1                | 1 | 1 | 1 | "Equilatero" |
| C2                | 1 | 1 | 11 | "No es un triangulo" | 
| C3                | 1 | 2 | 0 | "Valor c fuera del rango permitido" |
| C4                | 14 | 10 | 10 | "Isosceles" |

**Algoritmo que refleja la lógica que siguen los test:**

1. Inicializar los datos de entrada.
2. Inicializar resultado esperado.
3. Invocar método a probar con los datos de entrada y guardar el resultado.
4. Comparar el resultado esperado con el real.

### **Ejercicio 2**

**A)** Al ejecutar la fase **"clean"** se elimina la carpeta "target".

**B)** Al ejecutar la fase **"test"** para TrianguloTest se han ejecutado 4 tests y ha fallado 1.

Para añadir una nueva "Run Configuration" lo hacemos desde *Run > Edit Configurations* y añadimos una nueva configuración de Maven.

**C)** El error consiste en que se comprueba que el valor esté en el rango con la condición ``(x < 1) && (x > 200)`` cuando debería ser ``(x < 1) || (x > 200)``. Además hay que cambiar el mensaje a "Valor x fuera del rango permitido"

**D)** Un posible test adicional C5 con datos a=7, b=7, c=7 tendría la misma salida esperada que el test C1 y no aportaría ningún valor adicional ya que se detecta el mismo error (test **redundante**). Por el contrario los test C3 y C2 tienen salidas esperadas distintas por lo que prueban cosas distintas.

| ID Caso de prueba | a | b | c | Resultado esperado |
|:-----------------:|:-:|:-:|:-:|:------------------:|
| C5                | 2 | 3 | 4 | "Escaleno" |
| C6                | 1 | 2 | 3 | "No es un triangulo" |

### **Ejercicio 3**

**A)** La complejidad ciclomática es 8 por lo que como máximo necesitaremos 8 casos de prueba.

| ID Caso de prueba | edad | familiaNumemrosa | repetidor | Resultado esperado |
|:-----------------:|:----:|:----------------:|:--------:|:------------------:|
| C1                | 23 | true | true | 250.00 |
| C2                | 22 | false | true | 2000.00 |
| C3                | 55 | false | true | 400.00 |
| C4                | 25 | true | true | 250.00 |
| C5                | 21 | true | false | 250.00 |
| C6                | 65 | true | false | 250.00 |

6 tests son suficientes.

**B)** No se detecta ningún error con los tests implementados.

### **Ejercicio 4**

**A)** Al ejecutar la fase **"package"** se añade en target un archivo jar del proyecto. Los goals que se han ejecutado son resources, compile, testResources, testCompile, test y jar.

Al introducir un test que produce un fallo y ejecutar la fase package se ejecutan todos los goals hasta el test y no continua ya que este falla.

Si se añade un error de compilación falla el goal compile y no pasa de ahí.

**B)** La fase **"install"** copia el jar al repositorio local de maven.
