package studentSystem.studentSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentSystem.studentSystem.Dao.StudentAddressDao;
import studentSystem.studentSystem.Dao.StudentDao;
import studentSystem.studentSystem.Dao.SubjectDao;
import studentSystem.studentSystem.Dto.RegistrationBody;
import studentSystem.studentSystem.Dto.StudentAddressRegistrationBody;
import studentSystem.studentSystem.exception.StudentAlreadyExistsException;
import studentSystem.studentSystem.exception.StudentDoesNotExistException;
import studentSystem.studentSystem.exception.SubjectDoesNotExistException;
import studentSystem.studentSystem.model.Course;
import studentSystem.studentSystem.model.Student;
import studentSystem.studentSystem.model.StudentAddress;
import studentSystem.studentSystem.model.Subject;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentAddressDao studentAddressDao;


    public Student registerStudent(RegistrationBody registrationBody) throws StudentAlreadyExistsException {
        if (studentDao.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()) {
            throw new StudentAlreadyExistsException();
        }

        Student newStudent = new Student();

        StudentAddressRegistrationBody registrationAddress = registrationBody.getStudentAddresses().get(0);
        List<StudentAddress> studentAddresses = new ArrayList<>();
        StudentAddress newAddress = new StudentAddress();

        newAddress.setStreet(registrationAddress.getStreet());
        newAddress.setNumber(registrationAddress.getNumber());
        newAddress.setStudent(newStudent);

        studentAddresses.add(newAddress);
        newStudent.setStudentAddresses(studentAddresses);

        newStudent.setName(registrationBody.getName());
        newStudent.setSurname(registrationBody.getSurname());
        newStudent.setEmail(registrationBody.getEmail());

        studentDao.save(newStudent);
        studentAddressDao.save(newAddress);
        return  newStudent;
    }

    public List<Student> listStudents() {return studentDao.findAll();}


//
//
//        student.setCourses(course);
//
//
//        courses.add(subject);
//        student.setCourses(courses);
//        studentDao.save(student);

    }

