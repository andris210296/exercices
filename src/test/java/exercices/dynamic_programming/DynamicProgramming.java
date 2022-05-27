package exercices.dynamic_programming;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class DynamicProgramming {

	@Test
	public void fibonacciSimpleTest() {		
		
		assertEquals(Long.valueOf(8), fibonacciSimple(6l));
		assertEquals(Long.valueOf(13), fibonacciSimple(7l));
		assertEquals(Long.valueOf(21), fibonacciSimple(8l));
		/*Too slow this one*/ //assertEquals(12586269025l, fibonacciSimple(50));
	}

	//Complexity O(2^n) exponencial
	private Long fibonacciSimple(Long n) {		
		if(n <= 2) {
			return 1l;			
		}	
		return fibonacciSimple(n - 1) + fibonacciSimple(n - 2); 
		
	}
	
	@Test
	public void fibonacciMemoTest() {		
		
		assertEquals(Long.valueOf(8), fibonacciMemo(6l, new HashMap<>()));
		assertEquals(Long.valueOf(13), fibonacciMemo(7l, new HashMap<>()));
		assertEquals(Long.valueOf(21), fibonacciMemo(8l, new HashMap<>()));
		assertEquals(Long.valueOf(12586269025l), fibonacciMemo(50l, new HashMap<>()));
	}

	//Complexity O(n) linear
	private Long fibonacciMemo(Long n, HashMap<Long, Long> memoMap) {
		if (memoMap.get(n) != null) {
			return memoMap.get(n);
		}
		if (n <= 2) {
			return 1l;
		}
		memoMap.put(n, fibonacciMemo(n - 1, memoMap) + fibonacciMemo(n - 2, memoMap));
		return memoMap.get(n);

	}
	/* Say that you are a traveler on a 2D grid. You begin in the top-left corner and your goal is to travel
	 * to the bottom-right corner. You may only move down or right.
	 * In how many ways can you travel to the goal on a grid with dimensions m * n?
	 * 
	 * */
	@Test
	public void gridTravelerSimpleTest() {		
		
		assertEquals(Long.valueOf(0),gridTravelerSimple(0l, 1l));
		assertEquals(Long.valueOf(1),gridTravelerSimple(1l, 1l));
		assertEquals(Long.valueOf(3),gridTravelerSimple(2l, 3l));
		assertEquals(Long.valueOf(6),gridTravelerSimple(3l, 3l));
		/*Too slow this one*/ //assertEquals(Long.valueOf(2333606220l),gridTraveler(18l, 18l));

	}
	
	// Complexity O(2^(n+m)) exponencial
	private Long gridTravelerSimple(Long m, Long n) {
		if(m == 1 && n == 1) return 1l;
		if(m == 0 || n == 0) return 0l;
		return gridTravelerSimple(m - 1, n) + gridTravelerSimple(m, n - 1);
	}
	
	@Test
	public void gridTravelerMemoizeTest() {		
		
		assertEquals(Long.valueOf(0),gridTravelerMemoize(0l, 1l,new HashMap<>() ));
		assertEquals(Long.valueOf(1),gridTravelerMemoize(1l, 1l, new HashMap<>()));
		assertEquals(Long.valueOf(3),gridTravelerMemoize(2l, 3l, new HashMap<>()));
		assertEquals(Long.valueOf(6),gridTravelerMemoize(3l, 3l, new HashMap<>()));
		assertEquals(Long.valueOf(2333606220l),gridTravelerMemoize(18l, 18l, new HashMap<>()));

	}
	
	// Complexity O(m*n) linear
	private Long gridTravelerMemoize(Long m, Long n, HashMap<String, Long> memoHash) {
		String key = m + "," + n;
		if(memoHash.get(key) != null) return memoHash.get(key);
		if(m == 1 && n == 1) return 1l;
		if(m == 0 || n == 0) return 0l;
		memoHash.put(key, gridTravelerMemoize(m - 1, n, memoHash) + gridTravelerMemoize(m, n - 1, memoHash));
		
		return memoHash.get(key);
	}

}
