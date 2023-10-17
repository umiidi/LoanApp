package az.growlab.LoanApp.service.impl;

import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.ConfirmStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import az.growlab.LoanApp.exception.ReasonNotFoundException;
import az.growlab.LoanApp.repo.ClientRepo;
import az.growlab.LoanApp.service.inter.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {
    private final ClientRepo clientRepo;

    @Override
    public void identityStatus(int clientId, ConfirmStatus status, String rejectReason) {
        if (ConfirmStatus.APPROVE == status) {
            clientRepo.setActionStatus(clientId, ActionStatus.IDENTITY_CHECK_APPROVED);
        } else {
            checkRejectReason(clientId, rejectReason);
        }
    }

    @Override
    public void initialStatus(int clientId, ConfirmStatus status, String rejectReason) {
        if (ConfirmStatus.APPROVE == status) {
            clientRepo.setActionStatus(clientId, ActionStatus.INITIAL_CHECK_APPROVED);
        } else {
            checkRejectReason(clientId, rejectReason);
        }
    }

    @Override
    public void finalStatus(int clientId, ConfirmStatus status, String rejectReason) {
        if (ConfirmStatus.APPROVE == status) {
            clientRepo.setActionStatus(clientId, ActionStatus.FINAL_CHECK_APPROVED);
            clientRepo.setFinalStatus(clientId, FinalStatus.COMPLETED);
        } else {
            checkRejectReason(clientId, rejectReason);
        }
    }

    private void checkRejectReason(int clientId, String rejectReason) {
        if (rejectReason == null || rejectReason.isEmpty() || rejectReason.isBlank()) {
            throw new ReasonNotFoundException("If the confirm status is NOT APPROVED, the reason for rejection must be written.");
        } else {
            clientRepo.setRejectReason(clientId, rejectReason);
        }
    }

}
