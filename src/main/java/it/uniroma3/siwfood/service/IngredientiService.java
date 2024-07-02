package it.uniroma3.siwfood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Ingredienti;
import it.uniroma3.siwfood.repository.IngredientiRepository;

@Service
public class IngredientiService {
	
	@Autowired
	private IngredientiRepository ingredientiRepository;
	
	public Ingredienti findById(long id) {
		return ingredientiRepository.findById(id).get();
	}
	
	public Iterable<Ingredienti> findAll(){
		return ingredientiRepository.findAll();
	}
	
	
}
