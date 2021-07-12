package ua.org.code.personaldepartment.persistence.entity.personal.hall;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ua.org.code.personaldepartment.persistence.entity.personal.Personal;
import ua.org.code.personaldepartment.persistence.role.RoleType;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "waiters")
public class WaiterEntity implements Personal {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer salary;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private char[] password;

    @Column(name = "hired_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final RoleType role = RoleType.ROLE_WAITER;

    public WaiterEntity(String name,
                        String surname,
                        Date dateOfBirth,
                        String email,
                        String phoneNumber,
                        Integer salary,
                        String username,
                        char[] password,
                        Date hiredAt) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.hiredAt = hiredAt;
    }

    public WaiterEntity() {

    }
}