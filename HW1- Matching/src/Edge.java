
public class Edge implements Comparable<Edge>{
	private String id;
	private Tic tic;
	private Vertex tac;
	private int combinedWeight;
	private boolean activeMatch;
	
	public Edge(Tic tic, Vertex tac){
		this.tic = tic;
		this.tac = tac;
		combinedWeight = tic.getWeight() + tac.getWeight();
		activeMatch = false;
		id = tic.getID() + ":" + tac.getID();
	}
	
	public Tic getTic(){
		return tic;
	}
	
	public Vertex getTac(){
		return tac;
	}
	
	public String getID(){
		return id;
	}
	
	public int getWeight(){
		return combinedWeight;
	}
	
	public void setActiveMatch(boolean b){
		activeMatch = b;
	}
	
	public boolean isActiveMatch(){
		return activeMatch;
	}
	
	public boolean contains(Tic t){
		if (tic == t)
			return true;
		else
			return false;
	}
	
	public boolean contains(Vertex t){
		if (tac == t)
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(Edge e) {
		if(e.getWeight() < this.getWeight())
			return 1;
		else if(e.getWeight() == this.getWeight())
			return 0;
		else
			return -1;
	}
}
