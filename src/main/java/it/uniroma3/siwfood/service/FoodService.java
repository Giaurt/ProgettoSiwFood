package it.uniroma3.siwfood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwfood.model.Ingredienti;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.repository.FoodRepository;

@Service
public class FoodService {
	
	@Autowired
	private FoodRepository foodRepository;
	
	public Ricetta findById(long id) {
		return foodRepository.findById(id).get();
	}
	
	public Iterable<Ricetta> findAll(){
		return foodRepository.findAll();
	}
	
	public List<Ingredienti> findByRecipeId(long id){
		return foodRepository.findById(id).get().getIngredienti();
	}
	
	public Ricetta getRecipeWithIngredienti(long recipeId) {
        Ricetta recipe = foodRepository.findById(recipeId).orElseThrow();
        List<Ingredienti> ingredients = findByRecipeId(recipeId);
        recipe.setIngredienti(ingredients);
        return recipe;
    }
}
