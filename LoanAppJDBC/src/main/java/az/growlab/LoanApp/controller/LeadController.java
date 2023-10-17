package az.growlab.LoanApp.controller;

import az.growlab.LoanApp.enums.ConfirmStatus;
import az.growlab.LoanApp.service.inter.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lead")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping("/identity-status/{id}")
    public void identityStatus(@PathVariable Integer id,
                               @RequestParam ConfirmStatus status,
                               @RequestParam(required = false) String rejectReason) {
        leadService.identityStatus(id, status, rejectReason);
    }

    @PostMapping("/initial-status/{id}")
    public void initialStatus(@PathVariable Integer id,
                              @RequestParam ConfirmStatus status,
                              @RequestParam(required = false) String rejectReason) {
        leadService.initialStatus(id, status, rejectReason);
    }

    @PostMapping("/final-status/{id}")
    public void finalStatus(@PathVariable Integer id,
                            @RequestParam ConfirmStatus status,
                            @RequestParam(required = false) String rejectReason) {
        leadService.finalStatus(id, status, rejectReason);
    }
}
