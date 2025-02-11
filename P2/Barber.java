/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.hi
 */
public class Barber extends Thread {
	
	private CustomerQueue customerQueue;
	private boolean threadIsRunning;
	private Gui gui;
	private int pos;

	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.customerQueue = queue;
		this.gui = gui;
		this.pos = pos;
		this.threadIsRunning = false;
	}

	public void barber(){
		gui.fillBarberChair(this.pos, customerQueue.getCustomer());
		try {
			sleep(Globals.barberWork);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		doneBarbering();
	}

	public void doneBarbering(){

		gui.emptyBarberChair(this.pos);
		gui.barberIsSleeping(this.pos);
		try {
			sleep(Globals.barberSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		doneSleeping();

	}

	public void doneSleeping(){

		gui.barberIsAwake(this.pos);

	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		threadIsRunning = true;
		start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		threadIsRunning = false;
	}



	@Override
	public void run() {
		while (threadIsRunning) {
			barber();
			gui.println("Perform barbering");
		}
	}
}

