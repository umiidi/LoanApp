package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepo extends JpaRepository<PersonalInformation, Integer> {
}
