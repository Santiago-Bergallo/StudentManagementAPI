package studentSystem.studentSystem.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import studentSystem.studentSystem.Dao.StudentDao;
import studentSystem.studentSystem.Dto.RegistrationBody;
import studentSystem.studentSystem.Dto.StudentAddressRegistrationBody;
import studentSystem.studentSystem.exception.StudentAlreadyExistsException;
import studentSystem.studentSystem.model.Student;
import studentSystem.studentSystem.model.StudentAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentService studentService;

    @Test
    void registerStudent() throws StudentAlreadyExistsException {

        // setting the DTO
        RegistrationBody registrationBody = new RegistrationBody();
        StudentAddressRegistrationBody registrationAddress = new StudentAddressRegistrationBody();
        registrationAddress.setNumber("1234");
        registrationAddress.setStreet("Baker");
        registrationAddress.setRegistrationBody(registrationBody);

        registrationBody.setName("Peter");
        registrationBody.setSurname("Smith");
        registrationBody.setEmail("Peter@test.com");
        List<StudentAddressRegistrationBody> studentAddressRegistrationBodies = new ArrayList<>();
        studentAddressRegistrationBodies.add(registrationAddress);
        registrationBody.setStudentAddresses(studentAddressRegistrationBodies);

        //testing the service

        studentService.registerStudent(registrationBody);

        Optional<Student> studentTest = studentDao.findById(1L);

        assertNotNull(studentTest);




    }
}