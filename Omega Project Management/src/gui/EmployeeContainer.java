package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import backend.*;

@SuppressWarnings("serial")
public class EmployeeContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<Employee> list;
	private DefaultListModel<Employee> listDLM;
	private JLabel hoursLabel;
	private JTextField hoursField;
	private JButton addEmp, addSpecialty;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private CreateEmployeeContainer createEmpC;
	private CreateSpecialtyContainer createSpecialtyC;
	
	public EmployeeContainer() {
		setLayout(null);
		setBackground(new Color(227, 227, 227));
		
		listDLM = new DefaultListModel<>();
		for(Employee e : Employee.allEmployees) {
			listDLM.addElement(e);
		}
		list = new JList<Employee>(listDLM);
	    scrollPane = new JScrollPane(list);
	    scrollPane.setBounds(0, 14, 220, 140);
	    add(scrollPane);
	    
	    hoursField = new JTextField("0");
	    hoursField.setHorizontalAlignment(SwingConstants.CENTER);
	    hoursField.setBounds(240, 33, 140, 19);
	    add(hoursField);
	    hoursField.setColumns(10);
	    
	    addSpecialty = new JButton("Create Specialty");
	    addSpecialty.setBounds(240, 120, 140, 20);
	    add(addSpecialty);
	    
	    addEmp = new JButton("Create Employee");
	    addEmp.setBounds(240, 88, 140, 21);
	    add(addEmp);
	    
	    hoursLabel = new JLabel("Worked Hours");
	    hoursLabel.setBounds(270, 15, 100, 13);
	    add(hoursLabel);
	    
	    createEmpC = new CreateEmployeeContainer();
	    createSpecialtyC = new CreateSpecialtyContainer();
	    
	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        cardPanel.setBounds(0, 170, 395, 50);
        cardPanel.add(new JPanel(), "Empty");
        cardPanel.add(createEmpC, "CreateEmployee");
        cardPanel.add(createSpecialtyC, "CreateSpecialty");
        add(cardPanel);
        
        // Show Create Employee Container on click of Add Employee
        addEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	createEmpC.populateSpecialties();
                cardLayout.show(cardPanel, "CreateEmployee");
                revalidate();
                repaint();
            }
        });

        // Show Create Specialty Container on click of Add Specialty
        addSpecialty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateSpecialty");
                revalidate();
                repaint();
            }
        });
        
        createEmpC.getAddBTN().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(createEmpC.createEmployee() != null) {
            		listDLM.removeAllElements();
            		for(Employee emp : Employee.allEmployees) {
            			listDLM.addElement(emp);
            		}
            		cardLayout.show(cardPanel, "Empty");
            		revalidate();
                    repaint();
            	}
			}
        });

        createSpecialtyC.getAddBTN().addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		if(createSpecialtyC.addToJobs()) {
        			cardLayout.show(cardPanel, "Empty");
        		}
        	}
        });
	}
	
	public DefaultListModel<Employee> getEmployeeListModel() {
		return listDLM;
	}

	public JList<Employee> getEmployeeList() {
		return list;
	}
	
	public String getWorkedHoursField() {
		return hoursField.getText();
	}
	
	public void setWorkedHoursField(String text) {
		hoursField.setText(text);
	}
}