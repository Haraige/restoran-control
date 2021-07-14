package ua.org.code.personneldepartment.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.org.code.personneldepartment.persistence.role.RoleType;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private char[] nickname;

    @Column(nullable = false)
    private char[] password;

    @Transient
    private final RoleType role = RoleType.ROLE_ADMIN;

}
