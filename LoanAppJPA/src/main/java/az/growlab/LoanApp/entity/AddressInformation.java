package az.growlab.LoanApp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address_info")
public class AddressInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;
    private String city;
    private String street;
    private String postalCode;

    @OneToOne(mappedBy = "addressInformation")
    private PersonalInformation personalInformation;

}
