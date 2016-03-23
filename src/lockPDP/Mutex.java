package lockPDP;

import java.util.ArrayList;

public class Mutex {
	/* Status of the current mutex */
	private Long status;
	
	/* Array of threads waiting on the current mutex */
	private ArrayList<Thread> threadIDList;
	
	public Mutex()
	{
		this.status = (long)-1;
		this.threadIDList = new ArrayList<Thread>();
	}
	/**
	 * Locks the current mutex, if some thread already have the lock, the calling thread call's wait method.
	 * In order to make a FIFO, we make a monitor(synchronized) of the current entrant thread.
	 */
	public void lock() throws InterruptedException{
		System.out.println("Thread " + Thread.currentThread().getId() + " pediu o lock");
		synchronized(Thread.currentThread())
		{
			//debug
			if(status > 0)
			{
				System.out.println("Thread " + Thread.currentThread().getId() + " está em wait");
				threadIDList.add(Thread.currentThread());
				Thread.currentThread().wait();	
			}
		}
		status = Thread.currentThread().getId();	
		return;
	}
	/**
	 * Unlocks the current mutex, only by the Thread that locked it.
	 * The Synchronized method to guarantee only one thread in here, this avoid concurrent entrance who might bug the method.
	 * After freeing the lock, the 1st Thread in the list is notified to continue his flow.
	 */
	public synchronized void unlock(){
		if (status.longValue() == Thread.currentThread().getId())
			{
				status = (long) -1;
				System.out.println("Thread " + Thread.currentThread().getId() + " está notificando as outras threads ");
				if(!threadIDList.isEmpty())
					synchronized (threadIDList.get(0))
					{
						/* Prints usually make the code stop working because we are looking for the 
						 * list and adding data to it at the same time,
						 * we need a lock to implement this lock feature. (funny hunh?)
						 * 
						 * System.out.println("Lista de Threads:");
						 * for(Thread t : threadIDList)
						 * {
						 *	System.out.print(t.getId() + " ");
						 * }
						 * System.out.println();
						 * */
						Thread temp = threadIDList.get(0);
						threadIDList.remove(0);
						temp.notify();
						
					}
			}
		return;
	}
	
}
