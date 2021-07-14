package ua.org.code.personneldepartment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CookerRepository extends JpaRepository<CookerEntity, UUID>, CrudRepository<CookerEntity, UUID> {

    Optional<CookerEntity> findByEmail(String email);
    Optional<CookerEntity> findByPhoneNumber(String phoneNumber);
    Optional<CookerEntity> findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);

    @Modifying
    @Query(nativeQuery = true, value =
            "insert into cookers_specializations (cooker_id, specialization_id) values (?, ?)")
    void addSpecialization(UUID id, String specialization);

    @Query(value =
            "select * from cookers c inner join " +
                    "working_days wd on " +
                    "c.id = wd.worker_id where " +
                    "day_of_week = ?1",
            nativeQuery = true)
    List<CookerEntity> findAllByDayOfWeek(DayOfWeek dayOfWeek);

    @Query(
            value = "select * from cookers as c " +
                    "inner join working_days as wd " +
                    "on (c.id = wd.worker_id) where " +
                    "wd.day_of_week=?1", nativeQuery = true)
    List<CookerEntity> findAllByWorkingDay(DayOfWeek dayOfWeek);

}
