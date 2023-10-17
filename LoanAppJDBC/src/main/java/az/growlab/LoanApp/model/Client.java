package az.growlab.LoanApp.model;

import lombok.Data;

@Data
public class Client {

    private int id;
    private int passportInfoId;
    private int personalInfoId;
    private int loanInfoId;
    private int actionStatus;
    private int rejectReason;
    private int finalStatusId;

}
