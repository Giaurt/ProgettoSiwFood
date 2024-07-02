package it.uniroma3.siwfood.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwfood.model.Ricetta;

public interface FoodRepository extends CrudRepository<Ricetta, Long>{

}
