package ru.ulstu.is.sbapp.storage.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.manufacture.model.Manufacture;
import ru.ulstu.is.sbapp.storage.model.Storage;
import ru.ulstu.is.sbapp.storage.service.StorageService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/storage")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) { this.storageService = storageService; }

    @GetMapping("/{id}")
    public StorageDto getStorage(@PathVariable Long id) {
        return new StorageDto(storageService.findStorage(id));
    }

    @GetMapping("/")
    public List<StorageDto> getStorages() {
        return storageService.findAllStorages().stream().map(StorageDto::new).toList();
    }

    @PostMapping("/")
    public StorageDto createStorage(@RequestParam("numberOfPlaces") Integer numberOfPlaces,
                                 @RequestParam("numberOfFreePlaces") Integer numberOfFreePlaces,
                                 @RequestParam("manufactures") List<Manufacture> manufactures,
                                 @RequestParam("storageAddress") String storageAddress
    ) {
        return new StorageDto(storageService.addStorage(numberOfPlaces, numberOfFreePlaces, manufactures, storageAddress));
    }

    @PatchMapping("/{id}")
    public StorageDto updateStorage(@PathVariable Long id,
                                 @RequestParam("numberOfPlaces") Integer numberOfPlaces,
                                 @RequestParam("numberOfFreePlaces") Integer numberOfFreePlaces,
                                 @RequestParam("manufactures") List<Manufacture> manufactures,
                                 @RequestParam("storageAddress") String storageAddress
    ) {
        return new StorageDto(storageService.updateStorage(id, numberOfPlaces, numberOfFreePlaces, manufactures, storageAddress));
    }

    @DeleteMapping("/{id}")
    public StorageDto deleteStorage(@PathVariable Long id) {
        return new StorageDto(storageService.deleteStorage(id));
    }
}