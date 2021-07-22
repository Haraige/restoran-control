package ua.org.code.personneldepartment.view.dto.cooker;

import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.view.dto.PersonnelBasicDto;

public class CookerBasicDto extends PersonnelBasicDto {

    public CookerBasicDto(CookerEntity entity) {

        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setSurname(entity.getSurname());
        this.setUsername(entity.getUsername());

    }

    public CookerBasicDto() {
    }
}
