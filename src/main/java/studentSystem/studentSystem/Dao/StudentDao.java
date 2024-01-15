package studentSystem.studentSystem.Dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import studentSystem.studentSystem.model.Student;

import java.util.Optional;

@Repository
public interface StudentDao extends ListCrudRepository<Student, Long> {
    Optional<Student> findByEmailIgnoreCase(String email);


}
