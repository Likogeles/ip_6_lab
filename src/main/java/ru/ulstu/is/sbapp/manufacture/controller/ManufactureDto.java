package ru.ulstu.is.sbapp.manufacture.controller;

import ru.ulstu.is.sbapp.manufacture.model.Manufacture;
import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.storage.model.Storage;

import java.util.List;

public class ManufactureDto {
    private Long id;
    private String manufactureName;
    private Integer manufactureCost;
    private My_component my_component;
    private Storage storage;

    private List<My_component> myComponents;
    private List<Storage> storages;

    public void setId(Long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public ManufactureDto(){}

    public ManufactureDto(List<My_component> myComponents, List<Storage> storages){
        this.myComponents = myComponents;
        this.storages = storages;
    }

    public ManufactureDto(Manufacture manufacture, List<My_component> myComponents, List<Storage> storages) {
        this.id = manufacture.getId();
        this.manufactureName = manufacture.getManufactureName();
        this.manufactureCost = manufacture.getManufactureCost();
        this.my_component = manufacture.getComponent();
        this.storage = manufacture.getStorage();

        this.myComponents = myComponents;
        this.storages = storages;
    }

    public ManufactureDto(Manufacture manufacture) {
        this.id = manufacture.getId();
        this.manufactureName = manufacture.getManufactureName();
        this.manufactureCost = manufacture.getManufactureCost();
        this.my_component = manufacture.getComponent();
        this.storage = manufacture.getStorage();
    }


    public My_component getMy_component() {
        return my_component;
    }

    public String getMy_componentName() {
        return my_component.getMyComponentName();
    }

    public void setMy_component(My_component myComponent) {
        this.my_component = myComponent;
    }

    public void setMy_component(String componentName) {
        My_component newComponent = new My_component();
        for(int i = 0; i < myComponents.size(); i++){
            if(myComponents.get(i).getMy_component_name().equals(componentName)){
                newComponent = myComponents.get(i);
            }
        }
        this.my_component = newComponent;
    }

    public List<Storage> getStorages() {
        return storages;
    }

    public void setStorages(List<Storage> storages) {
        this.storages = storages;
    }

    public List<My_component> getMyComponents() {
        return myComponents;
    }

    public void setMyComponents(List<My_component> myComponents) {
        this.myComponents = myComponents;
    }

    public String getManufactureName() {
        return manufactureName;
    }

    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public Integer getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(Integer manufactureCost) {
        this.manufactureCost = manufactureCost;
    }


    public Storage getStorage() {
        return storage;
    }
    public String getStorageAdress() {
        return storage.getStorageAddress();
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setStorage(String storageAdress) {
        Storage newStorage = new Storage();
        for(int i = 0; i < storages.size(); i++){
            if(storages.get(i).getStorageAddress().equals(storageAdress)){
                newStorage = storages.get(i);
            }
        }
        this.storage = newStorage;
    }
}
