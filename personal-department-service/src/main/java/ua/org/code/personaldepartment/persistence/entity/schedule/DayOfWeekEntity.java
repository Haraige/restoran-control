package ua.org.code.personaldepartment.persistence.entity.schedule;

import javax.persistence.*;

@Entity
@Table(name = "day_of_week")
public class DayOfWeekEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    String name;

}
