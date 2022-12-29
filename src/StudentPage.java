import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPage extends JFrame implements ListSelectionListener {

    public StudentPage(Student student) {
        super("Student Page");

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
