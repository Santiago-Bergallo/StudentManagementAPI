package studentSystem.studentSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentSystem.studentSystem.Dao.StudentAddressDao;
import studentSystem.studentSystem.Dao.StudentDao;
import studentSystem.studentSystem.Dao.SubjectDao;
import studentSystem.studentSystem.Dto.LoginBody;
import studentSystem.studentSystem.Dto.RegistrationBody;
import studentSystem.studentSystem.Dto.StudentAddressRegistrationBody;
import studentSystem.studentSystem.exception.StudentAlreadyExistsException;
import studentSystem.studentSystem.exception.StudentDoesNotExistException;
import studentSystem.studentSystem.exception.SubjectDoesNotExistException;
import studentSystem.studentSystem.exception.WrongStudentOrPasswordEx;
import studentSystem.studentSystem.model.Course;
import studentSystem.studentSystem.model.Student;
import studentSystem.studentSystem.model.StudentAddress;
import studentSystem.studentSystem.model.Subject;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentAddressDao studentAddressDao;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JwtService jwtService;


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
        newStudent.setUsername(registrationBody.getUsername());
        newStudent.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        studentDao.save(newStudent);
        studentAddressDao.save(newAddress);
        return  newStudent;
    }

    public List<Student> listStudents() {return studentDao.findAll();}

    public String loginStudent(LoginBody loginBody) throws StudentDoesNotExistException, WrongStudentOrPasswordEx {
        Optional<Student> oPstudent = studentDao.findByUsernameIgnoreCase(loginBody.getUsername());
        if (studentDao.findByUsernameIgnoreCase(oPstudent.get().getUsername()).isEmpty()) {
            throw new StudentDoesNotExistException();
        }

//        if (!encryptionService.checkPassword(oPstudent.getPassword(), loginBody.getPassword())) {
//            throw new WrongStudentOrPasswordEx();
//        }
        Student student = oPstudent.get();
        if(encryptionService.checkPassword(loginBody.getPassword(), student.getPassword())) {

            String jwt = jwtService.CreateJWT(student);
            return jwt;
        }
        else return null;
    }


//
//
//        student.setCourses(course);
//
//
//        courses.add(subject);
//        student.setCourses(courses);
//        studentDao.save(student);

    }

