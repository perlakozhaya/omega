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
        setTitle("Project Progress");
        setBounds(420, 200, 420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.dataManager = dataManager;
        dataManager.addObserver(this);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);
        
        update(null, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        StringBuilder displayText = new StringBuilder();
        for (Project project : dataManager.getProjects()) {
            displayText.append("Project: ").append(project.getProjectName()).append("\n");
            for(Task task : project.getTasks()) {
                displayText.append("\tTask: ").append(task.getTaskName()).append("\n");
                for(Procedure procedure : task.getProcedures()) {
                    displayText.append("\tProcedure: ").append(procedure.getProcedureName()).append("\n");
                    for(ProcedureEmployee pe : procedure.getEmployees()) {
                    	displayText.append("\tEmployees: ").append(pe.getEmployee().getEmployeeCode()).append("\n");
                        displayText.append("\nWorked Hours: ").append(pe.getHoursWorked()).append("\n");
                    }
                }
            }
        }
        displayArea.setText(displayText.toString());
    }
}