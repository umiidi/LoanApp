package az.growlab.LoanApp.service.impl;

import az.growlab.LoanApp.dto.LoanInformationDto;
import az.growlab.LoanApp.dto.PassportInformationDto;
import az.growlab.LoanApp.dto.PersonalInformationDto;
import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import az.growlab.LoanApp.exception.NotApprovedException;
import az.growlab.LoanApp.entity.*;
import az.growlab.LoanApp.repo.*;
import az.growlab.LoanApp.service.inter.CreditorService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    private final OrderRepo orderRepo;


    @Override
    public void checkIdentity(PassportInformationDto pid) {
        Client c = new Client();
        c.setPassportInfo(passportRepo.save(modelMapper.map(pid, PassportInformation.class)));
        c.setOrder(orderRepo.save(new Order()));
        c.getOrder().setActionStatus(ActionStatus.WAITING_FOR_IDENTITY_APPROVE);
        c.getOrder().setFinalStatus(FinalStatus.IN_PROGRESS);
        clientRepo.save(c);
    }

    @Override
    public void initialApprove(int clientId, PersonalInformationDto pid) {
        Client c = clientRepo.getReferenceById(clientId);
        if (c.getOrder().getActionStatus().equals(ActionStatus.IDENTITY_CHECK_APPROVED)) {
            PersonalInformation pi = modelMapper.map(pid, PersonalInformation.class);
            pi.setAddressInformation(addressRepo.save(modelMapper.map(pid.getAddressInformationDto(), AddressInformation.class)));
            pi.setContactInformation(contactRepo.save(modelMapper.map(pid.getContactInformationDto(), ContactInformation.class)));
            c.setPersonalInfo(personalRepo.save(pi));
            c.getOrder().setActionStatus(ActionStatus.WAITING_FOR_INITIAL_APPROVE);
            clientRepo.save(c);
        } else {
            throw new NotApprovedException(c.getOrder().getRejectReason());
        }
    }

    @Override
    public void finalApprove(int clientId, LoanInformationDto lid) {
        Client c = clientRepo.getReferenceById(clientId);
        if (c.getOrder().getActionStatus().equals(ActionStatus.INITIAL_CHECK_APPROVED)) {
            LoanInformation loanInformation = loanRepo.save(modelMapper.map(lid, LoanInformation.class));
            loanInformation.getProducts().forEach(product -> {
                product.setLoanInformation(loanInformation);
                productRepo.save(product);
            });
            loanInformation.setTotalAmount(loanInformation.getTotalAmount());
            c.getOrder().setLoanInfo(loanInformation);
            c.getOrder().setActionStatus(ActionStatus.WAITING_FOR_FINAL_APPROVE);
            clientRepo.save(c);
        } else {
            throw new NotApprovedException(c.getOrder().getRejectReason());
        }
    }
}