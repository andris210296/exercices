package exercices.dynamic_programming;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.*;

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
		
		assertEquals(Long.valueOf(0),gridTravelerMemoize(0l, 1l,new HashMap<>()));
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
	
	
	/* Write a function 'canSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments	 * 
	 * This function should return a boolean indicating whether or not it is possible to generate the targetSum using numbers from the array
	 * 
	 * You may use an element of the array as many times as needed
	 * You may assume that all input numbers are nonnegative
	 * 
	 */
	
	@Test
	public void canSumSimpleTest() {
		assertTrue(canSumSimple(7l,Arrays.asList(1l, 2l, 3l, 4l, 5l)));
		assertFalse(canSumSimple(7l,Arrays.asList(2l,4l)));
		assertTrue(canSumSimple(8l,Arrays.asList(2l,3l,5l)));
		assertFalse(canSumSimple(300l,Arrays.asList(7l,14l)));

	}
	
	// Complexity O(n^m) exponencial  n=array length m=target sum
	private boolean canSumSimple(Long targetSum, List<Long> list) {
		if(targetSum == 0) return true;
		if(targetSum < 0) return false;
		
		for (Long numb : list) {
			Long remainder = targetSum - numb;
			if(canSumSimple(remainder, list) == true) {
				return true;
			}			
		}
		
		return false;
	}
	
	@Test
	public void canSumMemoTest() {
		assertTrue(canSumMemo(7l,Arrays.asList(1l, 2l, 3l, 4l, 5l), new HashMap<>()));
		assertFalse(canSumMemo(7l,Arrays.asList(2l,4l), new HashMap<>()));
		assertTrue(canSumMemo(8l,Arrays.asList(2l,3l,5l), new HashMap<>()));
		assertFalse(canSumMemo(300l,Arrays.asList(7l,14l), new HashMap<>()));

	}
	
	// Complexity O(n * m) linear  n=array length m=target sum
	private boolean canSumMemo(Long targetSum, List<Long> list, HashMap<Long, Boolean> memo) {
		if(memo.get(targetSum) != null) return memo.get(targetSum);
		if(targetSum == 0) return true;
		if(targetSum < 0) return false;
		
		for (Long numb : list) {
			Long remainder = targetSum - numb;
			if(canSumMemo(remainder, list, memo) == true) {
				memo.put(targetSum, true);
				return true;
			}			
		}
		memo.put(targetSum, false);
		return false;
	}
	
	/*
	 * Write a function 'howSum(targetSum,numbers)' that takes in a targetSum and an array of numbers as arguments.
	 * 
	 * The function should return an array containing any combination of elements that add up to exactly the targetSum.
	 * If there is no combination that adds up to the targetSum, then return null.
	 * 
	 * If there are multiple combinations possible, you may return any single one 
	 * 
	 */
	 
	
	@Test
	public void howSumSimpleTest() {
		
		List<Long> list1 = Arrays.asList(3l,2l,2l);
		assertThat(howSumSimple(7l,Arrays.asList(2l,3l))).hasSameElementsAs(list1);
		
		List<Long> list2 = Arrays.asList(4l,3l);		
		assertThat(howSumSimple(7l,Arrays.asList(5l,3l,4l,7l))).hasSameElementsAs(list2);		
		
		assertNull(howSumSimple(7l,Arrays.asList(2l,4l)));
		
		List<Long> list3 = Arrays.asList(2l,2l,2l,2l);
		assertThat(howSumSimple(8l,Arrays.asList(2l,3l,5l))).hasSameElementsAs(list3);
		
		assertNull(howSumSimple(300l,Arrays.asList(7l,14l)));
	}
	
	
	
	// Complexity O(n^m*m) exponencial  n=array length m=target sum
	private List<Long> howSumSimple(Long targetSum, List<Long> numbers) {
		if(targetSum == 0) return new ArrayList<Long>();
		if(targetSum < 0) return null;
		
		for (Long num : numbers) {
			Long remainder = targetSum - num;
			List<Long> remainderResult = howSumSimple(remainder, numbers);
			
			if(remainderResult != null) {
				remainderResult.add(num);
				return remainderResult;
			}			
		}		
		return null;
	}
	
	@Test
	public void howSumMemoTest() {
		
		List<Long> list1 = Arrays.asList(3l,2l,2l);
		assertThat(howSumMemo(7l,Arrays.asList(2l,3l), new HashMap<>())).hasSameElementsAs(list1);
		
		List<Long> list2 = Arrays.asList(4l,3l);		
		assertThat(howSumMemo(7l,Arrays.asList(5l,3l,4l,7l), new HashMap<>())).hasSameElementsAs(list2);		
		
		assertNull(howSumMemo(7l,Arrays.asList(2l,4l), new HashMap<>()));
		
		List<Long> list3 = Arrays.asList(2l,2l,2l,2l);
		assertThat(howSumMemo(8l,Arrays.asList(2l,3l,5l), new HashMap<>())).hasSameElementsAs(list3);
		
		assertNull(howSumMemo(300l,Arrays.asList(7l,14l), new HashMap<>()));
	}	
	
	// Complexity O(n*m^2) linear  n=array length m=target sum
	
	// 1:48:00 this program contains some flaw that I didn't identify
	private List<Long> howSumMemo(Long targetSum, List<Long> numbers, HashMap<Long, List<Long>> memo) {
		if(memo.get(targetSum) != null) return memo.get(targetSum); 
		if(targetSum == 0) return new ArrayList<Long>();
		if(targetSum < 0) return null;		
		for (Long num : numbers) {
			Long remainder = targetSum - num;
			List<Long> remainderResult = howSumMemo(remainder, numbers, memo);			
			if(remainderResult != null) {				
				remainderResult.add(num);
				memo.put(targetSum, remainderResult);				
				return memo.get(targetSum);
			}			
		}
		memo.put(targetSum, null);
		return null;
	}
	
	/*
	 * Write a function 'bestSum(targetSum, numbers)' that takes in a targetSum and an array of numbers as arguments
	 * 
	 * The function should return an array containing the shortest combination of numbers that add up  to exactly the targetSum
	 * 
	 * If there is a tie for the shortest combination, you may return any one of the shortest
	 * */
	
	@Test
	public void bestSumSimpleTest() {
		
		List<Long> list1 = Arrays.asList(7l);
		assertThat(bestSumSimple(7l,Arrays.asList(5l,3l,4l,7l))).hasSameElementsAs(list1);
		
		List<Long> list2 = Arrays.asList(3l,5l);		
		assertThat(bestSumSimple(8l,Arrays.asList(2l,3l,5l))).hasSameElementsAs(list2);				
		
		List<Long> list3 = Arrays.asList(4l,4l);
		assertThat(bestSumSimple(8l,Arrays.asList(1l,4l,5l))).hasSameElementsAs(list3);
		
		List<Long> list4 = Arrays.asList(25l,25l,25l,25l);
		//assertThat(bestSumSimple(100l,Arrays.asList(1l,2l,5l,25l))).hasSameElementsAs(list4);
	}
	
	// Complexity O(n^m*m) exponencial  n=array length m=target sum
	private List<Long> bestSumSimple(Long targetSum, List<Long> numbers) {
		if(targetSum == 0) return new ArrayList<Long>();
		if(targetSum < 0) return null;
		
		List<Long> shortestCombination = null;
		
		for (Long num : numbers) {
			Long remainder = targetSum - num;
			List<Long> remainderCombination = bestSumSimple(remainder, numbers);			
			if(remainderCombination != null) {				
				List<Long> combination =  remainderCombination;
				combination.add(num);
				
				if(shortestCombination == null ||combination.size() < shortestCombination.size()) {
					shortestCombination = combination;
				}				 
			}			
		}

		return shortestCombination;		
	}
	
	@Test
	public void bestSumMemoTest() {
		
		List<Long> list1 = Arrays.asList(7l);
		assertThat(bestSumMemo(7l,Arrays.asList(5l,3l,4l,7l), new HashMap<>())).hasSameElementsAs(list1);
		
		List<Long> list2 = Arrays.asList(3l,5l);		
		assertThat(bestSumMemo(8l,Arrays.asList(2l,3l,5l), new HashMap<>())).hasSameElementsAs(list2);				
		
		List<Long> list3 = Arrays.asList(4l,4l);
		assertThat(bestSumMemo(8l,Arrays.asList(1l,4l,5l), new HashMap<>())).hasSameElementsAs(list3);
		
		List<Long> list4 = Arrays.asList(25l,25l,25l,25l);
		assertThat(bestSumMemo(100l,Arrays.asList(1l,2l,5l,25l), new HashMap<>())).hasSameElementsAs(list4);
	}
	
	// Complexity O(n*m^2) linear  n=array length m=target sum

	// 2:08:00 this program contains some flaw that I didn't identify
	private List<Long> bestSumMemo(Long targetSum, List<Long> numbers, HashMap<Long, List<Long>> memo) {
		if(memo.get(targetSum) != null) return memo.get(targetSum);
		if(targetSum == 0) return new ArrayList<Long>();
		if(targetSum < 0) return null;
		
		List<Long> shortestCombination = null;
		
		for (Long num : numbers) {
			Long remainder = targetSum - num;
			List<Long> remainderCombination = bestSumMemo(remainder, numbers, memo);			
			if(remainderCombination != null) {				
				List<Long> combination =  remainderCombination;
				combination.add(num);
				
				if(shortestCombination == null ||combination.size() < shortestCombination.size()) {
					shortestCombination = combination;
				}				 
			}			
		}
		
		memo.put(targetSum, shortestCombination);
		return shortestCombination;
	}

}
