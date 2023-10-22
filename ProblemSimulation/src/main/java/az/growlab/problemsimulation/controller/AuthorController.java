package az.growlab.problemsimulation.controller;

import az.growlab.problemsimulation.entity.Author;
import az.growlab.problemsimulation.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AppService service;

    @PostMapping
    public void add(@RequestBody Author author) {

    }

    @GetMapping
    public List<Author> getAllAuthors() {
//        return service.getAllAuthors();
        return null;
    }

    @GetMapping
    public Author getAuthorById(@RequestParam Integer id) {
        return null;
//        return service.getAuthorById(id);
    }

}
