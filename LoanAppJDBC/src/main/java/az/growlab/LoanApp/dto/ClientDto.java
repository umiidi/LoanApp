package az.growlab.LoanApp.dto;

import lombok.Data;

@Data
public class ClientDto {

    private int passportInfoId;
    private int personalInfoId;
    private int loanInfoId;
    private int actionStatus;
    private int rejectReason;
    private int finalStatusId;

}
