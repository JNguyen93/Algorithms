import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {
	
	static HashMap<String, List<String>> memo = new HashMap<String, List<String>>();

	public static void main(String[] args) {
		int dictNum;
		String[] set;
		String str;
		String instructions;
		Scanner sc;
		ArrayList<String> dict = new ArrayList<String>();
		
		set = FileParser.Read(args[0]);	//read numbers from file;
		StringBuffer s = new StringBuffer();
		for(int i = 0; i < set.length; i++){	//concatenate the inputs
			s.append(set[i] + " ");
		}
		instructions = s.toString();
		sc = new Scanner(instructions);	//open scanner for the input
		dictNum = sc.nextInt();
		if(dictNum == 0)
			System.out.println(0);
		
		else{
			for(int i = 0; i < dictNum; i++){
				dict.add(sc.next());
			}
			str = sc.next();
			textParse(str, dict);
			List<String> solution = memo.get(str);
			if(solution == null)
				System.out.println(0);
			else{
				ArrayList<String> filteredSolution = new ArrayList<String>();
				for(int i = 0; i < solution.size(); i++){
					if(str.equals(solution.get(i).replaceAll("\\s+","")))
						filteredSolution.add(solution.get(i));
				}
				FileParser.Write(filteredSolution.size(), filteredSolution);
			}
		}
		sc.close();
	}
	
	public static void textParse(String str, List<String> dict){
		for(int i = str.length() - 1; i >= 0; i--){
			List<String> temp = new ArrayList<String>();
			String substr = str.substring(i, str.length());
			for(int j = 0; j <= substr.length(); j++){
				String subsubstr = substr.substring(0, j);
				String rest = substr.substring(j, substr.length());
				String ans = new String();
				if(dict.contains(subsubstr)){
					ans += subsubstr;
					if(memo.containsKey(rest)){
						List<String> suffix = memo.get(rest);
						for(int k = 0; k < suffix.size(); k++){
							ans += (" " + memo.get(rest).get(k));
							temp.add(ans);
							ans = ans.substring(0, subsubstr.length());
						}
					}
					else
						temp.add(ans);
				}			
			}
			if(!memo.containsKey(substr) && !temp.isEmpty())
				memo.put(substr, temp);
		}
	}
	
}
