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
	}}	public Event insertProcess(Process p, int clock) {
		cpuQueue.insert(p);
		if(runningProcess == null) {
			return switchProcess(clock);
		} else {
			return null;
		}
	}