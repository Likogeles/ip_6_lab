package ru.ulstu.is.sbapp.manufacture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.manufacture.model.Manufacture;
import ru.ulstu.is.sbapp.manufacture.repository.ManufactureRepository;
import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.storage.model.Storage;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class ManufactureService {
    @PersistenceContext
    private EntityManager em;

    private final ManufactureRepository manufactureRepository;
    private final ValidatorUtil validatorUtil;

    public ManufactureService(ManufactureRepository manufactureRepository, ValidatorUtil validatorUtil){
        this.manufactureRepository = manufactureRepository;
        this. validatorUtil = validatorUtil;
    }

    @Transactional
    public Manufacture addManufacture(String manufactureName, My_component myComponent, Integer manufactureCost, Storage storage) {
        final Manufacture manufacture = new Manufacture(manufactureName, myComponent, manufactureCost, storage);
        validatorUtil.validate(manufacture);
        return manufactureRepository.save(manufacture);
    }

    @Transactional(readOnly = true)
    public Manufacture findManufacture(Long id) {
        final Optional<Manufacture> manufacture = manufactureRepository.findById(id);

        return manufacture.orElseThrow(()->new ManufactureNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Manufacture> findAllManufactures() {
        return manufactureRepository.findAll();
    }

    @Transactional
    public Manufacture updateManufacture(Long id, String manufactureName, My_component myComponent, Integer manufactureCost, Storage storage) {
        final Manufacture currentManufacture = findManufacture(id);
        currentManufacture.setManufactureName(manufactureName);
        currentManufacture.setComponentId(myComponent);
        currentManufacture.setManufactureCost(manufactureCost);
        currentManufacture.setManufactureStorage(storage);
        validatorUtil.validate(currentManufacture);
        return manufactureRepository.save(currentManufacture);
    }

    @Transactional
    public Manufacture deleteManufacture(Long id) {
        final Manufacture currentManufacture = findManufacture(id);
        manufactureRepository.delete(currentManufacture);
        return currentManufacture;
    }

    @Transactional
    public void deleteAllManufactures() {
        manufactureRepository.deleteAll();
    }
}
