package array_heap;

public class HeapSort {
	private static <T extends Comparable<? super T>> void reheap(T[] heap, int rootIndex, int lastIndex){
		boolean done = false;//标记堆调整是否完成
		T orphan = heap[rootIndex];
		int largeChildIndex = 2 * rootIndex + 1;//默认左孩子的值较大
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
				largeChildIndex = 2 * rootIndex + 1;//总是默认左孩子的值较大
			}
			else//以rootIndex为根的子树已经构成堆了
				done = true;
		}
		heap[rootIndex] = orphan;
	}
	
	public static <T extends Comparable<? super T>> void heapSort(T[] array, int n){
		//直接在array上创建初始堆
		for(int rootIndex = n/2 - 1; rootIndex >= 0; rootIndex--)
			reheap(array, rootIndex, n-1);
		swap(array, 0 , n-1);
		//调整第i个元素(索引为i-1)与第n-i个元素交换后形成的半堆
		for(int lastIndex = n-2; lastIndex > 0; lastIndex--){
			reheap(array, 0 , lastIndex);
			swap(array, 0, lastIndex);
		}
	}
	
	private static <T> void swap(T[] array, int from ,int to){
		T temp = array[from];
		array[from] = array[to];
		array[to] = temp;
	}
}
