public class JobLList implements WaitingListADT<JobNode> {
	JobNode head = null;
	int size = 0;
	
	//constructor
	public JobLList(){
	}
	
	//constructor
	public JobLList(JobLList toCopy){
		head = new JobNode(toCopy.getNode(0));
		size = toCopy.size();
		JobNode prev = head;

		for (int i = 1; i < size; i++){
			prev.setNext(new JobNode(toCopy.getNode(i)));
			prev = prev.getNext();
		}
	}
	
	public void schedule(JobNode newJob) {
		size++;
		//if the list is null
		if(head == null) {
			head = newJob;
			return;
		}
		
		//0-0-0 -> 1-0-0-0
		if(head.getPriority() < newJob.getPriority()) {
			newJob.setNext(head);
			head = newJob;
			return;
		}
		//1-... -> 1-...-0
		if(newJob.getPriority() == 0) {
			JobNode temp = head;
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(newJob);
			return;
		}
		
		JobNode prev = head;
		JobNode next = head.getNext();
		//1-1-0-0 -> 1-1-1-0-0
		while(next != null) {
			if(newJob.getPriority() > next.getPriority()) {
				newJob.setNext(next);
				prev.setNext(newJob);
				return;
			}
			prev = next;
			next = prev.getNext();
		}
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	//clean the jobList
	public int clean(float cleaningTime) {
		int numCleanedJobs = 0;
		JobNode prev = null;
		JobNode elemToClean = head;
		//prev -> elemToClean -> elemToClean.getNext()
		while(elemToClean != null) {
			if (elemToClean.getArrivalTime() + elemToClean.getTimeToLive() < cleaningTime && elemToClean != null) {
				if (elemToClean == head) {
					head = head.getNext();
					elemToClean = head;
				}
				else {
					elemToClean = elemToClean.getNext();
					prev.setNext(elemToClean);
				}
				size--;
				numCleanedJobs++;
			}
			prev = elemToClean;
			elemToClean = elemToClean.getNext();
		}
		return numCleanedJobs;
	}
	//clear the jobList
	public void clear() {
		head = null;
		size = 0;
	}
	
	public WaitingListADT<JobNode> duplicate() {
		return new JobLList(this);
	}
	
	public String toString() {
		String str = new String();
		
		str += ("Job List is empty: " + this.isEmpty() + "\n");
		str += ("The size is " + size + " job" + ((size == 1)?"":"s") + "\n");
		
		JobNode next = head;
		for (int i = 0; i < size; i++) {
			str += ("job #" + next.getJobId() + " : " + next.getDescription() + " (UID " + next.getUserID() + ") " + next.getPriority() + "\n");
			next = next.getNext();
		}
		return str;
	}
	
	public JobNode getNode(int node) {
		if (node >= size) {
			return null;
		}
		
		JobNode prev = head;
		JobNode next = head.getNext();
		
		for (int i = 1; i <= node; i++) {
			prev = next;
			next = prev.getNext();
		}
		
		return prev;
	}
}