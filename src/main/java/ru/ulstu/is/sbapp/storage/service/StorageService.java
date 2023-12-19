package ru.ulstu.is.sbapp.storage.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.manufacture.model.Manufacture;
import ru.ulstu.is.sbapp.storage.model.Storage;
import ru.ulstu.is.sbapp.storage.repository.StorageRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {
    @PersistenceContext
    private EntityManager em;

    private final StorageRepository storageRepository;
    private final ValidatorUtil validatorUtil;

    public StorageService(StorageRepository storageRepository, ValidatorUtil validatorUtil){
        this.storageRepository = storageRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Storage addStorage(Integer numberOfPlaces, Integer numberOfFreePlaces, List<Manufacture> manufactures, String storageAddress) {

        final Storage storage = new Storage(numberOfPlaces, numberOfFreePlaces, manufactures, storageAddress);
        validatorUtil.validate(storage);
        return storageRepository.save(storage);
    }

    @Transactional(readOnly = true)
    public Storage findStorage(Long id) {
        final Optional<Storage> storage = storageRepository.findById(id);
        return storage.orElseThrow(() -> new StorageNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Storage> findAllStorages() {
        return storageRepository.findAll();
    }

    @Transactional
    public Storage updateStorage(Long id, Integer numberOfPlaces, Integer numberOfFreePlaces, List<Manufacture> manufactures, String storageAddress) {
        final Storage currentStorage = findStorage(id);
        currentStorage.setNumberOfPlaces(numberOfPlaces);
        currentStorage.setNumberOfFreePlaces(numberOfFreePlaces);
        currentStorage.setManufactures(manufactures);
        currentStorage.setStorageAddress(storageAddress);
        validatorUtil.validate(currentStorage);
        return storageRepository.save(currentStorage);
    }

    @Transactional
    public Storage deleteStorage(Long id) {
        final Storage currentStorage = findStorage(id);
        storageRepository.delete(currentStorage);
        return currentStorage;
    }

    @Transactional
    public void deleteAllStorages() {
        storageRepository.deleteAll();
    }
}
