package ua.org.code.hall.peristence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tables")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_in_hall", nullable = false)
    private Integer idInHall;

    @Column(name = "waiter_id")
    private UUID waiterId;

    @Column(name = "customers_capacity", nullable = false)
    private Integer customersCapacity;

    public TableEntity(Integer id, Integer idInHall, UUID waiterId, Integer customersCapacity) {
        this.id = id;
        this.idInHall = idInHall;
        this.waiterId = waiterId;
        this.customersCapacity = customersCapacity;
    }

    public TableEntity(Integer idInHall, Integer customersCapacity) {
        this.idInHall = idInHall;
        this.customersCapacity = customersCapacity;
    }

    public TableEntity() {
    }
}
