package az.growlab.LoanApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact_info")
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String homeNumber;
    private String workNumber;
    private String mobile;
    private String email;

    @OneToOne(mappedBy = "contactInformation")
    private PersonalInformation personalInformation;

}
