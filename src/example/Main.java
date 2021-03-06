package example;

import java.util.ArrayList;
import lockPDP.Mutex;


public class Main {
	public static int x = 0;
	
	public static void main(String[] args) {
		// Creates a new mutex
		Mutex mutex;
		mutex = new Mutex();
		

		// Creates the arary of threads to run the example
		ArrayList<ThreadExample> threads;
		threads = new ArrayList<ThreadExample>();
		
		threads.add(new ThreadExample(mutex));
		threads.add(new ThreadExample(mutex));
		threads.add(new ThreadExample(mutex));
		
		System.out.println("Thread List (Using ID's):");
		for(Thread t : threads)
		{
			System.out.print(t.getId() + " ");
		}

		// Run all the threads
		System.out.println();
		for(ThreadExample t : threads)
		{
			t.start();
		}
		
	}
}
