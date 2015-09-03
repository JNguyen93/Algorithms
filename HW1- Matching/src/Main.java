import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int num, tics, tacs, count = 0, matchCount = 0, totalWeight = 0;
		ArrayList<CBG> graphs = new ArrayList<CBG>();
		Tic[] U;
		Vertex[] V;
		String[] set;
		String instructions;
		String filename;
		Scanner sc;
		Edge unstable;
		
		File file = new File(args[0]);
		filename = file.getName();
		filename = filename.replace(".in", "");
		set = FileParser.Read(file);	//read numbers from file;
		StringBuffer s = new StringBuffer();
		
		for(int i = 0; i < set.length; i++){
			s.append(set[i] + " ");
		}
		
		instructions = s.toString();
		sc = new Scanner(instructions);
		num = sc.nextInt();
		
		while(count < num){
			tics = sc.nextInt();
			tacs = sc.nextInt();
			U = new Tic[tics];
			V = new Vertex[tacs];
			
			for(int i = 0; i < tics; i++){	//creates tics
				Tic t0 = new Tic(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
				U[i] = t0;
			}
			
			for(int i = 0; i < tacs; i++){	//creates tacs
				Vertex v0 = new Vertex(sc.nextInt(), sc.nextInt());
				V[i] = v0;
			}
			
			CBG G = new CBG(U, V);	//creates a graph with the given tics and tacs
			G.createEdges();	//creates the edges in the graph
			ArrayList<Edge> currentEdges = new ArrayList<Edge>();	//edges currently being examined
			LinkedList<Tic> q = new LinkedList<Tic>(Arrays.asList(G.getTics()));
			
			while(!q.isEmpty()){
				Tic t = q.poll();
				currentEdges = G.edgesInRangeOfTic(t);	//grabs all edges of the tic
				Collections.sort(currentEdges, Collections.reverseOrder());	//sorts with highest weight first
				ListIterator<Edge> it = currentEdges.listIterator();
				
				while(!t.isMatched()){	//exits if t is matched or there are no more edges
					while(it.hasNext()){
						Edge matching = it.next();	//check the highest weight edge
						boolean matched = G.isMatched(matching); //checks to see if the edges is not matched
						if(!matched){	//if not matched, create the matching
							G.setMatching(matching);
							break;
						}
						else{
							if(G.isUnstable(matching)){	//if unstable, find unstable match
								unstable = G.getUnstableMatch(matching.getTac());
								G.freeMatching(unstable);	//free unstable matching
								G.setMatching(matching);	//set new matching
								q.add(unstable.getTic());
								break;
							}
						}
						matchCount++;
					}
					if(matchCount >= currentEdges.size())
						break;
				}
				matchCount = 0;
			}	
			G.setMaximumWeight();
			G.addSolution();
			G.clearMatchings();
			
			ArrayList<Edge> allEdges = new ArrayList<Edge>(Arrays.asList(G.getEdges()));
			
			for(Edge this1: allEdges){	//sets every edge to be the initial matching
				boolean tested = G.isMatched(this1);
					if(!tested){	//if not matched, create the matching
					G.setMatching(this1);
					totalWeight += this1.getWeight();
				}
				for(int i = 0; i < allEdges.size(); i++){	//runs through the matchings with no checks (brute force)
					Edge x = allEdges.get(i);
					boolean matched = G.isMatched(x); //checks to see if the edges is not matched
					if(!matched){	//if not matched, create the matching
						G.setMatching(x);
						totalWeight += x.getWeight();
					}
				}
				if(totalWeight == G.getMaximumWeight()){
					if(!G.isDuplicate())
						G.addSolution();
				}	
				G.clearMatchings();
				totalWeight = 0;
			}
			
			G.setMCMCount();
			G.solutionSort();
			graphs.add(G);
			count++;
		}
		sc.close();
		FileParser.Write(graphs, filename);
	}

}
