package ru.ulstu.is.sbapp.manufacture.service;

public class ManufactureNotFoundException extends RuntimeException {
    public ManufactureNotFoundException(Long id) {
        super(String.format("Manufacture with id [%s] is not found", id));
    }
}
