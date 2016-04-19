public class IO implements Constants {
	
	private Queue ioQueue;
	private Statistics statistics;
	private Gui gui;
    private Process runningProcess;
	private int avgIoDuration;
	
	public IO {
		this.ioQueue = ioQueue;
		this.statistics = statistics;
		this.gui = gui;
		this.runningProcess = runningProcess;
		this.avgIoDuration = avgIoDuration;
	}
	
	//Adds a process requesting an IO operation to the ioQueue
	public Event addIoRequest(Process ioRequest, int clock ) {
		ioQueue.insert(ioRequest);
		ioRequest.timeToNextIoOperation();
		return runIoOperation(clock);
		
	}
	
	//Runs the first IO operation in the queue, if the queue is nonempty and there are no processes currently running
	public Event runIoOperation(int clock) {
		if((runningProcess == null) && (!ioQueue.isEmpty())) {
			runningProcess = (Process)ioQueue.removeNext();
			runningProcess.enteredIo(clock);
			gui.setIoActive(runningProcess);
			statistics.nofIoRequest++;
			return new Event(END_IO, clock + avgIoDuration);
		} else {
			return null;
		}
	}
	
	//Remove the currently running process 
	public Process removeRunningProcess() {
		Process removedProcess = runningProcess;
		runningProcess = null;
		gui.setIoActive(null);
		return removedProcess;
	
	// Gets the time that has passed since the last time it was called
	public void timePassed(int time) {
		statistics.totalIoQueueTime += ioQueue.getQueueLength()*time;
		statistics.LargestIoQueueLength = max(statistics.LargestIOQueueLength, ioQueue.getQueueLength());
		}
	}	
}