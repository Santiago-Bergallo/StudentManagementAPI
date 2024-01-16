package studentSystem.studentSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studentSystem.studentSystem.Dto.SubjectRegistrationBody;
import studentSystem.studentSystem.Service.SubjectService;
import studentSystem.studentSystem.exception.SubjectAlreadyExistsException;
import studentSystem.studentSystem.model.Subject;

import java.util.List;

@RequestMapping("subject")
@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @PostMapping("/create")
    public ResponseEntity createSubject(@RequestBody SubjectRegistrationBody subjectRegistrationBody) {
        try {
            subjectService.registerSubject(subjectRegistrationBody);
            return ResponseEntity.ok().build();
        } catch (SubjectAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/list")
    public List<Subject> listSubjects() {
        return subjectService.listSubjects();
    }


}
