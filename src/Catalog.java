import java.util.ArrayList;
import java.util.List;

public class Catalog implements Subject{

    private List<Observer> observers = new ArrayList<>();
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
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        for(Observer o : observers) {
            if(grade.getStudent().getMother().equals(o)) {
                o.update(new Notification(grade));
            }
        }
    }
}

