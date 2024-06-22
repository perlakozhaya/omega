package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FormFrame extends JFrame {
    private JPanel buttonPanel, centerPanel, comboPanel;
    private JButton clearButton, saveButton, deleteButton;
    private JComboBox<String> comboBox1, comboBox2, comboBox3;
    
    private ProjectContainer projectC;
    private TaskContainer taskC;
    private ProcedureContainer procedureC;
    private DetailsContainer detailsC;
    private CardLayout cardLayout;

    public FormFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(10, 200, 360, 420);
        setResizable(true);
        setLayout(new BorderLayout());

        projectC = new ProjectContainer();
        taskC = new TaskContainer();
        procedureC = new ProcedureContainer();
        detailsC = new DetailsContainer();

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adds padding around buttons
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        clearButton = new JButton("Clear");
        saveButton = new JButton("Save");
        deleteButton = new JButton("Delete");

        buttonPanel.add(Box.createHorizontalGlue()); // Pushes buttons to the center
        buttonPanel.add(clearButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between buttons
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Space between buttons
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue()); // Pushes buttons to the center

        cardLayout = new CardLayout();
        centerPanel = new JPanel(cardLayout);
        centerPanel.add(projectC, "Project");
        centerPanel.add(taskC, "Task");
        centerPanel.add(procedureC, "Procedure");
        centerPanel.add(detailsC, "Details");

        comboPanel = new JPanel();
        comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));
        comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        comboPanel.setBackground(Color.LIGHT_GRAY);

        comboBox1 = new JComboBox<>(new String[] {"", "Option 1", "Option 2", "Option 3"});
        comboBox2 = new JComboBox<>(new String[] {"", "Option 4", "Option 5", "Option 6"});
        comboBox3 = new JComboBox<>(new String[] {"", "Option 7", "Option 8", "Option 9"});

        comboPanel.add(comboBox1);
        comboPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        comboPanel.add(comboBox2);
        comboPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        comboPanel.add(comboBox3);
        
        add(buttonPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);
        add(comboPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FormFrame("Form Frame");
    }
}
