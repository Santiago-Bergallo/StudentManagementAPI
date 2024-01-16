package studentSystem.studentSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentSystem.studentSystem.Dao.SubjectDao;
import studentSystem.studentSystem.Dto.SubjectRegistrationBody;
import studentSystem.studentSystem.exception.SubjectAlreadyExistsException;
import studentSystem.studentSystem.model.Subject;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    SubjectDao subjectDao;

    public Subject registerSubject(SubjectRegistrationBody subjectRegistrationBody) throws SubjectAlreadyExistsException {
        if (subjectDao.findByNameIgnoreCase(subjectRegistrationBody.getName()).isPresent()) {
            throw new SubjectAlreadyExistsException();
        }
        Subject newSubject = new Subject();
        newSubject.setName(subjectRegistrationBody.getName());
        subjectDao.save(newSubject);
        return  newSubject;
    }

    public List<Subject> listSubjects() {
        return subjectDao.findAll();
    }

}
