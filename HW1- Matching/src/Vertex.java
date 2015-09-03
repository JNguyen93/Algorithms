
public class Vertex {
	private int id;
	private int weight;
	private boolean matched;
	
	public Vertex(int startID, int startWeight){
		id = startID;
		weight = startWeight;
		matched = false;
	}
	
	public void setID(int i){
		id = i;
	}
	
	public int getID(){
		return id;
	}
	
	public void setMatched(boolean b){
		matched = b;
	}
	
	public boolean isMatched(){
		return matched;
	}
	
	public void setWeight(int w){
		weight = w;
	}
	
	public int getWeight(){
		return weight;
	}
}
