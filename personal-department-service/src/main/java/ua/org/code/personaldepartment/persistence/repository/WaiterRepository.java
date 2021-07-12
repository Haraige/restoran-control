package ua.org.code.personaldepartment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.personaldepartment.persistence.entity.personal.hall.WaiterEntity;

import java.util.UUID;

@Repository
public interface WaiterRepository extends JpaRepository<WaiterEntity, UUID> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);

}
