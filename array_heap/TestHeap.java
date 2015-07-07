package array_heap;

public class TestHeap {
	public static void main(String[] args) {
		ArrayMaxHeap<Integer> heap1 = new ArrayMaxHeap<Integer>();
		System.out.println("heap1 is empty? " + heap1.isEmpty());
		System.out.println("Adding elements....");
		heap1.add(20);
		heap1.add(40);
		heap1.add(30);
		heap1.add(10);
		System.out.println("heap1 's size: " + heap1.getSize());
		System.out.println("heap1 's max element: " + heap1.getMax());
		System.out.println("delete top element of heap1....");
		heap1.removeMax();
		System.out.println("after delete ...the new top element of heap1 is: " + heap1.getMax());
		System.out.println("now heap1's size is : " + heap1.getSize());
		System.out.println("..............");
		Integer[] arr = new Integer[4];
		arr[0] = 20;
		arr[1] = 40;
		arr[2] = 30;
		arr[3] = 10;
		ArrayMaxHeap<Integer> heap2 = new ArrayMaxHeap<Integer>(arr);
		System.out.println("heap2 is empty? " + heap2.isEmpty());
		System.out.println("heap2 's size: " + heap2.getSize());
		System.out.println("heap2's max element: " + heap2.getMax());
		System.out.println("delete heap2's top element...");
		heap2.removeMax();
		System.out.println("now heap2's top element is : " + heap2.getMax());
		
		System.out.println("--------sorting arr----------------");
		System.out.println("original arr elements:");
		for(Integer i : arr)
			System.out.print(i + " ");
		HeapSort.heapSort(arr, arr.length);
		System.out.println("\nafter sorting.....");
		for(Integer i : arr)
			System.out.print(i + " ");
	}
}
