package az.growlab.LoanApp.entity;

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
    @JoinColumn(name = "order_id")
    private Order order;

}
