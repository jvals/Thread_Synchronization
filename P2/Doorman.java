/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman extends Thread {
	private CustomerQueue customerQueue;
	private boolean threadIsRunning;
	private Gui gui;
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	public Doorman(CustomerQueue queue, Gui gui) {
		customerQueue = queue;
		threadIsRunning = false;
		this.gui = gui;
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		threadIsRunning = true;
		start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		threadIsRunning = false;
	}

	@Override
	public void run() {
		while (threadIsRunning) {
			try {
				int minimum = Globals.MIN_DOORMAN_SLEEP;
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
