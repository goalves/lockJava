package example;

import lockPDP.Mutex;

public class ThreadExample extends Thread {
	/* Each thread have a instance of mutex (that is the same as the one created in the main class) */	
	static Mutex mutex;
	
	/* The constructor of the ThreadExample */
	public ThreadExample(Mutex m)
	{
		ThreadExample.mutex = m;
	}
	

	/* The run method of the thread is the code the it is going to execute,
	 * What this thread do is to request for the mutex lock (using our Mutex class)
	 * updates the value of a variable X and unlocks the mutex;
 	 */
    public void run() {   
        try {
			mutex.lock();
			Main.x = Main.x+1;
			System.out.println("Thread " + Thread.currentThread().getId() + " changed X value to: " + Main.x);
			mutex.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
    }
}
