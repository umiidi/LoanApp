package az.growlab.LoanApp.service.inter;

import az.growlab.LoanApp.enums.ConfirmStatus;

public interface LeadService {

    void identityStatus(int clientId, ConfirmStatus status, String rejectReason);

    void initialStatus(int clientId, ConfirmStatus status, String rejectReason);

    void finalStatus(int clientId, ConfirmStatus status, String rejectReason);

}
