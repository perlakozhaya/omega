package gui;

import java.awt.*;


import javax.swing.*;

@SuppressWarnings("serial")
public class LogisticContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<String> list;
	private JLabel quantityLabel;
	private JTextField quantityField;
	private JPanel cardPanel;
	
	public LogisticContainer() {
		list = new JList<String>(new String[] {"Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem"});

		setLayout(null);
		setBackground(new Color(227, 227, 227));
	    scrollPane = new JScrollPane(list);
	    scrollPane.setBounds(0, 14, 220, 140);
	    add(scrollPane);
	    
	    quantityField = new JTextField("0");
	    quantityField.setHorizontalAlignment(SwingConstants.CENTER);
	    quantityField.setBounds(240, 80, 140, 19);
	    add(quantityField);
	    quantityField.setColumns(10);
	    
	    quantityLabel = new JLabel("Quantity");
	    quantityLabel.setBounds(285, 65, 100, 13);
	    add(quantityLabel);

        cardPanel = new JPanel();
        
        cardPanel.setBounds(0, 170, 395, 50);
        add(cardPanel);
	}
	
}