package sommersemester_2009;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pruefung {

	public static void main(String[] args) {
		// 1
		System.err.println("Aufgabe1:");
		List<Character> l1 = Arrays.asList('a','b','c');
        List<Character> l2 = Arrays.asList('a','b','d');
        System.err.println("compareCharLists("+l1 + "," + l2 + ") ergibt:");
		System.err.println(compareCharLists(l1,l2));
		// 2
		System.err.println("Aufgabe2:");
		Set<Object> s1 = new HashSet<Object>();
		Set<Object> s2 = new HashSet<Object>();
		Set<Object> s3 = new HashSet<Object>();
		s1.add(1);
		s1.add(2);
		s3.add(1);
		s3.add(3);
		s3.add(2);
		s2.add(s3);
		s2.add(1);
		s2.add(3);
		s1.add(s2);
		System.err.println("noOfDuplicateLeaves(" + s1 + ") ergibt:");
		System.err.println(noOfDuplicateLeaves(s1));
		// 3
		System.err.println("Aufgabe3:");
		Map<Integer, Integer> m1 = new HashMap<Integer, Integer>();
		m1.put(1, 1);
		m1.put(2, 2);
		System.err.println("intMapToPairList(" + m1 + ") ergibt:");
		System.err.println(intMapToPairList(m1));
		// 4
		System.err.println("Aufgabe4:");
        List<String> l4 = Arrays.asList("a", "a", "b");
		System.err.println("listToMultiSet(" + l4 + ") ergibt:");
		System.err.println(listToMultiSet(l4));
	}
	
	/**
	 * Vergleich von Char-Sequenzen
	 * 
	 * Beide Listen sind Sequenzen von Charactern. Implementieren Sie ein compareCharLists(..), das sich so verhält als würden Sie
	 * zwei entsprechende Strings mit diesem Charactern mit compareTo(..) vergleichen.
	 * Sie dürfen aber dazu NICHT einfach die Listen als Strings umwandeln. Das wäre zu einfach! Das compareTo() für Strings ist 
	 * explizit verboten
	 * 
	 * 5.compareTo(3) => 1
	 * 5.compareTo(5) => 0
	 * 5.compareTo(6) => -1
	 */
	static int compareCharLists(List<Character> l1, List<Character> l2) {
		if (l1 == null || l2 == null) {
			throw new IllegalArgumentException();
		}
		Iterator<Character> iter1 = l1.iterator();
		Iterator<Character> iter2 = l2.iterator();
		
		while(iter1.hasNext() && iter2.hasNext())
		{
			Character char1 = iter1.next();
			Character char2 = iter2.next();
			
			if (char1 > char2) {
				return 1;
			} else if (char1 < char2) {
				return -1;
			}	
		}
		
		if (iter1.hasNext()) {
			return 1;
		}
		if (iter2.hasNext()) {
			return -1;
		}
		return 0;
	}
	
	
	/**
	 * Rekursion in Bäumen
	 * 
	 * Die Elemente von aSet sind entweder wieder Sets oder beliebige andere Objecte (d.h. Blätter, Leaves).
	 * Die Rekursionstiefe kann beliebig sein. Verwenden Sie eine explizite Rekursion!
	 * Sie sollen zählen wieviele Blätter mehr als einmal in dieser Baumstruktur vorkommen.
	 */
	static int noOfDuplicateLeaves(Set<?> aSet) {
		if(aSet == null) {
			throw new IllegalArgumentException();
		}
		
		Map<Object, Integer> counter = new HashMap<Object, Integer>();
		countLeaves(aSet, counter);
		
		int result = 0;
		for(Entry<Object, Integer> entry : counter.entrySet()) {
			if (entry.getValue() > 1) {
				result++;
			}
		}
		
		return result;
	}
	
	static void countLeaves(Set<?> set, Map<Object, Integer> counter) {
		if (set == null) {
			throw new IllegalArgumentException();
		}
		
		for(Object o : set) {
			if (o instanceof Set) {
				countLeaves((Set<?>) o, counter);
			} else {
				counter.put(o, counter.containsKey(o) ? counter.get(o) + 1 : 1);
			}
		}
	}
	
	/**
	 * Maps in Listen konvertieren
	 * 
	 * Die Map map soll eine tabellierte Funktion von Integer nach Integer darstellen. Sie sollen daraus eine ArrayList machen,
	 * die als Elemente ArrayLists von Key-Value-Paaren enthält.
	 */
	static List<List<Integer>> intMapToPairList(Map<Integer, Integer> map) {
		if (map == null) {
			throw new IllegalArgumentException();
		}
				
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(Entry<Integer, Integer> entry : map.entrySet()) {
			List<Integer> temp = new ArrayList<Integer>();
			temp.add(entry.getKey());
			temp.add(entry.getValue());
			result.add(temp);
		}
		
		return result;
	}
	
	/**
	 * Maps in Multisets konvertieren
	 * 
	 * Die Liste list enthält Strings als Elemente. Sie sollen darauf einen Multiset (Bag) machen, der als Map realisiert ist.
	 * D.h. die Strings sind die Keys und die Values geben an wie oft dieses Element in der Liste vorkommt.
	 */
	static Map<String, Integer> listToMultiSet(List<String> list) {
		if (list == null) {
			throw new IllegalArgumentException();
		}
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		for(String s : list) {
			if (!result.containsKey(s)) {
				result.put(s, 1);
			} else {
				result.put(s, result.get(s) + 1);
			}
		}
		return result;
	}
}
