package az.growlab.LoanApp.model;

import lombok.Data;

@Data
public class AddressInformation {

    private int id;
    private String Country;
    private String City;
    private String street;
    private String postalCode;

}
