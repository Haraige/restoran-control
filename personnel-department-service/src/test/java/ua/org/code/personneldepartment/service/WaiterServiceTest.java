package ua.org.code.personneldepartment.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;
import ua.org.code.personneldepartment.exception.status.RestBadRequestException;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personneldepartment.persistence.repository.WorkingDayRepository;
import ua.org.code.personneldepartment.service.impl.WaiterServiceImpl;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("h2db")
class WaiterServiceTest {

    @Autowired
    private WaiterService waiterService;

    private final List<WaiterEntity> entities = new ArrayList<>();

    @Test
    @Order(1)
    public void createSeveralWaiter() {

        WaiterEntity firstWaiterEntity = new WaiterEntity(
                "TestName",
                "TestUsername",
                new Date(),
                "test1@gmail.com",
                "+381111111",
                1000,
                "test1"
        );

        WaiterEntity secondWaiterEntity = new WaiterEntity(
                "TestName",
                "TestUsername",
                new Date(),
                "test2@gmail.com",
                "+3802222222",
                1000,
                "test2"
        );

        WaiterEntity thirdWaiterEntity = new WaiterEntity(
                "TestName",
                "TestUsername",
                new Date(),
                "test3@gmail.com",
                "+383333333",
                1000,
                "test3"
        );

        entities.add(firstWaiterEntity);
        entities.add(secondWaiterEntity);
        entities.add(thirdWaiterEntity);

        WaiterEntity firstCreated = waiterService.create(entities.get(0));
        WaiterEntity secondCreated = waiterService.create(entities.get(1));
        WaiterEntity thirdCreated = waiterService.create(entities.get(2));

        assertEquals(waiterService.findById(firstCreated.getId()).getEmail(), entities.get(0).getEmail());
        assertEquals(waiterService.findById(secondCreated.getId()).getEmail(), entities.get(1).getEmail());
        assertEquals(waiterService.findById(thirdCreated.getId()).getEmail(), entities.get(2).getEmail());

    }

    @Test
    @Order(2)
    void findAllAndUpdateFirst() {

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