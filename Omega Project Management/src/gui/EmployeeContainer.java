package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.*;

@SuppressWarnings("serial")
public class EmployeeContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<Employee> empLST;
	private DefaultListModel<Employee> empDLM;
	private DefaultComboBoxModel<Specialty> specialtiesDCBM;
	private JLabel hoursLB;
	private JTextField hoursFLD;
	private JButton empAddBTN, createEmp, createSpecialty;
	private JPanel cardPanel;
	private CreateEmployeeContainer createEmpC;
	private CreateSpecialtyContainer createSpecialtyC;
	
	private DataManager dataManager;
	private FormFrame formFrame;
	
	CardLayout cardLayout;
		
	public EmployeeContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
		this.formFrame = formFrame;
		
		setLayout(null);
		setBackground(new Color(227, 227, 227));
		
		specialtiesDCBM = new DefaultComboBoxModel<Specialty>();
		empDLM = new DefaultListModel<>();
		empLST = new JList<Employee>(empDLM);
	    scrollPane = new JScrollPane(empLST);
	    scrollPane.setBounds(0, 14, 220, 140);
	    add(scrollPane);
	    
	    hoursLB = new JLabel("Worked Hours");
	    hoursLB.setBounds(270, 15, 100, 13);
	    add(hoursLB);
	    
	    hoursFLD = new JTextField("0");
	    hoursFLD.setHorizontalAlignment(SwingConstants.CENTER);
	    hoursFLD.setBounds(240, 33, 140, 19);
	    add(hoursFLD);
	    hoursFLD.setColumns(10);
	    
	    empAddBTN = new JButton("Add Employee");
	    empAddBTN.setBounds(240, 55, 140, 21);
	    add(empAddBTN);
	    
	    createEmp = new JButton("Create Employee");
	    createEmp.setBounds(240, 107, 140, 21);
	    add(createEmp);
	    
	    createSpecialty = new JButton("Create Specialty");
	    createSpecialty.setBounds(240, 133, 140, 20);
	    add(createSpecialty);
	    
	    createEmpC = new CreateEmployeeContainer(formFrame, dataManager, this);
	    createSpecialtyC = new CreateSpecialtyContainer(formFrame, dataManager, this);
	    
	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        cardPanel.setBounds(0, 170, 395, 50);
        cardPanel.add(new JPanel(), "Empty");
        cardPanel.add(createEmpC, "CreateEmployee");
        cardPanel.add(createSpecialtyC, "CreateSpecialty");
        add(cardPanel);
        
//		--------------------------------------------------------------------
        
        fillEmployee();
        
        empAddBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Procedure selectedProcedure = formFrame.getSelectedProcedure();
				Employee selectedEmployee = getSelectedEmployee();
				handleEmployee(selectedProcedure, selectedEmployee);
		        fillEmployee();
			}
        });
        
        empLST.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				fillHours();
			}
        	
        });
        
        // Show Create Employee Container on click of Create Employee
        createEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateEmployee");
                fillSpecialty();
                revalidate();
                repaint();
            }
        });

        // Show Create Specialty Container on click of Create Specialty
        createSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateSpecialty");
                revalidate();
                repaint();
            }
        });
	}
	
	public void fillHours() {
		Procedure selectedProcedure = formFrame.getSelectedProcedure();
        Employee selectedEmployee = getSelectedEmployee();
        boolean found = false;
        for (ProcedureEmployee pe : selectedProcedure.getEmployees()) {
            if (pe.getEmployee().equals(selectedEmployee)) {
                setHoursFLD(pe.getHoursWorked() + "");
                found = true;
                break;
            }
        }
        if (!found) setHoursFLD("0");
    }

	public void fillEmployee() {
    	Set<Employee> employees = dataManager.getEmployees();
    	empDLM.removeAllElements();
		empDLM.addElement(null);
		if(!employees.isEmpty()) {
			for(Employee e : dataManager.getEmployees()) {
				empDLM.addElement(e);
			}			
		}
	}
	
	public void fillSpecialty() {
		specialtiesDCBM.removeAllElements();
		specialtiesDCBM.addElement(null);
	    for (Specialty specialty : dataManager.getSpecialties()) {
	    	specialtiesDCBM.addElement(specialty);
        }
	}
	
	public void handleEmployee(Procedure selectedProcedure, Employee selectedEmployee) {
		String hoursString = getHoursFLD().trim();
		
		if(selectedEmployee == null) {
			formFrame.showError("Select an employee and try again");
			return;
		}
		
		if (hoursString.isEmpty()) {
        	formFrame.showError("Worked hours cannot be empty!");
        	return;
        }
        
        try {
            double workedHours = formFrame.parsePositiveDouble(hoursString, "Worked hours must be a positive number!");
            
            if(workedHours > selectedProcedure.getProcedureDuration()) {
    			formFrame.showError("An employee work hours cannot exceed the procedure duration!");
    			return;
    		}
            
            // if employee works in procedure then update worked hours, otherwise add employee to procedure
    		boolean updated = dataManager.updateEmployeeInProcedure(selectedProcedure, selectedEmployee, workedHours);
    		if(!updated) {
    			dataManager.addEmployeeToProcedure(selectedProcedure, selectedEmployee, workedHours);
    		}
	    } catch (IllegalArgumentException ex) {
	        formFrame.showError("Worked hours must be a positive number!");
	        return;
	    }
	}
	
	public Employee getSelectedEmployee() {
		return (Employee) empLST.getSelectedValue();
	}
	
	public String getHoursFLD() {
		return hoursFLD.getText();
	}
	
	public void setHoursFLD(String text) {
		hoursFLD.setText(text);
	}
	
	public JPanel getCardPanel() {
		return cardPanel;
	}
	
	public DefaultComboBoxModel<Specialty> getSpecialtyComboModel(){
		return specialtiesDCBM;
	}
}