package it.uniroma3.siwfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwfood.model.Credentials;
import it.uniroma3.siwfood.model.Cuoco;
import it.uniroma3.siwfood.model.Image;
import it.uniroma3.siwfood.model.User;
import it.uniroma3.siwfood.repository.CredentialsRepository;
import it.uniroma3.siwfood.repository.CuocoRepository;
import it.uniroma3.siwfood.repository.ImageRepository;
import it.uniroma3.siwfood.service.CredentialService;
import it.uniroma3.siwfood.service.CuocoService;
import it.uniroma3.siwfood.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialService credentialsService;
	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CuocoRepository cuocoRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@GetMapping(value = "/") 
	public String index(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		model.addAttribute("user", new User());
//		model.addAttribute("credentials", new Credentials());
		if (authentication instanceof AnonymousAuthenticationToken) {
	        return "index.html";
		}
		else {		
			UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
			if(credentials.getCuoco() != null)
			{
				model.addAttribute("cuoco", credentials.getCuoco());
				System.out.println("debtro al if cuoco-----------------------------------------------");
				return "index.html";
			}
			if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
				System.out.println("debtro al if admin-----------------------------------------------");
				return "admin/indexAdmin.html";
			}
		}
		System.out.println("fuori al if -----------------------------------------------");
        return "index.html";
	}
	
	
//	@GetMapping(value = "/profile")
//	public String getProfile(Model model) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();model.addAttribute("user", new User());
//		model.addAttribute("credentials", new Credentials());
//	}
	
//	@GetMapping("/register")
//	public String registerForm(Model model) {
//		model.addAttribute("user", new User());
//		model.addAttribute("credentials", new Credentials());
//		return "registerUserForm.html";
//	}
	
	@GetMapping("/login")
	public String loginForm(Model model) {
		return "loginUserForm.html";
	}
	
	@GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/indexAdmin.html";
        }
    	if(credentials.getCuoco() != null)
		{
			model.addAttribute("cuoco", credentials.getCuoco());
			System.out.println("debtro al if cuoco-----------------------------------------------");
			return "index.html";
		}
        return "index.html";
    }
	
	
//	@PostMapping(value = { "/register" })
//    public String registerUser(@Valid @ModelAttribute("user") User user,
//                 BindingResult userBindingResult, @Valid
//                 @ModelAttribute("credentials") Credentials credentials,
//                 BindingResult credentialsBindingResult,
//                 Model model) {
//		// se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
//        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
//            userService.saveUser(user);
//            credentials.setUser(user);
//            credentialsService.saveCredentials(credentials);
//            model.addAttribute("user", user);
//            return "loginUserForm.html";
//        }
//        return "registerUser";
//    }
	
	
	@GetMapping(value = "/register")
	public String registerCuocoForm(Model model) {
		model.addAttribute("cuoco", new Cuoco());
		model.addAttribute("credentials", new Credentials());
		return "registerCuocoForm.html";
	}
	
	@PostMapping(value = { "/register" }, consumes = "multipart/form-data")
    public String registerCuoco(@Valid @ModelAttribute("cuoco") Cuoco cuoco,@RequestPart("file") MultipartFile file,
                 BindingResult userBindingResult, @Valid
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {

		// se user e credential hanno entrambi contenuti validi, memorizza User e the Credentials nel DB
        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
        	try {
				Image i=new Image();
				i.setImageData(file.getBytes());

				cuoco.setImage(i);
				this.imageRepository.save(i);
			} catch (Exception e) {
				System.out.println("erroreeee");
			}
            this.cuocoRepository.save(cuoco);
            credentials.setCuoco(cuoco);
            credentialsService.saveCredentials(credentials);
//            this.credentialsRepository.save(credentials);
            model.addAttribute("cuoco", cuoco);
            return "loginUserForm.html";
        }
        return "registerCuoco.html";
    }
}


