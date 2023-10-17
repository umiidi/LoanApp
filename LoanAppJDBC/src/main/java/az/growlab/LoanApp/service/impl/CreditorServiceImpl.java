package az.growlab.LoanApp.service.impl;

import az.growlab.LoanApp.dto.LoanInformationDto;
import az.growlab.LoanApp.dto.PassportInformationDto;
import az.growlab.LoanApp.dto.PersonalInformationDto;
import az.growlab.LoanApp.enums.ActionStatus;
import az.growlab.LoanApp.enums.FinalStatus;
import az.growlab.LoanApp.exception.NotApprovedException;
import az.growlab.LoanApp.model.LoanInformation;
import az.growlab.LoanApp.model.PassportInformation;
import az.growlab.LoanApp.model.PersonalInformation;
import az.growlab.LoanApp.model.Product;
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
    private final ClientRepo clientRepo;
    private final PassportRepo passportRepo;
    private final PersonalRepo personalRepo;
    private final LoanRepo loanRepo;
    private final ProductRepo productRepo;

    @Override
    public void checkIdentity(PassportInformationDto pid) {
        PassportInformation pi = modelMapper.map(pid, PassportInformation.class);
        int clientId = clientRepo.addClient();
        int passportId = passportRepo.save(pi);
        clientRepo.setPassportInfo(clientId, passportId);
        clientRepo.setFinalStatus(clientId, FinalStatus.IN_PROGRESS);
        clientRepo.setActionStatus(clientId, ActionStatus.WAITING_FOR_IDENTITY_APPROVE);
    }

    @Override
    public void initialApprove(int clientId, PersonalInformationDto pid) {
        if (clientRepo.getActionStatus(clientId) == ActionStatus.IDENTITY_CHECK_APPROVED) {
            PersonalInformation pi = modelMapper.map(pid, PersonalInformation.class);
            int personalId = personalRepo.save(pi);
            clientRepo.setPersonalInfo(clientId, personalId);
            clientRepo.setActionStatus(clientId, ActionStatus.WAITING_FOR_INITIAL_APPROVE);
        } else {
            throw new NotApprovedException(clientRepo.getRejectReason(clientId));
        }
    }

    @Override
    public void finalApprove(int clientId, LoanInformationDto lid) {
        if (clientRepo.getActionStatus(clientId) == ActionStatus.INITIAL_CHECK_APPROVED) {
            LoanInformation li = modelMapper.map(lid, LoanInformation.class);
            List<Product> listProduct = li.getProducts().stream().map(product -> modelMapper.map(product, Product.class)).toList();
            li.setProducts(listProduct);

            int loanId = loanRepo.save(li);

            listProduct.forEach(product -> product.setLoan_info_id(loanId));
            li.getProducts().forEach(productRepo::save);

            clientRepo.setLoanInfo(clientId, loanId);
            clientRepo.setActionStatus(clientId, ActionStatus.WAITING_FOR_FINAL_APPROVE);
        } else {
            throw new NotApprovedException(clientRepo.getRejectReason(clientId));
        }
    }

}