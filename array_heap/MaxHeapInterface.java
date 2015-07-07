package array_heap;

public interface MaxHeapInterface<T extends Comparable<? super T>> {
	public void add(T newEntry);//向堆中添加元素
	public T removeMax();//删除堆顶元素
	public T getMax();//取得堆顶元素
	public int getSize();//获得堆中元素个数
	public void clear();//清空堆中元素
}
