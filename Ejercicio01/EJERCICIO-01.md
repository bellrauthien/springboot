# Ejercicio 01

1. Navegar a https://start.spring.io/
2. Colocar la siguiente información:
	- Project: Maven Project
	- Language: Java
	- Spring Boot: 2.1.6
	- Group: mx.com.sps.spring
	- Artifact: jpa.database.articulos
	- Name: RepositorioArticulos
	- Description: Genera una base de datos de articulos en memoria
	- Package Name: mx.com.sps.spring.jpa.database.articulos
	- Packaging: Jar
	- Java: 8
	- Dependencies: Spring Data JPA, H2 Database
3. Generar y descargar el proyecto (botón verde).
4. Descomprimir el archivo jpa.database.articulos.zip en la carpeta de tu preferencia. A esto le llamaremos carpeta del proyecto.
5. Abrir una línea de comandos y navegar a la carpeta del proyecto.
6. Ejecutar mvn clean compile y verificar que todo finaliza satisfactoriamente.
7. ¡Felicidades, has creado tu primer aplicación de SpringBoot usando JPA!

## Caso de uso

Imaginemos que tenemos una tienda de bicicletas. Necesitamos un catálogo de productos para vender a nuestros clientes. Lo primero que debemos hacer es dar de alta todos los productos en el catálogo.

Utilizaremos Spring Data JPA para crear e interactuar con un catálogo de bicicletas. El catálogo vive en memoria; es decir, la base de datos de bicicletas se crea cuando levanta la aplicación y se destruye cuando la aplicación finaliza. Para crear la base de datos en memoria vamos a usar H2 Database. 

## Importar el código

Importar el código en eclipse es muy simple.

1. File > Import > Existing Maven Projects (Next).
2. Root Directory: seleccionar la carpeta del proyecto. Automáticamente se seleccionará el archivo pom.xml que está ahí (Finish).
3. ¡Ahora podemos ver el código en Eclipse!

## El Bean

Lo primero que necesitamos es crear un bean, es decir, una tabla que almacene todos los modelos de bicicletas que vamos a vender. Para facilidad de uso, a cada bicicleta le daremos un identificador, un nombre y una descripción.

1. Crear el paquete mx.com.sps.spring.jpa.database.articulos.entities
2. Debajo del paquete entities, crear una nueva clase Java: Articulo
3. Crear 3 propiedades privadas:
	- id (Long)
	- nombre (String)
	- descripcion (String)
4. Crear los getters y setters para las 3 propiedades.
5. Crear un constructor público con el nombre y la descripción.
6. Crear un constructor protegido para Spring.
7. Anotar la clase como Entity (javax.persistence.Entity). De esta manera Spring va a saber que ese bean representa una entidad o tabla.
8. Anotar el la propiedad id para que sea un identificador que se genere automáticamente.}
9. ¡Felicidades! Ya generaste tu primer bean/tabla que representa un artículo.

Articulo.java
```java
package mx.com.sps.spring.jpa.database.articulos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nombre;
	private String descripcion;

	protected Articulo() {
	}

	public Articulo(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
```

## La base de datos

Ya tenemos definida nuestra tabla, ahora necesitamos crear una base de datos para todos los modelos de bicicletas que vamos a vender. Esa base de datos va a vivir en memoria, esto quiere decir que va a existir mientras la aplicación esté corriendo.

Es aquí donde JPA y H2 entran en acción. Normalmente nos gustaría dar de alta nuevas bicicletas, o consultar el catálogo por alguna marca en particular, o consultar todos los modelos de bicicletas, o eliminar alguna cuando no exista disponibilidad.

Esto podría darnos una idea de que necesitamos crear mucho código para tener esa funcionalidad, pero JPA nos hará el trabajo fácil.

1. Crear el paquete mx.com.sps.spring.jpa.database.articulos.repositories
2. Debajo del paquete repositories, crear una nueva interfaz Java: RepositorioArticulos
3. Extender la interfaz de CrudRepository (org.springframework.data.repository.CrudRepository)
4. Indicar qué tipo de objeto administra el repositorio: Articulo
5. Indicar qué tipo de identificador usa la entidad: Long
6. ¡Felicidades! Ya generaste tu primer repositorio de artículos. Es en serio, con esto tenemos disponible toda la funcionalidad CRUD para esa entidad o tabla. No es necesario hacer nada más.

RepositorioArticulos.java
```java
package mx.com.sps.spring.jpa.database.articulos.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.sps.spring.jpa.database.articulos.entities.Articulo;

public interface RepositorioArticulos extends CrudRepository<Articulo, Long> {

}
```

## La aplicación

Finalmente lo único que tenemos qué hacer es probar la aplicación. Para eso utilizaremos la clase creada inicialmente: RepositorioArticulosApplication. Esta clase se generó cuando generamos la aplicación inicial a través del starter.

Vamos a usar la interfaz CommandLineRunner para crear una aplicación de Spring que corra desde la consola. Es decir, que cuando levante la aplicación de Spring, se ejecute nuestro código.

Como el objetivo es probar las funciones CRUD de la tabla de artículos, lo primero que haremos es agregar artículos al catálogo. Posteriormente realizaremos unas consultas y eliminaremos algunos registros. Con eso finalizaríamos el ejemplo de JPA.

1. Agregar un método que utilice CommandLineRunner. El método se llamará: catalogoBicicletasDemo. Deberá recibir como argumento de entrada el repositorio de artículos y anotarse como Bean (org.springframework.context.annotation.Bean).
2. Agregar un logger para nuestra aplicación (org.slf4j.Logger).
3. Implementar la lógica para:
	- Agregar algunas bicicletas al catálogo
	- Consultar todas las bicicletas del catálogo
	- Consultar la bicicleta con el id=3
	- Eliminar la bicicleta con el id=2
	- Mostrar de nuevo todo el catálogo de bicicletas actualizado
4. ¡Eso fue todo! Hasta aquí probamos la facilidad de uso de JPA para operaciones de tipo CRUD sobre entidades definidas.

RepositorioArticulosApplication.java
```java
package mx.com.sps.spring.jpa.database.articulos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mx.com.sps.spring.jpa.database.articulos.entities.Articulo;
import mx.com.sps.spring.jpa.database.articulos.repositories.RepositorioArticulos;

@SpringBootApplication
public class RepositorioArticulosApplication {

	private static final Logger logger = LoggerFactory.getLogger(RepositorioArticulosApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RepositorioArticulosApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner catalogoBicicletasDemo(RepositorioArticulos articulos) {
		return (args) -> {
			logger.info("*** 1. Agregar bicicletas a la base de datos ***");
			articulos.save(new Articulo("Giant On-Road City", "Escape"));
			articulos.save(new Articulo("Giant X-Road Adventure", "Roam Disc"));
			articulos.save(new Articulo("Giant Off-Road XC", "Anthem Advanced"));
			articulos.save(new Articulo("Alubike Urbana", "Spicy DIM"));
			articulos.save(new Articulo("Alubike MTB", "Sierra"));
			articulos.save(new Articulo("Alubike Ruta", "K24"));
			
			logger.info("*** 2. Consultar el catálogo completo de bicicletas ***");
			for (Articulo bicicleta : articulos.findAll()) {
				logger.info(bicicleta.toString());
			}
			
			logger.info("*** 3. Consultar la bicicleta con id=3 ***");
			logger.info(articulos.findById(new Long(3)).toString());
			
			logger.info("*** 4. Eliminar del catálogo la bicicleta con id=2 ***");
			articulos.deleteById(new Long(2));
			
			logger.info("*** 5. Consultar el catálogo actualizado de bicicletas ***");
			for (Articulo bicicleta : articulos.findAll()) {
				logger.info(bicicleta.toString());
			}
		};
	}

}
```

## Las pruebas

Una vez lista la aplicación, lo único que nos falta es probar que todo quedó bien. Para eso, en la carpeta de nuestro proyecto debemos ejecutar el siguiente comando:

```
mvn spring-boot:run
```

El resultado deberá ser lo siguiente:

1. La aplicación de SpringBoot arranca.
2. Se ejecuta el método catalogoBicicletasDemo.
3. Se agregan algunas bicicletas a la base de datos.
4. Se muestran todos los productos del catálogo de bicicletas, después de ser agregados.
5. Se consulta alguna bicicleta en particular, de acuerdo a su identificador.
6. Se elimina una bicicleta del catálogo.
7. Se consulta el catálogo actualizado.

Todo esto lo podremos ver en la consola, la salida deberá ser más o menos la siguiente:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.1.6.RELEASE)

Starting RepositorioArticulosApplication on DESKTOP-3B0II39 with PID 27896 (C:\Leo\repositories\springboot\Ejercicio01\jpa.database.articulos\target\classes started by admin in C:\Leo\repositories\springboot\Ejercicio01\jpa.database.articulos)
No active profile set, falling back to default profiles: default
Bootstrapping Spring Data repositories in DEFAULT mode.
Finished Spring Data repository scanning in 43ms. Found 1 repository interfaces.
HikariPool-1 - Starting...
HikariPool-1 - Start completed.
HHH000412: Hibernate Core {5.3.10.Final}
HHH000206: hibernate.properties not found
HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
HHH000476: Executing import script 'org.hibernate.tool.schema.internal.exec.ScriptSourceInputNonExistentImpl@6a66f1b8'
Initialized JPA EntityManagerFactory for persistence unit 'default'
Started RepositorioArticulosApplication in 3.116 seconds (JVM running for 6.808)
*** 1. Agregar bicicletas a la base de datos ***
*** 2. Consultar el catßlogo completo de bicicletas ***
HHH000397: Using ASTQueryTranslatorFactory
Articulo[id=1, nombre=Giant On-Road City, descripcion=Escape]
Articulo[id=2, nombre=Giant X-Road Adventure, descripcion=Roam Disc]
Articulo[id=3, nombre=Giant Off-Road XC, descripcion=Anthem Advanced]
Articulo[id=4, nombre=Alubike Urbana, descripcion=Spicy DIM]
Articulo[id=5, nombre=Alubike MTB, descripcion=Sierra]
Articulo[id=6, nombre=Alubike Ruta, descripcion=K24]
*** 3. Consultar la bicicleta con id=3 ***
Optional[Articulo[id=3, nombre=Giant Off-Road XC, descripcion=Anthem Advanced]]
*** 4. Eliminar del catßlogo la bicicleta con id=2 ***
*** 5. Consultar el catßlogo actualizado de bicicletas ***
Articulo[id=1, nombre=Giant On-Road City, descripcion=Escape]
Articulo[id=3, nombre=Giant Off-Road XC, descripcion=Anthem Advanced]
Articulo[id=4, nombre=Alubike Urbana, descripcion=Spicy DIM]
Articulo[id=5, nombre=Alubike MTB, descripcion=Sierra]
Articulo[id=6, nombre=Alubike Ruta, descripcion=K24]
Closing JPA EntityManagerFactory for persistence unit 'default'
HHH000477: Starting delayed evictData of schema as part of SessionFactory shut-down'
HikariPool-1 - Shutdown initiated...
HikariPool-1 - Shutdown completed.
```

## Hágalo usted mismo

Del ejercicio podemos sacar algunas conclusiones:

- JPA es un API de Java que nos facilita el acceso e interacción con bases de datos.
- H2 es una base de datos SQL escrita en Java, en el ejemplo utilizamos H2 para crear una base de datos en memoria (no persistente).
- El uso de la interfaz CrudRepository nos evita tener que escribir código para los métodos básicos de acceso a bases de datos (CRUD).
- El uso de la interfaz CommandLineRunner nos permite ejecutar código una vez que levanta la aplicación de SpringBoot.
- No nos dimos cuenta del uso de Hibernate gracias al API de JPA.

Adicional, tenemos algunas mejoras que podemos hacer al código. Por ejemplo:

- ¿Qué pasa si hago una consulta a la base de datos y no existen registros de acuerdo a los criterios de búsqueda? Seguramente habrá una excepción. ¿Cómo se corrige?
- ¿Cómo podría implementar una consulta por nombre o marca del artículo? Esto no forma parte de los métodos por default.