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

Utilizaremos Spring Data JPA para crear e interactuar con un catálogo de bicicletas. El catálogo vive en memoria; es decir, la base de datos de bicicletas se crea cuando levanta la aplicación y se destruye cuando la aplicación finaliza.

## Importar el código

Importar el código en eclipse es muy simple.

1. File > Import > Existing Maven Projects (Next).
2. Root Directory: seleccionar la carpeta del proyecto. Automáticamente se seleccionará el archivo pom.xml que está ahí (Finish).
3. ¡Ahora podemos ver el código en Eclipse!

## Bean

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



