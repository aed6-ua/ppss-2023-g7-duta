# P06A-Multimodulo

## Ejercicio 1

Se crea un módulo hotel con Maven Archtype y luego los módulos normales dentro de este seleccionando como parent el módulo hotel.

El módulo hotelWebApp se convierte en package war añadiendo en su pom 
~~~
<packaging>war</packaging>
~~~

Para cambiar el nombre del .war generado:
~~~
<finalName>${project.artifactId}</finalName>
~~~

Añadimos los plugins de war y wildfire al hotelWebApp
~~~
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.3.2</version>
        <configuration>
            <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
    </plugin>
    <plugin>
        <groupId>org.wildfly.plugins</groupId>
        <artifactId>wildfly-maven-plugin</artifactId>
        <version>3.0.2.Final</version>
        <configuration>
            <hostname>localhost</hostname>
            <port>9990</port>
            <jbossHome>C:\wildfly-26.1.3.Final</jbossHome>
        </configuration>
    </plugin>
</plugins>
~~~

Y la dependencia:

~~~
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>hotelDatabase</artifactId>
    <version>${project.version}</version>
</dependency>
~~~

## Ejercicio 2

Creamos los módulos y añadimos las dependencias. En matriculacion-bo no hace falta añadir matriculacion-comun ya que matriculacion-proxy y matriculacion-dao ya lo incluye y maven tiene **dependencias transitivas**.

Gracias al mecanismo **REACTOR**, cuando ejecutamos un comando sobre el proyecto:

- primero se ordenan todos los módulos según sus dependencias
- luego se ejecuta el MISMO comando maven sobre todos y cada uno

Si ejecutamos mvn compile desde un módulo y no hemos ejecutado mvn install de sus dependencias, entonces, como ya no tenemos acceso al target de las dependencias, si el jar correspondiente no se ha generado, la compilación fallará.
