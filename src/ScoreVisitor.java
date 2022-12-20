import java.util.*;

public class ScoreVisitor implements Visitor {
    Map<Teacher, List<Tuple<Student, String, Double>>> examScores;
    Map<Assistant, List<Tuple<Student, String, Double>>> partialScores;

    public ScoreVisitor() {
        examScores = new HashMap<>();
        partialScores = new HashMap<>();
    }
    @Override
    public void visit(Assistant assistant) {
        for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
            if(Catalog.getInstance().courses.get(i).isAssistant(assistant)) {
                String courseName = Catalog.getInstance().courses.get(i).getName();
                HashMap<String, Group> groups = Catalog.getInstance().courses.get(i).getGroups();

                for (Map.Entry<String, Group> entry : groups.entrySet()) {
                    if(entry.getValue().getAssistant().equals(assistant)) {
                        List<Tuple<Student, String, Double>> tupleList = new ArrayList<>();
                        Iterator it = entry.getValue().iterator();
                        while(it.hasNext()) {
                            Student stud = (Student) it.next();
                            Double grade = Catalog.getInstance().courses.
                                                            get(i).getGrade(stud).getPartialScore();
                            Tuple tuple = new Tuple(stud, courseName, grade);

                            tupleList.add(tuple);

                            Catalog.getInstance().notifyObservers(Catalog.getInstance().courses.
                                    get(i).getGrade(stud));
                        }
                        partialScores.put(assistant, tupleList);
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void visit(Teacher teacher) {
        for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
            if(Catalog.getInstance().courses.get(i).getTutor().equals(teacher)) {
                List<Tuple<Student, String, Double>> tupleList = new ArrayList<>();
                HashMap<Student, Grade> myMap =
                        Catalog.getInstance().courses.get(i).gettAllStudentGrades();
                String courseName = Catalog.getInstance().courses.get(i).getName();

                for (Map.Entry<Student,Grade> entry : myMap.entrySet()) {
                    Student stud = entry.getKey();
                    Double grade = entry.getValue().getExamScore();
                    Tuple tuple = new Tuple(stud, courseName, grade);

                    tupleList.add(tuple);

                    Catalog.getInstance().notifyObservers(Catalog.getInstance().courses.
                            get(i).getGrade(stud));
                }
                examScores.put(teacher, tupleList);
                break;
            }
        }
    }

    private class Tuple<K, V, S> {
        K student;
        V name;
        S grade;

        public K getStudent() { return student; }
        public void setStudent(K student) { this.student = student; }

        public V getName() { return name; }
        public void setName(V name) { this.name = name; }

        public S getGrade() { return grade; }
        public void setGrade(S grade) { this.grade = grade; }

        public Tuple(K student, V name, S grade) {
            this.student = student;
            this.name = name;
            this.grade = grade;
        }
    }
}
