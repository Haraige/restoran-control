package ua.org.code.personneldepartment.service;

import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface WorkingDayService {

    void create(WorkingDayEntity workingDayEntity);
    void delete(WorkingDayEntity workingDayEntity);
    List<WorkingDayEntity> getAllByWorkerId(UUID workerId);
    List<WaiterEntity> getAllWaitersByDayOfWeek(DayOfWeek dayOfWeek);
    List<CookerEntity> getAllCookersByDayOfWeek(DayOfWeek dayOfWeek);

}
