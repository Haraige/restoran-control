package ua.org.code.personneldepartment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.personneldepartment.persistence.entity.schedule.WorkingDayEntity;

import java.util.List;

@Repository
public interface WorkingDayRepository extends
        JpaRepository<WorkingDayEntity, Integer>,
        CrudRepository<WorkingDayEntity, Integer> {

    @Query(value = "" +
            "select exists(select * from working_days where " +
            "day_of_week = ?1 and worker_id = ?2)",
            nativeQuery = true)
    boolean existsByDayOfWeekAndWorkerId(String dayOfWeek, String id);

    @Query(value = "select * from working_days where worker_id = ?1", nativeQuery = true)
    List<WorkingDayEntity> findAllByWorkerId(String workerId);

    @Query(value = "select * from working_days where worker_type = 'waiter'", nativeQuery = true)
    List<WorkingDayEntity> findAllByTypeWaiter();

    @Query(value = "select * from working_days where worker_type = 'cooker'", nativeQuery = true)
    List<WorkingDayEntity> findAllByTypeCooker();
}
