package az.growlab.LoanApp.controller;

import az.growlab.LoanApp.dto.LoanInformationDto;
import az.growlab.LoanApp.dto.PassportInformationDto;
import az.growlab.LoanApp.dto.PersonalInformationDto;
import az.growlab.LoanApp.service.inter.CreditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/creditor")
@RequiredArgsConstructor
public class CreditorController {

    private final CreditorService creditorService;

    @PostMapping("/check-identity")
    public void checkIdentity(@RequestBody PassportInformationDto pi) {
        creditorService.checkIdentity(pi);
    }

    @PostMapping("/initial-approve/{id}")
    public void initialApprove(@PathVariable Integer id, @RequestBody PersonalInformationDto personalInformation) {
        creditorService.initialApprove(id, personalInformation);
    }

    @PostMapping("/final-approve/{id}")
    public void finalApprove(@PathVariable Integer id, @RequestBody LoanInformationDto loanInformation) {
        creditorService.finalApprove(id, loanInformation);
    }
}
