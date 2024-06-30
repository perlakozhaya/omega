package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.*;

public class CreateEmployeeContainer extends JPanel {
	private JLabel empNameLB, specialtyLB, emptyLB;
	private JTextField empNameFLD;
	private JComboBox<Specialty> specialtiesBOX;
	private JButton empAddBTN;
	
	private FormFrame formFrame;
	private DataManager dataManager;
	private EmployeeContainer empC;
	
	public CreateEmployeeContainer(FormFrame formFrame, DataManager dataManager, EmployeeContainer empC) {
		this.formFrame = formFrame;
		this.dataManager = dataManager;
		this.empC = empC;
		
	    setLayout(new GridLayout(2, 3, 10, 0));
	    
	    empNameLB = new JLabel("Employee Name");
	    empNameLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(empNameLB);
	    
	    specialtyLB = new JLabel("Select Specialty");
	    specialtyLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(specialtyLB);
	    
	    emptyLB = new JLabel("              ");
	    emptyLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(emptyLB);
	    
	    empNameFLD = new JTextField();
	    add(empNameFLD);
	    
	    specialtiesBOX = new JComboBox<>(empC.getSpecialtyComboModel());
	    add(specialtiesBOX);
	    
	    empAddBTN = new JButton("Add");
	    add(empAddBTN);
	    
	    empC.fillSpecialty();
	    
        empAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createEmployee();
				empNameFLD.setText("");
				specialtiesBOX.setSelectedIndex(-1);
        		empC.cardLayout.show(empC.getCardPanel(), "Empty");
        		revalidate();
                repaint();
            }
        });
	}
	
	public void createEmployee() {
	    String empName = empNameFLD.getText().trim();
	    Specialty selectedSpecialty = getSelectedSpecialty();

	    if (empName.isEmpty()) {
	        formFrame.showError("Employee name cannot be empty!\n");
	        return;
	    }

	    if (selectedSpecialty == null) {
	        formFrame.showError("Please choose a specialty.\n");
	        return;
	    }
	    
	    if(!dataManager.addEmployee(new Employee(empName, selectedSpecialty))) {
	    	formFrame.showError("Employee already exists with this name.\n");
	        return;
	    }
	    
	    empC.fillEmployee();
	}

	public Specialty getSelectedSpecialty() {
		return (Specialty) specialtiesBOX.getSelectedItem();
	}
}