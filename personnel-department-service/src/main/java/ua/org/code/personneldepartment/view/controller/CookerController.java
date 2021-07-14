package ua.org.code.personneldepartment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.service.CookerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personnel-department/cookers")
public class CookerController {

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
        try {
            return cookerService.findById(UUID.fromString(id));
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCooker(@RequestBody CookerEntity cookerEntity) {

        try {
            cookerService.create(cookerEntity);
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }

    }

    @PutMapping("/{id}")
    public void updateCooker(@RequestBody CookerEntity cookerEntity, @PathVariable UUID id) {

        try {
            cookerService.update(id, cookerEntity);
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }

    }

    @DeleteMapping("/{id}")
    public void deleteCooker(@PathVariable UUID id) {

        try {
            cookerService.delete(id);
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }

    }

}
