package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Workshop;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.WorkshopRepository;
import jakarta.validation.Valid;

@Controller
public class WorkshopController {

	@Autowired
	WorkshopRepository workshopRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@GetMapping("/workshops")
	public String listWorkshops(Model model) {
	
		List<Workshop> workshops = workshopRepository.findAll();
		
		model.addAttribute("workshops", workshops);
		
		return "workshops_list";
	}
	
	@GetMapping("/workshops/form")
    public String workshopForm(@ModelAttribute("workshop") Workshop workshop, Model model) {
        
		Set<String> servicos = new HashSet<>();
        servicos.add("Troca de Óleo");
        servicos.add("Alinhamento");
        servicos.add("Pintura");
        servicos.add("Balanceamento");
        servicos.add("Reforma");
        servicos.add("Funilaria");
        model.addAttribute("servicos", servicos);
        
        return "workshops_form";
    }
	
	@PostMapping("/workshops/register")
	public String workshopNew(@Valid @ModelAttribute("workshop") Workshop workshop, BindingResult errors) {
		
		if(errors.hasErrors()) {
			return "workshops_form";
		}
		
		addressRepository.save(workshop.getAddress());
		workshopRepository.save(workshop);
		
		return "redirect:/workshops";
	}
	
	@GetMapping("/workshops/update/{id}")
	public String workshopUpdate(@PathVariable("id") Integer id, Model model) {
	    
	    Optional<Workshop> workshopOpt = workshopRepository.findById(id);
	    Workshop workshop;
	    
	    if (workshopOpt.isPresent()) {
	        workshop = workshopOpt.get();
	    } else {
	        workshop = new Workshop();
	    }
	    
	    model.addAttribute("workshop", workshop);
	    
	    Set<String> servicos = new HashSet<>();
        servicos.add("Troca de Óleo");
        servicos.add("Alinhamento");
        servicos.add("Pintura");
        servicos.add("Balanceamento");
        servicos.add("Reforma");
        servicos.add("Funilaria");
        model.addAttribute("servicos", servicos);
	    
	    return "workshops_form";
	}
	
	@GetMapping("/workshops/delete/{id}")
	public String workshopDelete(@PathVariable("id") Integer id) {
		
		workshopRepository.delete(new Workshop(id));
		
		return ("redirect:/workshops");
	}
	
}
