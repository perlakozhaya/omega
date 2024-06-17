import java.util.*;

public class Procedure extends Status {
	private String procedureName;
	private float durationPerHour;
	Set<ProcedureDetail> procedureDetails;
	
	public Procedure(String procedureName,float durationPerHour, String status) {
		super(status);
		this.procedureName = procedureName;
		this.durationPerHour = durationPerHour;
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

	public float getDurationPerHour() {
		return durationPerHour;
	}

	public void setDurationPerHour(float durationPerHour) {
		this.durationPerHour = durationPerHour;
	}

}
//fi 3ena des cout logistique