package ua.org.code.hall.service;

import ua.org.code.hall.peristence.entity.ReservationEntity;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.view.VO.WaiterBasicVO;
import ua.org.code.hall.view.dto.ReservationWithTableIdDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TableManageService extends CRUDService<TableEntity, Integer> {

    ReservationEntity reserveTable(ReservationWithTableIdDto reservation);

    void setCustomerFinishedTrue(UUID reservationId);
    void freeTables();
    void refreshAllWaitersTables();
    List<WaiterBasicVO> getCurrentDayWaiters();

    List<TableEntity> getAllFreeTablesFromDateToDate(LocalDateTime from, LocalDateTime to);

}
