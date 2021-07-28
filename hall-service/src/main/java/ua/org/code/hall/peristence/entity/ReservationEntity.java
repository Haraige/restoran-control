package ua.org.code.hall.peristence.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableEntity tableEntity;

    @Column(name = "customer_finished", nullable = false)
    private Boolean customerFinished;

    @Column(name = "customer_absent")
    private Boolean customerAbsent;

    @Column(name = "date_time_from", nullable = false)
    private LocalDateTime dateTimeFrom;

    @Column(name = "date_time_to", nullable = false)
    private LocalDateTime dateTimeTo;

    public ReservationEntity(UUID id,
                             String name,
                             String surname,
                             String phoneNumber,
                             TableEntity tableEntity,
                             LocalDateTime dateTimeFrom,
                             LocalDateTime dateTimeTo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.tableEntity = tableEntity;
        this.customerAbsent = false;
        this.customerFinished = false;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    public ReservationEntity(String name,
                             String surname,
                             String phoneNumber,
                             TableEntity tableEntity,
                             LocalDateTime dateTimeFrom,
                             LocalDateTime dateTimeTo) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.tableEntity = tableEntity;
        this.customerAbsent = false;
        this.customerFinished = false;
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    public ReservationEntity() {
    }
}
