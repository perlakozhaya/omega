package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class CreateEmployeeContainer extends JPanel {
	private JLabel empNameLabel, specialtyLabel, emptyLabel;
	private JTextField empNameField;
	private JComboBox<String> specialtiesBOX;
	private DefaultComboBoxModel<String> specialtiesDCBM;
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
	
	public JButton getAddBTN() {
		return addButton;
	}
	
	public void populateSpecialties() {
		specialtiesDCBM.removeAllElements();
		specialtiesDCBM.addElement(null);
	    for (String key : Specialty.jobs.keySet()) {
	    	specialtiesDCBM.addElement(key);
        }
	}
	
	public Employee createEmployee() {
	    String empName;
	    String specialtyName;
	    Specialty specialty;

	    if (empNameField.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Name cannot be empty!\n", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }

	    if (specialtiesBOX.getSelectedItem() == null) {
	        JOptionPane.showMessageDialog(null, "Specialty cannot be empty!\n", "Error", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }

	    specialtyName = (String) specialtiesBOX.getSelectedItem();
	    specialty = new Specialty(specialtyName, Specialty.jobs.get(specialtyName));
	    empName = empNameField.getText();

	    return new Employee(empName, specialty);
	}

}