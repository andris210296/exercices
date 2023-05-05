package exercices.lambdaexpression;

import org.junit.Test;

public class LambdaExpression {
	
	@FunctionalInterface
	private interface InterfaceShow{
		void show();
	}
	
	@FunctionalInterface
	private interface InterfaceSum{
		Integer summ(Integer n1, Integer n2);
	}
	
	@Test	
	public void lambdaTest1() {	
		
		InterfaceShow interfaceShow = () ->	System.out.println("Showed!");		
		interfaceShow.show();
		
		
		InterfaceSum interfaceSum = (n1 , n2) -> {
			return n1 + n2;			
		};
		
		System.out.println(interfaceSum.summ(5,2));		
		
	}		
}
