package ru.ulstu.is.sbapp.manufacture.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.manufacture.model.Manufacture;
import ru.ulstu.is.sbapp.manufacture.service.ManufactureService;
import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.my_component.service.My_componentService;
import ru.ulstu.is.sbapp.storage.model.Storage;
import ru.ulstu.is.sbapp.storage.service.StorageService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/manufacture")
public class ManufactureController {
    private final ManufactureService manufactureService;
    private final My_componentService myComponentService;
    private final StorageService storageService;

    public ManufactureController(ManufactureService manufactureService, My_componentService myComponentService, StorageService storageService) {
        this.manufactureService = manufactureService;
        this.myComponentService = myComponentService;
        this.storageService = storageService;
    }

    @GetMapping("/{id}")
    public ManufactureDto getManufacture(@PathVariable Long id) {
        return new ManufactureDto(manufactureService.findManufacture(id), myComponentService.findAllMyComponents(), storageService.findAllStorages());
    }

    @GetMapping("/")
    public List<ManufactureDto> getManufactures() {
        return manufactureService.findAllManufactures().stream().map(ManufactureDto::new).toList();
    }

    @PostMapping("/")
    public ManufactureDto createManufacture(@RequestParam("manufactureName") String manufactureName,
                                         @RequestParam(required=false, name="component") My_component myComponent,
                                         @RequestParam("manufactureCost") Integer manufactureCost,
                                         @RequestParam(required=false, name="manufactureStorage") Storage storage
    ) {
        return new ManufactureDto(manufactureService.addManufacture(manufactureName, myComponent, manufactureCost, storage), myComponentService.findAllMyComponents(), storageService.findAllStorages());
    }

    @PatchMapping("/{id}")
    public ManufactureDto updateManufacture(@PathVariable Long id,
                                         @RequestParam("manufactureName") String manufactureName,
                                         @RequestParam(required=false, name="component") My_component myComponent,
                                         @RequestParam("manufactureCost") Integer manufactureCost,
                                         @RequestParam(required=false, name="manufactureStorage") Storage storage
    ) {
        return new ManufactureDto(manufactureService.updateManufacture(id, manufactureName, myComponent, manufactureCost, storage), myComponentService.findAllMyComponents(), storageService.findAllStorages());
    }

    @DeleteMapping("/{id}")
    public ManufactureDto deleteManufacture(@PathVariable Long id) {
        return new ManufactureDto(manufactureService.deleteManufacture(id), myComponentService.findAllMyComponents(), storageService.findAllStorages());
    }
}