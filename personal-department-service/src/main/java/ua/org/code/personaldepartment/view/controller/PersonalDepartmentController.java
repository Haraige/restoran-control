package ua.org.code.personaldepartment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.org.code.personaldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personaldepartment.service.CookerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personal-department")
public class PersonalDepartmentController {

    private final CookerService cookerService;

    @Autowired
    public PersonalDepartmentController(CookerService cookerService) {
        this.cookerService = cookerService;
    }

    @GetMapping("/cookers")
    public List<CookerEntity> getAllCookers() {
        return cookerService.getAll();
    }

    @GetMapping("/cookers/{id}")
    public CookerEntity getCookerById(@PathVariable String id) {
        return cookerService.findById(UUID.fromString(id));

    }

    @PostMapping("/cookers")
    public void createCooker(@RequestBody CookerEntity cookerEntity) {

        cookerService.create(cookerEntity);

    }

    @PutMapping("/cookers/{id}")
    public void updateCooker(@RequestBody CookerEntity cookerEntity, @PathVariable UUID id) {

        CookerEntity cooker = cookerService.findById(id);
        cookerService.update(cookerEntity);

    }

    @DeleteMapping("/cookers/{id}")
    public void deleteCooker(@PathVariable UUID id) {

        cookerService.delete(id);

    }

}
