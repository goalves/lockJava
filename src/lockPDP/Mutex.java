package lockPDP;

import java.util.ArrayList;

public class Mutex {
	/* Status of the current mutex
		-1 : free
		other values : is locked by the current thread that have the id stored in the value	
	 */
	private Long status;
	
	/* Array of threads waiting on the current mutex */
	private ArrayList<Thread> threadIDList;
	

	/* The constructor sets the default status of the lock to free */
	public Mutex()
	{
		this.status = (long)-1;
		this.threadIDList = new ArrayList<Thread>();
	}
	/**
	 * Locks the current mutex; If some thread already have the lock the calling thread 
	 * call's the wait method and change it's state to dormant. (Can only be awakened-up by a notify on its object)
	 * In order to make a FIFO, we make a monitor(synchronized) of the current entrant thread.
	 */
	public void lock() throws InterruptedException{
		System.out.println("Thread " + Thread.currentThread().getId() + " asked for lock");
		synchronized(Thread.currentThread())
		{
			// If there already is a thread on the current lock
			if(status > 0)
			{
				// This thread begins waiting for someone to notify that the lock is free
				System.out.println("Thread " + Thread.currentThread().getId() + " is waiting");
				threadIDList.add(Thread.currentThread());
				Thread.currentThread().wait();	
			}
		}
		// Change the state of the lock to the current thread (if it's free or has been freed
		status = Thread.currentThread().getId();	
		return;
	}
	/**
	 * Unlocks the current mutex, only by the Thread that locked it.
	 * The Synchronized method guarantees that only one thread will be in here, this avoid concurrent entrance.
	 * After releasing the lock, the 1st Thread in the list is notified to continue its flow.
	 */
	public synchronized void unlock(){
		if (status.longValue() == Thread.currentThread().getId())
			{
				//When unlocking we need to se the value of the lock to free (releasing the lock)
				status = (long) -1;
				System.out.println("Thread " + Thread.currentThread().getId() + " is notifying other threads ");
				if(!threadIDList.isEmpty())
					synchronized (threadIDList.get(0))
					{
						//Remove the first thread from the waiting list and notify it (the removed thread) that the lock is free
						Thread temp = threadIDList.get(0);
						threadIDList.remove(0);
						temp.notify();
						
					}
			}
		return;
	}

	/* Prints usually make the code stop working because we are looking for the 
	 * list and adding data to it at the same time,
	 * we need a lock to implement this lock feature. (funny hunh?)

	public void printThreadList()
	{	 
		System.out.println("Thread List:");
		for(Thread t : threadIDList)
		{
		System.out.print(t.getId() + " ");
		}
		System.out.println(); 
	}
	*/
	
}


