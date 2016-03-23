package example;

import lockPDP.Mutex;

public class ThreadExample extends Thread {
	static Mutex mutex;
	
	public ThreadExample(Mutex m)
	{
		ThreadExample.mutex = m;
	}
	
    public void run() {   
        try {
			mutex.lock();
			Main.x = Main.x+1;
			//System.out.println("Thread " + Thread.currentThread().getId() + " alterou o valor de X para: " + Main.x);
			mutex.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}