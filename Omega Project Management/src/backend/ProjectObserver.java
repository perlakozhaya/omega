package backend;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

public class ProjectObserver implements Observer {
	private DefaultComboBoxModel<Project> projectDCBM;
	ProjectObservable projectObservable;

	public ProjectObserver(DefaultComboBoxModel<Project> projectDCBM, ProjectObservable projectObservable) {
		this.projectDCBM = projectDCBM;
		this.projectObservable = projectObservable;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		projectDCBM.removeAllElements();
		projectDCBM.addElement(null);
		for(Project p : projectObservable.getProjects()) {
			projectDCBM.addElement(p);
		}
	}
	
	public DefaultComboBoxModel<Project> getProjectDCBM() {
	    return projectDCBM;
	}
}