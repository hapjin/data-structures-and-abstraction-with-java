package list;

public interface Stack<E> {
	public int length();//返回栈的长度
	
	public E pop();//出栈
	
	public void push(E element);//进栈
	
	public E peek();//访问栈顶元素
	
	public boolean empty();//判断栈是否为空
	
	public void clear();//清空栈
}