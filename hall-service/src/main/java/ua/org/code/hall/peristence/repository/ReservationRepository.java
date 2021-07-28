package ua.org.code.hall.peristence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.org.code.hall.peristence.entity.ReservationEntity;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

    @Modifying
    @Query("update ReservationEntity r set r.customerFinished=true where r.id=?1")
    void setCustomerFinishedTrue(UUID reservationId);

    @Modifying
    @Query("update ReservationEntity r set r.customerAbsent=true where r.id=?1")
    void setCustomerAbsentTrue(UUID reservationId);

    @Query(value = "select r from reservations r where date(r.date_time_to + ? * interval '1 minute') > current_timestamp",
            nativeQuery = true)
    List<ReservationEntity> findReservationEntitiesWhereCustomerNotCame(Integer delayInMinute);

}
