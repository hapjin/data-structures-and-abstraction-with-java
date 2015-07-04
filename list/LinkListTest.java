package list;

public class LinkListTest {
	public static void main(String[] args) {
		LinkList<String> ll = new LinkList<String>();
		LinkList<Integer> lli = new LinkList<Integer>(1);
//		try{
//		ll.delete(0);
//		
//	}catch(IndexOutOfBoundsException e)
//		{
//		System.out.println("越界");
//		}
//		ll.add(1);
		ll.add("a");
		lli.add(2);
		lli.add(3);
//		lli.add(4);
		System.out.println(lli);
		lli.deleteByElement(3);
		lli.add(4);
//		lli.delete(0);
		System.out.println(ll);
		System.out.println(lli);
		
}
}
