import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;


public class FileParser {
	
	static public String[] Read(File file){
		ArrayList<String> output = new ArrayList<String>();
		String[] out = null;
		Scanner s = null;
		try {
			s = new Scanner(new FileReader(file));
			while(s.hasNextLine()){
				output.add(s.nextLine());
			}
			out = output.toArray(new String[output.size()]);
			return out;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			s.close();
		}
		return out;
	}
	
	static public void Write(ArrayList<CBG> graphs, String filename){
		PrintWriter writer = null;
		Edge[] matchings;
		Edge edge;
		StringBuffer output = new StringBuffer();
		ListIterator<CBG> gIt = graphs.listIterator();
		try {
			while(gIt.hasNext()){
				CBG G = gIt.next();
				ArrayList<Edge[]> so = G.getSolutions();
				ListIterator<Edge[]> it = so.listIterator();
				writer = new PrintWriter(filename + ".out");
				writer.println(G.getMCMCount());
				while(it.hasNext()){
					matchings = it.next();
					ArrayList<Edge> edges = new ArrayList<Edge>(Arrays.asList(matchings));
					ListIterator<Edge> listIt = edges.listIterator();
					while(listIt.hasNext()){
						edge = listIt.next();
						output.append(edge.getID() + " ");
					}
					writer.println(output);
					output = new StringBuffer();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			writer.close();
		}

	}
}
