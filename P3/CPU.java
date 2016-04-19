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
	}}