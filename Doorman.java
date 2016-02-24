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
		// Incomplete
	}

	// Add more methods as needed
}
