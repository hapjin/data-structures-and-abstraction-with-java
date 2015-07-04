package dictionary;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDictionary<K,V> implements DictionaryInterface<K, V>, Serializable{

	private Entry<K,V>[] dictionary;//封装了 key-value对  的对象
	private int currentSize;//数组元素个数
	private final static int DEFAULT_SIZE = 25;
	
	public ArrayDictionary(){
		this(DEFAULT_SIZE);
	}
	@SuppressWarnings("unchecked")
	public ArrayDictionary(int initialCapacity){
		dictionary = new Entry[initialCapacity];
		currentSize = 0;
	}
	
	private class Entry<S,T>{
		S key;
		T value;
		
		private Entry(S searchKey, T dataValue){
			key = searchKey;
			value = dataValue;
		}
		//键是不可改变的，因此没有setKey方法
		public S getKey() {
			return key;
		}
		public T getValue() {
			return value;
		}
		public void setValue(T value) {
			this.value = value;
		}
	}//end class Entry
	
	/*
	 * EntryIterator类用来实现Entry数组的遍历器
	 * 错！！！这样做是没有意义的，因为Entry是一个内部类，在其它类的是访问不到它的。
	 * 因而也就不能在其他类中遍历Entry类的对象了
	 */
	private class EntryIterator<Entry> implements Iterator<Entry>, Serializable{
		private int nextIndex;//标记当前正在遍历的元素
		
		private EntryIterator(){
			nextIndex = 0;
		}
		@Override
		public boolean hasNext() {
			return nextIndex < currentSize;
		}
		@Override
		public Entry next() {
			if(hasNext()){
				Entry result = (Entry) dictionary[nextIndex];//将泛型的Entry转换为非泛型的Entry???
				nextIndex++;
				return result;
			}
			else
				throw new NoSuchElementException();
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	}
	
	private class KeyIterator<K> implements Iterator<K>{
		
		private Iterator<Entry> entryIterator;
		private KeyIterator(){
			entryIterator = new EntryIterator<ArrayDictionary.Entry>();
		}
		@Override
		public boolean hasNext() {
			return entryIterator.hasNext();//有Entry元素就表示有Key(同时还会有Value)
		}

		@Override
		public K next() {
			Entry nextEntry = entryIterator.next();
			return (K) nextEntry.getKey();//??泛型与非泛型之间的转换???
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private class ValueIterator<V> implements Iterator<V>,Serializable{

		private EntryIterator<Entry> entryIterator;
		private ValueIterator(){
			entryIterator = new EntryIterator<ArrayDictionary.Entry>();
		}
		@Override
		public boolean hasNext() {
			return entryIterator.hasNext();
		}

		@Override
		public V next() {
			Entry<K, V> nextEntry = entryIterator.next();
			return nextEntry.getValue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	/*
	 * Task 根据查找键来 查找 该元素在Entry数组中的位置
	 * @param key 待查找的键
	 * @return 若查找成功，返回的index < currentSize .否则，index > currentSize
	 */
	private int locateIndex(K key){
		int index = 0;
//		System.out.println(key.getClass());
		while((index < currentSize) && (!(key.equals(dictionary[index].getKey())))){
//			System.out.println("index = " + index);
			index++;
		}
		return index;
	}
	
	@Override
	public V add(K key, V value) {
		V result = null;
		int KeyIndex = locateIndex(key);
		if(KeyIndex < currentSize){
			result = dictionary[KeyIndex].getValue();
			dictionary[KeyIndex].setValue(value);
		}
		else{
			if(isFull())
				doubleArray();
			dictionary[currentSize] = new Entry<K, V>(key, value);
			currentSize ++;
			System.out.println("currentSize = " + currentSize);
		}
		return result;
	}
	
	private void doubleArray(){
		
	}

	@Override
	public V remove(K key) {
		V result = null;
		int keyIndex = locateIndex(key);
		if(keyIndex < currentSize){
			result = dictionary[keyIndex].getValue();//获得待删除key的value
			//用最后一个元素代替待删元素，然后再删除最后一个元素
			dictionary[keyIndex] = dictionary[currentSize - 1];
			currentSize--;
		}//end if
		return result;
	}

	@Override
	public V getValue(K key) {
		V result = null;
		int keyIndex = locateIndex(key);
//		System.out.println("searching key= " + keyIndex);
		if(keyIndex < currentSize)//找到了
			result = dictionary[keyIndex].getValue();
		return result;
	}

	@Override
	public boolean contains(K key) {
		return locateIndex(key) < currentSize; 
	}

	@Override
	public Iterator<K> getKeyIterator() {
		// TODO Auto-generated method stub
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		// TODO Auto-generated method stub
		return new ValueIterator();
	}

	public ArrayDictionary<K, V>.EntryIterator<Entry> getEntryIetartor(){
		return new EntryIterator<Entry>();
	}
	@Override
	public boolean isEmpty() {
		return currentSize == 0;
	}

	@Override
	public boolean isFull() {
		System.out.println("dictionary.length = " + dictionary.length);
		return currentSize == DEFAULT_SIZE;
	}

	@Override
	public int getSize() {
		return currentSize;
	}

	@Override
	public void clear() {
		currentSize = 0;
	}

}
