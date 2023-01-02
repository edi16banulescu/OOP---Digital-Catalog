import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class User {
    private String firstName, lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
}

class Parent extends User implements Observer{
    Vector<String> notifications;
    public Parent(String firstName, String lastName) {

        super(firstName, lastName);
        notifications = new Vector<>();
    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification.getMessageContent());
    }
}

class Student extends User implements Comparable<Student>{
    private Parent mother, father;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }

    public void setFather(Parent father) {
        this.father = father;
    }
    public Parent getFather() { return father; }
    public Parent getMother() { return mother; }

    @Override
    public int compareTo(Student o) {
        return this.getLastName().compareTo(o.getLastName());
    }
}

class Assistant extends User implements Element {
    public Assistant(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Teacher extends User  implements Element {
    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
