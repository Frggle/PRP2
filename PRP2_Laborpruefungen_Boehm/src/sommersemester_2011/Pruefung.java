package sommersemester_2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pruefung {

	public static void main(String[] args) {
		// 1
		System.err.println("Aufgabe1");
		List<String> list1 = Arrays.asList("a", "b", "c");
		List<String> list2 = Arrays.asList("x", "y", "z");
		List<List<String>> list = Arrays.asList(list1, list2);
		System.err.println(transposeList(list));
		// 2
		System.err.println("Aufgabe2");
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		set1.add("a");
		set1.add("b");
		set2.add("b");
		set2.add("c");
		System.err.println(symmDiff(set1, set2));
		// 3
		System.err.println("Aufgabe3");
		Set<Object> set3 = new HashSet<Object>();
		Set<Object> set4 = new HashSet<Object>();
		Set<Object> set5 = new HashSet<Object>();
		set3.add("b");
		set3.add("c");
		set4.add(set3);
		set4.add("d");
		set5.add("a");
		set5.add(set4);
		System.err.println(deepCopyTree(set5));
		// 4
		System.err.println("Aufgabe4");
		Set<List<String>> set6 = new HashSet<List<String>>();
		List<String> list3 = Arrays.asList("Emil", "PR1");
		List<String> list4 = Arrays.asList("Otto", "PR2");
		List<String> list5 = Arrays.asList("Emil", "AF");
		set6.add(list3);
		set6.add(list4);
		set6.add(list5);
		System.err.println(set6);
		System.err.println(setToMultiMap(set6));		
	} 
	
	/**
	 * Sie sollen eine (n X m)-Liste in eine (m X n)-Liste konvertieren (m, n > 0)
	 * Beispiel:
	 * [["a", "b", "c"], ["x", "y", "z"]] -> [["a", "x"], ["b", "y"], ["c", "z"]]
	 */
	static List<List<String>> transposeList(List<List<String>> list) {
		if(list == null) {
			throw new IllegalArgumentException();
		}
		if(list.size() <= 0) {
			throw new IllegalArgumentException();
		}		
		if(list.get(0).size() <= 0) {
			throw new IllegalArgumentException();
		}
				
		List<List<String>> result = new ArrayList<List<String>>();
		for(int i = 0; i < list.get(0).size(); i++) {
			List<String> temp = new ArrayList<String>();
			for(List<String> elem : list) {
				temp.add(elem.get(i));
			}
			result.add(temp);
		}
		
		return result;
	}
	
	/**
	 * Die symmetrische Differenz von zwei Mengen enthält diejenigen Elemente, die in einer der beiden Mengen aber nicht
	 * in beiden enthalten sind. Verwenden Sie Bulk-Operations! Bitte nicht destruktiv!
	 * Beispiel:
	 * {"a", "b"},{"b", "c"} -> {"a", "c"} 
	 */
	static Set<String> symmDiff(Set<String> set1, Set<String> set2) {
		if(set1 == null || set2 == null) {
			throw new IllegalArgumentException();
		}
		
		Set<String> schnitt = new HashSet<String>();
		Set<String> result = new HashSet<String>();
		schnitt.addAll(set1);
		schnitt.retainAll(set2);
		result.addAll(set1);
		result.addAll(set2);
		result.removeAll(schnitt);
		
		return result;
	}
	
	/**
	 * Die Elemente von set sind entweder wieder Sets oder beliebige andere Objekte (d.h. Blätter).
	 * Die Rekursionstiefe dieses Baumes kann beliebig sein. Sie sollen eine tiefe Kopie des Baumes erzeugen. 
	 * Blätter sollen nicht kopiert werden, weil diese evtl. nicht kopierbar sind.
	 * Verwenden Sie eine explizite Rekursion!
	 * Beispiel:
	 * {"a", {{"b", "c"}, "d"}} -> {"a", {{"b", "c"}, "d"}} 
	 */
	static Set<?> deepCopyTree(Set<?> set) {
		if(set == null) {
			throw new IllegalArgumentException();
		}
		
		Set<Object> result = new HashSet<Object>();
		for(Object o : set) {
			if(o instanceof Set) {
				result.add(o);
				deepCopyTree((Set<?>) o);
			} else {
				result.add(o);
			}
		}
		
		// Lösung macht meines Erachtens keinen Sinn, ist jedoch von Böhm mit voller Punktzahl bewertet
		
		return result;
	}
	
	/**
	 * Eine Multimap hat für einen Key eine Liste von Werten als Value. Sie sollen einen Set bom 2er Listen (Key-Value-Paare)
	 * in eine solche Multimap konvertieren.
	 * Beispiel:
	 * {["Emil", "PR1"], ["Otto", "PR2"], ["Emil", "AF"]} -> {("Emil", ["PR1", "AF"]), ("Otto", ["PR""])}
	 */
	static Map<String, List<String>> setToMultiMap(Set<List<String>> set) {
		if(set == null) {
			throw new IllegalArgumentException();
		}
		for(List<String> elem : set) {
			if(elem.size() != 2) {
				throw new IllegalArgumentException();
			}
		}
		
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		for(List<String> elem : set) {
			if(result.containsKey(elem.get(0))) {
				List<String> temp = new ArrayList<String>();
				// alle bisherigen Vorlesungen übernehmen
				temp.addAll(result.get(elem.get(0)));
				// neue Vorlesung hinzufügen
				temp.add(elem.get(1));	
				result.put(elem.get(0), temp);
			} else {
				result.put(elem.get(0), Arrays.asList(elem.get(1)));
			}
		}
		return result;
	}
}
