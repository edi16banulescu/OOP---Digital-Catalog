import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class StudentPage extends JFrame implements ListSelectionListener {
    JList myList;
    DefaultListModel<Course> courses = null;
    DefaultListModel<Assistant> assistants = null;
    HashMap<String, Group> groups = null;
    Grade grade = null;
    Student student = null;
    public StudentPage(Student student) {
        super("Student Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.student = student;
        courses = new DefaultListModel<>();
        /* Create a list which contains all the courses where our stud is assigned */
        for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
            List<Student> listofStudents = Catalog.getInstance().courses.get(i).getAllStudents();
            for(Student s : listofStudents) {
                if(s.getFirstName().equals(student.getFirstName()) &&
                        s.getLastName().equals(student.getLastName())) {
                    courses.addElement(Catalog.getInstance().courses.get(i));
                }
            }
        }

        myList = new JList<>(courses);
        myList.addListSelectionListener(this);
        myList.setFixedCellHeight(100);
        myList.setFixedCellHeight(100);
        add(myList);
        setSize(1000,600);
        setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(myList.isSelectionEmpty())
            return;

        JFrame second = new JFrame();
        second.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        assistants = new DefaultListModel<>();
        Assistant aux = new Assistant("Assistants", ":");
        assistants.addElement(aux);
        groups = new HashMap<>();

        JList list = (JList) e.getSource();
        Course course = (Course)list.getSelectedValue();

        Assistant his = null;
        groups = course.getGroups();
        for(Group g : groups.values()) {
            assistants.addElement(g.getAssistant());

            for(Student s : g) {
                if(s.getFirstName().equals(student.getFirstName()) &&
                        s.getLastName().equals(student.getLastName())) {
                    his = g.getAssistant();
                    break;
                }
            }
        }

        grade = course.getGrade(student);
        JList jlist = new JList(assistants);
        JTextField text1 = null;
        JTextField text2 = null;
        JTextField text3 = null;
        JTextField text4 = null;

        if(grade == null) {
            text1 = new JTextField("Partial score: not assigned");
            text2 = new JTextField("Exam score: not assigned");
            text3 = new JTextField("Teacher: " + course.getTutor().getFirstName() + " " +
                    course.getTutor().getLastName());
            text4 = new JTextField("Assistant: " + his);
        } else {
            text1 = new JTextField("Partial score: " + grade.getPartialScore().toString());
            text2 = new JTextField("Exam score: " + grade.getExamScore().toString());
            text3 = new JTextField("Teacher: " + course.getTutor().getFirstName() + " " +
                    course.getTutor().getLastName());
            text4 = new JTextField("Assistant: " + his);
        }

        text1.setEditable(false);
        text2.setEditable(false);
        text3.setEditable(false);
        text4.setEditable(false);


        JPanel rb = new JPanel();
        rb.setLayout(new GridLayout());

        rb.add(text3);
        rb.add(jlist);
        rb.add(text4);
        rb.add(text1);
        rb.add(text2);

        second.add(rb);
        second.setVisible(true);
        second.setSize(900,200);
    }
}
