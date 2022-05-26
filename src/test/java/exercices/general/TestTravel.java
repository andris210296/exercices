package exercices.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class TestTravel {

	@Test
	public void contextLoads() {
		
		int array1[] = {2,1,1,3,2,1,1,3};
		
		//System.out.println(solution(array1));
		
		int array2[] = {7,5,2,7,2,7,4,7};
		
		//System.out.println(solution(array2));
		
		int array3[] = {7,3,7,3,1,3,4,1};
		
		System.out.println(solution(array3));
		
		
	}
	
	int solution(int[] A) {
		HashMap<Integer, List<Integer>> hashMap = new HashMap<Integer, List<Integer>>();
		
		for (int i = 0; i < A.length; i++) {
			if(hashMap.get(A[i]) == null) {
				
				List<Integer> list = new ArrayList<Integer>();
				list.add(i);
				
				hashMap.put(A[i], list);
			}else {
				hashMap.get(A[i]).add(i);
			}
		}
		
		
		int larger = 0;
		
		for (Integer i : hashMap.keySet()) {
			if(larger <= hashMap.get(i).get(0)) {
				larger = hashMap.get(i).get(0);
			}
		}
		
		return larger;
	}

	


	
	
}

