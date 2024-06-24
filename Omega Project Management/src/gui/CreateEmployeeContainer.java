package gui;

import java.awt.*;
import javax.swing.*;

import backend.*;

public class CreateEmployeeContainer extends JPanel {
	private JLabel empNameLabel, specialtyLabel, emptyLabel;
	private JTextField empNameField;
	private JComboBox<Specialty> specialtiesBOX;
	private DefaultComboBoxModel<Specialty> specialtiesDCBM;
	private JButton addButton;
	
	public CreateEmployeeContainer() {
	    setLayout(new GridLayout(2, 3, 10, 0));
	    
	    empNameLabel = new JLabel("Name");
	    empNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(empNameLabel);
	    
	    specialtyLabel = new JLabel("Select Specialty");
	    specialtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(specialtyLabel);
	    
	    emptyLabel = new JLabel("              ");
	    emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(emptyLabel);
	    
	    empNameField = new JTextField();
	    add(empNameField);
	    
	    specialtiesDCBM = new DefaultComboBoxModel<>();
	    specialtiesBOX = new JComboBox<>(specialtiesDCBM);
	    add(specialtiesBOX);
	    
	    addButton = new JButton("Add");
	    add(addButton);
	}
}