package studentSystem.studentSystem.Dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import studentSystem.studentSystem.model.StudentAddress;

@Repository
public interface StudentAddressDao extends ListCrudRepository<StudentAddress, Long> {
}
