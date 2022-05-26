package exercices.general;

import org.junit.Test;

public class IsUppercaseOrLowerCase {

	@Test
	public void contextLoads() {
		
		solution("Teste");
		solution("teste");
		solution("1");
		solution("*");
		

		
	}

	public String solution(String s) {
        char c = s.charAt(0);
                
        if (Character.isUpperCase(c)) {  // please fix condition
            return "upper";
        } else if (Character.isLowerCase(c)) {  // please fix condition
            return "lower";
        } else if(Character.isDigit(c)){  // please fix condition
            return "digit";
        } else {
            return "other";
        }
    }
	
}
