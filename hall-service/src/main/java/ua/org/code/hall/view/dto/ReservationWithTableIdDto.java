package ua.org.code.hall.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.org.code.hall.peristence.entity.ReservationEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationWithTableIdDto {

    private UUID id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Boolean customerAbsent;
    private Boolean customerFinished;
    private Integer tableId;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

    public ReservationWithTableIdDto(ReservationEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.phoneNumber = entity.getPhoneNumber();
        this.customerAbsent = entity.getCustomerAbsent();
        this.customerFinished = entity.getCustomerFinished();
        this.tableId = entity.getTableEntity().getId();
        this.dateFrom = entity.getDateTimeFrom();
        this.dateTo = entity.getDateTimeTo();
    }

    public ReservationWithTableIdDto(String name,
                                     String surname,
                                     String phoneNumber,
                                     Boolean customerAbsent,
                                     Boolean customerFinished,
                                     Integer tableId,
                                     LocalDateTime dateFrom,
                                     LocalDateTime dateTo) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.customerAbsent = customerAbsent;
        this.customerFinished = customerFinished;
        this.tableId = tableId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
