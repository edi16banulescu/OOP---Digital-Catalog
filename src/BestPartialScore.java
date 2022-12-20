import java.util.HashMap;
import java.util.Map;

public class BestPartialScore implements Strategy{

    @Override
    public Student getBestStudent(Course course) {
        Double best = 0.0;
        Student stud = null;
        HashMap<Student, Grade> myMap = course.gettAllStudentGrades();
        for (Map.Entry<Student,Grade> entry : myMap.entrySet()) {
            if(entry.getValue().getPartialScore() > best) {
                best = entry.getValue().getPartialScore();
                stud = entry.getValue().getStudent();
            }
        }

        return stud;
    }
}
