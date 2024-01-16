package studentSystem.studentSystem.Dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import studentSystem.studentSystem.model.Subject;

import java.util.Optional;

@Repository
public interface SubjectDao extends ListCrudRepository<Subject, Long> {
    Optional<Subject> findByNameIgnoreCase(String name);
}
