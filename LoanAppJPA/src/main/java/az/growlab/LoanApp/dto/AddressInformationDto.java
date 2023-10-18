package az.growlab.LoanApp.dto;

import lombok.Data;

@Data
public class AddressInformationDto {

    private String country;
    private String city;
    private String street;
    private String postalCode;

}
