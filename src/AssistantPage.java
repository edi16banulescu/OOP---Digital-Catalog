import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AssistantPage extends JFrame implements ListSelectionListener, ActionListener {
    JButton button = null;
    JList myList = null;
    DefaultListModel<Course> courses = null;
    Assistant assistant = null;
    Visitor visitor = null;
    JFrame second = null;
    JList list = null;

    public AssistantPage(Assistant assistant, Visitor visitor) {
        super("Assistant Page");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.assistant = assistant;
        this.visitor = visitor;

        courses = new DefaultListModel<>();

        /* Create a list which contains all courses taught by our assistant */
        for(int i = 0; i < Catalog.getInstance().courses.size(); i ++) {
            if(Catalog.getInstance().courses.get(i).isAssistant(assistant)) {
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

        second = new JFrame();
        button = new JButton("Validate");
        button.addActionListener(this);
        second.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        List partialScorees = ((ScoreVisitor) visitor).partialScores.get(assistant);
        DefaultListModel listModel = new DefaultListModel();

        for(int i = 0; i < partialScorees.size(); i ++ ) {
            listModel.addElement(partialScorees.get(i));
        }

        list = new JList<>(listModel);

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

        /* Pressing "Validate" will validate the notes of the assistant in the catalog */
        if(e.getSource() instanceof JButton) {
            assistant.accept(visitor);
            repaint();
        }
    }
}
