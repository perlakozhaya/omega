package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class CreateSpecialtyContainer extends JPanel {
	private JLabel specialtyNameLabel, specialtyLabel, emptyLabel;
	private JTextField specialtyNameField, salaryField;
	private JButton addButton;
	
	private FormFrame formFrame;
	private DataManager dataManager;
	private EmployeeContainer empC;
	
	public CreateSpecialtyContainer(FormFrame formFrame, DataManager dataManager, EmployeeContainer empC) {
		this.formFrame = formFrame;
		this.dataManager = dataManager;
		this.empC = empC;
		
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
	    
      addButton.addActionListener(new ActionListener() {
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		createSpecialty();
    		specialtyNameField.setText("");
    		salaryField.setText("");
    		empC.cardLayout.show(empC.getCardPanel(), "Empty");
    		revalidate();
            repaint();
    	}
    	
      });
	}
	
	public void createSpecialty() {
		String specName = specialtyNameField.getText().trim();
		double wagePerhour = formFrame.parsePositiveDouble(salaryField.getText().trim(), "Invalid Input");		
		
		if (specName.isEmpty()) {
	        formFrame.showError("Name cannot be empty!\n");
	        return;
	    }

	    if (wagePerhour == 0) {
	        formFrame.showError("0 are not valid.\n");
	        return;
	    }
	    
	    if(!dataManager.addSpecialty(new Specialty(specName, wagePerhour))) {
	    	formFrame.showError("Specialty already exists.\n");
	        return;
	    }else {
	    	empC.fillSpecialty();
	    }
	}

}