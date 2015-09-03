import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		int deviceNum;
		int traceNum;
		int currentTime;
		int startTime;
		int endTime;
		int counter;
		Trace[] traces;
		Trace[] rTraces;
		ArrayList<Trace> relTraces = new ArrayList<Trace>();
		Device[] devices;
		String[] set;
		String instructions;
		LinkedList<String> solutions = new LinkedList<String>();
		LinkedList<Device> queue = new LinkedList<Device>();
		Scanner sc = null;
		
		set = FileParser.Read(args[0]);	//read numbers from file;
		StringBuffer s = new StringBuffer();
		
		for(int i = 0; i < set.length; i++){	//concatenate the inputs
			s.append(set[i] + " ");
		}
		
		instructions = s.toString();
		sc = new Scanner(instructions);	//open scanner for the input
		deviceNum = sc.nextInt();	//read num of devices
		traceNum = sc.nextInt();	//read num of traces
		
		devices = new Device[deviceNum];
		traces = new Trace[traceNum];
		
		for(int i = 0; i < deviceNum; i++){	//creates devices
			Device d0 = new Device(i);
			devices[i] = d0;
		}
		
		for(int i = 0; i < traceNum; i++){	//creates traces
			Trace t0 = new Trace(set[i+1], 
					devices[sc.nextInt()], devices[sc.nextInt()], sc.nextInt());
			traces[i] = t0;
		}
		
		Query q = new Query(set[set.length - 1], 
				devices[sc.nextInt()], devices[sc.nextInt()], sc.nextInt(), sc.nextInt());
		sc.close();
		
		startTime = q.getStartTime();
		endTime = q.getEndTime();
		
		for(int i = 0; i < traces.length; i++){		//retrieve relevant traces within timeframe
			if(startTime <= traces[i].getTContact() && endTime >= traces[i].getTContact()){
				relTraces.add(traces[i]);
			}
		}
		rTraces = relTraces.toArray(new Trace[relTraces.size()]);
		currentTime = startTime;
		Device start = q.getDevice1();
		Device d = start;
		d.setMessage();
		d.setTContact(currentTime);
		queue.add(d);
		
		while(!queue.isEmpty()){	//breadth-first search (dijkstra's)
			d = queue.poll();
			currentTime = d.getTContact();
			
			if(d == q.getDevice2())
				break;
			
			for(int i = 0; i < rTraces.length; i++){
				Device found;
				if(d == rTraces[i].getDevice1()){
					found = rTraces[i].getDevice2();
					if(!(currentTime <= rTraces[i].getTContact()))
						continue;
					else{
						if(!found.hasMessage()){
							found.setMessage();
							found.setPreviousD(d, rTraces[i].getTrace(), rTraces[i].getTContact());
							queue.add(found);
						}
					}
				}
				else if(d == rTraces[i].getDevice2()){
					found = rTraces[i].getDevice1();
					if(!(currentTime <= rTraces[i].getTContact()))
						continue;
					else{
						if(!found.hasMessage()){
							found.setMessage();
							found.setPreviousD(d, rTraces[i].getTrace(), rTraces[i].getTContact());
							queue.add(found);
						}
					}
				}
			}
		
		}
		
		if(d != q.getDevice2()){
			System.out.println("0");
		}
		
		else{
			while(d != start){
				solutions.addFirst(d.getTraceString());
				d = d.getPreviousD();
			}
			counter = solutions.size();
			String[] lines = new String[solutions.size()];
			
			for(int i = 0; i < lines.length; i++){
				lines[i] = solutions.poll() + " ";
			}
			
			FileParser.Write(counter, lines);
		}
		
	}
}
