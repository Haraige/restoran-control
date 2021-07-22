package ua.org.code.hall.peristence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ua.org.code.hall.peristence.entity.ReservationEntity;

import java.util.UUID;

public interface ReservationRepository extends JpaRepository<ReservationEntity, UUID> {

}
