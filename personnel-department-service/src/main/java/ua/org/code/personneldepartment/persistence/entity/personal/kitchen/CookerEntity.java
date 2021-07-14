package ua.org.code.personneldepartment.persistence.entity.personal.kitchen;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ua.org.code.personneldepartment.persistence.entity.personal.Personnel;
import ua.org.code.personneldepartment.persistence.role.RoleType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cookers")
public class CookerEntity implements Personnel {
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

    @OneToMany(mappedBy = "cooker", cascade = CascadeType.ALL)
    private List<CookersSpecializationEntity> cookerSpecializations;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final RoleType role = RoleType.ROLE_COOKER;

    public CookerEntity(String name,
                        String surname,
                        Date dateOfBirth,
                        String email,
                        String phoneNumber,
                        Integer salary,
                        List<CookersSpecializationEntity> cookerSpecializations,
                        String username,
                        char[] password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.username = username;
        this.password = password;
        this.hiredAt = new Date();
        this.cookerSpecializations = cookerSpecializations;
    }

    public CookerEntity() {
    }
}