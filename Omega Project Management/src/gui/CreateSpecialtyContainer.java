package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.*;

@SuppressWarnings("serial")
public class CreateSpecialtyContainer extends JPanel {
	private JLabel specialtyNameLB, specialtyLB, emptyLB;
	private JTextField specialtyNameFLD, wagePerHourFLD;
	private JButton specialtyAddBTN;
	
	private FormFrame formFrame;
	private DataManager dataManager;
	private EmployeeContainer empC;
	
	public CreateSpecialtyContainer(FormFrame formFrame, DataManager dataManager, EmployeeContainer empC) {
		this.formFrame = formFrame;
		this.dataManager = dataManager;
		this.empC = empC;
		
	    setLayout(new GridLayout(2, 3, 10, 0));
	    
	    specialtyNameLB = new JLabel("Specialty Name");
	    specialtyNameLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(specialtyNameLB);
	    
	    specialtyLB = new JLabel("Wage per hour");
	    specialtyLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(specialtyLB);
	    
	    emptyLB = new JLabel("              ");
	    emptyLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(emptyLB);
	    
	    specialtyNameFLD = new JTextField();
	    add(specialtyNameFLD);
	    
	    wagePerHourFLD = new JTextField();
	    add(wagePerHourFLD);
	    
	    specialtyAddBTN = new JButton("Add");
	    add(specialtyAddBTN);
	    
        specialtyAddBTN.addActionListener(new ActionListener() {
    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		if(createSpecialty()) {
	    			specialtyNameFLD.setText("");
	    			wagePerHourFLD.setText("");
	    			empC.cardLayout.show(empC.getCardPanel(), "Empty");	    			
	    		}
	    	}
      });
	}
	
	public boolean createSpecialty() {
		String specialtyName = specialtyNameFLD.getText().trim();
		String wageString = wagePerHourFLD.getText().trim();
		
		if (specialtyName.isEmpty()) {
	        formFrame.showMessage("Specialty Name cannot be empty!\n");
	        return false;
	    }

	    if (wageString.isEmpty()) {
	        formFrame.showMessage("Please enter a wage per hour.\n");
	        return false;
	    }
	    
	    try {
	    	double wagePerhour = formFrame.parsePositiveDouble(wageString, "Wage must be a positive number!");	
	    	
	    	if(!dataManager.addSpecialty(new Specialty(specialtyName, wagePerhour))) {
	    		formFrame.showMessage("Specialty already exists.\n");
	    		return false;
	    	}
    		empC.fillSpecialty();
    		return true;
    		
	    } catch (IllegalArgumentException ex) {
	        formFrame.showMessage("Wage must be a positive number!");
	        return false;
	    }
	}
}