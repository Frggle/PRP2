package sommersemester_2010;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Pruefung {

	public static void main(String[] args) {
		// 1
		System.err.println("Aufgabe1");
		List<String> list = Arrays.asList("a", "b", "a");
		System.err.println(isSorted(list));
		// 2
		System.err.println("Aufgabe2");
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
		System.err.println(noOfAllNodes(s1));
		// 3
		System.err.println("Aufgabe3");
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map1.put("der", 2);
		map1.put("die", 3);
		map1.put("das", 1);
		map2.put("dass", 2);
		map2.put("das", 4);
		System.err.println(addFreqMaps(map1, map2));
		// 4
		System.err.println("Aufgabe4");
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<String> list2 = Arrays.asList("1", "2");
		System.err.println(interleave(list1, list2));
	}
	
	/**
	 * Stellen Sie fest, ob die Liste von Strings aufsteigend sortiert ist.
	 */
	static boolean isSorted(List<String> list) {
		if(list.isEmpty() || list == null) {
			throw new IllegalArgumentException();
		}
		Boolean result = false;
		List<String> listClone = list; 
		Collections.sort(list);
		Iterator<String> iter1 = list.iterator();
		Iterator<String> iter2 = listClone.iterator();
		while(iter1.hasNext() && iter2.hasNext()) {
			if(!iter1.next().equals(iter2.next())) {
				result = result && false;
			}				
		}
		return result;
	}
	
	/**
	 * Die Elemente von aSet sind entweder wieder Sets oder beliebige andere Objekte (d.h. Blätter, Leaves). 
	 * Die Rekursionstiefe kann beliebig sein. Sie sollen die Gesamtzahl aller Knoten dieses Baumes bestimmen. 
	 * Verwenden Sie eine explizite Rekursion !
	 */
	static int noOfAllNodes(Set<?> aSet) {
		if(aSet == null) {
			throw new IllegalArgumentException();
		}
		
//		int res = 0;
		Set<Object> res = new HashSet<Object>();
		res.add(aSet);
		countLeaves(aSet, res);		
				
		return res.size();
	}
	
	static void countLeaves(Set<?> set, Set<Object> res) {
		if(set == null) {
			throw new IllegalArgumentException();
		}
		
		for(Object o : set) {
			if (o instanceof Set) {
//				res++;
				res.add(o);
				countLeaves((Set<?>) o, res);
			} 
		}
	}
	
	/**
	 * Sie haben zwei Frequency-Maps für Worte, die jeweils angeben, wie oft ein Wort in einem Text vorkommt. 
	 * Sie sollen die Summe beider Maps bilden (nicht destruktiv!). 
	 * Das Ergebnis entspricht dann der Frequency-Map der konkatenierten Texte.
	 */
	static Map<String,Integer> addFreqMaps(Map <String,Integer> map1, Map <String,Integer> map2) {
		if(map1 == null || map2 == null) {
			throw new IllegalArgumentException();
		}
		
		Map<String, Integer> result = map1;
		for(Entry<String, Integer> entry : map2.entrySet()) {
			result.put(entry.getKey(), result.containsKey(entry.getKey()) ? result.get(entry.getKey()) + entry.getValue() : entry.getValue());
		} 
		
		return result;
	}
	
	/**
	 * Sie sollen beide Listen mischen (nicht destruktiv!), indem Sie abwechselnd ein Wort aus jeder Liste nehmen. 
	 * Falls eine Liste erschöpft ist, nehmen Sie nur noch Worte aus der längeren Liste.
	 */
	static List<String> interleave(List <String> list1, List <String> list2) {
		if(list1 == null || list2 == null) {
			throw new IllegalArgumentException();
		}
		
		List<String> result = new ArrayList<String>();
		Iterator<String> iter1 = list1.iterator();
		Iterator<String> iter2 = list2.iterator();
		
		while(iter1.hasNext() && iter2.hasNext()) {
			result.add(iter1.next());
			result.add(iter2.next());
		}
		while(iter1.hasNext()) {
			result.add(iter1.next());
		}
		while(iter2.hasNext()) {
			result.add(iter2.next());
		}
		
		return result;
	}
}
