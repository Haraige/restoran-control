package ua.org.code.personneldepartment.persistence.entity.schedule;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
import ua.org.code.personneldepartment.persistence.entity.personal.Personnel;
import ua.org.code.personneldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personneldepartment.persistence.entity.personal.kitchen.CookerEntity;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "working_days")
@Getter
@Setter
public class WorkingDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Any(
            metaColumn = @Column(name = "workerType"))
    @AnyMetaDef(
            metaType = "string", idType = "uuid-char",
            metaValues = {
            @MetaValue(value = "waiter", targetEntity = WaiterEntity.class),
            @MetaValue(value = "cooker", targetEntity = CookerEntity.class),
    })
    @JoinColumn(name = "worker_id")
    private Personnel worker;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    public WorkingDayEntity(Personnel worker, DayOfWeek dayOfWeek) {
        this.worker = worker;
        this.dayOfWeek = dayOfWeek;
    }

    public WorkingDayEntity() {
    }
}
