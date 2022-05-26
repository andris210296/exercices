package exercices.stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.partitioningBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;

import org.junit.Test;

import lombok.Data;
import lombok.NoArgsConstructor;

public class TestStreams {

	@Test	
	public void streamsUpperCaseAllListTest() {		
	    assertTrue(mapToUppercase("aaron", "frank", "william", "gilliam").containsAll(Arrays.asList("AARON", "FRANK", "WILLIAM", "GILLIAM")));
		assertTrue(mapToUppercase("cegeka").containsAll(Arrays.asList("CEGEKA")));	
		
	}	
	public static Collection<String> mapToUppercase(String... names) {
		return Stream.of(names).map(String::toUpperCase).collect(Collectors.toList());
	}
	
	@Test	
	public void getTotalNumberOfLettersOfNamesLongerThanFiveTest() {		
		assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("william", "jones", "aaron", "seppe", "frank", "gilliam"), 14);
	    assertEquals(getTotalNumberOfLettersOfNamesLongerThanFive("aaron"), 0);		
	}	
		
	public static int getTotalNumberOfLettersOfNamesLongerThanFive(String... names) {
		return Stream.of(names).filter(name -> name.length() > 5).mapToInt(name -> name.length()).sum();			
	}
	
	//https://www.codingame.com/playgrounds/20782/java-guild-meeting-52018/streams---micro-katas
	
	@Test
	public void transformShouldFlattenCollection() {
		List<List<String>> collection = Arrays.asList(Arrays.asList("Viktor", "Farcic"), Arrays.asList("John", "Doe", "Third"));
		List<String> expected = Arrays.asList("Viktor", "Farcic", "John", "Doe", "Third");
		assertThat(transformMultidimensionalToFlatten(collection)).hasSameElementsAs(expected);
	}
	
	public static List<String> transformMultidimensionalToFlatten(List<List<String>> collection) {
		return collection.stream().flatMap(List::stream).collect(Collectors.toList());
	}
	
	@Test
	public void getOldestPersonShouldReturnOldestPerson() {
		Person sara = new Person("Sara", 4);
		Person viktor = new Person("Viktor", 40);
		Person eva = new Person("Eva", 42);
		List<Person> collection = Arrays.asList(sara, eva, viktor);
		assertThat(getOldestPerson(collection)).isEqualToComparingFieldByField(eva);
	}

	@NoArgsConstructor
	@Data
	@Entity
	class Person {
		private String name;
		private int age;
		private String nationality;

		public Person(String name, int age, String nationality) {
			this.name = name;
			this.age = age;
			this.nationality = nationality;
		}

		public Person(String name, int age) {
			this(name, age, "");
		}
	}

	public static Person getOldestPerson(List<Person> people) {
		return people.stream().max(Comparator.comparing(Person::getAge)).orElseThrow(NoSuchElementException::new);
	}
	
	@Test
	public void calculateShouldSumAllNumbers() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		assertThat(calculateShouldSumAllNumbers(numbers)).isEqualTo(1 + 2 + 3 + 4 + 5);
	}

	public static int calculateShouldSumAllNumbers(List<Integer> numbers) {
		return numbers.stream().reduce(0, Integer::sum);
	}
	
	@Test
	public void getKidNameShouldReturnNamesOfAllKidsUnder18() {
		Person sara = new Person("Sara", 4);
		Person viktor = new Person("Viktor", 40);
		Person eva = new Person("Eva", 42);
		Person anna = new Person("Anna", 5);
		List<Person> collection = Arrays.asList(sara, eva, viktor, anna);
		assertThat(getKidNameShouldReturnNamesOfAllKidsUnder18(collection)).contains("Sara", "Anna").doesNotContain("Viktor", "Eva");
	}

	public static Set<String> getKidNameShouldReturnNamesOfAllKidsUnder18(List<Person> people) {
		return people.stream().filter(p -> p.getAge() < 18).map(Person::getName).collect(Collectors.toSet());
	}
	
	@Test
	public void partitionAdultsShouldSeparateKidsFromAdults() {
		Person sara = new Person("Sara", 4);
		Person viktor = new Person("Viktor", 40);
		Person eva = new Person("Eva", 42);
		List<Person> collection = Arrays.asList(sara, eva, viktor);
		Map<Boolean, List<Person>> result = partitionAdults(collection);
		assertThat(result.get(true)).hasSameElementsAs(Arrays.asList(viktor, eva));
		assertThat(result.get(false)).hasSameElementsAs(Arrays.asList(sara));
	}

	public static Map<Boolean, List<Person>> partitionAdults(List<Person> people) {
		return people.stream().collect(partitioningBy(p -> p.getAge() >= 18));
	}
	
	@Test
	public void groupByNationalityTest() {
		Person sara = new Person("Sara", 4, "Norwegian");
		Person viktor = new Person("Viktor", 40, "Serbian");
		Person eva = new Person("Eva", 42, "Norwegian");
		List<Person> collection = Arrays.asList(sara, eva, viktor);
		Map<String, List<Person>> result = groupByNationality(collection);
		assertThat(result.get("Norwegian")).hasSameElementsAs(Arrays.asList(sara, eva));
		assertThat(result.get("Serbian")).hasSameElementsAs(Arrays.asList(viktor));
	}

	public static Map<String, List<Person>> groupByNationality(List<Person> people) {
		return people.stream().collect(groupingBy(p -> p.getNationality()));
	}
	
	@Test
	public void toStringShouldReturnPeopleNamesSeparatedByComma() {
		Person sara = new Person("Sara", 4);
		Person viktor = new Person("Viktor", 40);
		Person eva = new Person("Eva", 42);
		List<Person> collection = Arrays.asList(sara, viktor, eva);
		assertThat(namesToString(collection)).isEqualTo("Names: Sara, Viktor, Eva.");
	}

	public static String namesToString(List<Person> people) {
		return people.stream().map(Person::getName).collect(joining(", ", "Names: ", "."));
	}
	
	@Test
	public void getStringShouldOutputEvenUnevenString() {
		assertThat(getString(Arrays.asList(3, 44))).isEqualTo("o3,e44");
		assertThat(getString(Arrays.asList(3))).isEqualTo("o3");
	}

	public static String getString(List<Integer> list) {
		return null;
	}
    
}
