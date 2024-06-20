package gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class ProjectContainer extends JPanel{
	private JComboBox<String> showAll;
    private JLabel projectNameLabel;
    private JTextField projectName;
    
	public ProjectContainer() {
		setLayout(null);
		
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
        
        add(showAll);
        add(projectNameLabel);
        add(projectName);
	}

}
