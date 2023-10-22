package az.growlab.problemsimulation.service;

import az.growlab.problemsimulation.entity.Author;
import az.growlab.problemsimulation.repo.AuthorRepo;
import az.growlab.problemsimulation.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;

    public List<Author> getAllAuthors() {
        return null;
    }

    public Author getAuthorById(Integer id) {
        return null;
    }

    public void addAuthor(Author author) {
        author.setBookList(bookRepo.saveAll(author.getBookList()));
        authorRepo.save(author);
    }
}
