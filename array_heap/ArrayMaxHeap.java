package array_heap;

public class ArrayMaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T>,java.io.Serializable {


	private T[] heap;//用来存储堆元素的数组
	private int lastIndex;//最后一个元素的索引
	private static final int DEFAULT_INITIAL_CAPACITY = 25;
	
	public ArrayMaxHeap() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	public ArrayMaxHeap(int initalCapacity){
		heap = (T[]) new Comparable[initalCapacity];//???
		lastIndex = 0;
	}
	
	/*
	 * @Task 根据给定的数组元素来构建最大堆
	 * @param entries 将entries数组中的元素创建成堆
	 */
	public ArrayMaxHeap(T[] entries){
		heap = (T[]) new Comparable[entries.length + 1];
		lastIndex = entries.length;
		for(int index = 0; index < entries.length; index++)
		{
			heap[index + 1] = entries[index];//第0号位置不存放元素
			System.out.println(heap[index + 1]);
		}
//		System.out.println("lastIndex = " + lastIndex);
		for(int index = lastIndex / 2; index >= 1; index--)
			reheap(index);//从最后一个非叶结点到根结点调用reheap进行堆调整操作
//		for(int index = 1; index <= lastIndex; index++)
//			System.out.println(heap[index]);
	}
	
	@Override
	public void add(T newEntry) {
		lastIndex++;
		if(lastIndex >= heap.length)
			doubleArray();//若堆空间不足，则堆大小加倍
		int newIndex = lastIndex;//从最后一个元素开始逐渐向上与父结点比较
		int parentIndex = newIndex / 2;
		heap[0] = newEntry;//哨兵
		while(newEntry.compareTo(heap[parentIndex]) > 0){
			heap[newIndex] = heap[parentIndex];
			newIndex = parentIndex;
			parentIndex = newIndex / 2;
		}
//		while(newIndex > 1 && (newEntry.compareTo(heap[parentIndex]) > 0)){
//			heap[newIndex] = heap[parentIndex];
//			newIndex = parentIndex;
//			parentIndex = newIndex / 2;
//		}
		heap[newIndex] = newEntry;
	}
	private void doubleArray(){
		T[] oldHeap = heap;
		heap = (T[]) new Comparable[lastIndex * 2];
		for(int i = 1; i < lastIndex; i++)//lastIndex 在未插入元素前先自增了 1
			heap[i] = oldHeap[i];
		oldHeap = null;//垃圾回收
	}

	@Override
	public T removeMax() {
		T root = null;
		if(!isEmpty()){
			root = heap[1];
			heap[1] = heap[lastIndex];//将最后一个元素代替第一个元素
			lastIndex--;//转化为删除最后一个元素
			reheap(1);//在树根处进行堆调整
		}
		return root;
	}

	/*
	 * @Task:将树根为rootIndex的半堆调整为新的堆，半堆：树的左右子树都是堆
	 * @param rootIndex 以rootIndex为根的子树
	 */
	private void reheap(int rootIndex){
		boolean done = false;//标记堆调整是否完成
		T orphan = heap[rootIndex];
		int largeChildIndex = 2 * rootIndex;//默认左孩子的值较大
		//堆调整基于以largeChildIndex为根的子树进行
		while(!done && (largeChildIndex <= lastIndex)){
			//largeChildIndex 标记rootIndex的左右孩子中较大的孩子
			int leftChildIndex = largeChildIndex;//默认左孩子的值较大
			int rightChildIndex = leftChildIndex + 1;
			//右孩子也存在,比较左右孩子
			if(rightChildIndex <= lastIndex && (heap[largeChildIndex].compareTo(heap[rightChildIndex] )< 0))
				largeChildIndex = rightChildIndex;
		//	System.out.println(heap[largeChildIndex]);//这里有问题。。使用构造函数创建时reheap。。。。。
			if(orphan.compareTo(heap[largeChildIndex]) < 0){
				heap[rootIndex] = heap[largeChildIndex];
				rootIndex = largeChildIndex;
				largeChildIndex = 2 * rootIndex;//总是默认左孩子的值较大
			}
			else//以rootIndex为根的子树已经构成堆了
				done = true;
		}
		heap[rootIndex] = orphan;
	}
	
	@Override
	public T getMax() {
		T root = null;
		if(!isEmpty())
			root = heap[1];
		return root;
	}

	@Override
	public int getSize() {
		return lastIndex;
	}

	@Override
	public void clear() {
		for(;lastIndex > -1; lastIndex--)
			heap[lastIndex] = null;
		lastIndex = 0;
	}
	
	public boolean isEmpty(){
		return lastIndex < 1;//堆元素从数组下标为1处开始存放
	}
}
