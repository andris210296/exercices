package exercices.general;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

public class TestStreamBigDecimal {

	@Test
	public void contextLoads() {
		
		
		Stream<BigDecimal> input =  Stream.of(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN, null, BigDecimal.TEN.negate());
		
		
        
		//preprocess(input).forEach(s -> System.out.println(s));
		
		preprocess(input);
		
        
		
	}
	
	Stream<BigDecimal> preprocess(Stream<BigDecimal> input) {
       // return input.filter(c -> c != null || c != c.negate());
		
		Stream<BigDecimal> positives =  input.filter(i -> i != null && i.signum() != -1);
		
		
		
		
		System.out.println(Arrays.asList(positives));
		
		return input;
		
    }

	


	
	
}

