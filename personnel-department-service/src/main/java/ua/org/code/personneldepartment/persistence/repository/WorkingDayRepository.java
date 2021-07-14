package ua.org.code.personneldepartment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkingDayRepository extends JpaRepository<WorkingDayEntity, Integer> {

    @Query(value = "select * from working_days where worker_id = ?1", nativeQuery = true)
    List<WorkingDayEntity> findAllByWorkerId(UUID workerId);
}
