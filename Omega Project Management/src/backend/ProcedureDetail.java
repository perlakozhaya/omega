package backend;

import java.io.Serializable;

public abstract class ProcedureDetail implements Comparable<ProcedureDetail>, Serializable {
	protected Procedure procedure;
	
	public ProcedureDetail(Procedure procedure) {
		this.procedure = procedure;
	}
	
	public abstract double getCost();
	
	@Override
	public int compareTo(ProcedureDetail o) {
		return this.procedure.compareTo(o.procedure);
	}
	
	public Procedure getprocedure() {
		return procedure;
	}
}