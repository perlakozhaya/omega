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
    private JButton procedureApplyBTN;
	
	private JPanel detailsC, buttonsPanel, centerPanel;
	private JButton empButton, itemButton, logisticButton;
	private EmployeeContainer employeeC; 
	private ItemContainer itemC; 
	private LogisticContainer logisticC; 
	private CardLayout cardLayout;
	
	private String currentCard;
	
	private DataManager dataManager;
    private FormFrame formFrame;
		
	public ProcedureContainer(DataManager dataManager, FormFrame formFrame) {
		this.dataManager = dataManager;
        this.formFrame = formFrame;
	    setLayout(null);
	    
	    procedureNameLB = new JLabel("Create new Procedure");
	    procedureNameLB.setBounds(60, 10, 130, 13);
	    procedureNameLB.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    procedureNameFLD = new JTextField("Procedure Name...");
	    procedureNameFLD.setBounds(60, 24, 130, 25);
//	    procedureNameFLD.setColumns(10);

	    procedureDurationLB = new JLabel("Duration/h");
	    procedureDurationLB.setBounds(210, 10, 130, 13);
	    procedureNameLB.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    procedureDurationFLD = new JTextField("0");
	    procedureDurationFLD.setBounds(210, 24, 130, 25);
//	    procedureDurationFLD.setColumns(10);
	    
	    procedureApplyBTN = new JButton("Apply Changes");
        procedureApplyBTN.setBounds(360, 10, 130, 38);
	    
	    add(procedureNameLB);
	    add(procedureNameFLD);
	    add(procedureDurationLB);
	    add(procedureDurationFLD);
	    add(procedureApplyBTN);
	    
	    detailsC = new JPanel();
	    detailsC.setBackground(new Color(181, 181, 181));
	    detailsC.setBounds(0, 53, 405, 310);
	    detailsC.setLayout(new BorderLayout(0, 0));
	    add(detailsC);
	    
	    buttonsPanel = new JPanel();
	    buttonsPanel.setLayout(new GridLayout(0, 3));
	    detailsC.add(buttonsPanel, BorderLayout.NORTH);
	    
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
	    setCurrentCard("employee");
	    centerPanel.add(itemC, "Item Container");
	    centerPanel.add(logisticC, "Logistic Container");
	    
	    empButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentCard("employee");
				cardLayout.show(centerPanel, "Employee Container");
			}   	
	    });

	    itemButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		setCurrentCard("item");
	    		cardLayout.show(centerPanel, "Item Container");
	    	}   	
	    });

	    logisticButton.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		setCurrentCard("logistic");
	    		cardLayout.show(centerPanel, "Logistic Container");
	    	}   	
	    });
	}
		
	public String getProcedureLabel() {
		return procedureNameLB.getText();
	}
	
	public void setProcedureLabel(String text) {
		procedureNameLB.setText(text);
	}
	
	public String getProcedureField() {
		return procedureNameFLD.getText();
	}

	public void setProcedureField(String text) {
		procedureNameFLD.setText(text);
	}
	
	public String getDurationLabel() {
		return procedureDurationLB.getText();
	}
	
	public void setDurationLabel(String text) {
		procedureDurationLB.setText(text);
	}
	
	public String getDurationField() {
		return procedureDurationFLD.getText();
	}
	
	public void setDurationField(String text) {
		procedureDurationFLD.setText(text);
	}

	public String getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(String currentCard) {
		this.currentCard = currentCard;
	}
	
	public EmployeeContainer getEmployeeContainer() {
		return employeeC;
	}
	
	public ItemContainer getItemContainer() {
		return itemC;
	}
	
	public LogisticContainer getLogisticContainer() {
		return logisticC;
	}
}
