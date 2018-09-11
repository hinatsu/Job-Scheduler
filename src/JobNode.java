public class JobNode {
	private int jobId;
	private float arrivalTime;
	private int userID;
	private int priority;
	private int timeToLive;
	private String description;
	private JobNode next = null;
	private static int jobCount = 0;
	
	//constructor
	public JobNode() {
		jobId = ++jobCount;
	}
	//constructor
	public JobNode(float arrivalTime, int userID, int priority, int ttl, String description){
		this();
		setVal(arrivalTime, userID, priority, ttl, description);
	}
	//constructor
	public JobNode(JobNode toCopy){
		this.jobId = toCopy.getJobId();
		setVal(toCopy.getArrivalTime(), toCopy.getUserID(), toCopy.getPriority(), toCopy.getTimeToLive(), toCopy.getDescription());
		this.next = toCopy.getNext();
	}
	
	public void setVal(float arrivalTime, int userID, int priority, int ttl, String description){
		this.arrivalTime = arrivalTime;
		this.userID = userID;
		this.priority = priority;
		this.timeToLive = ttl;
		this.description = description;
	}
	
	public int getJobId(){
		return jobId;
	}
	
	public float getArrivalTime(){
		return arrivalTime;
	}
	
	public int getUserID(){
		return userID;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public int getTimeToLive(){
		return timeToLive;
	}
	
	public String getDescription(){
		return description;
	}
	
	public JobNode getNext(){
		return next;
	}
	
	public void setNext(JobNode next){
		this.next = next;
	}
	
	public JobNode copy(){
		return new JobNode(this);
	}
}