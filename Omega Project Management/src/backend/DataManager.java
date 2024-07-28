package backend;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

public class DataManager extends Observable implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Project> projects;
	private Set<Employee> employees;
	private Set<Specialty> specialties;
	private Set<Item> items;
    private Set<Logistic> logistics;

	public DataManager() {
		projects = new TreeSet<>();
		employees = new TreeSet<>();
		specialties = new TreeSet<>();
		items = new TreeSet<>();
		logistics = new TreeSet<>();
	}
	
	public void addProject(Project project) {
	     projects.add(project);
	     setChanged();
	     notifyObservers();
	}
	
	public void updateProject(Project project, String name) {
		if(projects.contains(project)) {
			project.setProjectName(name);
			setChanged();
			notifyObservers();
		}
	}
	
	public void removeProject(Project project) {
	     projects.remove(project);
	     setChanged();
	     notifyObservers();
	}
	
	public void addTaskToProject(Project project, Task task) {
		if (projects.contains(project)) {
            project.addTask(task);
            setChanged();
            notifyObservers();
        }
	}
	
	public void updateTaskInProject(Project project, Task task, String name) {
		if (projects.contains(project)) {
            Set<Task> tasks = project.getTasks();
            if (tasks.contains(task)) {
            	task.setTaskName(name);
                setChanged();
                notifyObservers();
            }
        }
	}
	
	public void removeTaskFromProject(Project project, Task task) {
		if (projects.contains(project)) {
            project.removeTask(task);
            setChanged();
            notifyObservers();
        }
	}
	
	public void addProcedureToTask(Task task, Procedure procedure) {
	     task.addProcedure(procedure);
	     setChanged();
	     notifyObservers();
	}
	
	public void updateProcedureInTask(Task task, Procedure procedure, String name, Double duration) {
        Set<Procedure> procedures = task.getProcedures();
        if (procedures.contains(procedure)) {
        	procedure.setProcedureName(name);
        	procedure.setProcedureDuration(duration);
            setChanged();
            notifyObservers();
        }
	}
	
	public void removeProcedureFromTask(Task task, Procedure procedure) {
	     task.removeProcedure(procedure);
	     setChanged();
	     notifyObservers();
	}
	
	public void addEmployeeToProcedure(Procedure procedure, Employee employee, double hoursWorked) {
		procedure.addEmployee(employee, hoursWorked);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateEmployeeInProcedure(Procedure procedure, Employee employee, double hoursWorked) {
	    if (!employees.contains(employee)) {
	        return false;
	    }
	    for (ProcedureEmployee pe : procedure.getEmployees()) {
	        if (pe.getEmployee().equals(employee)) {
	            pe.setHoursWorked(hoursWorked);
	            setChanged();
	            notifyObservers();
	            return true;
	        }
	    }
	    return false;
	}

	public boolean addEmployee(Employee employee) {
		if(!employees.contains(employee)) {
			employees.add(employee);
			setChanged();
			notifyObservers();	
			return true;
		}
		return false;
	}
	
	public boolean addSpecialty(Specialty specialty) {
		if(!specialties.contains(specialty)) {
			specialties.add(specialty);
			setChanged();
			notifyObservers();	
			return true;
		}
		return false;
    }
	
	public boolean addItem(Item item) {
		if(!items.contains(item)) {
			items.add(item);
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
	
	public void addItemToProcedure(Procedure procedure, Item item, double quantity) {
		procedure.addItem(item, quantity);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateItemInProcedure(Procedure procedure, Item item, double quantity) {
		if(!items.contains(item)) {
			return false;
		}
		for(ProcedureItem pi : procedure.getItems()) {
			if(pi.getItem().equals(item)) {
				pi.setQuantity(quantity);
				setChanged();
				notifyObservers();
				return true;
			}
		}
		return false;
	}
	
	public void addLogistic(Logistic logistic) {
		logistics.add(logistic);
		setChanged();
		notifyObservers();
	}
	
	public void addLogisticToProcedure(Procedure procedure, Logistic logistic, double quantity) {
		procedure.addLogistic(logistic, quantity);
		setChanged();
		notifyObservers();
	}
	
	public boolean updateLogisticInProcedure(Procedure procedure, Logistic logistic, double quantity) {
        if(!logistics.contains(logistic)) {
        	return false;
        }
    	for(ProcedureLogistic pl : procedure.getLogistics()) {
    		if(pl.getLogistic().equals(logistic)) {
        		pl.setQuantity(quantity);
        		setChanged();
        		notifyObservers();
        		return true;
    		}
    	}
    	return false;
	}
	
	public String changeStatus(Procedure procedure) {
	    final String[] statusOrder = {"Execute", "Ongoing", "Done"};
	    String currentStatus = procedure.getStatus();
	    
	    for (int i = 0; i < statusOrder.length - 1; i++) {
	        if (statusOrder[i].equals(currentStatus)) {
	            procedure.setStatus(statusOrder[i + 1]);
	            setChanged();
	            notifyObservers();
	            return statusOrder[i + 1];
	        } 
	    }
	    return currentStatus;
	}
	
	public boolean saveDataToFile(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving data: " + e.getMessage());
            return false;
        }
    }
	
	public static DataManager loadDataFromFile(File file) {
        DataManager dataManager = null;
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        	dataManager = (DataManager) ois.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading data: " + e.getMessage());
        }
        return dataManager;
    }

	public void export_to_pdf(Project p) {
		StringBuilder html = new StringBuilder();
		
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>Project Details</title>\n");
        html.append("    <style>\n");
        html.append("        body {\n");
        html.append("            font-family: Arial, sans-serif;\n");
        html.append("            margin: 20px;\n");
        html.append("        }\n");
        html.append("        .project, .task, .procedure {\n");
        html.append("            border: 1px solid #ddd;\n");
        html.append("            margin-bottom: 20px;\n");
        html.append("            padding: 20px;\n");
        html.append("            border-radius: 5px;\n");
        html.append("        }\n");
        html.append("        .project-title, .task-title, .procedure-title {\n");
        html.append("            font-weight: bold;\n");
        html.append("            font-size: 1.2em;\n");
        html.append("        }\n");
        html.append("        .details {\n");
        html.append("            margin-top: 10px;\n");
        html.append("        }\n");
        html.append("        .employees, .materials {\n");
        html.append("            margin-top: 10px;\n");
        html.append("        }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");

        html.append("    <div class=\"project\">\n");
        html.append("        <div class=\"project-title\">Project: ").append(p.getProjectName()).append("</div>\n");
        html.append("        <div class=\"details\">\n");
        html.append("            <p>Price: $").append(p.projectCost()).append("</p>\n");
        html.append("            <p>Time: ").append(p.projectDuration()).append("</p>\n");
        html.append("        </div>\n");

        for (Task task : p.getTasks()) {
            html.append("        <div class=\"task\">\n");
            html.append("            <div class=\"task-title\">Task: ").append(task.getTaskName()).append("</div>\n");
            html.append("            <div class=\"details\">\n");
            html.append("                <p>Price: $").append(String.format("%.2f",task.taskCost())).append("</p>\n");
            html.append("                <p>Time: ").append(task.taskDuration()).append("</p>\n");
            html.append("            </div>\n");

            for (Procedure procedure : task.getProcedures()) {
                html.append("            <div class=\"procedure\">\n");
                html.append("                <div class=\"procedure-title\">Procedure: ").append(procedure.getProcedureName()).append("</div>\n");
                html.append("                <div class=\"details\">\n");
                html.append("                    <p>Price: $").append(String.format("%.2f",procedure.procedureCost())).append("</p>\n");
                html.append("                    <p>Time: ").append(procedure.getProcedureDuration()).append("</p>\n");
                html.append("                    <div class=\"employees\">\n");
                html.append("                        <p>Employees:</p>\n");
                html.append("                        <ul>\n");

                Set<ProcedureEmployee> employees = procedure.getEmployees();
                for (ProcedureEmployee pe : employees) {
                    html.append("                            <li>").append(pe.getEmployee().getEmployeeCode()).append("</li>\n");
                }

                html.append("                        </ul>\n");
                html.append("                    </div>\n");
                html.append("                    <div class=\"materials\">\n");
                html.append("                        <p>Materials:</p>\n");
                html.append("                        <ul>\n");

                Set<ProcedureItem> items = procedure.getItems();
                for (ProcedureItem pi : items) {
                    html.append("                            <li>").append(pi.getItem().getItemName()).append("</li>\n");
                }

                html.append("                        </ul>\n");
                html.append("                    </div>\n");
                html.append("                </div>\n");
                html.append("            </div>\n");
            }

            html.append("        </div>\n");
        }

        html.append("    </div>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        // Create the PDF from HTML
        try (FileOutputStream outputStream = new FileOutputStream("src/" + p.getProjectName() + ".pdf")) {
                // Create converter properties, if needed
                ConverterProperties converterProperties = new ConverterProperties();
                // Convert HTML to PDF
                HtmlConverter.convertToPdf(html.toString(), outputStream, converterProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
 	public Set<Task> getAllTasks() {	
		Set<Task> tasks = new TreeSet<>();
		if(!projects.isEmpty()) {
			for(Project project : projects) {
				tasks.addAll(project.getTasks());
			}
		}
		return tasks;
	}
	
	public Set<Procedure> getAllProcedures() {
		Set<Task> allTasks = getAllTasks();
		Set<Procedure> procedures = new TreeSet<>();
		if(!allTasks.isEmpty()) {
			for(Task task : allTasks) {
				procedures.addAll(task.getProcedures());
			}
		}
		return procedures;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public Set<Specialty> getSpecialties() {
		return specialties;
	}
	
	public Set<Item> getItems() {
		return items;
	}
	
	public Set<Logistic> getLogistics() {
		return logistics;
	}
}