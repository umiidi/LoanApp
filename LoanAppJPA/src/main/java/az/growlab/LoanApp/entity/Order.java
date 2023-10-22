package az.growlab.LoanApp.entity;

import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "loan_id")
    private LoanInformation loanInfo;

    @Enumerated(EnumType.STRING)
    private ActionStatus actionStatus;

    private String rejectReason;

    @Enumerated(EnumType.STRING)
    private FinalStatus finalStatus;

    @OneToOne(mappedBy = "order")
    private Client client;
}
