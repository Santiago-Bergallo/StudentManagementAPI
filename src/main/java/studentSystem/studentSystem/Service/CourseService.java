package studentSystem.studentSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studentSystem.studentSystem.Dao.CourseDao;
import studentSystem.studentSystem.Dao.StudentDao;
import studentSystem.studentSystem.Dao.SubjectDao;
import studentSystem.studentSystem.Dto.CourseRegistration;
import studentSystem.studentSystem.exception.StudentDoesNotExistException;
import studentSystem.studentSystem.exception.SubjectDoesNotExistException;
import studentSystem.studentSystem.model.Course;
import studentSystem.studentSystem.model.Student;
import studentSystem.studentSystem.model.Subject;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    StudentDao studentDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    CourseDao courseDao;

    public void addCourse(CourseRegistration courseRegistration) throws SubjectDoesNotExistException, StudentDoesNotExistException {
        if (subjectDao.findByNameIgnoreCase(courseRegistration.getSubjectName()).isEmpty()) {
            throw new SubjectDoesNotExistException();
        }
        if (studentDao.findByEmailIgnoreCase(courseRegistration.getStudentEmail()).isEmpty()) {
            throw new StudentDoesNotExistException();
        }

        Student student = studentDao.findByEmailIgnoreCase(courseRegistration.getStudentEmail()).get();
        Subject subject = subjectDao.findByNameIgnoreCase(courseRegistration.getSubjectName()).get();

        Course course = new Course();
        course.setStudent(student);
        course.setSubject(subject);
        courseDao.save(course);
    }

    public List<Course> listCourses() {return courseDao.findAll();}



}
