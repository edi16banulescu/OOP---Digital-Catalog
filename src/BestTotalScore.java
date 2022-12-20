import java.util.HashMap;
import java.util.Map;

public class BestTotalScore implements Strategy {
    @Override
    public Student getBestStudent(Course course) {
        Double best = 0.0;
        Student stud = null;
        HashMap<Student, Grade> myMap = course.gettAllStudentGrades();
        for (Map.Entry<Student,Grade> entry : myMap.entrySet()) {
            if(entry.getValue().getTotal() > best) {
                best = entry.getValue().getTotal();
                stud = entry.getValue().getStudent();
            }
        }

        return stud;
    }
}
