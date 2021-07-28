package ua.org.code.hall.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.org.code.hall.service.HallService;
import ua.org.code.hall.service.TableManageService;
import ua.org.code.hall.view.VO.WaiterBasicVO;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hall")
public class HallController implements SecuredRestController {

    private final HallService hallService;
    private final TableManageService tableManageService;

    @Autowired
    public HallController(HallService hallService, TableManageService tableManageService) {
        this.hallService = hallService;
        this.tableManageService = tableManageService;
    }

    @GetMapping("/finished/{reservationId}")
    public void customerFinished(@PathVariable UUID reservationId) {
        tableManageService.setCustomerFinishedTrue(reservationId);
    }

    @GetMapping("/refresh")
    public void refreshWaitersTables() {
        tableManageService.refreshAllWaitersTables();
    }

    @GetMapping("/waiters/current_day")
    public List<WaiterBasicVO> getCurrentDayWaiters() {
        return tableManageService.getCurrentDayWaiters();
    }


}
