package exercices.threads;

import org.junit.Test;

public class TestThread {

	@Test
	public void testThread() throws InterruptedException {

		MinhaThread thread1 = new MinhaThread("Thread 1", 1000);
		MinhaThread thread2 = new MinhaThread("Thread 2", 1);

		MinhaRunnable runnable1 = new MinhaRunnable("Runnable 1", 1000);
		Thread t1 = new Thread(runnable1);
		t1.start();

		MinhaRunnable runnable2 = new MinhaRunnable("Runnable 2", 1000);
		Thread t2 = new Thread(runnable2);
		t2.start();

		/*  Forma de verificar se as threads terminaram...
		while (t1.isAlive() || t2.isAlive()) {
			Thread.sleep(200);
		}
		*/
		
		// verifica se t1 terminou ou deu os 200 milissegundos
		t1.join(200);
		t2.join(200);
		
		System.out.println("Terminou a execução");

	}
	
	@Test
	public void testThreadPrioridade() throws InterruptedException {

		MinhaRunnable runnable1 = new MinhaRunnable("Runnable 1", 1000);
		Thread t1 = new Thread(runnable1);
		t1.start();

		MinhaRunnable runnable2 = new MinhaRunnable("Runnable 2", 1000);
		Thread t2 = new Thread(runnable2);
		t2.start();
		
		t1.setPriority(5);
		t1.setPriority(2);

		t1.join(200);
		t2.join(Thread.MAX_PRIORITY);
		
		System.out.println("Terminou a execução");

	}

	public class MinhaThread extends Thread {

		private String nome;
		private int tempo;

		public MinhaThread(String nome, int tempo) {
			this.nome = nome;
			this.tempo = tempo;
			start();
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < 6; i++) {
					System.out.println(nome + " contador " + i);
					Thread.sleep(tempo);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();

			}
			System.out.println(nome + " terminou a execução");

		}

	}

	public class MinhaRunnable implements Runnable {

		private String nome;
		private int tempo;

		public MinhaRunnable(String nome, int tempo) {
			this.nome = nome;
			this.tempo = tempo;
			// Thread t1 = new Thread(this);
			// t1.start();
		}

		@Override
		public void run() {
			try {

				for (int i = 0; i < 6; i++) {
					System.out.println(nome + " contador " + i);
					Thread.sleep(tempo);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(nome + " terminou a execução");

		}

	}

}
