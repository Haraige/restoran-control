package ua.org.code.personneldepartment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WaiterRepository extends JpaRepository<WaiterEntity, UUID>, CrudRepository<WaiterEntity, UUID> {

    Optional<WaiterEntity> findByEmail(String email);
    Optional<WaiterEntity> findByPhoneNumber(String phoneNumber);
    Optional<WaiterEntity> findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);

    @Query(
            value = "select * from waiters as w " +
                    "inner join working_days as wd " +
                    "on (w.id = wd.worker_id) where " +
                    "wd.day_of_week=?1",
            nativeQuery = true)
    List<WaiterEntity> findAllWaitersByWorkingDay(DayOfWeek dayOfWeek);
}
