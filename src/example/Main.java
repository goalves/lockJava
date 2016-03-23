package example;

import java.util.ArrayList;
import lockPDP.Mutex;


public class Main {
	public static int x = 0;
	
	public static void main(String[] args) {
		Mutex mutex;
		mutex = new Mutex();
		
		ArrayList<ThreadExample> threads;
		threads = new ArrayList<ThreadExample>();
		
		threads.add(new ThreadExample(mutex));
		threads.add(new ThreadExample(mutex));
		threads.add(new ThreadExample(mutex));
		
		System.out.println("Lista de Threads por ID:");
		for(Thread t : threads)
		{
			System.out.print(t.getId() + " ");
		}
		System.out.println();
		for(ThreadExample t : threads)
		{
			t.start();
		}
		
	}
}
