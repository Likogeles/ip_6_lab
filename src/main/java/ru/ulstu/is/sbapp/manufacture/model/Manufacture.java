package ru.ulstu.is.sbapp.manufacture.model;

import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.storage.model.Storage;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    @NotBlank(message = "Name can't be null or empty")
    private String manufactureName;
    private Integer manufactureCost;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="componentId")
    private My_component myComponent;

    @ManyToOne()
    @JoinColumn(name="storageId")
    private Storage storage;

    public Manufacture() {
    }

    public Manufacture(String manufactureName, My_component myComponent, Integer manufactureCost, Storage storage) {
        this.manufactureName = manufactureName;
        this.myComponent = myComponent;
        this.manufactureCost = manufactureCost;
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public String getManufactureName() {
        return manufactureName;
    }
    public void setManufactureName(String manufactureName) {
        this.manufactureName = manufactureName;
    }

    public My_component getComponent() {
        return myComponent;
    }
    public String getComponentName() {
        return myComponent.getMyComponentName();
    }
    public void setComponentId(My_component myComponent) {
        this.myComponent = myComponent;
    }

    public Integer getManufactureCost() {
        return manufactureCost;
    }
    public void setManufactureCost(Integer manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public Storage getManufactureStorage() {
        return storage;
    }
    public void setManufactureStorage(Storage storage) { this.storage = storage; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacture manufacture = (Manufacture) o;
        return Objects.equals(id, manufacture.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "id=" + id +
                ", manufactureName=" + manufactureName + '\'' +
                ", manufactureCost=" + manufactureCost.toString() + '\'' +
                ", manufactureStorage=" + storage.toString() + '\'' +
                '}';
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
        if (!storage.getManufactures().contains(this)) {
            storage.getManufactures().add(this);
        }
    }
}
