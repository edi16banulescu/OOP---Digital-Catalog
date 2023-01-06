import java.util.*;

public abstract class Course {

    private String name;
    private Teacher tutor;
    private HashSet<Assistant> assistants = new HashSet<Assistant>();
    private TreeSet<Grade> grades = new TreeSet<Grade>();
    private HashMap<String, Group> groups = new HashMap<String, Group>();
    private int credits;
    private Snapshot snapshot = new Snapshot();

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Teacher getTutor() { return tutor; }
    public void setTutor(Teacher tutor) { this.tutor = tutor; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public boolean isAssistant(Assistant assistant) {
        for(Assistant a : assistants) {
            if(a.getFirstName().equals(assistant.getFirstName()) &&
                    a.getLastName().equals(assistant.getLastName())) {
                return true;
            }
        }

        return false;
    }
    public HashMap<String, Group> getGroups() { return groups; }


    public void addAssistant(String ID, Assistant assistant) {
        if(groups.containsKey(ID)) {
            groups.get(ID).setAssistant(assistant);
            if(!assistants.contains(assistant)) {
                assistants.add(assistant);
            }
        }
    }

    public void addStudent(String ID, Student student) {
        groups.get(ID).add(student);
    }

    public void addGroup(Group group) {

        groups.put(group.getID(), group);
        assistants.add(group.getAssistant());

    }

    public void addGroup(String ID, Assistant assistant) {
        Group aux = new Group(ID, assistant);
        assistants.add(assistant);
        groups.put(aux.getID(), aux);
    }

    public void addGroup(String ID, Assistant assist, Comparator<Student> comp) {
        Group aux = new Group(ID, assist, comp);
        assistants.add(assist);
        groups.put(aux.getID(), aux);
    }

    public Grade getGrade(Student student) {
        for(Grade grade : grades) {
            if(grade.getStudent().getFirstName().equals(student.getFirstName()) &&
                    grade.getStudent().getLastName().equals(student.getLastName())) {
                return grade;
            }
        }

        return null;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> myList = new ArrayList<Student>();

        for(Group g : groups.values()) {
            for(Student s : g) {
                myList.add(s);
            }
        }

        return myList;
    }

    public HashMap<Student, Grade> gettAllStudentGrades() {
        HashMap<Student, Grade> myMap = new HashMap<Student, Grade>();

        for(Grade grade : grades) {
            myMap.put(grade.getStudent(), grade);
        }

        return myMap;
    }

    public abstract ArrayList<Student> getGraduatedStudents();

    public abstract static class CourseBuilder {
        public abstract CourseBuilder name(String name);
        public abstract CourseBuilder tutor(Teacher tutor);
        public abstract CourseBuilder credit(int credit);
    }

    private class Snapshot {
        private HashMap<Student, Grade> backup = null;
        private HashMap<Student, Grade> copy = null;

        public Snapshot() {
            backup = new HashMap<Student, Grade>();
            copy = new HashMap<Student, Grade>();
        }

        public HashMap<Student, Grade> getBackup() {
            return backup;
        }

        public String toString() {
            String s = "My backup for course " + getName() + ":\n";

            for (Map.Entry<Student,Grade> entry : backup.entrySet()) {
                s += entry.getValue() + "\n";
            }

            return s;
        }
    }

    public void makeBackup() {
        snapshot.copy = snapshot.backup;
        snapshot.backup = gettAllStudentGrades();
    }

    public void undo() {
        snapshot.backup = snapshot.copy;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public String toString() { return getName(); }
}
