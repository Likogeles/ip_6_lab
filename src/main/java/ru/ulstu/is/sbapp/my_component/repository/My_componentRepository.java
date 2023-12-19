package ru.ulstu.is.sbapp.my_component.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.my_component.model.My_component;

public interface My_componentRepository extends JpaRepository<My_component, Long> {
}