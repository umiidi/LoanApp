package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<ContactInformation, Integer> {
}
