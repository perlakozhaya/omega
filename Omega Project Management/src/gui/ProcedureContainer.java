package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.*;

@SuppressWarnings("serial")
public class ProcedureContainer extends JPanel {
	private JLabel procedureNameLabel, procedureDurationLabel;
	private JTextField procedureName, procedureDuration;
	private JPanel detailsC, buttonsPanel, centerPanel;
	private JButton empButton, itemButton, logisticButton;
	private EmployeeContainer employeeC; 
	private ItemContainer itemC; 
	private LogisticContainer logisticC; 
	private CardLayout cardLayout;
	
	public ProcedureContainer() {
	    setLayout(null);
	    
	    procedureNameLabel = new JLabel("Name");
	    procedureNameLabel.setBounds(60, 10, 130, 13);
	    procedureNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(procedureNameLabel);
	    
	    procedureName = new JTextField();
	    procedureName.setBounds(60, 24, 130, 25);
	    add(procedureName);
	    procedureName.setColumns(10);

	    procedureDurationLabel = new JLabel("Duration/h");
	    procedureDurationLabel.setBounds(210, 10, 130, 13);
	    procedureDurationLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    add(procedureDurationLabel);
	    
	    procedureDuration = new JTextField();
	    procedureDuration.setBounds(210, 24, 130, 25);
	    procedureDuration.setHorizontalAlignment(SwingConstants.CENTER);
	    add(procedureDuration);
	    procedureDuration.setColumns(10);
	    
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
	    
	    cardLayout = new CardLayout();
	    centerPanel.setLayout(cardLayout);
	    
	    employeeC = new EmployeeContainer();
	    itemC = new ItemContainer();
	    logisticC = new LogisticContainer();

	    centerPanel.add(employeeC, "Employee Container");
	    centerPanel.add(itemC, "Item Container");
	    centerPanel.add(logisticC, "Logistic Container");
	    
	    
	    empButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "Employee Container");
			}   	
	    });

	    itemButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		cardLayout.show(centerPanel, "Item Container");
	    	}   	
	    });

	    logisticButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		cardLayout.show(centerPanel, "Logistic Container");
	    	}   	
	    });
	}
	
	public void applyProcedure(Task t, Procedure pr) {
		String procedureName;
		double duration;
		
		try {
			duration = Double.parseDouble(getProcedureDuration().getText().trim());
			if((procedureName = getProcedureName().getText().trim()) != "") {
				
				t.addProcedure(new Procedure(procedureName, duration, "Incomplete"));
			}
		} catch (IllegalArgumentException ie) {
			JOptionPane.showMessageDialog(null, "Invalid number!\n" + ie.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public JLabel getProcedureNameLabel() {
		return procedureNameLabel;
	}
	
	public JTextField getProcedureName() {
		return procedureName;
	}

	public JLabel getProcedureDurationLabel() {
		return procedureDurationLabel;
	}
	
	public JTextField getProcedureDuration() {
		return procedureDuration;
	}
}
