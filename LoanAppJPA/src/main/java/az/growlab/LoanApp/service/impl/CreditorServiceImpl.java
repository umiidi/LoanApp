package az.growlab.LoanApp.service.impl;

import az.growlab.LoanApp.dto.LoanInformationDto;
import az.growlab.LoanApp.dto.PassportInformationDto;
import az.growlab.LoanApp.dto.PersonalInformationDto;
import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import az.growlab.LoanApp.exception.NotApprovedException;
import az.growlab.LoanApp.model.*;
import az.growlab.LoanApp.repo.*;
import az.growlab.LoanApp.service.inter.CreditorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditorServiceImpl implements CreditorService {
    private final ModelMapper modelMapper;
    private final AddressRepo addressRepo;
    private final ClientRepo clientRepo;
    private final ContactRepo contactRepo;
    private final LoanRepo loanRepo;
    private final PassportRepo passportRepo;
    private final PersonalRepo personalRepo;
    private final ProductRepo productRepo;


    @Override
    public void checkIdentity(PassportInformationDto pid) {
        Client c = new Client();
        c.setPassportInfo(passportRepo.save(modelMapper.map(pid, PassportInformation.class)));
        c.setFinalStatus(FinalStatus.IN_PROGRESS.name());
        c.setActionStatus(ActionStatus.WAITING_FOR_IDENTITY_APPROVE.name());
        clientRepo.save(c);
    }

    @Override
    public void initialApprove(int clientId, PersonalInformationDto pid) {
        Client c = clientRepo.getReferenceById(clientId);
        if (c.getActionStatus().equals(ActionStatus.IDENTITY_CHECK_APPROVED.name())) {
            PersonalInformation pi = modelMapper.map(pid, PersonalInformation.class);
            pi.setAddressInformation(addressRepo.save(modelMapper.map(pid.getAddressInformationDto(), AddressInformation.class)));
            pi.setContactInformation(contactRepo.save(modelMapper.map(pid.getContactInformationDto(), ContactInformation.class)));
            c.setPersonalInfo(personalRepo.save(pi));
            c.setActionStatus(ActionStatus.WAITING_FOR_INITIAL_APPROVE.name());
            clientRepo.save(c);
        } else {
            throw new NotApprovedException(c.getRejectReason());
        }
    }

    @Override
    public void finalApprove(int clientId, LoanInformationDto lid) {
        Client c = clientRepo.getReferenceById(clientId);
        if (c.getActionStatus().equals(ActionStatus.INITIAL_CHECK_APPROVED.name())) {
            LoanInformation loanInformation = loanRepo.save(modelMapper.map(lid, LoanInformation.class));
            loanInformation.getProducts().forEach(product -> {
                product.setLoanInformation(loanInformation);
                productRepo.save(product);
            });
            loanInformation.setTotalAmount(loanInformation.getTotalAmount());
            c.setLoanInfo(loanInformation);
            c.setActionStatus(ActionStatus.WAITING_FOR_FINAL_APPROVE.name());
            clientRepo.save(c);
        } else {
            throw new NotApprovedException(c.getRejectReason());
        }
    }
}