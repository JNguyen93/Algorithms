import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;


public class CBG {
	private Tic[] tics;
	private Vertex[] tacs;
	private Edge[] edges;
	private ArrayList<Edge> matchings;
	private ArrayList<Edge[]> solutions;
	private int maximumWeight;
	private int numberOfMCMS;
	
	public CBG(Tic[] U, Vertex[] V){
		tics = U;
		tacs = V;
		matchings = new ArrayList<Edge>();
		solutions = new ArrayList<Edge[]>();
		maximumWeight = 0;
		numberOfMCMS = 0;
	}
	
	public void createEdges(){
		ArrayList<Edge> e = new ArrayList<Edge>();
		
		for(Tic t : tics){
			for(int i = 0; i < tacs.length; i++){
				if((tacs[i].getID() >= t.getMin()) && tacs[i].getID() <= t.getMax()){
					e.add(new Edge(t, tacs[i]));
				}
			}
		}
		edges = e.toArray(new Edge[e.size()]);
	}
	
	public Tic[] getTics(){
		return tics;
	}
	
	public Vertex[] getTacs(){
		return tacs;
	}
	
	public Edge[] getEdges(){
		return edges;
	}
	public Edge findEdge(Tic U, Vertex V){
		for(Edge e: edges){
			if((U == e.getTic()) && (V == e.getTac()))
				return e;
		}
		return null;
	}
	
	public Edge findEdge(Edge edge){
		for(Edge e: edges){
			if(e == edge)
				return e;
		}
		return null;
	}
	
	public boolean isUnstable(Edge edge){
		for(Edge e: matchings){
			if(e.getTic() == edge.getTic() || e.getTac() == edge.getTac()){
				if(e.getWeight() < edge.getWeight())
					return true;
			}
		}
		return false;
	}
	
	public boolean isMatched(Edge edge){
		if(!edge.isActiveMatch() && !edge.getTic().isMatched() 
				&& !edge.getTac().isMatched()){
			return false;
		}
		else
			return true;
	}
	
	public Edge getUnstableMatch(Vertex tac){
		ListIterator<Edge> it = matchings.listIterator();
		while(it.hasNext()){
			Edge unstable = it.next();
			if(unstable.getTac() == tac)
				return unstable;
			else
				unstable = it.next();
		}
		return null;
	}
	
	public void setMatching(Edge matching){
		matching.setActiveMatch(true);
		matching.getTic().setMatched(true);
		matching.getTac().setMatched(true);
		matchings.add(matching);
	}
	
	public void freeMatching(Edge matching){
		matching.setActiveMatch(false);
		matching.getTic().setMatched(false);
		matching.getTac().setMatched(false);
		matchings.remove(matching);
	}
	
	public ArrayList<Edge> getMatchings(){
		return matchings;
	}
	
	public ArrayList<Edge> edgesInRangeOfTic(Tic t){
		ArrayList<Edge> currentEdges = new ArrayList<Edge>();
		for(Edge ed: edges){
			if(ed.contains(t))
				currentEdges.add(ed);
		}
		return currentEdges;
	}
	
	public void setMaximumWeight(){
		ListIterator<Edge> it = matchings.listIterator();
		while(it.hasNext()){
			Edge e = it.next();
			maximumWeight += e.getWeight();
		}
	}
	
	public int getMaximumWeight(){
		return maximumWeight;
	}
	
	public void setMCMCount(){
		numberOfMCMS = solutions.size();
	}
	
	public int getMCMCount(){
		return numberOfMCMS;
	}
	
	public void addSolution(){
		solutions.add(matchings.toArray(new Edge[matchings.size()]));
	}
	
	public ArrayList<Edge[]> getSolutions(){
		return solutions;
	}
	
	public void clearMatchings(){
		Edge[] temp = matchings.toArray(new Edge[matchings.size()]);
		final int size = temp.length;
		for(int i = 0; i < size; i++){
			this.freeMatching(temp[i]);
		}
	}
	
	public boolean isDuplicate(){
		int count = 0;
		final int maxLength = solutions.get(0).length;
		final int maxSize = matchings.size();
		for(Edge[] sets: solutions){
			for(Edge edge: sets){
				for(int i = 0; i < maxSize; i++){
					if(edge == matchings.get(i)){
						count++;
						break;
					}
				}
			}
			if(count >= maxLength)
				return true;
		}
		return false;
	}
	
	public void solutionSort(){
		int id1, id2;
		for(int n = 0; n < 1; n++){
			for(int i = 0; i < solutions.size(); i++){
				if(i+1 == solutions.size())
					break;
				id1 = solutions.get(i)[n].getTic().getID();
				id2 = solutions.get(i+1)[n].getTic().getID();
				if(id1 > id2)
					Collections.swap(solutions, i, i+1);
				else if(id1 == id2){
					for(int j = 0; j < solutions.size(); j++){
						if(j+1 == solutions.size())
							break;
						id1 = solutions.get(j)[n].getTac().getID();
						id2 = solutions.get(j+1)[n].getTac().getID();
						if(id1 > id2)
							Collections.swap(solutions, j, j+1);
					}
				}
			}
		}
	}
}
