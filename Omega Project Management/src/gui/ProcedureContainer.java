package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import backend.*;

@SuppressWarnings("serial")
public class ProcedureContainer extends JPanel {
	private JLabel procedureNameLB, procedureDurationLB;
	private JTextField procedureNameFLD, procedureDurationFLD;
	private JPanel detailsC, buttonsPanel, centerPanel;
	private JButton empButton, itemButton, logisticButton;
	private EmployeeContainer employeeC; 
	private ItemContainer itemC; 
	private LogisticContainer logisticC; 
	private CardLayout cardLayout;
	
	public ProcedureContainer() {
	    setLayout(null);
	    
	    procedureNameLB = new JLabel("Name");
	    procedureNameLB.setBounds(60, 10, 130, 13);
	    procedureNameLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(procedureNameLB);
	    
	    procedureNameFLD = new JTextField();
	    procedureNameFLD.setBounds(60, 24, 130, 25);
	    add(procedureNameFLD);
	    procedureNameFLD.setColumns(10);

	    procedureDurationLB = new JLabel("Duration/h");
	    procedureDurationLB.setBounds(210, 10, 130, 13);
	    procedureDurationLB.setHorizontalAlignment(SwingConstants.CENTER);
	    add(procedureDurationLB);
	    
	    procedureDurationFLD = new JTextField();
	    procedureDurationFLD.setBounds(210, 24, 130, 25);
	    procedureDurationFLD.setHorizontalAlignment(SwingConstants.CENTER);
	    add(procedureDurationFLD);
	    procedureDurationFLD.setColumns(10);
	    
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
	
	public String getNameLabelContent() {
		return procedureNameLB.getText();
	}
	
	public void setNameLabelContent(String text) {
		procedureNameLB.setText(text);
	}
	
	public String getNameFieldContent() {
		return procedureNameFLD.getText();
	}

	public void setNameFieldContent(String text) {
		procedureNameFLD.setText(text);
	}
	
	public String getDurationLabelContent() {
		return procedureDurationLB.getText();
	}
	
	public void setDurationLabelContent(String text) {
		procedureDurationLB.setText(text);
	}
	
	public String getDurationFieldContent() {
		return procedureDurationFLD.getText();
	}
	
	public void setDurationFieldContent(String text) {
		procedureDurationFLD.setText(text);
	}
}
