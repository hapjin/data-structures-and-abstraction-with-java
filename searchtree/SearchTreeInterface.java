package searchtree;
import tree.TreeInterface;
import java.util.Iterator;

public interface SearchTreeInterface <T extends Comparable <? super T>> extends TreeInterface<T>{
	public boolean contains(T entry);//在树中查找指定元素
	public T getEntry(T entry);//从树中检索指定元素，未找到返回null
	
	/*
	 * 将新元素插入树，若该元素与树中已存在的一个对象相匹配，则用新元素替换这个对象
	 * @param newEntry待插入的对象
	 * @return ??
	 */
	public T add(T newEntry);
	public T remove(T entry);
	public Iterator<T> getInorderIterator();
}
