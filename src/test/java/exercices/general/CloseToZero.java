package exercices.general;

import org.junit.Test;

public class CloseToZero {

	@Test
	public void contextLoads() {
		
		int[] test1 = {5,6,7,-6,-2-1,1};

		System.out.println(closestToZero(test1));
		
		int[] test2 = null;

		System.out.println(closestToZero(test2));
		
		int[] test3 = {};

		System.out.println(closestToZero(test3));

	}

	int closestToZero(int[] ints) {
		if(ints == null || ints.length == 0) {
			return 0;
		}
		
		int result = 0;
		
		for (int i = 0; i< ints.length; i++) {
			if(result == 0) {
				result = ints[i];
			}else if(ints[i] > 0 && ints[i] <= Math.abs(result)) {
				result = ints[i];
			} else if(ints[i] < 0 && - ints[i] < Math.abs(result)) {
				result = ints[i];
			}
		}
		
		return result;

		
	}

}
