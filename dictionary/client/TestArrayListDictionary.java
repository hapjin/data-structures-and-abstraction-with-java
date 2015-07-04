package dictionary.client;

import java.util.Iterator;

import dictionary.ArrayListDictionary;

public class TestArrayListDictionary {
	public static void main(String[] args) {
		ArrayListDictionary<String, Integer> ald = new ArrayListDictionary<String, Integer>();
		ald.add("aa", 1);
		ald.add("bb", 2);
		System.out.println(ald.contains("aa"));
		System.out.println("empty() test " + ald.isEmpty());
		System.out.println("getValue() test " + ald.getValue("aa"));
		
		System.out.println("iterator test....");
		Iterator<String> keyit = ald.getKeyIterator();
		while(keyit.hasNext()){
			System.out.println(keyit.next());
		}
		
		System.out.println("value iterator test....");
		Iterator<Integer> valueit = ald.getValueIterator();
		while(valueit.hasNext()){
			System.out.println(valueit.next());
		}
	}
}
