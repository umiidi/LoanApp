package az.growlab.LoanApp.model;

import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "passport_id")
    private PassportInformation passportInfo;

    @OneToOne
    @JoinColumn(name = "personal_id")
    private PersonalInformation personalInfo;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private LoanInformation loanInfo;

    private String actionStatus;
    private String rejectReason;
    private String finalStatus;

}