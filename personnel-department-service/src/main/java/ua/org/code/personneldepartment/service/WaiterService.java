package ua.org.code.personneldepartment.service;

import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface WaiterService extends CRUDService<WaiterEntity> {

    void addWaiterWorkingDays(UUID id, List<DayOfWeek> daysOfWeek);

    List<WorkingDayEntity> getAllWaitersWorkingDays();
    List<DayOfWeek> getWorkingDays(UUID id);

    List<WaiterEntity> getAllWaitersByDayOfWeek(DayOfWeek dayOfWeek);

    WaiterEntity findByUsername(String username);
    WaiterEntity findByEmail(String email);
    WaiterEntity findByPhoneNumber(String phoneNumber);
}
