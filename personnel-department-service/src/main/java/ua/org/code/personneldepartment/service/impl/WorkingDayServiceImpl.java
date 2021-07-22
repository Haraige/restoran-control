package ua.org.code.personneldepartment.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;
import ua.org.code.personneldepartment.persistence.repository.WorkingDayRepository;
import ua.org.code.personneldepartment.service.WorkingDayService;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class WorkingDayServiceImpl implements WorkingDayService {

    private final WorkingDayRepository workingDayRepository;

    @Autowired
    public WorkingDayServiceImpl(WorkingDayRepository workingDayRepository) {
        this.workingDayRepository = workingDayRepository;
    }

    @Override
    public void create(WorkingDayEntity workingDayEntity) {
        workingDayRepository.save(workingDayEntity);
    }

    @Override
    public void delete(WorkingDayEntity workingDayEntity) {

    }

    @Override
    public List<WorkingDayEntity> getAllByWorkerId(UUID workerId) {
        return null;
    }

    @Override
    public List<WaiterEntity> getAllWaitersByDayOfWeek(DayOfWeek dayOfWeek) {
        return null;
    }

    @Override
    public List<CookerEntity> getAllCookersByDayOfWeek(DayOfWeek dayOfWeek) {
        return null;
    }
}
