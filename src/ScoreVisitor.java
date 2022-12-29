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
                        HashMap<Student, Grade> myMap = Catalog.getInstance().courses.get(i).gettAllStudentGrades();

                        for(Student s : entry.getValue()) {
                            if(myMap.containsKey(s)) {
                                List<Tuple<Student, String, Double>> allStudents = partialScores.get(assistant);
                                Double exam = null;
                                for(Tuple t : allStudents) {
                                    if(t.getStudent().equals(s)) {
                                        exam = (Double) t.getGrade();
                                    }
                                }

                                Catalog.getInstance().courses.get(i).gettAllStudentGrades()
                                        .get(s).setPartialScore(exam);

                                Catalog.getInstance().notifyObservers(Catalog.getInstance().courses.
                                        get(i).getGrade(s));
                            } else {
                                Grade g = new Grade();

                                List<Tuple<Student, String, Double>> allStudents = partialScores.get(assistant);
                                Double exam = null;
                                for(Tuple t : allStudents) {
                                    if(t.getStudent().equals(s)) {
                                        exam = (Double) t.getGrade();
                                    }
                                }

                                g.setStudent(s);
                                g.setCourse(Catalog.getInstance().courses.get(i).getName());
                                g.setPartialScore(exam);
                                g.setExamScore(0.0);

                                Catalog.getInstance().courses.get(i).addGrade(g);


                                Catalog.getInstance().notifyObservers(Catalog.getInstance().courses.
                                        get(i).getGrade(s));
                            }
                        }
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

                List<Student> myList = Catalog.getInstance().courses.get(i).getAllStudents();
                HashMap<Student, Grade> myMap = Catalog.getInstance().courses.get(i).gettAllStudentGrades();

                for(Student s : myList) {
                    if(myMap.containsKey(s)) {
                        List<Tuple<Student, String, Double>> allStudents = examScores.get(teacher);
                        Double exam = null;
                        for(Tuple t : allStudents) {
                            if(t.getStudent().equals(s)) {
                                exam = (Double) t.getGrade();
                            }
                        }

                        Catalog.getInstance().courses.get(i).gettAllStudentGrades()
                                .get(s).setExamScore(exam);

                        Catalog.getInstance().notifyObservers(Catalog.getInstance().courses.
                                get(i).getGrade(s));
                    } else {
                        Grade g = new Grade();
                        List<Tuple<Student, String, Double>> allStudents = examScores.get(teacher);
                        Double exam = null;
                        for(Tuple t : allStudents) {
                            if(t.getStudent().equals(s)) {
                                exam = (Double) t.getGrade();
                            }
                        }

                        g.setStudent(s);
                        g.setCourse(Catalog.getInstance().courses.get(i).getName());
                        g.setExamScore(exam);
                        g.setPartialScore(0.0);

                        Catalog.getInstance().courses.get(i).addGrade(g);


                        Catalog.getInstance().notifyObservers(Catalog.getInstance().courses.
                                get(i).getGrade(s));
                    }
                }
            }
        }
    }

    public void addPartialScore(Assistant assistant, Student student, String courseName, Double grade) {
        Tuple tuple = new Tuple(student, courseName, grade);
        if(partialScores.containsKey(assistant)) {
            partialScores.get(assistant).add(tuple);
        } else {
            List<Tuple<Student, String, Double>> tupleList = new ArrayList<>();
            tupleList.add(tuple);
            partialScores.put(assistant, tupleList);
        }
    }

    public void addExamScore(Teacher teacher, Student student, String courseName, Double grade) {
        Tuple tuple = new Tuple(student, courseName, grade);
        if(examScores.containsKey(teacher)) {
            examScores.get(teacher).add(tuple);
        } else {
            List<Tuple<Student, String, Double>> tupleList = new ArrayList<>();
            tupleList.add(tuple);
            examScores.put(teacher, tupleList);
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
