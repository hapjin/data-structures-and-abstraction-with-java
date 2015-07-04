package list;

import java.util.Arrays;

public class SequenceStack<E> implements Stack<E> {
	
	private int DEFAULT_SIZE = 16;//定义栈的初始默认长度
	private int capacity;//保存顺序栈的长度
	private int size;//保存顺序栈中元素的个数
	private Object[] elementData;//定义一个数组用于保存顺序栈中的元素
	
	public SequenceStack() {
		capacity = DEFAULT_SIZE;
		elementData = new Object[capacity];
	}
	
	//以指定的大小来创建栈
	public SequenceStack(int initSize){
		capacity = 1;
		while(capacity < initSize)
			capacity <<= 1;//将capacity设置成大于initSize的最小2次方
		elementData = new Object[capacity];
	}

	//返回当前顺序栈中元素的个数
	public int length() {
		return size;
	}

	public E pop() {
		if(empty())
			throw new IndexOutOfBoundsException("栈空，不能出栈");
		E oldValue = (E)elementData[size - 1];
		elementData[--size] = null;//让垃圾回收器及时回收，避免内存泄露
		return oldValue;
	}

	public void push(E element) {
		ensureCapacity(size + 1);
		elementData[size++] = element;
	}
	
	private void ensureCapacity(int minCapacity){
		if(minCapacity > capacity){
			while(capacity < minCapacity)
				capacity <<= 1;
		elementData = Arrays.copyOf(elementData, capacity);
		}
	}

	//获取栈顶元素，不会将栈顶元素删除
	public E peek() {
		if(size == 0)
			throw new ArrayIndexOutOfBoundsException("栈为空");
		return (E)elementData[size - 1];
	}

	public boolean empty() {
		return size == 0;
	}

	public void clear() {
//		Arrays.fill(elementData, null);
		for(int i = 0; i < size; i++)
			elementData[i] = null;
		size = 0;
	}

	public String toString(){
		if(size == 0)
			return "[]";
		else{
			StringBuilder sb = new StringBuilder("[");
			for(int i = size - 1; i >= 0; i--)
				sb.append(elementData[i].toString() + ", ");
			int len = sb.length();
			//删除由于上面for循环中最后添加的多余的两个字符 (一个是逗号，一个是空格符号)
			return sb.delete(len - 2, len).append("]").toString();
		}
	}
}
