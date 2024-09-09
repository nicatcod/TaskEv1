package projecttask.taskev1.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projecttask.taskev1.dto.AuthorDTO;
import projecttask.taskev1.entity.Author;
import projecttask.taskev1.mapper.AuthorMapper;
import projecttask.taskev1.service.AuthorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@Validated
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.findAll().stream().map(authorMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Optional<Author> author = authorService.findById(id);
        return author.map(a -> ResponseEntity.ok(authorMapper.toDTO(a))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = authorMapper.toEntity(authorDTO);
        return new ResponseEntity<>(authorMapper.toDTO(authorService.save(author)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorDTO authorDTO) {
        if (authorService.findById(id).isPresent()) {
            Author author = authorMapper.toEntity(authorDTO);
            author.setId(id);
            return ResponseEntity.ok(authorMapper.toDTO(authorService.save(author)));
        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        if (authorService.findById(id).isPresent()) {
            authorService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
