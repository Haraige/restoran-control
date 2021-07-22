package ua.org.code.hall.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.service.TableManageService;
import ua.org.code.hall.view.VO.WaiterBasicVO;

import java.util.Date;
import java.util.List;

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
    public void createTable(@RequestBody TableEntity entity) {
        tableManageService.create(entity);
    }

    @PostMapping("/reserve/{id}")
    public void reserveTable(@PathVariable Integer id) {
        tableManageService.reserveTable(id);
    }

    @PostMapping("/free/{id}")
    public void freeTable(@PathVariable Integer id) {
        tableManageService.freeTable(id);
    }

    @GetMapping("/free/{dateFrom}/{dateTo}")
    public void getAllFreeTablesFromTo(@PathVariable Date dateFrom, @PathVariable Date dateTo) {
        tableManageService.getAllFreeTablesFromDateToDate(dateFrom, dateTo);
    }

}
