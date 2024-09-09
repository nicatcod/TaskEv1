package projecttask.taskev1.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import projecttask.taskev1.dto.BookDTO;
import projecttask.taskev1.entity.Book;
import projecttask.taskev1.mapper.BookMapper;
import projecttask.taskev1.service.BookService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.findAll().stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.findById(id);
        return book.map(b -> ResponseEntity.ok(bookMapper.toDTO(b))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        if (bookDTO.getAuthorId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Book book = bookMapper.toEntity(bookDTO);

        if (bookService.getAuthorById(bookDTO.getAuthorId()) == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return new ResponseEntity<>(bookMapper.toDTO(bookService.save(book)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        if (bookService.findById(id).isPresent()) {
            Book book = bookMapper.toEntity(bookDTO);
            book.setId(id);
            return ResponseEntity.ok(bookMapper.toDTO(bookService.save(book)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.findById(id).isPresent()) {
            bookService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
