package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Mechanic;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.MechanicRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repo.WorkshopRepository;
import jakarta.validation.Valid;

@Controller
public class MechanicController {

    @Autowired
    private MechanicRepository mechanicRepository;

    @Autowired
    private WorkshopRepository workshopRepository;

    @GetMapping("/mechanics")
    public String listMechanics(Model model) {
        List<Mechanic> mechanics = mechanicRepository.findAll();
        model.addAttribute("mechanics", mechanics);
        return "mechanics_list";
    }

    @GetMapping("/mechanics/form")
    public String mechanicForm(Model model) {
        model.addAttribute("mechanic", new Mechanic());
        model.addAttribute("workshops", workshopRepository.findAll());
        return "mechanics_form";
    }

    @PostMapping("/mechanics/register")
    public String registerMechanic(@Valid @ModelAttribute("mechanic") Mechanic mechanic, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("workshops", workshopRepository.findAll());
            return "mechanics_form";
        }
        mechanicRepository.save(mechanic);
        return "redirect:/mechanics";
    }

    @GetMapping("/mechanics/update/{id}")
    public String updateMechanic(@PathVariable("id") Integer id, Model model) {
        Optional<Mechanic> mechanicOpt = mechanicRepository.findById(id);
        if (mechanicOpt.isPresent()) {
            model.addAttribute("mechanic", mechanicOpt.get());
            model.addAttribute("workshops", workshopRepository.findAll());
            return "mechanics_form";
        }
        return "redirect:/mechanics";
    }

    @GetMapping("/mechanics/delete/{id}")
    public String deleteMechanic(@PathVariable("id") Integer id) {
        mechanicRepository.deleteById(id);
        return "redirect:/mechanics";
    }
}
