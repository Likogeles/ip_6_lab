package ru.ulstu.is.sbapp.my_component.service;

public class My_componentNotFoundException extends RuntimeException {
    public My_componentNotFoundException(Long id) {
        super(String.format("My_component with id [%s] is not found", id));
    }
}
