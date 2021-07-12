package ua.org.code.personaldepartment.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.org.code.personaldepartment.persistence.entity.personal.kitchen.CookerEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CookerRepository extends JpaRepository<CookerEntity, UUID> {

    Optional<CookerEntity> findById(UUID id);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);

    @Modifying
    @Query(nativeQuery = true, value =
            "insert into cookers_specializations cookers (cooker_id, specialization_id) values (?, ?)")
    void addSpecialization(UUID id, String specialization);

}
