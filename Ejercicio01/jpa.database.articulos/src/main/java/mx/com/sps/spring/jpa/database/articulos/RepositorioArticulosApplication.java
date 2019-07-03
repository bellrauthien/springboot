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
