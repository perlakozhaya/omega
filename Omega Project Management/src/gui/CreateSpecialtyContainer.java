package gui;

import java.awt.*;
import javax.swing.*;

import backend.*;

public class CreateSpecialtyContainer extends JPanel {
	private JLabel specialtyNameLabel, specialtyLabel, emptyLabel;
	private JTextField specialtyNameField, salaryField;
	private JButton addButton;
	
	public CreateSpecialtyContainer() {
	    setLayout(new GridLayout(2, 3, 10, 0));
	    
	    specialtyNameLabel = new JLabel("Specialty Name");
	    specialtyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(specialtyNameLabel);
	    
	    specialtyLabel = new JLabel("Wage per hour");
	    specialtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(specialtyLabel);
	    
	    emptyLabel = new JLabel("              ");
	    emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(emptyLabel);
	    
	    specialtyNameField = new JTextField();
	    add(specialtyNameField);
	    
	    salaryField = new JTextField();
	    add(salaryField);
	    
	    addButton = new JButton("Add");
	    add(addButton);
	}
}