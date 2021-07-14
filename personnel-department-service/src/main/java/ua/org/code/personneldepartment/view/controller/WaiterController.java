package ua.org.code.personneldepartment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;
import ua.org.code.personneldepartment.service.WaiterService;

import java.time.DayOfWeek;
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
        return waiterService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createWaiter(@RequestBody WaiterEntity waiterEntity) {
        waiterService.create(waiterEntity);
    }

    @PutMapping("/{id}")
    public void updateWaiter(@PathVariable UUID id, @RequestBody WaiterEntity waiterEntity) {
        waiterService.update(id, waiterEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteWaiter(@PathVariable UUID id) {
        waiterService.delete(id);
    }

    @GetMapping("/schedules")
    public List<WorkingDayEntity> getAllWaitersSchedules() {
        return waiterService.getAllWaitersWorkingDays();
    }

    @GetMapping("/schedules/{id}")
    public List<DayOfWeek> getWaiterWorkingDays(@PathVariable UUID id) {
        return waiterService.getWorkingDays(id);
    }

    @PostMapping("/schedules/{id}")
    public void addWaiterWorkingDays(@PathVariable UUID id, @RequestBody List<DayOfWeek> daysOfWeek) {
        waiterService.addWaiterWorkingDays(id, daysOfWeek);
    }

    @GetMapping("/schedules/day/{dayOfWeek}")
    public List<WaiterEntity> getAllWaitersByDayOfWeek(@PathVariable DayOfWeek dayOfWeek) {
        return waiterService.getAllWaitersByDayOfWeek(dayOfWeek);
    }

}
