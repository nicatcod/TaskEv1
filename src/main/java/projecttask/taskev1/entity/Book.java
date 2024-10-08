package projecttask.taskev1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int count;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


}
