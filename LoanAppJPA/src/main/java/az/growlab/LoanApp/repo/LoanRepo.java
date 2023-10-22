package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.entity.LoanInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<LoanInformation, Integer> {
}
