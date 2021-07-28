package ua.org.code.hall.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.org.code.hall.peristence.entity.ReservationEntity;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.service.TableManageService;
import ua.org.code.hall.view.VO.WaiterBasicVO;
import ua.org.code.hall.view.dto.ReservationWithTableIdDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hall/tables")
public class TableManageController implements SecuredRestController {

    private final TableManageService tableManageService;

    @Autowired
    public TableManageController(TableManageService tableManageService) {
        this.tableManageService = tableManageService;
    }

    @GetMapping("/{id}")
    public TableEntity getTableById(@PathVariable Integer id) {
        return tableManageService.findById(id);
    }

    @GetMapping
    public List<TableEntity> getAllTables() {
        return tableManageService.getAll();
    }

    @PostMapping
    public TableEntity createTable(@RequestBody TableEntity entity) {
        return tableManageService.create(entity);
    }

    @PostMapping("/reserve")
    public void reserveTable(@RequestBody ReservationWithTableIdDto reservation) {
        tableManageService.reserveTable(reservation);
    }

    @GetMapping("/free/from/{dateFrom}/to/{dateTo}")
    public List<TableEntity> getAllFreeTablesFromTo(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        return tableManageService.getAllFreeTablesFromDateToDate(dateFrom, dateTo);
    }

}
