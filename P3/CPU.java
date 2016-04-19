public class CPU {
	private Gui gui;
	private Statistics statistics;
	private Queue cpuQueue;
	private Process runningProcess;
	private long cpuUsageLimit;
	
	public CPU(Gui gui, Statistics statistics, Queue cpuQueue, long cpuUsageLimit) {
		this.gui = gui;
		this.statistics = statistics;
		this.cpuQueue = cpuQueue;
		this.cpuUsageLimit = cpuUsageLimit;
	}
	
	public Event insertProcess(Process p, long clock) {
		cpuQueue.insert(p);
		if(runningProcess == null) {
			return switchProcess(clock);
		} else {
			return null;
		}
	}
	
	// Put a process from the cpuqueue into the CPU
	public Event switchProcess(long clock) {
		if (runningProcess != null && !cpuQueue.isEmpty()) {
			runningProcess.leftCPU(clock);
			cpuQueue.insert(runningProcess);
			runningProcess = (Process)cpuQueue.removeNext();
			runningProcess.enteredCPU(clock);
			gui.setCpuActive(runningProcess);
			statistics.nofSwitchedProcesses++;
		} else {
			if(!cpuQueue.isEmpty()) {
				runningProcess = (Process)cpuQueue.removeNext();
				runningProcess.enteredCPU(clock);
				gui.setCpuActive(runningProcess);
			}
		}
		if (runningProcess != null) {
			return runningProcess.getNextEvent(clock, cpuUsageLimit);
		} else {
			return null;
		}
	}

	// Process leaves the CPU
	public Event activeProcessLeft(long clock) {
		runningProcess = null;
		gui.setCpuActive(null);
		return switchProcess(clock);
	}

	// Get the time since the last time it was called
	public void timePassed(long time) {
		if (runningProcess != null) {
			runningProcess.cpuTimePassed(time);
			statistics.totCPUProcessTime += time;
		}
		statistics.totalCPUQueueTime += cpuQueue.getQueueLength()*time;

		statistics.largestCpuQueueLength = Math.max(statistics.largestCpuQueueLength, cpuQueue.getQueueLength());
	}

	public Process getRunningProcess() {
		return runningProcess;
	}

}