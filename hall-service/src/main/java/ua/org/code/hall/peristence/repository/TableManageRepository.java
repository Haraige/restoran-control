package ua.org.code.hall.peristence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.hall.peristence.entity.TableEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TableManageRepository extends
        JpaRepository<TableEntity, Integer>,
        CrudRepository<TableEntity, Integer> {

    @Query("select t from TableEntity t where not exists (select r from ReservationEntity r where r.tableEntity=t) " +
            "or exists (" +
            "select r from ReservationEntity r where " +
            "((r.dateTimeFrom >= :dateFrom and r.dateTimeTo >= :dateFrom " +
            "and r.dateTimeFrom >= :dateTo and r.dateTimeTo >= :dateTo) " +
            "or (r.dateTimeFrom <= :dateFrom and r.dateTimeTo <= :dateFrom " +
            "and r.dateTimeFrom <= :dateTo and r.dateTimeTo <= :dateTo))" +
            "and (r.customerAbsent=true or r.customerFinished=true))")
    List<TableEntity> getTableEntitiesByFreeIsTrueFromAndTo(LocalDateTime dateFrom, LocalDateTime dateTo);

    @Modifying
    @Query("update TableEntity t set t.waiterId = :waiterId where t.id = :tableId")
    void setTableWaiterById(Integer tableId, UUID waiterId);

}
