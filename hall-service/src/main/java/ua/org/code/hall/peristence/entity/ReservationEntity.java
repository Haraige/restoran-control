package ua.org.code.hall.peristence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    @Column(name = "client_present", nullable = false)
    private Boolean clientPresent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time_from", nullable = false)
    private Date dateTimeFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time_to", nullable = false)
    private Date dateTimeTo;

    public ReservationEntity(String name,
                             String surname,
                             String phoneNumber,
                             TableEntity tableEntity,
                             Date dateTimeFrom) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.tableEntity = tableEntity;
        this.clientPresent = false;
        this.dateTimeFrom = dateTimeFrom;
    }

    public ReservationEntity() {
    }
}
