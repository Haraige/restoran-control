package ua.org.code.personneldepartment.view.dto.waiter;

import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.view.dto.PersonnelBasicDto;

public class WaiterBasicDto extends PersonnelBasicDto {

    public WaiterBasicDto(WaiterEntity entity) {

        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setSurname(entity.getSurname());
        this.setUsername(entity.getUsername());

    }
}
