package az.growlab.LoanApp.repo;

import az.growlab.LoanApp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
