
public abstract class ProcedureDetail implements Comparable<ProcedureDetail> {
	protected Procedure procedure;
	
	public ProcedureDetail(Procedure procedure) {
		this.procedure = procedure;
	}
	
	public abstract double getCost();
	
	@Override
	public int compareTo(ProcedureDetail o) {
		return this.procedure.compareTo(o.procedure);
	}
}
