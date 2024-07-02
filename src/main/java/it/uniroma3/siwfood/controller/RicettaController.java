package it.uniroma3.siwfood.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Image;
import it.uniroma3.siwfood.model.Ricetta;
import it.uniroma3.siwfood.repository.FoodRepository;
import it.uniroma3.siwfood.repository.ImageRepository;
import it.uniroma3.siwfood.service.FoodService;
import jakarta.validation.Valid;

@Controller
public class RicettaController {
	@Autowired FoodService foodService;
	@Autowired FoodRepository foodRepository;
	@Autowired ImageRepository imageRepository;
	
	@GetMapping("/ricetta/{id}")
	public String getRicetta(@PathVariable("id")Long id, Model model) {
		model.addAttribute("ricetta", this.foodService.findById(id));
		model.addAttribute("ricette", this.foodService.findAll());
		return "ricetta.html";
	}
	
	@GetMapping("/ricette")
	public String showRicette(Model model) {
		model.addAttribute("ricette", this.foodService.findAll());
		return "ricette.html";
	}
	
	@GetMapping(value="/nuovaricetta")
	public String formNewRicetta(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		return "formNewRicetta.html";
	}
	
	@PostMapping(value={"/ricetta"}, consumes = "multipart/form-data")
	public String newRicetta(@Valid @ModelAttribute("ricetta") Ricetta ricetta,@RequestPart("file") MultipartFile file, BindingResult bindingResult, Model model) {
		
		//this.movieValidator.validate(movie, bindingResult);
		if (!bindingResult.hasErrors()) {
			try {
				Image i=new Image();
				i.setImageData(file.getBytes());

				ricetta.setDefaultImage(i);
				this.imageRepository.save(i);
			} catch (Exception e) {
				System.out.println("erroreeee");
			}
			this.foodRepository.save(ricetta); 
			model.addAttribute("ricetta", ricetta);
			return "redirect:/ricetta/" + ricetta.getId();
		} else {
			return "formNewRicetta.html"; 
		}
	}
	
	@GetMapping("/antipasti")
	public String showAntipasti(Model model) {
		List<Ricetta> antipasti = new ArrayList<>();
		for(Ricetta ricetta : this.foodService.findAll()) {
			if (ricetta.getTipo() != null && ricetta.getTipo().equals("Antipasto")) {
				antipasti.add(ricetta);
			}
		}
		model.addAttribute("antipasti", antipasti);
		return "antipasti.html";
	}
	
	@GetMapping("/primi")
	public String showPrimi(Model model) {
		List<Ricetta> primi = new ArrayList<>();
		for(Ricetta ricetta : this.foodService.findAll()) {
			if (ricetta.getTipo() != null && ricetta.getTipo().equals("Primo")) {
				primi.add(ricetta);
			}
		}
		model.addAttribute("primi", primi);
		return "primi.html";
	}
	
	
	@GetMapping("/secondi")
	public String showSecondi(Model model) {
		List<Ricetta> secondi = new ArrayList<>();
		for(Ricetta ricetta : this.foodService.findAll()) {
			if (ricetta.getTipo() != null && ricetta.getTipo().equals("Secondo")) {
				secondi.add(ricetta);
			}
		}
		model.addAttribute("secondi", secondi);
		return "secondi.html";
	}
	
	
	@GetMapping("/dessert")
	public String showDesserts(Model model) {
		List<Ricetta> desserts = new ArrayList<>();
		for(Ricetta ricetta : this.foodService.findAll()) {
			if (ricetta.getTipo() != null && ricetta.getTipo().equals("Dessert")) {
				desserts.add(ricetta);
			}
		}
		model.addAttribute("desserts", desserts);
		return "desserts.html";
	}

}
