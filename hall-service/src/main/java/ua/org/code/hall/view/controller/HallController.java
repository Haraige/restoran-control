package ua.org.code.hall.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.code.hall.service.HallService;

@RestController
@RequestMapping("/hall")
public class HallController implements SecuredRestController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public void getBy() {
        hallService.getWaiterProfileInfoByUsername("dd");
    }


}
