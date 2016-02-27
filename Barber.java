/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber {
	
	private CustomerQueue customerQueue;
	private boolean threadIsRunning;
	private Gui gui;
	private int pos;

	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		// Incomplete

		this.customerQueue = queue;
		this.gui = gui;
		this.pos = pos;

	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		// Incomplete
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		// Incomplete
	}

	// Add more methods as needed
}

