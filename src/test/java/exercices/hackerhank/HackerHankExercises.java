package exercices.hackerhank;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class HackerHankExercises {
	
	@Test	
	public void diagonalDifferenceTest() {		
		
		List<List<Integer>> collection = Arrays.asList(Arrays.asList(11,2,4), Arrays.asList(4,5,6), Arrays.asList(10,8,-12));
		
		assertEquals(15, diagonalDifference(collection));
	    
	}	
	
	public static int diagonalDifference(List<List<Integer>> arr) {
        
        Integer diagonal1 = 0;
        Integer diagonal2 = 0;      
                       
        for(int i=0; i< arr.size(); i++){
            diagonal1 += arr.get(i).get(i);
            diagonal2 += arr.get(i).get(arr.size()-1-i);
            
        }
        
        return Math.abs(diagonal1 - diagonal2);
    }
	
	@Test	
	public void plusMinusPrintRatioOfPositiveNegativeZeroValuesTest() {

		List<Integer> list = Arrays.asList(1, 1, 0, -1, -1);
		plusMinus(list);	
	
	}

	public static void plusMinus(List<Integer> arr) {
		
		Double positive = Double.valueOf(0);
		Double negative =Double.valueOf(0);
		Double zero = Double.valueOf(0);
		
		for (Integer integer : arr) {
			if(integer == 0) {
				zero+=1;				
			}
			if(integer < 0) {
				negative+=1;				
			}
			if(integer > 0) {
				positive+=1;				
			}
		}
		
		System.out.printf("%.6f \n", positive/Double.valueOf(arr.size()));
		System.out.printf("%.6f \n", negative/Double.valueOf(arr.size()));
		System.out.printf("%.6f \n", zero/Double.valueOf(arr.size()));

	}
	
	@Test
	public void staircaseTest() {
		staircase(4);
	}

	public static void staircase(int n) {
		
		Integer spaces = n-1;
		Integer stair = 1;
		
		for(Integer i=0; i < n; i++) {			
			printHelper(spaces, stair);			
			spaces -=1;
			stair +=1;
		}
	}
	
	private static void printHelper(Integer spaces, Integer stair) {
		for(Integer i=0; i < spaces; i++) {			
			System.out.print(" ");
		}
		for(Integer i=0; i < stair; i++) {			
			System.out.print("#");
		}
		System.out.print("\n");		
	}
	
	@Test
	public void miniMaxSumTest() {
		
		List<Integer> list = Arrays.asList(1,3,5,7,9);
		
		miniMaxSum(list);
	}
	
	public static void miniMaxSum(List<Integer> arr) {
		List<Integer> listInOrder = arr.stream().sorted().collect(Collectors.toList());
		List<Long> listInOrderLong = arr.stream().mapToLong(i -> i).boxed().collect(Collectors.toList());
		Long sum = listInOrderLong.stream().reduce(0l, Long:: sum);
		
		
		System.out.println(sum - listInOrder.get(listInOrder.size() - 1) + " "+ 
				(sum - listInOrder.get(0)));

    }
	
	@Test
	public void birthdayCakeCandlesTest() {

		List<Integer> list = Arrays.asList(4,4,1,3);

		assertEquals(2, birthdayCakeCandles(list));
	}

	public static int birthdayCakeCandles(List<Integer> candles) {
		
		int highest = 0;
        int qtdHighest = 0;
        
        for (Integer integer : candles) {
            if(integer > highest) {
                highest = integer;
                qtdHighest = 0;
            }
            if (integer == highest) {
                qtdHighest+=1;
            }            
        }        
        return qtdHighest;
	}
	
	@Test
	public void timeConversionAMPMToMilitary() {

		assertEquals("07:05:45", timeConversion("07:05:45AM"));
		assertEquals("00:05:45", timeConversion("12:05:45AM"));
		assertEquals("19:05:45", timeConversion("07:05:45PM"));
		assertEquals("12:45:54", timeConversion("12:45:54PM"));
	}
	
	public static String timeConversion(String s) {
		
		String[] splitTime = s.split(":");
		
		if(String.valueOf(s.charAt(s.length() -2)).equalsIgnoreCase("A") && !splitTime[0].equals("12")) {
			return s.substring(0, s.length() -2);
		}if(String.valueOf(s.charAt(s.length() -2)).equalsIgnoreCase("A") && splitTime[0].equals("12")) {
			return "00:"+ s.substring(3, s.length() -2);
		}if(String.valueOf(s.charAt(s.length() -2)).equalsIgnoreCase("P") && splitTime[0].equals("12")) {
			return s.substring(0, s.length() -2);
		}
					
		return (Integer.valueOf(splitTime[0]) + 12)+":" + s.substring(3, s.length() -2);

	}
	
	//https://www.hackerrank.com/challenges/the-grid-search/problem
	
	@Test
	public void theGridSearchTest() {

		List<String> grid1 = Arrays.asList("1234567890","0987654321","1111111111","1111111111","2222222222");
		List<String> pattern1 = Arrays.asList("876543","111111","111111");
		
		assertEquals("YES", gridSearch(grid1,pattern1));
		
		List<String> grid2 = Arrays.asList("1234","4321","9999","9999");
		List<String> pattern2 = Arrays.asList("12","21");
		
		assertEquals("NO", gridSearch(grid2,pattern2));
		
		List<String> grid3 = Arrays.asList("111111111111111","111111111111111","111111011111111","111111111111111","111111111111111");
		List<String> pattern3 = Arrays.asList("11111","11111","11110");
		
		assertEquals("YES", gridSearch(grid3,pattern3));
	}
	
	public static String gridSearch(List<String> G, List<String> P) {		
		int qtyOfTriesLeftToRight = G.get(0).length() - P.get(0).length() + 1;
		int qtyOfTriesTopToDown = G.size() - P.size() + 1;
		for (int x = 0; x < qtyOfTriesLeftToRight; x++) {
			for (int y = 0; y < qtyOfTriesTopToDown; y++) {
				
				List<String> subList = G.subList(y, y + P.size());	
				if (checkSubList(subList, P, x)) return "YES";				
			}			
		}	
		
		return "NO";
	}
	private static boolean checkSubList(List<String> subList, List<String> P, Integer x) {
		for (int i = 0; i < subList.size(); i++) {
			String gridLineSubstring = subList.get(i).substring(x, x + P.get(0).length());
			String patternLine = P.get(i);
			if (!gridLineSubstring.contains(patternLine)) {
				return false;
			}
		}
		return true;
	}

	
	
}
