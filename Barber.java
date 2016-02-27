/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.hi
 */
public class Barber {
	
	private CustomerQueue customerQueue;
	private boolean threadIsRunning;
	private Gui gui;
	private int pos;
	private boolean isSleeping;
	private boolean isBarbering;
	private boolean isWaiting;

	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.customerQueue = queue;
		this.gui = gui;
		this.pos = pos;
		isWaiting = true;
		isSleeping = false;
		isBarbering = false;
	}

	public void barber(){

		isBarbering = true;
		isSleeping = false;
		isWaiting = false;

	}

	public void doneBarbering(){

		isSleeping = true;
		isBarbering = false;
		isWaiting = false;
		gui.barberIsSleeping();

	}

	public void doneSleeping(){

		isWaiting = true;
		isSleeping = false;
		isBarbering = false;

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
			try {
				int minimum = Globals.MIN_BARBER_SLEEP;
				int maximum = Globals.MAX_DOORMAN_SLEEP;
				sleep(minimum+(int)(Math.random()*(maximum-minimum+1)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			customerQueue.addNewCustomer();
			gui.println("The doorman has added a new customer to the queue.");
		}
	}
}

