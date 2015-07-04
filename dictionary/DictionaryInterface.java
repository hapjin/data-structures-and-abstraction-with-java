package dictionary;

import java.util.Iterator;

public interface DictionaryInterface<K, V> {
	/*
	 * Task: 将一个新元素插入词典。若给定的键已在词典中，则替换相应的值
	 * @param key	新元素的的查找键
	 * @param value 与键关联的值对象
	 * @return	若新元素被插入到词典中则返回null，若与key关联的value被替换，则返回原来的value
	 */
	public V add(K key, V value);
	
	/*
	 * Task: 从词典中删除一个指定的元素
	 * @param key	欲删除的元素的key对象
	 * @return	返回与key关联的value，若不存在这样的对象则返回null
	 */
	public V remove(K key);
	
	/*
	 * Task: 检索与给定的键相关联的值
	 * @param key	待检索元素的查找键对象
	 * @return	与查找键对象相关联的值，若不存在这样的对象则返回null
	 */
	public V getValue(K key);
	
	/*
	 * Task: 确定一个指定的元素在不在词典中
	 * @param key	待查找的元素的键对象
	 * @return 若key与词典中的一个元素相关联则返回true
	 */
	public boolean contains(K key);
	
	/*
	 * Task: 创建一个迭代器遍历词典中所有的查找键
	 * @return 返回一个迭代器，提供对词典中的键对象的顺序访问
	 */
	public Iterator<K> getKeyIterator();
	
	/*
	 * Task: 创建一个迭代器遍历词典中所有的值
	 * @return 返回一个迭代器，提供对词典中值的顺序访问
	 */
	public Iterator<V> getValueIterator();
	
	public boolean isEmpty();//判断词典是否为空
	public boolean isFull();//判断词典是否满
	public int getSize();//返回词典中当前元素(键-值二元组)个数
	public void clear();//删除词典中所有的元素
}
