package ua.org.code.personaldepartment.entity.personal.kitchen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.org.code.personaldepartment.entity.personal.PersonalEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cookers")
public class CookerEntity extends PersonalEntity {



}
