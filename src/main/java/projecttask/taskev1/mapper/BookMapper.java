package projecttask.taskev1.mapper;
import org.springframework.stereotype.Component;
import projecttask.taskev1.dto.BookDTO;
import projecttask.taskev1.entity.Book;

@Component
public class BookMapper {

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setCount(book.getCount());
        dto.setAuthorId(book.getAuthor().getId());
        return dto;
    }

    public Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName());
        book.setCount(dto.getCount());
        return book;
    }
}
