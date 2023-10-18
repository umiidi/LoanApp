package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.AddressInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<AddressInformation, Integer> {
}
