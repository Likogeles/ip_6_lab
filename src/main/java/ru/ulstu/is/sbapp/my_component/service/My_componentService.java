package ru.ulstu.is.sbapp.my_component.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.my_component.repository.My_componentRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class My_componentService {
    @PersistenceContext
    private EntityManager em;

    private final My_componentRepository my_componentRepository;
    private final ValidatorUtil validatorUtil;

    public My_componentService(My_componentRepository my_componentRepository, ValidatorUtil validatorUtil) {
        this.my_componentRepository = my_componentRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public My_component addMyComponent(String myComponentName, Integer myComponentStorage, Integer myComponentCost, String myComponentPurveyor) {

        final My_component myComponent = new My_component(myComponentName, myComponentStorage, myComponentCost, myComponentPurveyor);
        validatorUtil.validate(myComponent);
        return my_componentRepository.save(myComponent);
    }

    @Transactional(readOnly = true)
    public My_component findMyComponent(Long id) {
        final Optional<My_component> myComponent = my_componentRepository.findById(id);

        return myComponent.orElseThrow(()->new My_componentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<My_component> findAllMyComponents() {

        return my_componentRepository.findAll();
    }

    @Transactional
    public My_component updateMyComponent(Long id, String myComponentName, Integer myComponentStorage, Integer myComponentCost, String myComponentPurveyor) {
        final My_component currentMyComponent = findMyComponent(id);
        currentMyComponent.setMyComponentName(myComponentName);
        currentMyComponent.setMyComponentStorage(myComponentStorage);
        currentMyComponent.setMyComponentCost(myComponentCost);
        currentMyComponent.setMyComponentPurveyor(myComponentPurveyor);
        validatorUtil.validate(currentMyComponent);
        return my_componentRepository.save(currentMyComponent);
    }

    @Transactional
    public My_component deleteMyComponent(Long id) {
        final My_component currentMyComponent = findMyComponent(id);
        my_componentRepository.delete(currentMyComponent);
        return currentMyComponent;
    }

    @Transactional
    public void deleteAllMyComponents() {
        my_componentRepository.deleteAll();
    }
}
