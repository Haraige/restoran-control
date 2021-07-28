package ua.org.code.personneldepartment.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personneldepartment.persistence.repository.WorkingDayRepository;
import ua.org.code.personneldepartment.service.impl.WaiterServiceImpl;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WaiterServiceTest {

    @Mock
    private PersonnelCheckForExistDataService checkForExistDataService;
    @Mock
    private WaiterRepository waiterRepository;
    @Mock
    private WorkingDayRepository workingDayRepository;
    @Mock
    private KeycloakService keycloakService;

    private WaiterServiceImpl waiterService;

    @BeforeEach
    void setUp() {
        waiterService = new WaiterServiceImpl(waiterRepository,
                workingDayRepository,
                checkForExistDataService,
                keycloakService);
    }


    @Test
    @Order(1)
    public void createWaiter() {
        WaiterEntity waiterEntity = new WaiterEntity("test", "test", new Date(), "test", "test", 1000, "test");

        WaiterEntity createdEntity = waiterService.create(waiterEntity);

        /*assertThat(createdEntity.getName()).isEqualTo(waiterEntity.getName());
        assertThat(createdEntity.getSurname()).isEqualTo(waiterEntity.getSurname());
        assertThat(createdEntity.getEmail()).isEqualTo(waiterEntity.getEmail());
        assertThat(createdEntity.getPhoneNumber()).isEqualTo(waiterEntity.getPhoneNumber());
        assertThat(createdEntity.getDateOfBirth()).isEqualTo(waiterEntity.getDateOfBirth());
        assertThat(createdEntity.getSalary()).isEqualTo(waiterEntity.getSalary());
        assertThat(createdEntity.getUsername()).isEqualTo(waiterEntity.getUsername());*/


    }

    @Test
    public void updateWaiter() {

    }

    @Test
    void findById() {



    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAllWaitersWorkingDays() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void findByEmail() {
    }

    @Test
    void findByPhoneNumber() {
    }

    @Test
    void addWaiterWorkingDays() {
    }

    @Test
    void getWorkingDays() {
    }

    @Test
    void getAllWaitersByDayOfWeek() {
    }
}