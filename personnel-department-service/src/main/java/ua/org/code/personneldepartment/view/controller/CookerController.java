package ua.org.code.personneldepartment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.service.CookerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personnel-department/cookers")
public class CookerController implements SecuredRestController{

    private final CookerService cookerService;

    @Autowired
    public CookerController(CookerService cookerService) {
        this.cookerService = cookerService;
    }

    @GetMapping("")
    public List<CookerEntity> getAllCookers() {
        return cookerService.getAll();
    }

    @GetMapping("/{id}")
    public CookerEntity getCookerById(@PathVariable String id) {
        return cookerService.findById(UUID.fromString(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CookerEntity createCooker(@RequestBody CookerEntity cookerEntity) {
        return cookerService.create(cookerEntity);
    }

    @PutMapping("/{id}")
    public CookerEntity updateCooker(@RequestBody CookerEntity cookerEntity, @PathVariable UUID id) {
        return cookerService.update(id, cookerEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteCooker(@PathVariable UUID id) {
        cookerService.delete(id);
    }
}
