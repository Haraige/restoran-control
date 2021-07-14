package ua.org.code.personneldepartment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.service.WaiterService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personnel-department/waiters")
public class WaiterController {

    private final WaiterService waiterService;

    @Autowired
    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping()
    public List<WaiterEntity> getAllWaiters() {
        return waiterService.getAll();
    }

    @GetMapping("/{id}")
    public WaiterEntity getWaiterById(@PathVariable UUID id) {

        try {
            return waiterService.findById(id);
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
    public void createWaiter(@RequestBody WaiterEntity waiterEntity) {
        try {
            waiterService.create(waiterEntity);
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }
    }

    @PutMapping("/{id}")
    public void updateWaiter(@PathVariable UUID id, @RequestBody WaiterEntity waiterEntity) {
        try {
            waiterService.update(id, waiterEntity);
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }
    }

    @DeleteMapping("/{id}")
    public void deleteWaiter(@PathVariable UUID id) {
        try {
            waiterService.delete(id);
        } catch (RestBadRequestException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getFieldError().getMessage(),
                    e
            );
        }
    }

}
