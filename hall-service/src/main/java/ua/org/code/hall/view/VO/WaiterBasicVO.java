package ua.org.code.hall.view.VO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WaiterBasicVO {

    private UUID id;
    private String name;
    private String surname;

    public WaiterBasicVO(UUID id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public WaiterBasicVO() {
    }
}
