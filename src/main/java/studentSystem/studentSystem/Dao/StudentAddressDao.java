package studentSystem.studentSystem.Dao;

import org.springframework.data.repository.ListCrudRepository;
import studentSystem.studentSystem.model.StudentAddress;

public interface StudentAddressDao extends ListCrudRepository<StudentAddress, Long> {
}
