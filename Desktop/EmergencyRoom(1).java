
// Copy paste this Java Template and save it as "EmergencyRoom.java"
import java.util.*;
import java.io.*;

// write your matric number here: A0142325R
// write your name here: Li Xiaowei
// write list of collaborators here:
// year 2016 hash code: XAbyuzR78fXeaMHBdLan (do NOT delete this line)

public class EmergencyRoom {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
	Comparator<Patient> comparator=new PatientComparator();
	PriorityQueue<Patient> queue=new PriorityQueue<Patient>(10,comparator);
	HashMap<String,Patient> map=new HashMap<String,Patient>();

  public EmergencyRoom() {
    // Write necessary code during construction
    //
    // write your answer here


  }

  private void ArriveAtHospital(String patientName, int emergencyLvl) {
    // You have to insert the information (patientName, emergencyLvl)
    // into your chosen data structure
    //
    // write your answer here

    Patient pt=new Patient(patientName,emergencyLvl);
    queue.add(pt);
    map.put(patientName,pt);
  }

  private void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
    // You have to update the emergencyLvl of patientName to
    // emergencyLvl += incEmergencyLvl
    // and modify your chosen data structure (if needed)
    //
    // write your answer here
    Patient pt=map.get(patientName);
    pt.updateLvl(incEmergencyLvl);
    queue.remove(pt);
    queue.add(pt);

  }

  private void Treat(String patientName) {
    // This patientName is treated by the doctor
    // remove him/her from your chosen data structure
    //
    // write your answer here
    Patient pt=map.get(patientName);
    queue.remove(pt);


  }

  private String Query() {
    String ans = "The emergency room is empty";
    if(queue.isEmpty())
    	return ans;
    else
    	return queue.peek().getPatientName();
    // You have to report the name of the patient that the doctor
    // has to give the most attention to currently. If there is no more patient to
    // be taken care of, return a String "The emergency room is empty"
    //
    // write your answer here
  }

  public void run() throws Exception {
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
  //private void print(){
	//  for(Patient p:queue)
		//  System.out.println(p.getPatientName()+" "+p.getPatientIndex());
  //}

  public static void main(String[] args) throws Exception {
    // do not alter this method
    EmergencyRoom ps1 = new EmergencyRoom();
    ps1.run();
  }
}
class Patient{
	private String name;
	private int index;
	private int order;
	public static int orderPublic=0;
	public Patient(String name,int index){
		this.name=name;
		this.index=index;
		orderPublic++;
		order=orderPublic;
	}
	public void updateLvl(int increaseAmt){
		index+=increaseAmt;
	}
	public String getPatientName(){
		return name;
	}
	public int getPatientIndex(){
		return index;
	}
	public int getOrder(){
		return order;
	}
}
class PatientComparator implements Comparator<Patient>{
	public int compare(Patient x,Patient y){
		if( x.getPatientIndex()-y.getPatientIndex()>0)
			return -1;
		else if(x.getPatientIndex()-y.getPatientIndex()<0)
			return 1;
		else if(x.getOrder()-y.getOrder()>0){
			//System.out.println(x.getOrder()+" "+y.getOrder());
		//System.out.println(x.getPatientName() +"over"+y.getPatientName()+"="+(x.getOrder()-y.getOrder()));	
			return 1;
		}
		else {
			//System.out.println(x.getOrder()+" "+y.getOrder());
			//System.out.println(x.getPatientName() +"over"+y.getPatientName()+"="+(x.getOrder()-y.getOrder()));	
			return -1; 

		}
		
			
	}
}
