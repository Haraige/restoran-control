package ua.org.code.personaldepartment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ua.org.code.personaldepartment.persistence.entity.personal.kitchen.CookerEntity;

import java.util.Date;

@SpringBootApplication
public class PersonalDepartmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalDepartmentServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
