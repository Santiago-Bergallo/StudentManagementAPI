package studentSystem.studentSystem.Dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import studentSystem.studentSystem.model.Course;

import java.util.Optional;

@Repository
public interface CourseDao extends ListCrudRepository<Course, Long> {
    Optional<Course> findByStudent_EmailIgnoreCase(String email);

    Optional<Course> findBySubject_NameIgnoreCase(String name);




}
