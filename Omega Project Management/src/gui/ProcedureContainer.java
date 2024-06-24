package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.*;

public class ProcedureContainer extends JPanel {
	private JLabel procedureNameLabel;
	private JTextField procedureName;
	private JPanel detailsC, buttonsPanel, centerPanel;
	private JButton empButton, itemButton, logisticButton;
	private EmployeeContainer employeeC; 
	
	public ProcedureContainer() {
	    setLayout(null);
	    
	    procedureNameLabel = new JLabel("Procedure Name");
	    procedureNameLabel.setBounds(155, 10, 100, 13);
	    add(procedureNameLabel);
	    
	    procedureName = new JTextField();
	    procedureName.setBounds(137, 24, 130, 25);
	    add(procedureName);
	    procedureName.setColumns(10);
	    
	    detailsC = new JPanel();
	    detailsC.setBackground(new Color(181, 181, 181));
	    detailsC.setBounds(0, 53, 405, 310);
	    add(detailsC);
	    detailsC.setLayout(new BorderLayout(0, 0));
	    
	    buttonsPanel = new JPanel();
	    detailsC.add(buttonsPanel, BorderLayout.NORTH);
	    buttonsPanel.setLayout(new GridLayout(0, 3, 0, 0));
	    
	    empButton = new JButton("Employees");
	    buttonsPanel.add(empButton);
	    
	    itemButton = new JButton("Items");
	    buttonsPanel.add(itemButton);
	    
	    logisticButton = new JButton("Logistics");
	    buttonsPanel.add(logisticButton);
	    
	    centerPanel = new JPanel();
	    centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    detailsC.add(centerPanel, BorderLayout.CENTER);
	    centerPanel.setLayout(new CardLayout(0, 0));
	    
	    employeeC = new EmployeeContainer();
	    employeeC.setBackground(new Color(227, 227, 227));
	    centerPanel.add(employeeC, "Employee Container");
	    employeeC.setLayout(null);
  }
	
	public JLabel getProcedureNameLabel() {
		return procedureNameLabel;
	}
	
	public JTextField getProcedureName() {
		return procedureName;
	}
}
