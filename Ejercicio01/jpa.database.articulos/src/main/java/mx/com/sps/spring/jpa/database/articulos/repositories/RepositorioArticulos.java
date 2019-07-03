package mx.com.sps.spring.jpa.database.articulos.repositories;

import org.springframework.data.repository.CrudRepository;

import mx.com.sps.spring.jpa.database.articulos.entities.Articulo;

public interface RepositorioArticulos extends CrudRepository<Articulo, Long> {

}