package exercices.threads;

import org.junit.Test;

public class TestThreadWaitNotify {

	@Test
	public void testThreadWaitNotify() throws InterruptedException {
		
		//https://www.youtube.com/watch?v=FxgjNTVyEP0
		
		TiqueTaque tt = new TiqueTaque();
		ThreadTiqueTaque tique = new ThreadTiqueTaque("Tique", tt);
		ThreadTiqueTaque taque = new ThreadTiqueTaque("Taque", tt);

		
		tique.thread.join();
		taque.thread.join();


	}

	public class TiqueTaque{

		boolean tique;

		synchronized void tique(boolean estaExecutando) {
			if(!estaExecutando) {
				tique = true;
				notify();
				return;
			}

			System.out.print("Tique ");

			tique = true;

			notify();


			try {
				while(tique) {
					wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		synchronized void taque(boolean estaExecutando) {
			if(!estaExecutando) {
				tique = false;
				notify();
				return;
			}

			System.out.println("Taque");

			tique = false;

			notify();


			try {
				while(!tique) {
					wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	public class ThreadTiqueTaque implements Runnable{
		
		TiqueTaque tiqueTaque;
		Thread thread;
		
		final int NUM = 5;
		
		public ThreadTiqueTaque(String nome, TiqueTaque tt) {
			this.tiqueTaque = tt;
			thread = new Thread(this, nome);
			thread.start();
		}

		@Override
		public void run() {
			if(thread.getName().equalsIgnoreCase("Tique")) {
				for(int i = 0; i< NUM; i++) {
					tiqueTaque.tique(true);
				}
				tiqueTaque.tique(false);				
			}else {
				for(int i = 0; i< NUM; i++) {
					tiqueTaque.taque(true);
				}
				tiqueTaque.taque(false);
				
			}
			
		}
		
	}

}