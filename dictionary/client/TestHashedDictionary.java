package dictionary.client;

import java.util.Iterator;

import dictionary.HashedDictionary;

public class TestHashedDictionary {
	public static void main(String[] args) {
		HashedDictionary<String, Integer> hashDict = new HashedDictionary<String, Integer>();
		System.out.println("isEmpty? " + hashDict.isEmpty());
		System.out.println("adding elements....");
		hashDict.add("1", 1);
		hashDict.add("2", 2);
		System.out.println("isEnpty? " + hashDict.isEmpty());
		System.out.println("isFUll? " + hashDict.isFull());
		System.out.println("hashtable's size " + hashDict.getSize());
		
		System.out.println("contains key(1) ? "  + hashDict.contains("1"));
		System.out.println("contains key(8) ? " + hashDict.contains("8"));
		
//		System.out.println("foreach hash dict's key and value....");
		Iterator<String> keyIterator = hashDict.getKeyIterator();
//		while(keyIterator.hasNext()){
//			System.out.println("Key:" + keyIterator.next());
//		}
		Iterator<Integer> valueIterator = hashDict.getValueIterator();
//		while(valueIterator.hasNext()){
//			System.out.println("Value: " + valueIterator.next());
//		}
		System.out.println("foreach hash dict");
		while(keyIterator.hasNext() && valueIterator.hasNext()){
			System.out.println("Key: " + keyIterator.next() + " Value: " + valueIterator.next());
		}
		Integer i = hashDict.getValue("1");
		System.out.println("key(1)'s value --" + i);
	}
}
