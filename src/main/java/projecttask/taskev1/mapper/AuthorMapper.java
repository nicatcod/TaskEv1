package projecttask.taskev1.mapper;
import org.springframework.stereotype.Component;
import projecttask.taskev1.dto.AuthorDTO;
import projecttask.taskev1.entity.Author;
@Component
public class AuthorMapper {

    public AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setSurname(author.getSurname());
        return dto;
    }

    public static Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        return author;
    }
}
