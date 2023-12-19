package ru.ulstu.is.sbapp.my_component.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.configuration.WebConfiguration;
import ru.ulstu.is.sbapp.my_component.model.My_component;
import ru.ulstu.is.sbapp.my_component.service.My_componentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/myComponent")
public class My_componentController {
    private final My_componentService myComponentService;

    public My_componentController(My_componentService myComponentService) { this.myComponentService =  myComponentService; }

    @GetMapping("/{id}")
    public My_componentDto getMyComponent(@PathVariable Long id) {
        return new My_componentDto(myComponentService.findMyComponent(id));
    }

    @GetMapping("/")
    public List<My_componentDto> getMyComponents() {
        return myComponentService.findAllMyComponents().stream().map(My_componentDto::new).toList();
    }

    @PostMapping("/")
    public My_componentDto createMyComponent(@RequestBody @Valid My_componentDto my_componentDto) {
        return new My_componentDto(myComponentService.addMyComponent(my_componentDto.getName(), my_componentDto.getNum_in_storage(), my_componentDto.getCost(), my_componentDto.getPurveyor()));
    }

    @PatchMapping("/{id}")
    public My_componentDto updateMyComponent(@PathVariable Long id,@RequestBody @Valid My_componentDto my_componentDto
    ) {
        return new My_componentDto(myComponentService.updateMyComponent(id, my_componentDto.getName(), my_componentDto.getNum_in_storage(), my_componentDto.getCost(), my_componentDto.getPurveyor()));
    }

    @DeleteMapping("/{id}")
    public My_componentDto deleteMyComponent(@PathVariable Long id) {
        return new My_componentDto(myComponentService.deleteMyComponent(id));
    }
}