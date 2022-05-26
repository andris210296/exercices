package exercices.general;

import java.util.*;

import org.junit.Test;

public class GemStones {

	@Test	
	public void isPangram() {
		
		List<String> strings = new ArrayList<String>();
		strings.add("abcdde");
		strings.add("baccd");
		strings.add("eeabg");
		
		System.out.println(gemStones(strings));
		
		
		
	}
	
	public static int gemStones(List<String> rocks) {
		
		HashMap<String, HashSet<Integer>> hashMap = new HashMap<String, HashSet<Integer>>();
		
		int sequence = 0;
		
		for (String string : rocks) {
			
			sequence +=1;
			
			for (char letter : string.toCharArray()) {
				
				String stringLetter = String.valueOf(letter);
				
				if(hashMap.get(stringLetter) == null) {
					
					HashSet<Integer> list = new HashSet<Integer>();
					list.add(sequence);
					
					hashMap.put(stringLetter, list);
				}
				else {
					hashMap.get(stringLetter).add(sequence);
				}
				
			}			
			
		}
		
		int qtd = 0;
		
		for(Map.Entry<String, HashSet<Integer>> entry : hashMap.entrySet()) {
			
			HashSet<Integer> value = entry.getValue();
			
			if(value.size() == rocks.size()) {
				qtd += 1;
			}
			
		}
		
		
       return qtd;

    }
    
    
}
