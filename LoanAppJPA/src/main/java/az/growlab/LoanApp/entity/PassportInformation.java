package az.growlab.LoanApp.entity;

import az.growlab.LoanApp.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "passport_info")
public class PassportInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String patronymic;
    private Date birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String passportNumber;

    @OneToOne(mappedBy = "passportInfo")
    private Client client;

}
