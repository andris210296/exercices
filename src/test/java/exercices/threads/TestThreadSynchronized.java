package exercices.threads;

import org.junit.Test;

public class TestThreadSynchronized {

	@Test
	public void testThreadSynchronized() throws InterruptedException {
		
		int[] array = {1,2,3,4,5};
		MinhaThreadSoma minhaThreadSoma1 = new MinhaThreadSoma("Thread 1", array);
		MinhaThreadSoma minhaThreadSoma2 = new MinhaThreadSoma("Thread 2", array);
		
		Thread.sleep(100000);
	

	}
		
	
	public static class Calculadora {

		private int soma;
		
		public synchronized int somaArray(int[] array)  {
			soma = 0;
			
			for (int i : array) {
				soma += i;
				
				System.out.println("Executando a soma "+ Thread.currentThread().getName() +
						" somando o valor " + i + " com o total de "+soma);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return soma;
		}

	}
	
	public static class MinhaThreadSoma implements Runnable{
		private String nome;
		private int[] nums;
		private static Calculadora calculadora = new Calculadora();
		
		public MinhaThreadSoma(String nome, int[] nums) {
			this.nome = nome;
			this.nums = nums;
			new Thread(this, nome).start();

		}

		@Override
		public void run() {
			
			System.out.println(this.nome + " iniciada");
			
			int soma = calculadora.somaArray(this.nums);
			
			System.out.println("Resultado da thread" + this.nome + ": " + soma);
			
			System.out.println(this.nome + " terminada");
			
		}
		
		
	}

}
