package ua.org.code.hall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ua.org.code.hall.entity.TableEntity;
import ua.org.code.hall.service.HallService;

@RestController
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    public TableEntity getTableById(Integer id) {
        return hallService.findById(id);
    }

}
