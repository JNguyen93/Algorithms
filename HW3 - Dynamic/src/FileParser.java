import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileParser {
	
	static public String[] Read(String file){
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
	
	static public void Write(int count, List<String> s){
		System.out.println(count);
		for(int i = 0; i < s.size(); i++){
			System.out.println(s.get(i));
		}
	}
	
}
