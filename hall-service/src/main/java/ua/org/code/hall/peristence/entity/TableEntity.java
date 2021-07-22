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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "waiter_id")
    private UUID waiterId;

    @Column(nullable = false)
    private boolean free;

    @Column(name = "customers_capacity", nullable = false)
    private Integer customersCapacity;

    public TableEntity(Integer id, UUID waiterId) {
        this.id = id;
        this.waiterId = waiterId;
        this.free = true;
    }

    public TableEntity(UUID waiterId) {
        this.waiterId = waiterId;
        this.free = true;
    }

    public TableEntity() {
    }
}
