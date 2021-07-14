package ua.org.code.hall.peristence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.hall.peristence.entity.TableEntity;

@Repository
public interface TableManageRepository extends
        JpaRepository<TableEntity, Integer>,
        CrudRepository<TableEntity, Integer> {

}
