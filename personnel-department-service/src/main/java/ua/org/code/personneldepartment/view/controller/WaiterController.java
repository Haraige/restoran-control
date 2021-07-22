package ua.org.code.personneldepartment.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;
import ua.org.code.personneldepartment.service.WaiterService;
import ua.org.code.personneldepartment.view.dto.waiter.WaiterBasicDto;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personnel-department/waiters")
public class WaiterController implements SecuredRestController {

    private final WaiterService waiterService;

    @Autowired
    public WaiterController(WaiterService waiterService) {
        this.waiterService = waiterService;
    }

    @GetMapping
    public List<WaiterEntity> getAllWaiters() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(a -> System.out.println(a.getAuthority()));

        return waiterService.getAll();
    }

    @GetMapping("/{id}")
    public WaiterEntity getWaiterById(@PathVariable UUID id) {
        return waiterService.findById(id);
    }

    @GetMapping("/username/{username}")
    public WaiterEntity getWaiterByUsername(@PathVariable String username) {
        return waiterService.findByUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WaiterEntity createWaiter(@RequestBody WaiterEntity waiterEntity) {
        return waiterService.create(waiterEntity);
    }

    @PutMapping("/{id}")
    public WaiterEntity updateWaiter(@PathVariable UUID id, @RequestBody WaiterEntity waiterEntity) {
        return waiterService.update(id, waiterEntity);
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
    public List<WaiterBasicDto> getAllBasicWaitersByDayOfWeek(@PathVariable DayOfWeek dayOfWeek) {
        return waiterService.getAllWaitersByDayOfWeek(dayOfWeek).
                stream().
                map(WaiterBasicDto::new).
                collect(Collectors.toList());
    }

}
