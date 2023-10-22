package az.growlab.problemsimulation.repo;

import az.growlab.problemsimulation.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
