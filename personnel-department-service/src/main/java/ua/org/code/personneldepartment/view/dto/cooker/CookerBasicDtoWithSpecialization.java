package ua.org.code.personneldepartment.view.dto.cooker;

import lombok.Getter;
import lombok.Setter;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookersSpecializationEntity;

import java.util.List;

@Getter
@Setter
public class CookerBasicDtoWithSpecialization extends CookerBasicDto {
    private List<CookersSpecializationEntity> specializations;

    public CookerBasicDtoWithSpecialization(CookerEntity entity) {
        super(entity);
        this.specializations = entity.getCookerSpecializations();
    }

    public CookerBasicDtoWithSpecialization() {
    }
}
