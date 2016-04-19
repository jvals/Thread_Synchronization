import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	private LinkedBlockingQueue<Customer> customerQueue;
	private int queueLength;
	private Gui gui;
	private int nextSeat;

	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	public CustomerQueue(int queueLength, Gui gui) {
		// A blocking queue is a queue that support operations that wait
		// for the queue to be non-empty, or that wait for space to become
		// available. A LinkedBlockingQueue is an implementation of this
		// based on linked nodes.
		customerQueue = new LinkedBlockingQueue<>(queueLength);
		this.queueLength = queueLength;
		this.gui = gui;
		nextSeat = 0;
	}

    public synchronized void addNewCustomer() {
        Customer customer = new Customer(nextSeat);
        while (customerQueue.size() == queueLength) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
            customerQueue.put(customer);
            gui.fillLoungeChair(nextSeat++, customer);
			if (nextSeat >= queueLength) {
				nextSeat = 0;
			}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		notify();
    }

	public synchronized Customer getCustomer() {
		while(customerQueue.size() == 0) {
			// Waiting for customers
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Customer customer = customerQueue.poll();
		System.out.println(customer.getChairPosition());
		gui.emptyLoungeChair(customer.getChairPosition());
		notify();
		return customer;
	}

	// Add more methods as needed
}
