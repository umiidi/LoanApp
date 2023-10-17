package az.growlab.LoanApp.model;

import lombok.Data;

@Data
public class ContactInformation {

    private int id;
    private String homeNumber;
    private String workNumber;
    private String mobile;
    private String email;

}
