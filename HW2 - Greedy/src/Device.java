
public class Device {
	private boolean hasMessage;
	private int ID;
	private int tContact;
	private Device previousD;
	private String trace;
	
	public Device(int i){
		ID = i;
		hasMessage = false;
		previousD = null;
		trace = null;
		tContact = 0;
	}
	
	public void setMessage(){
		this.hasMessage = true;
	}
	
	public boolean hasMessage(){
		return this.hasMessage;
	}
	
	public int getID(){
		return ID;
	}
	
	public Device getPreviousD(){
		return previousD;
	}
	
	public void setPreviousD(Device d, String t, int time){
		previousD = d;
		trace = t;
		tContact = time;
	}
	
	public String getTraceString(){
		return trace;
	}
	
	public void setTContact(int t){
		tContact = t;
	}
	
	public int getTContact(){
		return tContact;
	}
}
