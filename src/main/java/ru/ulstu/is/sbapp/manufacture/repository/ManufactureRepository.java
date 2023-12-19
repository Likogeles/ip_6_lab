package ru.ulstu.is.sbapp.manufacture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.manufacture.model.Manufacture;

public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
}
