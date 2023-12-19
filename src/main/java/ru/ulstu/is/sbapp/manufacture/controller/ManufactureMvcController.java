package ru.ulstu.is.sbapp.manufacture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.manufacture.service.ManufactureService;
import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.my_component.service.My_componentService;
import ru.ulstu.is.sbapp.storage.controller.StorageDto;
import ru.ulstu.is.sbapp.storage.service.StorageService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/manufacture")
public class ManufactureMvcController {

    private final ManufactureService manufactureService;
    private final My_componentService myComponentService;
    private final StorageService storageService;

    public ManufactureMvcController(ManufactureService manufactureService, My_componentService myComponentService, StorageService storageService) {
        this.manufactureService = manufactureService;
        this.myComponentService = myComponentService;
        this.storageService = storageService;
    }

    @GetMapping
    public String getManufactures(Model model) {
        model.addAttribute("manufactures",
                manufactureService.findAllManufactures().stream()
                        .map(ManufactureDto::new)
                        .toList());
        return "manufacture";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editManufacture(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("my_components", myComponentService.findAllMyComponents());
            model.addAttribute("storages", storageService.findAllStorages());
            model.addAttribute("manufactureDto", new ManufactureDto(myComponentService.findAllMyComponents(), storageService.findAllStorages()));
        } else {
            model.addAttribute("manufactureId", id);
            model.addAttribute("my_components", myComponentService.findAllMyComponents());
            model.addAttribute("storages", storageService.findAllStorages());

            model.addAttribute("manufactureDto", new ManufactureDto(manufactureService.findManufacture(id), myComponentService.findAllMyComponents(), storageService.findAllStorages()));
        }
        return "manufacture-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveManufacture(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid ManufactureDto manufactureDto,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "manufacture-edit";
        }
        if (id == null || id <= 0) {
            manufactureService.addManufacture(manufactureDto.getManufactureName(), manufactureDto.getMy_component(), manufactureDto.getManufactureCost(), manufactureDto.getStorage());
        } else {
            manufactureService.updateManufacture(id, manufactureDto.getManufactureName(), manufactureDto.getMy_component(), manufactureDto.getManufactureCost(), manufactureDto.getStorage());
        }
        return "redirect:/manufacture";
    }

    @PostMapping("/delete/{id}")
    public String deleteManufacture(@PathVariable Long id) {
        manufactureService.deleteManufacture(id);
        return "redirect:/manufacture";
    }
}
