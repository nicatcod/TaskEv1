package projecttask.taskev1.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Min(0)
    private int count;

    private Long authorId;

}
