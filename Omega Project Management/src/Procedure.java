import java.util.*;

public class Procedure extends Status {
	private String procedureName;
	private double duration;
//	private Set<Logistic> logistics;
	
	Set<ProcedureDetail> procedureDetails;
	
	public Procedure(String procedureName,float duration, String status) {
		super(status);
		this.procedureName = procedureName;
		this.duration = duration;
		procedureDetails = new TreeSet<ProcedureDetail>();
	}
	
	
	//----------------------------------------------------
	//getters and setters
	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public double getduration() {
		return duration;
	}

	public void setduration(double duration) {
		this.duration = duration;
	}

}