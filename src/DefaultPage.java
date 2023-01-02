import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DefaultPage extends JFrame implements ActionListener {

    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JTextField t1 = new JTextField(15);
    JTextField t2 = new JTextField(15);
    Visitor visitor = null;
    public DefaultPage(Visitor visitor) {
        super("Default");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.visitor = visitor;
        button1 = new JButton("Student");
        button2 = new JButton("Teacher");
        button3 = new JButton("Assistant");
        button4 = new JButton("Parent");
        JLabel label = new JLabel("Who are you ?");

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        JPanel rb = new JPanel();
        rb.add(button1);
        rb.add(button2);
        rb.add(button3);
        rb.add(button4);

        setLayout(new FlowLayout());
        setSize(500,150);
        add(label);
        add(rb);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Go Student")) {
            String firstName = t1.getText();
            String lastName = t2.getText();
            boolean ok = false;
            for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
                ArrayList<Student> listofStudents = Catalog.getInstance().courses.get(i).getAllStudents();
                for(Student s : listofStudents) {
                    if(s.getFirstName().equals(firstName) && s.getLastName().equals(lastName)) {
                        StudentPage studentPage = new StudentPage(s);
                        ok = true;
                        break;
                    }
                }
                if(ok)
                    break;
            }

            if(!ok)
                JOptionPane.showMessageDialog(new JFrame(), "Not found.");
        }

        if(e.getActionCommand().equals("Go Teacher")) {
            String firstName = t1.getText();
            String lastName = t2.getText();
            boolean ok = false;
            for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
                Teacher teacher = Catalog.getInstance().courses.get(i).getTutor();
                if(teacher.getFirstName().equals(firstName) && teacher.getLastName().equals(lastName)) {
                    TeacherPage teacherPage = new TeacherPage(teacher, visitor);
                    ok = true;
                    break;
                }
            }

            if(!ok)
                JOptionPane.showMessageDialog(new JFrame(), "Not found.");
        }

        if(e.getActionCommand().equals("Go Assistant")) {
            String firstName = t1.getText();
            String lastName = t2.getText();
            boolean ok = false;
            for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
                HashMap<String, Group> groups = Catalog.getInstance().courses.get(i).getGroups();
                for (Map.Entry<String, Group> entry : groups.entrySet()) {
                    Assistant assistant = entry.getValue().getAssistant();
                    if(assistant.getFirstName().equals(firstName) && assistant.getLastName().equals(lastName)) {
                        ok = true;
                        AssistantPage assistantPage = new AssistantPage(assistant, visitor);
                        break;
                    }
                }
                if(ok)
                    break;
            }

            if(!ok)
                JOptionPane.showMessageDialog(new JFrame(), "Not found.");
        }

        if(e.getActionCommand().equals("Go Parent")) {
            String firstName = t1.getText();
            String lastName = t2.getText();
            boolean ok = false;
            for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
                ArrayList<Student> listofStudents = Catalog.getInstance().courses.get(i).getAllStudents();
                for(Student s : listofStudents) {
                    Parent parent = s.getMother();
                    if(parent.getFirstName().equals(firstName) && parent.getLastName().equals(lastName)) {
                        ParentPage parentPage = new ParentPage(parent);
                        ok = true;
                        break;
                    }
                }

                if(ok)
                    break;
            }

            if(!ok)
                JOptionPane.showMessageDialog(new JFrame(), "Not found.");
        }

        if(e.getSource() instanceof JButton && !e.getActionCommand().equals("Go Student")
                && !e.getActionCommand().equals("Go Teacher") && !e.getActionCommand().equals("Go Assistant")
                && !e.getActionCommand().equals("Go Parent")) {
            JFrame frame1 = new JFrame();
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setVisible(true);
            frame1.setLayout(new BorderLayout());
            JLabel label1 = new JLabel("First Name");
            JLabel label2 = new JLabel("Last Name");
            t1.addActionListener(this);
            t2.addActionListener(this);

            JPanel rb = new JPanel();
            rb.add(label1);
            rb.add(t1);
            rb.add(label2);
            rb.add(t2);

            frame1.add(rb, BorderLayout.CENTER);

            frame1.setSize(300, 300);

            if(e.getActionCommand().equals("Student")) {
                JButton button = new JButton("Go Student");
                button.addActionListener(this);
                frame1.add(button, BorderLayout.PAGE_END);
            }

            if(e.getActionCommand().equals("Assistant")) {
                JButton button = new JButton("Go Assistant");
                button.addActionListener(this);
                frame1.add(button, BorderLayout.PAGE_END);
            }

            if(e.getActionCommand().equals("Teacher")) {
                JButton button = new JButton("Go Teacher");
                button.addActionListener(this);
                frame1.add(button, BorderLayout.PAGE_END);
            }

            if(e.getActionCommand().equals("Parent")) {
                JButton button = new JButton("Go Parent");
                button.addActionListener(this);
                frame1.add(button, BorderLayout.PAGE_END);
            }
        }
    }
}
