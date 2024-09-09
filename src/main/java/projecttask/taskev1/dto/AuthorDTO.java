package projecttask.taskev1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Getter
@Setter
public class AuthorDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    private String surname;

    private Set<BookDTO> books;

}
