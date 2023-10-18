package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.PassportInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepo extends JpaRepository<PassportInformation, Integer> {
}
