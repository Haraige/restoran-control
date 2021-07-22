package ua.org.code.hall.view.dto;

import lombok.Getter;
import lombok.Setter;
import ua.org.code.hall.peristence.entity.ReservationEntity;
import ua.org.code.hall.peristence.entity.TableEntity;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@Setter
public class WaiterProfileDTO {

    private String name;
    private String surname;
    private List<TableEntity> tables;
    private List<DayOfWeek> workingDays;
    private List<ReservationEntity> reservationsForCurrentDay;

}
