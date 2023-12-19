package ru.ulstu.is.sbapp.storage.model;

import ru.ulstu.is.sbapp.manufacture.model.Manufacture;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private Integer numberOfPlaces;
    private Integer numberOfFreePlaces;
    @NotBlank(message = "Storage Address can't be null or empty")
    private String storageAddress;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "StorageId")
    private List<Manufacture> manufactures = new ArrayList<>();

    public Storage() {
    }
    public Storage(Integer numberOfPlaces, Integer numberOfFreePlaces, List<Manufacture> manufactures, String storageAddress) {
        this.numberOfPlaces = numberOfPlaces;
        this.numberOfFreePlaces = numberOfFreePlaces;
        this.manufactures = manufactures;
        this.storageAddress = storageAddress;
    }

    public Long getId() {
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

    public List<Manufacture> getManufactures() {
        return manufactures;
    }

    public void setManufactures(List<Manufacture> manufactures) {
        this.manufactures = manufactures;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", numberOfPlaces=" + numberOfPlaces + '\'' +
                ", numberOfFreePlaces=" + numberOfFreePlaces + '\'' +
                ", storageAddress=" + storageAddress + '\'' +
                '}';
    }

    public void addManufacture(Manufacture manufacture) {
        this.manufactures.add(manufacture);
        if (manufacture.getStorage() != this) {
            manufacture.setStorage(this);
        }
    }
}
