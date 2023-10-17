package az.growlab.LoanApp.service.inter;


import az.growlab.LoanApp.dto.LoanInformationDto;
import az.growlab.LoanApp.dto.PassportInformationDto;
import az.growlab.LoanApp.dto.PersonalInformationDto;

public interface CreditorService {

    void checkIdentity(PassportInformationDto pid);

    void initialApprove(int clientId, PersonalInformationDto pid);

    void finalApprove(int clientId, LoanInformationDto lid);
}
