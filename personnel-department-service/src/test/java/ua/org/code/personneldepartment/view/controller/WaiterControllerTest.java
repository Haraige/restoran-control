package ua.org.code.personneldepartment.view.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.repository.CookerRepository;
import ua.org.code.personneldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personneldepartment.persistence.repository.WorkingDayRepository;
import ua.org.code.personneldepartment.service.PersonnelCheckForExistDataService;
import ua.org.code.personneldepartment.service.WaiterService;
import ua.org.code.personneldepartment.service.impl.PersonnelCheckForExistDataServiceImpl;
import ua.org.code.personneldepartment.service.impl.WaiterServiceImpl;

import java.util.Date;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


class WaiterControllerTest {

    private final static String URI = "/personnel-department/waiters";
    private final static UUID ID = UUID.randomUUID();

    private WaiterRepository waiterRepository;
    private WorkingDayRepository workingDayRepository;
    private CookerRepository cookerRepository;

    private WaiterService waiterService;

    private PersonnelCheckForExistDataService checkForExistDataService;

    @BeforeEach
    void setUp() {

    }

    //@Test
    void createWaiter() throws Exception {
        WaiterEntity waiterEntity = new WaiterEntity(
                "TestName",
                "TestUsername",
                new Date(),
                "test@gmail.com",
                "+3800000000",
                1000,
                "test"
        );

        System.out.println(waiterEntity);

        System.out.println(waiterService.create(waiterEntity));
    }

    @Test
    void getAllWaiters() {
    }

    //@Test
    void getWaiterById() {
        WaiterEntity entity = waiterService.findByEmail("testemail1@gmail.com");
        assert(entity.getPhoneNumber().equals("+3800000000"));
    }

    @Test
    void updateWaiter() {
    }

    @Test
    void deleteWaiter() {
    }

    @Test
    void getAllWaitersSchedules() {
    }

    @Test
    void getWaiterWorkingDays() {
    }

    @Test
    void addWaiterWorkingDays() {
    }

    @Test
    void getAllBasicWaitersByDayOfWeek() {
    }
}