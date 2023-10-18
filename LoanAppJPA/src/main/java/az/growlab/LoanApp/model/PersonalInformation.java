package az.growlab.LoanApp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personal_info")
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name="contact_id")
    private ContactInformation contactInformation;

    @OneToOne
    @JoinColumn(name="address_id")
    private AddressInformation addressInformation;

    @OneToOne(mappedBy = "personalInfo")
    private Client client;

}
