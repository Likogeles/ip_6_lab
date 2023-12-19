package ru.ulstu.is.sbapp.my_component.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class My_component {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    @NotBlank(message = "Name can't be null or empty")
    private String my_component_name;
    @NotNull(message = "Num in storage can't be null")
    private Integer my_component_num_in_storage;
    @NotNull(message = "Cost can't be null")
    private Integer my_component_cost;
    @NotBlank(message = "Purveyor can't be null or empty")
    private String my_component_purveyor;

    public My_component() {
    }

    public My_component(String my_component_name, Integer my_component_storage, Integer my_component_cost, String my_component_purveyor) {
        this.my_component_name = my_component_name;
        this.my_component_num_in_storage = my_component_storage;
        this.my_component_cost = my_component_cost;
        this.my_component_purveyor = my_component_purveyor;
    }

    public Long getId() {
        return id;
    }

    public String getMyComponentName() {
        return my_component_name;
    }

    public void setMyComponentName(String myComponentName) { this.my_component_name = myComponentName; }

    public String getMy_component_name() {
        return my_component_name;
    }


    public Integer getMyComponentStorage() {
        return my_component_num_in_storage;
    }

    public void setMyComponentStorage(Integer myComponentStorage) {
        this.my_component_num_in_storage = myComponentStorage;
    }

    public Integer getMyComponentCost() {
        return my_component_cost;
    }

    public void setMyComponentCost(Integer myComponentCost) {
        this.my_component_cost = myComponentCost;
    }

    public String getMyComponentPurveyor() {
        return my_component_purveyor;
    }

    public void setMyComponentPurveyor(String myComponentPurveyor) {
        this.my_component_purveyor = myComponentPurveyor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        My_component student = (My_component) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MyComponent{" +
                "id=" + id +
                ", my_component_name=" + my_component_name + '\'' +
                ", my_component_storage=" + my_component_num_in_storage + '\'' +
                ", my_component_cost=" + my_component_cost + '\'' +
                ", my_component_purveyor=" + my_component_purveyor + '\'' +
                '}';
    }
}

