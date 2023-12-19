package ru.ulstu.is.sbapp.storage.controller;

import ru.ulstu.is.sbapp.manufacture.model.Manufacture;
import ru.ulstu.is.sbapp.storage.model.Storage;

import java.util.List;

public class StorageDto {
    private Long id;
    private Integer numberOfPlaces;
    private Integer numberOfFreePlaces;
    private String storageAddress;
    private List<Manufacture> manufactures;

    public StorageDto(){}

    public StorageDto(Storage storage) {
        this.id = storage.getId();
        this.numberOfPlaces = storage.getNumberOfPlaces();
        this.numberOfFreePlaces = storage.getNumberOfFreePlaces();
        this.storageAddress = storage.getStorageAddress();
        this.manufactures = storage.getManufactures();
    }

    public long getId() {
        return id;
    }


    public Integer getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(Integer numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public Integer getNumberOfFreePlaces() {
        return numberOfFreePlaces;
    }

    public void setNumberOfFreePlaces(Integer numberOfFreePlaces) {
        this.numberOfFreePlaces = numberOfFreePlaces;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public List<Manufacture> getManufactures() {
        return manufactures;
    }

    public String getManufacturesNames() {
        String newStr = "";
        for(int i =0; i < manufactures.size(); i++){
            newStr += manufactures.get(i).getManufactureName() + ": " +
                    manufactures.get(i).getComponentName() + " - " +
                    manufactures.get(i).getManufactureCost() + " || ";
        }
        if(newStr.length() > 4) {
            newStr = newStr.substring(0, newStr.length() - 4);
        }else{
            newStr = "Нет изделий";
        }
        return newStr;
    }

    public void setManufactures(List<Manufacture> manufactures) {
        this.manufactures = manufactures;
    }
}
