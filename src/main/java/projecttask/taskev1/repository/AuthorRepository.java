package projecttask.taskev1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projecttask.taskev1.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
