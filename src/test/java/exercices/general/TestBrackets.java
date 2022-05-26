package exercices.general;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Stack;

import org.junit.Test;

public class TestBrackets {

	@Test	
	public void brackets() {
		
		
		String test1 = "{}()[()]";		
		assertTrue(brackets(test1));
		
		String test2 = "{[}()[";		
		assertFalse(brackets(test2));
		
		
		String test3 = "{}(";		
		assertFalse(brackets(test3));
		
		
		String test4 = "}()";		
		assertFalse(brackets(test4));
		
	}
	
	public boolean brackets(String value) {		
		Stack<String> stack = new Stack<String>();
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("{", "}");
		hashMap.put("[", "]");
		hashMap.put("(", ")");		
		
		for (char v: value.toCharArray()) {			
			String vString = String.valueOf(v);
			
			if(vString.equals("{") || vString.equals("[") || vString.equals("(")) {				
				stack.add(vString);
				
			}else if(stack.size() ==0) {
				return false;
				
			}else if(hashMap.get(stack.lastElement()).equals(vString)) {				
				stack.pop();
				
			}else if(!hashMap.get(stack.lastElement()).equals(vString)) {				
				return false;
			}						
		}
		
		if(stack.size() != 0) return false;
		return true;
		
	}    
    
}
