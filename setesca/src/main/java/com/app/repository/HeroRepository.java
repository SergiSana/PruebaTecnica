package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.model.Hero;

public interface HeroRepository extends CrudRepository<Hero, Integer>{

	@Query("select h from Hero h where h.nombre like %:nombre%")
	List<Hero> buscarPorPatron(@Param("nombre") String nombre);
}
