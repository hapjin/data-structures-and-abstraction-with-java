package dictionary;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashedDictionary<K, V> implements DictionaryInterface<K, V>,
		Serializable {
	
	private static final long serialVersionUID = 1L;
	private TableEntry<K, V>[] hashTable;
	private int numberOfEntries;//hashTable元素个数
	private int locationUsed;//记录hashTable 位置的使用
	private static final int DEFAULT_SIZE = 101;// 哈希表的默认大小，素数
	private static final double MAX_LOAD_FACTOR = 0.5;// 装载因子

	public HashedDictionary() {
		this(DEFAULT_SIZE);
	}

	public HashedDictionary(int tableSize) {
		/*
		 * 当用来构造哈希表的参数不是素数时，寻找与该参数最接近的下一个素数
		 */
		int primeSize = getNextPrime(tableSize);
		hashTable = new TableEntry[primeSize];
		numberOfEntries = 0;
		locationUsed = 0;
	}

	private class TableEntry<S, T> implements Serializable {
		private S entryKey;
		private T entryValue;
		private boolean inTable;//标记元素是否在table中，为了冲突处理，删除元素时作标记删除
		
		private TableEntry(S key, T value){
			entryKey = key;
			entryValue = value;
			inTable = true;
		}
		
		public S getEntryKey() {
			return entryKey;
		}
		public T getEntryValue() {
			return entryValue;
		}
		public void setEntryValue(T entryValue) {
			this.entryValue = entryValue;
		}

		//判断某个元素是否在hashTable中，没有被标记删除
		public boolean isIn(){
			return inTable == true;
		}
		public boolean isRemoved(){
			return inTable == false;
		}
		public void setInTable(){
			this.inTable = true;
		}
		public void setToRemoved(){
			this.inTable = false;
		}
		
	}
	
	private class KeyIterator implements Iterator<K>{

		private int currentIndex;//散列中元素的当前位置
		private int numberLeft;//迭代中剩下的元素个数
		
		private KeyIterator(){
			currentIndex = 0;
			numberLeft = numberOfEntries;
		}
		
		@Override
		public boolean hasNext() {
			return numberLeft > 0;
		}

		@Override
		public K next() {
			K result = null;
			if(hasNext()){
				while((hashTable[currentIndex] == null) || hashTable[currentIndex].isRemoved())
					currentIndex++;//如何当前元素为空或者被标记为删除，则跳过该元素
				result = hashTable[currentIndex].getEntryKey();
				numberLeft--;//剩下的待遍历的元素减少 1
				currentIndex++;//指针前移，准备遍历下一个元素
			}//end if
			else
				throw new NoSuchElementException();
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	private class ValueIterator implements Iterator<V>{
		
		private int currentIndex;//散列中元素的当前位置
		private int numberLeft;//迭代中剩下的元素个数
		
		private ValueIterator(){
			currentIndex = 0;
			numberLeft = numberOfEntries;
		}
		
		@Override
		public boolean hasNext() {
			return numberLeft > 0;
		}

		@Override
		public V next() {
			V result = null;
			if(hasNext()){
			while((hashTable[currentIndex] == null) || hashTable[currentIndex].isRemoved())
				currentIndex++;//如何当前元素为空或者被标记为删除，则跳过该元素
			result = hashTable[currentIndex].getEntryValue();
			numberLeft--;
			currentIndex++;
			}
			else
				throw new NoSuchElementException();
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
	private int getNextPrime(int size){
		if(size <= 0)
			throw new RuntimeException();
		if(size % 2 == 0){
			size++;
		}
		while(!isPrime(size)){
			size = size + 2;
		}
		return size;
	}
	
	private boolean isPrime(int num){
		for(int i = 2; i <= Math.sqrt(num); i++){
			if(num % i == 0)
				return false;
		}
		return true;
	}

	/*
	 * @Task: 自定义的哈希函数，将key转化成合适的索引
	 * @param K key 查找键
	 * @return 返回查找键相应的索引
	 */
	private int getHashIndex(K key){
		//先获得散列码，再通过 % 将散列码压缩为索引
		int hashIndex = key.hashCode() % hashTable.length;
		if(hashIndex < 0)
			hashIndex = hashIndex + hashTable.length;
		return hashIndex;
	}
	
	/*
	 * @Task 从index开始，判断key是否与hashTable中的元素冲突并返回最终的key的位置
	 * @param index 通过散列函数获得的key的最初index
	 * @param key 查找键
	 */
	private int locate(int index, K key){
		boolean found = false;
		//当找到一个hashTable中为null的元素，表示查找失败
		while(!found && (hashTable[index] != null)){
			//index 处 的key对应的value没有被标记删除且key 相同时
			if(hashTable[index].isIn() && key.equals(hashTable[index].getEntryKey()))
				found = true;
			else 
				index = (index + 1) % hashTable.length;
		}
		int result = -1;
		if(found)
			result = index;
		return result;
	}
	
	@Override
	public V add(K key, V value) {
		V oldValue;
		if(isFull())
			rehash();
		int index = getHashIndex(key);
		index = probe(index, key);//线性探测
		
		assert (index >= 0) && (index < hashTable.length);
		if((hashTable[index] == null) || hashTable[index].isRemoved()){
			hashTable[index] = new TableEntry<K,V>(key, value);
			numberOfEntries++;
			locationUsed++;
			oldValue = null;
		}else
		{
			oldValue = hashTable[index].getEntryValue();
			hashTable[index].setEntryValue(value);
		}
		return oldValue;
	}
	
	private void rehash(){
		//increase hash table size
	}
	
	private int probe(int index, K key){
		boolean found = false;
		int removedStateIndex = -1;
		while(!found && (hashTable[index] != null)){
			if(hashTable[index].isIn()){
				if(key.equals(hashTable[index].getEntryKey()))
					found = true;
				else
					index = (index + 1) % hashTable.length;
			}
			else
			{//保存处于已删除状态的第一个位置的索引
				if(removedStateIndex == -1)
					removedStateIndex = index;
				index = (index + 1) % hashTable.length;//线性探测
			}
		}//end while
		if(found || (removedStateIndex == -1))
			return index;
		else
			return removedStateIndex;
	}

	@Override
	public V remove(K key) {
		V removedValue = null;
		int index = getHashIndex(key);
		index = locate(index, key);
		if(index != -1){//key已找到，将元素标记为已删除并返回它的值
			removedValue = hashTable[index].getEntryValue();
			hashTable[index].setToRemoved();
			numberOfEntries--;
		}
		return removedValue;
	}

	@Override
	public V getValue(K key) {
		V result = null;
		int index = getHashIndex(key);//获得key对应的哈希索引
		index = locate(index, key);//返回处理冲突后的最终的索引地址
		
		if(index != -1)
			result = hashTable[index].getEntryValue();
		return result;
	}

	@Override
	public boolean contains(K key) {
		boolean result = false;
		int index = getHashIndex(key);
		index = locate(index, key);
		if(index != -1)
			result = true;
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
		return numberOfEntries == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {
		numberOfEntries = 0;
		locationUsed = 0;
	}
}
