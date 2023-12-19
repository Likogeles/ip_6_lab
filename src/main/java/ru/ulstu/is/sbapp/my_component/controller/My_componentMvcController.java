package ru.ulstu.is.sbapp.my_component.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.my_component.service.My_componentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/my_component")
public class My_componentMvcController {
    private final My_componentService myComponentService;

    public My_componentMvcController(My_componentService myComponentService) {
        this.myComponentService = myComponentService;
    }

    @GetMapping
    public String getMy_components(Model model) {
        model.addAttribute("my_components",
                myComponentService.findAllMyComponents().stream()
                        .map(My_componentDto::new)
                        .toList());

        return "my_component";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editMy_component(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("my_componentDto", new My_componentDto());
        } else {
            model.addAttribute("my_componentId", id);
            model.addAttribute("my_componentDto", new My_componentDto(myComponentService.findMyComponent(id)));
        }
        return "my_component-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveMy_component(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid My_componentDto my_componentDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "my_component-edit";
        }
        if (id == null || id <= 0) {
            myComponentService.addMyComponent(my_componentDto.getName(), my_componentDto.getNum_in_storage(), my_componentDto.getCost(), my_componentDto.getPurveyor());
        } else {
            myComponentService.updateMyComponent(id, my_componentDto.getName(), my_componentDto.getNum_in_storage(), my_componentDto.getCost(), my_componentDto.getPurveyor());
        }
        return "redirect:/my_component";
    }

    @PostMapping("/delete/{id}")
    public String deleteMy_component(@PathVariable Long id) {
        myComponentService.deleteMyComponent(id);
        return "redirect:/my_component";
    }
}
