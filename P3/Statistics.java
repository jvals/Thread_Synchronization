/**
 * This class contains a lot of public variables that can be updated
 * by other classes during a simulation, to collect information about
 * the run.
 */
public class Statistics
{
	/** The number of processes that have exited the system */
	public long nofCompletedProcesses = 0;
	/** The number of processes that have entered the system */
	public long nofCreatedProcesses = 0;


	//Added variables---------------------------------------------
	public long nofSwitchedProcesses = 0;

	public long nofIOReqests = 0;

	public double avgThroughput = 0;

	public long largestCpuQueueLength = 0;
	public long totalCPUQueueTime = 0;
	public long totalIoQueueTime = 0;
	public long LargestIoQueueLength = 0;

	//Memory dependent vars
	public double avgMemWaitTime = 0;	
	public long totMemWaitTime = 0;

	// CPU dependent vars
	public long totCPUProcessTime = 0;
	public double avgProcessTimeInSystem = 0;
	public long totProcessTimeInSystem = 0;
	public long nonBusyCPUTime = 0;
	public double cpuUtilization = 0;
	public double percentAvailableTime = 0;
	//--------------------------------------------------------------

	/** The total time that all completed processes have spent waiting for memory */
	public long totalTimeSpentWaitingForMemory = 0;
	/** The time-weighted length of the memory queue, divide this number by the total time to get average queue length */
	public long memoryQueueLengthTime = 0;
	/** The largest memory queue length that has occured */
	public long memoryQueueLargestLength = 0;
    
	/**
	 * Prints out a report summarizing all collected data about the simulation.
	 * @param simulationLength	The number of milliseconds that the simulation covered.
	 */
	public void printReport(long simulationLength) {

		avgThroughput = (double)nofCompletedProcesses/simulationLength;
		avgMemWaitTime = (double)totMemWaitTime/simulationLength;
		nonBusyCPUTime = simulationLength - totCPUProcessTime;
		cpuUtilization = (double) totCPUProcessTime/simulationLength;
		percentAvailableTime = (double) (nonBusyCPUTime/simulationLength);

		System.out.println();
		System.out.println("Simulation statistics:");
		System.out.println();
		System.out.println("Number of completed processes:                                "+nofCompletedProcesses);
		System.out.println("Number of created processes:                                  "+nofCreatedProcesses);
		System.out.println();
		System.out.println("Largest occuring memory queue length:                         "+memoryQueueLargestLength);
		System.out.println("Average memory queue length:                                  "+(float)memoryQueueLengthTime/simulationLength);
		if(nofCompletedProcesses > 0) {
			System.out.println("Average # of times a process has been placed in memory queue: "+1);
			System.out.println("Average time spent waiting for memory per process:            "+
				totalTimeSpentWaitingForMemory/nofCompletedProcesses+" ms");
		}
	}
}
