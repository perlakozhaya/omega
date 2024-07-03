package backend;

import java.io.Serializable;

public abstract class ProcedureDetail implements Comparable<ProcedureDetail>, Serializable {
	private static final long serialVersionUID = -6586074805560794845L;
	
	protected Procedure procedure;
	
	public ProcedureDetail(Procedure procedure) {
		this.procedure = procedure;
	}
	
	public abstract double getCost();
	
	@Override
	public int compareTo(ProcedureDetail o) {
		return this.procedure.compareTo(o.procedure);
	}
	
	public Procedure getProcedure() {
		return procedure;
	}
}