package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Integer> {

}
