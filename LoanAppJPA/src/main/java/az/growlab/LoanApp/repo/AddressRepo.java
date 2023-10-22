package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.entity.AddressInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<AddressInformation, Integer> {
}
