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

    @Column(name = "waiter_id", nullable = false)
    private UUID waiterId;

    public TableEntity(UUID waiterId) {
        this.waiterId = waiterId;
    }

    public TableEntity() {
    }
}
