package gui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class FormFrame extends JFrame {
	private JPanel sidebar, main;
	private JButton projectButton, taskButton, procedureButton, detailsButton;
	private JComboBox<String> showAll; // String mwa2at ta yemche chi bas ha ysir <Project>
	private JLabel projectNameLabel;
	private JTextField projectName;
	private JButton clearButton, saveButton, deleteButton;

	public FormFrame(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);
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

        main = new JPanel();
        main.setLayout(null);

        showAll = new JComboBox<>();
        showAll.addItem(" ");
        showAll.addItem("Project 1");
        showAll.addItem("Project 2");
        showAll.addItem("Project 3");
        showAll.setBounds(130, 30, 150, 30);

        projectNameLabel = new JLabel("Project Name/Code");
        projectNameLabel.setBounds(130, 80, 150, 30);

        projectName = new JTextField("placeholder...");
        projectName.setBounds(130, 110, 150, 30);

        clearButton = new JButton("Clear");
        clearButton.setBounds(60, 400, 80, 30);

        saveButton = new JButton("Save");
        saveButton.setBounds(160, 400, 80, 30);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(260, 400, 80, 30);

        main.add(showAll);
        main.add(projectNameLabel);
        main.add(projectName);
        main.add(clearButton);
        main.add(saveButton);
        main.add(deleteButton);

        add(sidebar, BorderLayout.WEST);
        add(main, BorderLayout.CENTER);
        
        setVisible(true);
	}
}