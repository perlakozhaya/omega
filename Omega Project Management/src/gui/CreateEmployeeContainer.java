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
	private JLabel empNameLB, specialtyLB, emptyLB;
	private JTextField empNameFLD;
	private DefaultComboBoxModel<Specialty> specialtiesDCBM;
	private JComboBox<Specialty> specialtiesBOX;
	private JButton empAddBTN;
	
	private FormFrame formFrame;
	private EmployeeContainer empC;
	private DataManager dataManager;
	
	public CreateEmployeeContainer(FormFrame formFrame, DataManager dataManager) {
		this.formFrame = formFrame;
		this.dataManager = dataManager;
		
	    setLayout(new GridLayout(2, 3, 10, 0));
	    
	    empNameLB = new JLabel("Name");
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
	    
	    specialtiesDCBM = new DefaultComboBoxModel<>();
	    specialtiesBOX = new JComboBox<>(specialtiesDCBM);
	    add(specialtiesBOX);
	    
	    empAddBTN = new JButton("Add");
	    add(empAddBTN);
	    
// ---------------------------------------------------------------
	    
	    fillSpecialty();
	    
        empAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createEmployee();
//        		empC.cardLayout.show(empC.getCardPanel(), "Empty");
//        		revalidate();
//                repaint();
            }
        });
	}
	
	public void fillSpecialty() {
		specialtiesDCBM.removeAllElements();
		specialtiesDCBM.addElement(null);
	    for (Specialty specialty : dataManager.getSpecialties()) {
	    	specialtiesDCBM.addElement(specialty);
        }
	}
	
	public void createEmployee() {
	    String empName = empNameFLD.getText().trim();
	    Specialty selectedSpecialty = getSelectedSpecialty();

	    if (empName.isEmpty()) {
	        formFrame.showError("Name cannot be empty!\n");
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
	}

	public Specialty getSelectedSpecialty() {
		return (Specialty) specialtiesBOX.getSelectedItem();
	}
}