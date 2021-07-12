package ua.org.code.hall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.hall.entity.TableEntity;

@Repository
public interface HallRepository extends JpaRepository<TableEntity, Integer> {
}
