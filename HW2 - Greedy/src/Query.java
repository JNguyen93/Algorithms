
public class Query {
	private String query;
	private Device d1;
	private Device d2;
	private int tInit;
	private int tEnd;
	
	public Query(String q, Device u1, Device u2, int t0, int t1){
		query = q;
		d1 = u1;
		d2 = u2;
		tInit = t0;
		tEnd = t1;
	}
	
	public String getQuery(){
		return query;
	}
	
	public Device getDevice1(){
		return d1;
	}
	
	public Device getDevice2(){
		return d2;
	}
	
	public int getStartTime(){
		return tInit;
	}
	
	public int getEndTime(){
		return tEnd;
	}
}
