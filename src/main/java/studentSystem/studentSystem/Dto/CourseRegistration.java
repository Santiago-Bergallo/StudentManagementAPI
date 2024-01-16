package studentSystem.studentSystem.Dto;


import lombok.Getter;
import lombok.Setter;
import studentSystem.studentSystem.model.Student;
import studentSystem.studentSystem.model.Subject;

import java.util.List;

public class CourseRegistration {

    @Getter @Setter
    private Integer grade;

    @Getter @Setter
    private String studentEmail;

    @Getter @Setter
    private String subjectName;


}
