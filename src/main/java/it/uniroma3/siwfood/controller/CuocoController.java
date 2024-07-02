package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.service.CuocoService;
import jakarta.validation.Valid;

@Controller
public class CuocoController {

	@Autowired CuocoService cuocoService;
	
	@GetMapping("/cuoco/{id}")
	public String getCuoco(@PathVariable("id")Long id, Model model) {
		model.addAttribute("cuoco", this.cuocoService.findById(id));
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuoco.html";
	}
	
	@GetMapping("/cuochi")
	public String showCuochi(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuochi.html";
	}
	
	
	
	
}
