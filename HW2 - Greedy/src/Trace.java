
public class Trace {
	private String trace;
	private Device d1;
	private Device d2;
	private int tcontact;
	
	public Trace(String t, Device u1, Device u2, int time){
		trace = t;
		d1 = u1;
		d2 = u2;
		tcontact = time;
	}
	
	public String getTrace(){
		return trace;
	}
	
	public Device getDevice1(){
		return d1;
	}
	
	public Device getDevice2(){
		return d2;
	}
	
	public int getTContact(){
		return tcontact;
	}

}
