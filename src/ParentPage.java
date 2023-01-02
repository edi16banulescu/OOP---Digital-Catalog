import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParentPage extends JFrame implements ActionListener {
    JButton button = null;
    JList jlist = null;
    Parent parent = null;

    public ParentPage(Parent parent) {
        super("Parent Page");
        this.parent = parent;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        button = new JButton("Mark all as read");

        jlist = new JList<>(parent.notifications);
        button.addActionListener(this);

        setLayout(new BorderLayout());

        jlist.setFixedCellHeight(100);
        jlist.setFixedCellHeight(100);
        add(jlist, BorderLayout.CENTER);
        add(button, BorderLayout.PAGE_END);
        setSize(1000,600);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            parent.notifications.clear();
            repaint();
        }
    }
}
