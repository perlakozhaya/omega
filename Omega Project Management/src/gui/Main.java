package gui;

import java.io.*;
import java.util.*;

import backend.*;

public class Main {

	public static void main(String[] args) {
		File file = new File("src/data.dat");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Error creating file: " + ex.getMessage());
            }
        }
        
        // If failed to load DataManager from file, create new instance.
		DataManager dataManager = DataManager.loadDataFromFile(file);
		if (dataManager == null) {
            dataManager = new DataManager();
        }
		
		// chefle he leh null
		Set<Task> allTasks = dataManager.getTasks();
		for(Task t : allTasks) {
			System.out.println(t);
		}
		
        FormFrame formFrame = new FormFrame(dataManager);
        formFrame.setVisible(true);
        
        ProgressFrame progressFrame = new ProgressFrame(dataManager);
        progressFrame.setVisible(true);
        
        FilterFrame filterFrame = new FilterFrame(dataManager);
        filterFrame.setVisible(true);
    }
}