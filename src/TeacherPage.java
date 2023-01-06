import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TeacherPage extends JFrame implements ListSelectionListener, ActionListener {
    JButton button = null;
    JList myList = null;
    DefaultListModel<Course> courses = null;
    Teacher teacher = null;
    Visitor visitor = null;

    public TeacherPage(Teacher teacher, Visitor visitor) {
        super("Teacher Page");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.teacher = teacher;
        this.visitor = visitor;

        courses = new DefaultListModel<>();

        /* Create a list which contains all courses taught by our teacher */
        for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
            if(Catalog.getInstance().courses.get(i).getTutor().getFirstName()
                    .equals(teacher.getFirstName()) && Catalog.getInstance().courses.get(i).
                    getTutor().getLastName().equals(teacher.getLastName()) ) {
                courses.addElement(Catalog.getInstance().courses.get(i));
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
        button = new JButton("Validate");
        button.addActionListener(this);
        second.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List examScores = ((ScoreVisitor) visitor).examScores.get(teacher);
        DefaultListModel listModel = new DefaultListModel();

        for(int i = 0; i < examScores.size(); i ++ ) {
            listModel.addElement(examScores.get(i));
        }

        JList list = new JList<>(listModel);

        JPanel rb = new JPanel();
        rb.setLayout(new BorderLayout());
        rb.add(list);

        second.add(rb, BorderLayout.CENTER);
        second.add(button, BorderLayout.PAGE_END);
        second.setVisible(true);
        second.setSize(900,200);
        myList.clearSelection();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            teacher.accept(visitor);
            repaint();
        }
    }
}
