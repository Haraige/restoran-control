package ua.org.code.personaldepartment.persistence.entity.schedule;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;
import ua.org.code.personaldepartment.persistence.entity.personal.Personal;
import ua.org.code.personaldepartment.persistence.entity.personal.hall.WaiterEntity;
import ua.org.code.personaldepartment.persistence.entity.personal.kitchen.CookerEntity;

import javax.persistence.*;

@Entity
@Table(name = "schedules")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class ScheduleEntity {

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
    private Personal worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_of_week_id")
    private DayOfWeekEntity dayOfWeek;

    public ScheduleEntity() {
    }
}
