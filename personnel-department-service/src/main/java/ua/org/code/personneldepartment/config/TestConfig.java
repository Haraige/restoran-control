/*package ua.org.code.personaldepartment.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.org.code.personaldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personaldepartment.persistence.repository.CookerRepository;
import ua.org.code.personaldepartment.persistence.repository.WaiterRepository;
import ua.org.code.personaldepartment.service.impl.CookerServiceImpl;

import java.util.Date;

@Configuration
public class TestConfig {

    @Bean
    CommandLineRunner commandLineRunner(CookerServiceImpl cookerService,
                                        WaiterRepository waiterRepository) {
     return args -> {
         CookerEntity cooker = new CookerEntity("Peter",
                 "Deblin1",
                 new Date(),
                 "b1bb@gmail.com",
                 "+38035345123",
                 1000,
                 "peter1",
                 "123".toCharArray(),
                 new Date(),
                 null);
            cookerService.create(cooker);
        };
    }

}
*/