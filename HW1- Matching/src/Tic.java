
public class Tic extends Vertex{
	private int min;
	private int max;
	
	public Tic(int startID, int startMin, int startMax, int startWeight){
		super(startID, startWeight);
		min = startMin;
		max = startMax;
	}
	
	public void setMin(int m){
		min = m;
	}
	
	public int getMin(){
		return min;
	}
	
	public void setMax(int m){
		max = m;
	}
	
	public int getMax(){
		return max;
	}
}
