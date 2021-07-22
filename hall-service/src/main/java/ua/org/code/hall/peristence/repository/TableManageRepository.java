package ua.org.code.hall.peristence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.code.hall.peristence.entity.TableEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TableManageRepository extends
        JpaRepository<TableEntity, Integer>,
        CrudRepository<TableEntity, Integer> {

    @Modifying
    @Query("update TableEntity t set t.free = :free where t.id = :id")
    void setTableFreeById(Integer id, boolean free);

    @Query("select t from TableEntity t inner join " +
            "ReservationEntity r where " +
            "r.dateTimeFrom < :dateFrom and r.dateTimeTo > :dateTo")
    List<TableEntity> getTableEntitiesByFreeIsTrueFromAndTo(Date dateFrom, Date dateTo);

    @Modifying
    @Query("update TableEntity t set t.waiterId = :waiterId where t.id = :tableId")
    void setTableWaiterById(Integer tableId, UUID waiterId);

}
