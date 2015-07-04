package list;


public class LinkList<T> implements ListInterface<T>{
	private class Node{
		private T data;
		private Node next;
		private Node(){
			
		}
		private Node(T data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	private Node header;//保存链表的头结点
	private Node tail;//保存链表的尾结点，采用尾插法添加结点时，不需要遍历整个链表
	private int size;//保存链表中已包含的节点数
	
	public LinkList(){
		header = tail = null;
	}
	public LinkList(T element){
		header = new Node(element, null);
		tail = header;
		size++;
	}
	
	public int length(){
		return size;
	}
	
	//获得索引为index处的元素值，注意：索引从0开始
	public T get(int index){
		return getNodeByIndex(index).data;
	}
	private Node getNodeByIndex(int index){
		if(index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("单链表越界");
		Node current = header;
		for(int i = 0; (i < size) && (current != null); i++, current = current.next)
			if(i == index)
				return current;
		return null;
	}
	
	//返回链表中指定元素的索引，若指定元素不存在，返回-1
	public int locate(T element){
		Node current = header;
		for(int i = 0; i < size && current != null; i++, current = current.next)
			if(current.data.equals(element))
				return i;
		return -1;
	}

	public void insert(T element, int index){
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException("单链表索引越界");
		if(header == null)//链表是空链表时
			add(element);
		else{
			if(index == 0)//在表头插入
				addAtHeader(element);
			else{
				Node prev = getNodeByIndex(index - 1);//获取插入结点的前驱
				prev.next = new Node(element, prev.next);
				size++;
			}
		}
	}
	
	//采用尾插法为链表增加新节点
	public void add(T element){
		if(header == null){
			header = new Node(element, null);
			tail = header;
		}
		else{
			Node newNode = new Node(element, null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	//采用头插法为链表增加新节点
	public void addAtHeader(T element){
		header = new Node(element, header);//新建的结点的next 需要指向 header结点
		if(tail == header)//如果插入之前是空链表
			tail = header;
		size++;
	}
	
	public T delete(int index){
		if(index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("链表索引越界");
		Node del;
		//待删除的是header节点
		if(index == 0){
			del = header;
			header = header.next;
		}
		else{
			Node prev = getNodeByIndex(index - 1);//获取待删节点的前驱
			del = prev.next;//del 指向待删除的节点
			prev.next = del.next;
		}
		del.next = null;//将待删节点从链表中脱离出去
		size--;
		return del.data;
	}
	
	//根据指定的元素来删除节点
	public boolean deleteByElement(T element){
		//链表为空
		if(empty()){
			return false;
		}
		//待删元素为第一个元素
		else if(header.data.equals(element)){
			Node del = header;
			header = header.next;
			if(tail == header)//说明整个链表中只有一个元素，tail也应当置null
				tail = null;
			del.next = null;
			size --;
			return true;
		}
		//待删元素为链表中其他元素
		else{
			Node del = header.next;
			Node prev = header;
			while(del != null){
				if(del.data.equals(element)){
					if(tail == del)//如果待删元素是最后一个元素，需要将tail指针指向其前驱
						tail = prev;
					prev.next = del.next;
					del.next = null;
					size--;
					return true;
				}
				//若没有找到element，则继续下一轮的循环
				prev = del;
				del = del.next;
			}
			return false;
		}
	}
	
	//删除链表中的最后一个元素
	public T remove(){
		return delete(size - 1);
	}
	
	//判断链表是否为空
	public boolean empty(){
		boolean result;
		if(size == 0){
			assert header == null;//当出现链表为空，但size不为0时，使用断言能够帮助找到逻辑错误
			result = true;
		}
		else{
			assert header != null;
			result = false;
		}
		return result;
		//return size == 0;
	}
	
	//清空链表
	public void clear(){
		header = tail = null;
		size = 0;
	}
	
	public String toString(){
		if(empty())
			return "[]";
		else{
			StringBuilder sb = new StringBuilder("[");
			for(Node current = header; current != null; current = current.next)
				sb.append(current.data.toString() + ", ");
			int len = sb.length();
			//注意删除最后添加的两个多余的字符
			return sb.delete(len - 2, len).append("]").toString();
		}
	}
}
