import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FullCourse extends Course{

    private String name;
    private Teacher tutor;
    private int credits;

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> myList = new ArrayList<Student>();
        HashMap<Student, Grade> aux = gettAllStudentGrades();

        for (Map.Entry<Student,Grade> entry : aux.entrySet()) {
            if(entry.getValue().getPartialScore() >= 3 && entry.getValue().getExamScore() >= 2)
                myList.add(entry.getKey());
        }

        return myList;
    }

    public static class FullCourseBuilder extends CourseBuilder {
        private String name;
        private Teacher tutor;
        private int credits;

        public FullCourseBuilder(String name, Teacher tutor, int credits) {
            this.name = name;
            this.tutor = tutor;
            this.credits = credits;
        }

        @Override
        public FullCourseBuilder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public FullCourseBuilder tutor(Teacher tutor) {
            this.tutor = tutor;
            return this;
        }

        @Override
        public FullCourseBuilder credit(int credit) {
            this.credits = credits;
            return this;
        }

        public FullCourse build() {
            return new FullCourse(this);
        }
    }

    private FullCourse(FullCourseBuilder builder) {
        this.name = builder.name;
        this.tutor = builder.tutor;
        this.credits = builder.credits;
    }
}
