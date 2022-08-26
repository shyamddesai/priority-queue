import java.util.ArrayList;
import java.util.HashMap;

public class ERPriorityQueue{

	public ArrayList<Patient>  patients;
	public HashMap<String,Integer>  nameToIndex;

	public ERPriorityQueue(){

		// Use a dummy node so that indexing starts at 1, not 0
		patients = new ArrayList<Patient>();
		patients.add(new Patient("dummy", 0.0) );
		nameToIndex  = new HashMap<String,Integer>();
	}

	private int parent(int i){ return i/2; }

	private int leftChild(int i){ return 2*i; }

	private int rightChild(int i){ return 2*i+1; }

    /*
    TODO: OPTIONAL
    TODO: Additional helper methods such as isLeaf(int i), isEmpty(), swap(int i, int j) could be useful for this assignment
     */
	private void swap(int i, int j) {
		//String tempName = patients.get(i).getName();
		//double tempPriority = patients.get(i).getPriority();
		int tempIndex = nameToIndex.get(patients.get(i).name);
		Patient tempPatient = patients.get(i);

		nameToIndex.replace(patients.get(i).name, nameToIndex.get(patients.get(j).name));
		patients.set(i, patients.get(j));
		//patients.get(i).name = patients.get(j).name;
		//patients.get(i).priority = patients.get(j).priority;

		nameToIndex.replace(patients.get(j).name, tempIndex);
		patients.set(j, tempPatient);
//		patients.get(j).name = tempName;
//		patients.get(j).priority = tempPriority;
	}
	
	private void adjustQueue(int index) {
		if(index>1 && index<patients.size() && patients.get(index).getPriority()<patients.get(parent(index)).getPriority()) {
			upHeap(index);
		} downHeap(index);
	}

	public boolean isEmpty() {
		return(patients.size()<=1); //Accounts for dummy value in the arraylist
	}

	public void upHeap(int i){
		// TODO: Implement your code here
		while (i>1 && patients.get(i).getPriority() < patients.get(parent(i)).getPriority()){
			swap(i, parent(i));
			i = parent(i);
		}
	}

	public void downHeap(int i){
        // TODO: Implement your code here
		int lastIndex = patients.size()-1; //To avoid array out of bounds exception
		while (leftChild(i) <= lastIndex){ //Check if there is a left child
			int child = leftChild(i);
					if(child < lastIndex) { //Check if there is a right sibling
						if(patients.get(child+1).getPriority() < patients.get(child).getPriority()) //Is right child < left child?
							child = child + 1; //If yes, then smaller child is right
					}
			if(patients.get(child).getPriority() < patients.get(i).getPriority()){ //Swap with child?
				swap(child , i);
				i = child;
			} else return;
		}
	}

	public boolean contains(String name){
        // TODO: Implement your code here & remove return statement
		return nameToIndex.containsKey(name);
	}

	public double getPriority(String name){
        // TODO: Implement your code here & remove return statement
        if(isEmpty()) return -1;
        return patients.get(nameToIndex.get(name)).getPriority();
	}

	public double getMinPriority(){
        // TODO: Implement your code here & remove return statement
		String MinPriority = peekMin();
		if(MinPriority != null) return nameToIndex.get(MinPriority);
        return -1;
	}

	public String removeMin(){
        // TODO: Implement your code here & remove return statement
		if(!isEmpty()) {
			String minPatientName = peekMin();
			remove(minPatientName);
			return minPatientName;
		} return null;
	}

	public String peekMin(){
        // TODO: Implement your code here & remove return statement
        if(!isEmpty()) {
        	return patients.get(1).getName();
        } return null;
	}

	/*
	 * There are two add methods.  The first assumes a specific priority.
	 * The second gives a default priority of Double.POSITIVE_INFINITY
	 *
	 * If the name is already there, then return false.
	 */
	public boolean add(String name, double priority){
        // TODO: Implement your code here & remove return statement
		int size = patients.size(); //Number of elements in heap
		if(!contains(name)) {
			patients.add(new Patient(name, priority));
			nameToIndex.put(name, size);
			upHeap(size);
			return true;
		} return false;
	}

	public boolean add(String name){
        // TODO: Implement your code here
		double priority = Double.POSITIVE_INFINITY;
		int size = patients.size();
		if(!contains(name)) {
			patients.add(new Patient(name, priority));
			nameToIndex.put(name, size);
			upHeap(size);
			return true;
		} return false;
	}

	public boolean remove(String name){
        // TODO: Implement your code here
		int size = patients.size()-1;
		if(contains(name)) {
			int index = nameToIndex.get(name);
			swap(index, size);
			patients.remove(size);
			nameToIndex.remove(name);
			adjustQueue(index);
			return true;
		} return false;
	}

	/*
	 *   If new priority is different from the current priority then change the priority
	 *   (and possibly modify the heap).
	 *   If the name is not there, return false
	 */
	public boolean changePriority(String name, double priority){
        // TODO: Implement your code here & remove return statement
		if(contains(name)) {
			int index = nameToIndex.get(name);
			patients.get(index).priority = priority;
			adjustQueue(index);
			return true;
		} return false;
	}

	public ArrayList<Patient> removeUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
		ArrayList<Patient> urgentPatients = new ArrayList<Patient>();
		if(!isEmpty()) {
			int size = patients.size();
			int i = 1;
			while(i<size) {
				if(patients.get(i).getPriority() <= threshold) {
					urgentPatients.add(patients.get(i));
					remove(patients.get(i).getName());
					size = size - 1; 
					i = 0; //Go back to the start of the arraylist since it has been modified
				} i++;
				adjustQueue(i);
			} return urgentPatients;
		} else return new ArrayList<Patient>();
	}

	public ArrayList<Patient> removeNonUrgentPatients(double threshold){
        // TODO: Implement your code here & remove return statement
	     //  what if heap is empty ?  return an empty list
        //  what if the return ArrayList is empty ?  return empty ArrayList
        //  return ArrayList in any order
		ArrayList<Patient> nonUrgentPatients = new ArrayList<Patient>();
		if(!isEmpty()) {
			int size = patients.size();
			int i = 1;
			while(i<size) {
				if(patients.get(i).getPriority() >= threshold) {
					nonUrgentPatients.add(patients.get(i));
					remove(patients.get(i).getName());
					size = size - 1; 
					i = 0; //Go back to the start of the arraylist since it has been modified
				} i++;
				adjustQueue(i);
			} return nonUrgentPatients;
		} else return new ArrayList<Patient>();
	}

	
	static class Patient{
		private String name;
		private double priority;

		Patient(String name,  double priority){
			this.name = name;
			this.priority = priority;
		}

		Patient(Patient otherPatient){
			this.name = otherPatient.name;
			this.priority = otherPatient.priority;
		}

		double getPriority() {
			return this.priority;
		}

		void setPriority(double priority) {
			this.priority = priority;
		}

		String getName() {
			return this.name;
		}

		@Override
		public String toString(){
			return this.name + " - " + this.priority;
		}

		public boolean equals(Object obj){
			if (!(obj instanceof  ERPriorityQueue.Patient)) return false;
			Patient otherPatient = (Patient) obj;
			return this.name.equals(otherPatient.name) && this.priority == otherPatient.priority;
		}
	}
}
