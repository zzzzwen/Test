 // Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2016 hash code: XAbyuzR78fXeaMHBdLan (do NOT delete this line)

public class EmergencyRoom {
	private TreeMap<String, Integer> map;
	private TreeMap<String, Integer> seq;
	private int idx;
	private Comparator<String> lvlComparator = new Comparator<String>(){
		@Override
		public int compare(String name1, String name2){
			int diffInVal= map.get(name2)-map.get(name1);
			int diffInSeq= seq.get(name1)-seq.get(name2);
			if (diffInVal==0){
				return diffInSeq;
			}
			return diffInVal;
		}
	};
	private TreeSet<String> queue;

	public EmergencyRoom() {
		map = new TreeMap<String,Integer>();
		seq = new TreeMap<String,Integer>();
		queue = new TreeSet<String>(lvlComparator);
		idx=1;
	}

	void ArriveAtHospital(String patientName, int emergencyLvl) {
		map.put(patientName, emergencyLvl);
		seq.put(patientName, idx++);
		queue.add(patientName);
	}

	void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
		map.put(patientName, incEmergencyLvl + map.get(patientName));
		queue.remove(patientName);
		queue.add(patientName);
	}

	void Treat(String patientName) {
		queue.remove(patientName);
		map.remove(patientName);
		seq.remove(patientName);
	}

	String Query() {
		String ans = "The emergency room is empty";
		if (queue.size()!=0){
			ans = queue.first();
		}		
		return ans;
	}

	void run() throws Exception {
		// do not alter this method
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
		while (numCMD-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			switch (command) {
				case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
				case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
				case 2: Treat(st.nextToken()); break;
				case 3: pr.println(Query()); break;
			}
		}
		pr.close();
	}

	public static void main(String[] args) throws Exception {
		EmergencyRoom ps1 = new EmergencyRoom();
		ps1.run();
	}
}
