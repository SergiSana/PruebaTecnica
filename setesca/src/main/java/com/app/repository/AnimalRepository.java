package com.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Integer>{

}
