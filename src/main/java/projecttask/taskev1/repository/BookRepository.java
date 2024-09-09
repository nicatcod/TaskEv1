package projecttask.taskev1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projecttask.taskev1.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
