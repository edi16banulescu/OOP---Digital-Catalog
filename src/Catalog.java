import java.util.ArrayList;
import java.util.List;

public class Catalog implements Subject{

    private List<Parent> observers = new ArrayList<>();
    private static Catalog instance = null;
    public List<Course> courses = new ArrayList<Course>();
    private Catalog() {}
    public static Catalog getInstance() {
        if(instance == null)
            instance = new Catalog();

        return instance;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add((Parent)observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((Parent)observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        for(Parent o : observers) {
            if(grade.getStudent().getMother().getFirstName().equals(o.getFirstName()) &&
                    grade.getStudent().getMother().getLastName().equals(o.getLastName())) {
                o.update(new Notification(grade));
            }
        }
    }
}

