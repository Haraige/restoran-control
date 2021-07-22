package ua.org.code.hall.service;

import ua.org.code.hall.peristence.entity.TableEntity;

import java.util.Date;
import java.util.List;

public interface TableManageService extends CRUDService<TableEntity, Integer> {

    void reserveTable(Integer id);
    void freeTable(Integer id);
    void refreshAllWaitersTables();

    List<TableEntity> getAllFreeTablesFromDateToDate(Date from, Date to);

}
