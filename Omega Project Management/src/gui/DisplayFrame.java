package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DisplayFrame extends JFrame implements Observer {
    private DataManager dataManager;
    private JTextArea displayArea;

    public DisplayFrame(DataManager dataManager) {
        this.dataManager = dataManager;
        setTitle("Project Progress");
        setBounds(420, 200, 420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        dataManager.addObserver(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        StringBuilder displayText = new StringBuilder();
        for (Project project : dataManager.getProjects()) {
            displayText.append("Project: ").append(project.getProjectName()).append("\n");
        }
        displayArea.setText(displayText.toString());
    }
}