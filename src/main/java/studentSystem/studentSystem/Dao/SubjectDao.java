package studentSystem.studentSystem.Dao;

import org.springframework.data.repository.ListCrudRepository;
import studentSystem.studentSystem.model.Subject;

public interface SubjectDao extends ListCrudRepository<Subject, Long> {
}
