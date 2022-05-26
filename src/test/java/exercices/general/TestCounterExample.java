package exercices.general;

import org.junit.Test;

public class TestCounterExample {

	@Test
	public void contextLoads() {
		
		int array1[] = {4,2,4,5};
		
		System.out.println(find_min(array1));
		
		int array2[] = {10,567,99,456};
		
		System.out.println(find_min(array2));
		
		
	}
	
	int find_min(int[] A) {
		int ans  =0;
		for (int i = 1; i < A.length; i++) {
			if(ans > A[i]) {
				ans = A[i];
			}			
		}
		
		return ans;
	}

	


	
	
}

