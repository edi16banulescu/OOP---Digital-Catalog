import java.util.Comparator;
import java.util.TreeSet;

public class Group extends TreeSet<Student> {
    private Assistant assistant;
    private String ID;
    private Comparator<Student> comp = (Student s1, Student s2) -> ( s1.getLastName().compareTo(s2.getLastName()) );

    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        this.assistant = assistant;
        this.ID = ID;
        this.comp = comp;
    }

    public Group(String ID, Assistant assistant) {
        this.assistant = assistant;
        this.ID = ID;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
}
