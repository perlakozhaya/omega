package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ItemContainer extends JPanel {
	private JScrollPane scrollPane;
	private JList<String> list;
	private JLabel quantityLabel;
	private JTextField quantityField;
	private JButton addItem;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private CreateItemContainer createItemC;
	
	public ItemContainer() {
		list = new JList<String>(new String[] {"Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem", "Avocat", "batikha", "chemem"});

		setLayout(null);
		setBackground(new Color(227, 227, 227));
	    scrollPane = new JScrollPane(list);
	    scrollPane.setBounds(0, 14, 220, 140);
	    add(scrollPane);
	    
	    quantityField = new JTextField("0");
	    quantityField.setHorizontalAlignment(SwingConstants.CENTER);
	    quantityField.setBounds(240, 33, 140, 19);
	    add(quantityField);
	    quantityField.setColumns(10);
	    
	    addItem = new JButton("Create Item");
	    addItem.setBounds(240, 120, 140, 20);
	    add(addItem);
	    
	    quantityLabel = new JLabel("Quantity");
	    quantityLabel.setBounds(270, 15, 100, 13);
	    add(quantityLabel);
	    
	    createItemC = new CreateItemContainer();
	    
	    cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        cardPanel.setBounds(0, 170, 395, 50);
        cardPanel.add(new JPanel(), "Empty");
        cardPanel.add(createItemC, "CreateItem");
        add(cardPanel);

        // Show Create Employee Container on click of Add Employee
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateItem");
                revalidate();
                repaint();
            }
        });
        
        createItemC.getAddBTN().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Empty");
			}
        	
        });
	}
	
}