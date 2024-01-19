package studentSystem.studentSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentSystem.studentSystem.Dto.LoginBody;
import studentSystem.studentSystem.Dto.LoginResponse;
import studentSystem.studentSystem.Dto.RegistrationBody;
import studentSystem.studentSystem.Service.StudentService;
import studentSystem.studentSystem.exception.StudentAlreadyExistsException;
import studentSystem.studentSystem.exception.StudentDoesNotExistException;
import studentSystem.studentSystem.exception.WrongStudentOrPasswordEx;
import studentSystem.studentSystem.model.Student;

import java.util.List;

@RestController
//@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/create")
    public ResponseEntity registerStudent(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            studentService.registerStudent(registrationBody);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (StudentAlreadyExistsException e) {
            return  ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/list")
    public List<Student> liststudents() {
        return studentService.listStudents();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginBody loginBody) {
        try {
            String jwt = studentService.loginStudent(loginBody);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            return ResponseEntity.ok(loginResponse);
        } catch (StudentDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (WrongStudentOrPasswordEx e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
