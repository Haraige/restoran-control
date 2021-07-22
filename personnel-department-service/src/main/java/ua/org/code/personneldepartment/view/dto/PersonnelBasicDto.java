package ua.org.code.personneldepartment.view.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PersonnelBasicDto {

    private UUID id;
    private String name;
    private String surname;
    private String username;

    public PersonnelBasicDto() {
    }
}
