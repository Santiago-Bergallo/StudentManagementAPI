package studentSystem.studentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentSystem.studentSystem.Dto.CourseRegistration;
import studentSystem.studentSystem.Service.CourseService;
import studentSystem.studentSystem.Service.StudentService;
import studentSystem.studentSystem.exception.StudentDoesNotExistException;
import studentSystem.studentSystem.exception.SubjectDoesNotExistException;
import studentSystem.studentSystem.model.Course;
import studentSystem.studentSystem.model.Student;
import studentSystem.studentSystem.model.Subject;

import java.util.List;

@RestController
@RequestMapping("courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody CourseRegistration courseRegistration) throws SubjectDoesNotExistException {
        try {
            courseService.addCourse(courseRegistration);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        catch (StudentDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/list")
    public List<Course> listCourses() {return courseService.listCourses();}
}


