package dictionary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDictionary<K,V> implements DictionaryInterface<K, V>,Serializable {
	
	private static final long serialVersionUID = 1L;
	ArrayList<Entry> listDictionary = null;
	
	public ArrayListDictionary(){
		listDictionary = new ArrayList<Entry>();
	}
	
	private class Entry{
		K key;
		V value;
		
		private Entry(K key, V value){
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
	
	private class KeyIterator<S> implements Iterator<K>{

		Iterator<Entry> it = null;
		public KeyIterator(){
			it = listDictionary.iterator();
		}
		
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public K next() {
			return  (K) it.next().getKey();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("can not remove a entry in iterator.unsupported");
			
		}
	}
	
	private class ValueIterator<V> implements Iterator<V>{

		Iterator<Entry> it = null;
		public ValueIterator(){
			it = listDictionary.iterator();
		}
		
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public V next() {
			return (V) it.next().getValue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	//返回指定的查找键所代表的元素在arraylist中的位置
	private int locateIndex(K key){
		int index = -1;
		Iterator<Entry> it = listDictionary.iterator();
		while(it.hasNext()){
			Entry e = it.next();//先获得每一个Map元素
			if(e.getKey().equals(key)){//依次与每一个Map元素的查找键比较
				index = listDictionary.indexOf(e);//获得给定的key匹配的Map元素在Arraylist中的位置
				break;
			}
		}
		return index;
	}
	
	@Override
	public V add(K key, V value) {
		V result = null;
		int index = locateIndex(key);
		if(index == -1){//key 不在ArrayList中
			listDictionary.add(new Entry(key, value));
		}
		else{
			//这里index不会为-1，所以 get(index)不会抛出IndexOutOfBoundsException
			result = listDictionary.get(index).getValue();
			listDictionary.get(index).setValue(value);//调用Entry内部类中的setValue方法更新Entry的Value
		}
		return result;
	}

	@Override
	public V remove(K key) {
		V  result = null;
		int index = locateIndex(key);
		if(index == -1){//key 不存在于ArrayList中
			result = null;
		}
		else{
			result = listDictionary.get(index).getValue();
			listDictionary.remove(index);
		}
		return result;
	}

	@Override
	public V getValue(K key) {
		V result = null;
		int index = locateIndex(key);
		try{
			result = listDictionary.get(index).getValue();//当index=-1时，表示key不存在
		}catch(IndexOutOfBoundsException e){//index = -1时抛出异常
			result = null;
		}
		return result;
	}

	@Override
	public boolean contains(K key) {
		boolean result = false;
		Iterator<Entry> it = listDictionary.iterator();
		while(it.hasNext()){
			Entry e = it.next();
			if(e.getKey().equals(key)){
				result = true;
				break;
			}
		}
		return result;
	}
	

	@Override
	public Iterator<K> getKeyIterator() {
		return new KeyIterator();
	}

	@Override
	public Iterator<V> getValueIterator() {
		return new ValueIterator();
	}

	@Override
	public boolean isEmpty() {
		return listDictionary.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int getSize() {
		return listDictionary.size();
	}

	@Override
	public void clear() {
		listDictionary.clear();
	}
}
