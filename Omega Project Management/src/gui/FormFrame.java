package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class FormFrame extends JFrame {
    private JPanel sidebar, buttonPanel;
    private JButton projectButton, taskButton, procedureButton, detailsButton;
    private JButton clearButton, saveButton, deleteButton;
    
    private ProjectContainer projectC;
    private TaskContainer taskC;
    private ProcedureContainer procedureC;
    private DetailsContainer detailsC;

    public FormFrame(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(true);
        setLayout(new BorderLayout());

        sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(4, 1));

        projectButton = new JButton("Project");
        taskButton = new JButton("Task");
        procedureButton = new JButton("Procedure");
        detailsButton = new JButton("Procedure Details");

        sidebar.add(projectButton);
        sidebar.add(taskButton);
        sidebar.add(procedureButton);
        sidebar.add(detailsButton);

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

        add(sidebar, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(projectC, BorderLayout.CENTER);
        
        //--------------------------------------------
        projectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(projectC);
            }
        });

        taskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(taskC);
            }
        });

        procedureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(procedureC);
            }
        });

        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchPanel(detailsC);
            }
        });

        setVisible(true);
    }

    // Method to switch panels in the center
    private void switchPanel(JPanel panel) {
        getContentPane().removeAll();
        add(sidebar, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        repaint();
    }
        //--------------------------------------------

    public static void main(String[] args) {
        new FormFrame("Form Frame");
    }
}
