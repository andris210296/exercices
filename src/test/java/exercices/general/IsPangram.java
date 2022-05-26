package exercices.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IsPangram {

	@Test
	public void contextLoads() {

		int x = 1;
		int y = 1;		
		int z = 0;

		while (x < 25) {
			
			z = x;
			x = x + y;
			
			y = z;

		}
		
		System.out.println(x);

	}
	
	@Test	
	public void isPangram() {
		
		List<String> strings = new ArrayList<String>();
		strings.add("pack my box with five dozen liquor jugs");
		strings.add("this is not a pangram");
		
		System.out.println(isPangram(strings));
		
		
		
	}
	
	public static String isPangram(List<String> pangram) {
        
        String result = "";
        
        for (String sentence : pangram) {
            
            if(checkPangram(sentence)){
                result += 1;
            }
            else{
                result+=0;
            }
            
        }
        
        return result;

    }
    
    private static boolean checkPangram(String str){
    	
    	if (str == null) {
            return false;
        }
        Boolean[] alphabetMarker = new Boolean[26];
        Arrays.fill(alphabetMarker, false);
        int alphabetIndex = 0;
        str = str.toUpperCase();
        for (int i = 0; i < str.length(); i++) {
            if ('A' <= str.charAt(i) && str.charAt(i) <= 'Z') {
                alphabetIndex = str.charAt(i) - 'A';
                alphabetMarker[alphabetIndex] = true;
            }
        }
        for (boolean index : alphabetMarker) {
            if (!index) {
                return false;
            }
        }
        return true;
    }

}
