import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PartialCourse extends Course{
    private String name;
    private Teacher tutor;
    private int credits;

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> myList = new ArrayList<Student>();
        HashMap<Student, Grade> aux = gettAllStudentGrades();

        for (Map.Entry<Student,Grade> entry : aux.entrySet()) {
            if(entry.getValue().getTotal() >= 5)
                myList.add(entry.getKey());
        }

        return myList;
    }

    public static class PartialCourseBuilder extends CourseBuilder {
        private String name;
        private Teacher tutor;
        private int credits;

        @Override
        public PartialCourseBuilder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public PartialCourseBuilder tutor(Teacher tutor) {
            this.tutor = tutor;
            return this;
        }

        @Override
        public PartialCourseBuilder credit(int credit) {
            this.credits = credits;
            return this;
        }

        public PartialCourse build() {
            return new PartialCourse(this);
        }
    }

    private PartialCourse(PartialCourseBuilder builder) {
        this.name = builder.name;
        this.tutor = builder.tutor;
        this.credits = builder.credits;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Teacher getTutor() { return tutor; }
    public void setTutor(Teacher tutor) { this.tutor = tutor; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
