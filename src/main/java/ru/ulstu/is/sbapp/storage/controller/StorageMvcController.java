package ru.ulstu.is.sbapp.storage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.my_component.controller.My_componentDto;
import ru.ulstu.is.sbapp.my_component.service.My_componentService;
import ru.ulstu.is.sbapp.storage.service.StorageService;

import javax.validation.Valid;

@Controller
@RequestMapping("/storage")
public class StorageMvcController {

        private final StorageService storageService;

        public StorageMvcController(StorageService storageService) {
            this.storageService = storageService;
        }

        @GetMapping
        public String getStorages(Model model) {
            model.addAttribute("storages",
                    storageService.findAllStorages().stream()
                            .map(StorageDto::new)
                            .toList());
            return "storage";
        }

        @GetMapping(value = {"/edit", "/edit/{id}"})
        public String editStorage(@PathVariable(required = false) Long id, Model model) {
            if (id == null || id <= 0) {
                model.addAttribute("storageDto", new StorageDto());
            } else {
                model.addAttribute("storageId", id);
                model.addAttribute("storageDto", new StorageDto(storageService.findStorage(id)));
            }
            return "storage-edit";
        }

        @PostMapping(value = {"", "/{id}"})
        public String saveStorage(@PathVariable(required = false) Long id,
                                       @ModelAttribute @Valid StorageDto storageDto,
                                       BindingResult bindingResult,
                                       Model model) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("errors", bindingResult.getAllErrors());
                return "storage-edit";
            }
            if (id == null || id <= 0) {
                storageService.addStorage(storageDto.getNumberOfPlaces(), storageDto.getNumberOfFreePlaces(), storageDto.getManufactures(), storageDto.getStorageAddress());
            } else {
                storageService.updateStorage(id, storageDto.getNumberOfPlaces(), storageDto.getNumberOfFreePlaces(), storageDto.getManufactures(), storageDto.getStorageAddress());
            }
            return "redirect:/storage";
        }

        @PostMapping("/delete/{id}")
        public String deleteStorage(@PathVariable Long id) {
            storageService.deleteStorage(id);
            return "redirect:/storage";
        }

}
