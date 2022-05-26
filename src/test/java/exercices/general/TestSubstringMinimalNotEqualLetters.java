package exercices.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class TestSubstringMinimalNotEqualLetters {

	@Test
	public void contextLoads() {
		
		String world = "world";		
		System.out.println(solution(world));
		
		
		String dddd = "dddd";
		System.out.println(solution(dddd));
		
		String cycle = "cycle";
		System.out.println(solution(cycle));
		
	}
	
	int solution(String S) {
		
		HashMap<String, List<Integer>> mapa = new HashMap<String, List<Integer>>();
		
		Integer position = 0;
		
		for (char letter : S.toCharArray()) {
			
			
			if(mapa.get(String.valueOf(letter)) == null ) {
				
				List<Integer> positions = new ArrayList<Integer>();
				positions.add(position);
				
				mapa.put(String.valueOf(letter), positions);
			}else {
				mapa.get(String.valueOf(letter)).add(position);
			}
			
			position+=1;
			
		}
		
		int qtd = 0;
		
		for (String i : mapa.keySet()) {
			if(qtd <= mapa.get(i).size()) {
				qtd = mapa.get(i).size();
			}
		}
		
		
	
		
		return qtd;
		
		
		
		
	}

	


	
	
}

