package ru.ulstu.is.sbapp.my_component.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ulstu.is.sbapp.my_component.model.My_component;

import javax.validation.constraints.NotBlank;

public class My_componentDto {
    private long id;
    @NotBlank(message = "Name can't be null or empty")
    private String name;
    private Integer num_in_storage;
    private Integer cost;
    @NotBlank(message = "Purveyor can't be null or empty")
    private String purveyor;

    public My_componentDto(){}

    public My_componentDto(My_component my_component) {
        this.id = my_component.getId();
        this.name = my_component.getMyComponentName();
        this.num_in_storage = my_component.getMyComponentStorage();
        this.cost = my_component.getMyComponentCost();
        this.purveyor = my_component.getMyComponentPurveyor();
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public long getId() {
        return id;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return name;
    }

    public void setName(String my_component_name) {
        this.name = my_component_name;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Integer getNum_in_storage() {
        return num_in_storage;
    }

    public void setNum_in_storage(Integer my_component_num_in_storage) {
        this.num_in_storage = my_component_num_in_storage;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer my_component_cost) {
        this.cost = my_component_cost;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getPurveyor() {
        return purveyor;
    }

    public void setPurveyor(String my_component_purveyor) {
        this.purveyor = my_component_purveyor;
    }

}
