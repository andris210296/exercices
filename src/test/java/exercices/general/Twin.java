package exercices.general;

import java.util.HashMap;

import org.junit.Test;

public class Twin {

	@Test
	public void contextLoads() {

		System.out.println(solution("Silent", "ListEn"));
		
		System.out.println(solution("Silenty", "Listen"));
		
		System.out.println(solution("Silent", "Listeny"));
		
		System.out.println(solution("Silent", "Listenyl"));

	}

	boolean solution(String a, String b) {

		String aLower = a.toLowerCase();
		String bLower = b.toLowerCase();

		String[] aSplit = aLower.split("");
		String[] bSplit = bLower.split("");

		HashMap<String, Integer> hashMapA = new HashMap<String, Integer>();

		for (int i = 0; i < aSplit.length; i++) {
			if (hashMapA.get(aSplit[i]) == null) {
				
				hashMapA.put(aSplit[i], 1);
			} else {
				
				int value = hashMapA.get(aSplit[i]);				
				 value += 1;
				 hashMapA.put(aSplit[i], value);
				 
			}
		}

		HashMap<String, Integer> hashMapB = new HashMap<String, Integer>();

		for (int i = 0; i < bSplit.length; i++) {
			if (hashMapB.get(bSplit[i]) == null) {
				
				hashMapB.put(bSplit[i], 1);
			} else {
				
				int value = hashMapB.get(bSplit[i]);				
				 value += 1;
				 hashMapB.put(bSplit[i], value);
				 
			}
		}

		for (String i : hashMapA.keySet()) {
			
			if(hashMapA.keySet().size() != hashMapB.keySet().size()) {
				return false;
			}
			
			if (hashMapA.get(i) != hashMapB.get(i)) {
				return false;
			}
		}

		return true;
	}

}
