package studentSystem.studentSystem.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentSystem.studentSystem.Dto.RegistrationBody;
import studentSystem.studentSystem.Service.StudentService;
import studentSystem.studentSystem.exception.StudentAlreadyExistsException;
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

}
