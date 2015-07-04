package list;

public interface ListInterface<T> {
	//返回当前链表中元素个数
	public int length();
	
	////获得索引为index处的元素值，注意：索引从0开始
	public T get(int index);
	
	//返回链表中指定元素的索引，若指定元素不存在，返回-1
	public int locate(T element);
	
	//在指定索引index处添加指定的element
	public void insert(T element, int index);
	
	//采用尾插法为链表增加新节点
	public void add(T element);
	
	//采用头插法为链表增加新节点
	public void addAtHeader(T element);
	
	//删除指定索引处的节点
	public T delete(int index);
	
	
	public T remove();
	
	
	public void clear();
}
