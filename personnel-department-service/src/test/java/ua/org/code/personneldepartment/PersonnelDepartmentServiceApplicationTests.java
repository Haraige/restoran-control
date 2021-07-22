package ua.org.code.personneldepartment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2db")
class PersonnelDepartmentServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
