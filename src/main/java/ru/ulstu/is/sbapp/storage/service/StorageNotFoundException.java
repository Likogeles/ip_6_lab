package ru.ulstu.is.sbapp.storage.service;

public class StorageNotFoundException extends RuntimeException {
    public StorageNotFoundException(Long id) {
        super(String.format("Storage with id [%s] is not found", id));
    }
}
