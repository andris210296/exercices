package exercices.general;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestRainySeason {

	@Test
	public void contextLoads() {

		String test = "SRRS";

		//solve(test);
		
		assertEquals(4, solve("SSSRRRSRRSRRRRS"));

	}

	public static int solve(String input) {
		
		int longest = 0;		
		int rainy = 0;
		
		for (char day: input.toCharArray()) {			
			if(String.valueOf(day).equals("R")) {
				rainy +=1;
			}
			
			else {
				if(longest < rainy) {
					longest = rainy;					
					rainy = 0;
				}
				rainy = 0;				
			}			
		}		

		return longest;
	}

}
