package sommersemester_2012;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pruefung {
	public static void main(String[] args) {
		// 1
		System.err.println("Aufgabe1");
		List<String> list1 = Arrays.asList("a", "b");
		List<String> list2 = Arrays.asList("x", "y", "z");
		System.err.println(interleaveLists(list1, list2));
		// 2
		System.err.println("Aufgabe2");
		List<Object> list3 = new ArrayList<Object>(Arrays.asList());
		List<Object> list4 = new ArrayList<Object>(Arrays.asList("a"));
		List<Object> list5 = new ArrayList<Object>(Arrays.asList("b"));
		list5.add(list4);
		System.err.println(maxDepthOf(list5));	// 2
		System.err.println(maxDepthOf(list3));  // 0
		System.err.println(maxDepthOf(list4));  // 1
		// 4 
		System.err.println("Aufgabe4");
		List<String> list6 = Arrays.asList("Emil", "PR1");
		List<String> list7 = Arrays.asList("Otto", "PR2");
		List<String> list8 = Arrays.asList("Emil", "AF");
		List<List<String>> list9 = Arrays.asList(list6, list7, list8);
		System.err.println(stringPairsToMap(list9));
	}
	
	/**
	 * Sie sollen zwei Listen so mischen, das jeweils ein Element abwechselnd von den Listen genommen wird.
	 * Die Listen müssen nicht gleich lang sein. In diesem Fall wird der Rest der längeren Liste angehängt.
	 */
	static List<String> interleaveLists(List<String> list1, List<String> list2) {
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
	
	/**
	 * Die Collection aColl soll eine beliebige rekursiv geschachtelte Collection sein. Sie sollen die maximale
	 * Schachtelungstiefe bestimmen. Eine leere Collection soll die Tiefe 0 haben. Also wie bei der Höhe eines Baumes
	 * Beispiel:
	 * {} -> 0
	 * {"a", "b"} -> 1,
	 * {"a", {{"b", "c"}, "d"}} -> 3
	 */
	static int maxDepthOf(Collection<?> aColl) {
		if(aColl == null) {
			throw new IllegalArgumentException();
		}
			
		int result = 0;
		for(Object o : aColl) {
			result = Math.max(result, (o instanceof Collection ? maxDepthOf((Collection<?>) o) + 1 : 1));
		}
		
		return result;
	}
	
	/**
	 * Schreiben Sie eine Methode die prüft, ob ein Set wertgleich zu einem Objekt ist.
	 * Für die tieferen Ebenen dürfen Sie das normale "equals" verwenden.
	 * Beispiel:
	 * {"a", {"b", "c"}, "d"}, {"a", {"b", "c"}, "d"} -> true
	 */
	static boolean setEquals(Set<?> set, Object obj) {
		if(set == null || obj == null) {
			throw new IllegalArgumentException();
		}
		
		// TODO
		
		return true;
	}
	
	/**
	 * Sie sollen eine Liste von Key-Value-Paaren in eine Map konvertieren. Diese Konversion ist aber offensichtlich
	 * nicht für alle Inputs möglich!
	 * Beispiel:
	 * [["Emil", "PR1"], ["Otto", "PR2"], ["Emil", "AF"]] -> Exception
	 */
	static Map<String, String> stringPairsToMap(List<List<String>> list) {
		if(list == null) {
			throw new IllegalArgumentException();
		}

		Map<String, String> result = new HashMap<String, String>();
		for(List<String> elem : list) {
			if(elem.size() != 2) { throw new IllegalArgumentException(); }
			if(elem.get(0) == null) { throw new IllegalArgumentException(); }
			if(elem.get(1) == null) { throw new IllegalArgumentException(); }
			if(result.containsKey(elem.get(0))) { throw new IllegalArgumentException(); }
			result.put(elem.get(0), elem.get(1));
		}
		
		// Fehlerhaft mit den assert-Tests
				
		return result;
	}
}
