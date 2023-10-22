package az.growlab.LoanApp.service.impl;

import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.ConfirmStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import az.growlab.LoanApp.exception.ReasonNotFoundException;
import az.growlab.LoanApp.entity.Client;
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
        Client c = clientRepo.getReferenceById(clientId);
        if (ConfirmStatus.APPROVE == status) {
            c.getOrder().setActionStatus(ActionStatus.IDENTITY_CHECK_APPROVED);
            c.getOrder().setRejectReason(null);
            clientRepo.save(c);
        } else {
            checkRejectReason(clientId, rejectReason);
        }
    }

    @Override
    public void initialStatus(int clientId, ConfirmStatus status, String rejectReason) {
        Client c = clientRepo.getReferenceById(clientId);
        if (ConfirmStatus.APPROVE == status) {
            c.getOrder().setActionStatus(ActionStatus.INITIAL_CHECK_APPROVED);
            c.getOrder().setRejectReason(null);
            clientRepo.save(c);
        } else {
            checkRejectReason(clientId, rejectReason);
        }
    }

    @Override
    public void finalStatus(int clientId, ConfirmStatus status, String rejectReason) {
        Client c = clientRepo.getReferenceById(clientId);
        if (ConfirmStatus.APPROVE == status) {
            c.getOrder().setActionStatus(ActionStatus.FINAL_CHECK_APPROVED);
            c.getOrder().setRejectReason(null);
            c.getOrder().setFinalStatus(FinalStatus.COMPLETED);
            clientRepo.save(c);
        } else {
            checkRejectReason(clientId, rejectReason);
        }
    }

    private void checkRejectReason(int clientId, String rejectReason) {
        if (rejectReason == null || rejectReason.isEmpty() || rejectReason.isBlank()) {
            throw new ReasonNotFoundException("If the confirm status is NOT APPROVED, the reason for rejection must be written.");
        } else {
            Client c = clientRepo.getReferenceById(clientId);
            c.getOrder().setRejectReason(rejectReason);
            clientRepo.save(c);
        }
    }
}
