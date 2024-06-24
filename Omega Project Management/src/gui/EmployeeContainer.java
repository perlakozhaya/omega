package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EmployeeContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<String> list;
	private JLabel hoursLabel;
	private JTextField hoursField;
	private JButton addEmp, addSpecialty;
	private CardLayout cardLayout;
	private CreateEmployeeContainer createEmpC;
	private CreateSpecialtyContainer createSpecialtyC;
	
	public EmployeeContainer() {
		list = new JList<String>(new String[] {"Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem"});
	    
	    scrollPane = new JScrollPane(list);
	    scrollPane.setBounds(0, 14, 220, 140);
	    add(scrollPane);
	    
	    hoursField = new JTextField();
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
        JPanel cardPanel = new JPanel(cardLayout);
        
        cardPanel.setBounds(0, 170, 395, 50);
        cardPanel.add(new JPanel());
        cardPanel.add(createEmpC, "CreateEmployee");
        cardPanel.add(createSpecialtyC, "CreateSpecialty");
        add(cardPanel);

        // Show Create Employee Container on click of Add Employee
        addEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
	}
}