package az.growlab.LoanApp.dto;

import az.growlab.LoanApp.enums.Gender;
import lombok.Data;

import java.sql.Date;

@Data
public class PassportInformationDto {

    private String name;
    private String surname;
    private String patronymic;
    private Date birthdate;
    private Gender gender;
    private String passportNumber;

}
