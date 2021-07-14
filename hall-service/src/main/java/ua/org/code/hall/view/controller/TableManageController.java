package ua.org.code.hall.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.org.code.hall.peristence.entity.TableEntity;
import ua.org.code.hall.service.TableManageService;

import java.util.List;

@RestController
@RequestMapping("/hall")
public class TableManageController {

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

}
