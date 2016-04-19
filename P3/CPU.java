public class CPU {
	private Gui gui;
	private Statistics statistics;
	private Queue cpuQueue;
	private Process runningProcess;
	private int cpuUsageLimit;
	
	public CPU(Gui gui, Statistics statistics, Queue cpuQueue, int cpuUsageLimit) {
		this.gui = gui;
		this.statistics = statistics;
		this.cpuQueue = cpuQueue;
		this.cpuUsageLimit = cpuUsageLimit;
	}
	
	public Event insertProcess(Process p, int clock) {
		cpuQueue.insert(p);
		if(runningProcess == null) {
			return switchProcess(clock);
		} else {
			return null;
		}
	}
	
	// Put a process from the cpuqueue into the CPU
	public Event switchProcess(int clock) {
		if (runningProcess != null && !cpuQueue.isEmpty()) {
			runningProcess.leftCpu(clock);
			cpuQueue.insert(runningProcess);
			runningProcess = (Process)cpuQueue.removeNext();
			runningProcess.enteredCpu(clock);
			gui.setCpuActive(runningProcess);
			statistics.nofProcessSwitches++;
		} else {
			if(!cpuQueue.isEmpty()) {
				runningProcess = (Process)cpuQueue.removeNext();
				runningProcess.enteredCpu(clock);
				gui.setCpuActive(runningProcess);
			}
		}
		if (runningProcess != null) {
			return runningProcess.getNextEvent(clock, maxCpuTime);
		} else {
			return null;
		}

}