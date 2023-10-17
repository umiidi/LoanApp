package az.growlab.LoanApp.model;

import lombok.Data;

@Data
public class AddressInformation {

    private int id;
    private String country;
    private String city;
    private String street;
    private String postalCode;

}
