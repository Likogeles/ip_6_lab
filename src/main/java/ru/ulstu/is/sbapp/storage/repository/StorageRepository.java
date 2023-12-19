package ru.ulstu.is.sbapp.storage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.sbapp.storage.model.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
}
